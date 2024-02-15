package estm.dsic.umi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;
import estm.dsic.umi.business.DefaultTransactionService;

public class AccountDaoJDBC implements AccountDao {
    private static AccountDaoJDBC instance;
    private Connection connection;
    public AccountDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Account create(Account account) {
        try {
            // use prepared statement
            String query = "INSERT INTO account (balance, ownerId) VALUES (?, ?)";
            java.sql.PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, account.getBalance());
            statement.setInt(2, account.getOwnerId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Error while creating account");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while creating account" + e.getMessage());
        }
        return account;
    }

    @Override
    public List<Account> getAccountsOfAUser(User user) {
        List<Account> accounts = new ArrayList<Account>();
        try {
            String query = "SELECT * FROM account WHERE account.ownerId = " + user.getId();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("account.id"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setOwnerId(user.getId());
                account.setTransactions(
                    TransactionDaoJDBC.getInstance().getAllTransactionsOfAnAccount(account)
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting accounts of a user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while getting accounts of a user" + e.getMessage());
        }
        return accounts;
    }

    @Override
    public Transaction deposit(Account destAccount, Double amount) {
        Transaction transaction = null;
        String query = "UPDATE account SET balance = balance + " + amount + " WHERE account.id = " + destAccount.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            if (statement.getUpdateCount() == 0) {
                throw new SQLException("No account updated");
            } else {
                transaction = DefaultTransactionService.getInstance().createTransaction(
                    new Transaction(
                        amount,
                        destAccount.getId(),
                        destAccount.getId(),
                        Transaction.DEPOSIT,
                            new java.util.Date()
                    ));
            }
        } catch (SQLException e) {
            System.out.println("Error while depositing");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while depositing" + e.getMessage());
        }
        return transaction;
    }

    @Override
    public Transaction withdraw(Account srcAccount, Double amount) {
        Transaction transaction = null;
        String query = "UPDATE account SET balance = balance - " + amount + " WHERE account.id = " + srcAccount.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            if (statement.getUpdateCount() == 0) {
                throw new SQLException("No account updated");
            } else {
                transaction = DefaultTransactionService.getInstance().createTransaction(
                    new Transaction(
                        amount,
                        srcAccount.getId(),
                        srcAccount.getId(),
                        Transaction.WITHDRAWAL,
                        new java.util.Date()
                    ));
            }
        } catch (SQLException e) {
            System.out.println("Error while withdrawing");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while withdrawing" + e.getMessage());
        }
        return transaction;
    }

    @Override
    public Transaction transfer(Account srcAccount, Account destAccount, Double amount) {
        Transaction transaction = null;
        String query = "UPDATE account SET balance = balance - " + amount + " WHERE account.id = " + srcAccount.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            if (statement.getUpdateCount() == 0) {
                throw new SQLException("No account updated");
            } else {
                query = "UPDATE account SET balance = balance + " + amount + " WHERE account.id = " + destAccount.getId();
                statement.executeUpdate(query);
                if (statement.getUpdateCount() == 0) {
                    throw new SQLException("No account updated");
                } else {
                    transaction = DefaultTransactionService.getInstance().createTransaction(
                        new Transaction(
                            amount,
                            srcAccount.getId(),
                            destAccount.getId(),
                            Transaction.TRANSFER,
                            new java.util.Date()
                        ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while transferring");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while transferring" + e.getMessage());
        }
        return transaction;
    }

    public static AccountDaoJDBC getInstance() {
        if (instance == null)
            instance = new AccountDaoJDBC(JDBCconnection.getConnection());
        return instance;
    }

}
