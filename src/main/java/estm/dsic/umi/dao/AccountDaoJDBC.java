package estm.dsic.umi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.User;

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

    public static AccountDaoJDBC getInstance() {
        if (instance == null)
            instance = new AccountDaoJDBC(JDBCconnection.getConnection());
        return instance;
    }

}
