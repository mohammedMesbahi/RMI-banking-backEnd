package estm.dsic.umi.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;
import estm.dsic.umi.beans.User;
import estm.dsic.umi.business.DefaultAccountService;

public class TransactionDaoJDBC implements TransactionDao {
    private static TransactionDaoJDBC instance;
    private Connection connection;

    private TransactionDaoJDBC(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Transaction create(Transaction transaction) {
        // use prepared statement
        try {
            String query;
            PreparedStatement preparedStatement;
            if (transaction.getTransactionType().equals(Transaction.WITHDRAWAL)) {
                query = "INSERT INTO transaction (amount, srcAccount, transactionType, date) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDouble(1, transaction.getAmount());
                preparedStatement.setInt(2, transaction.getSrcAccount());
                preparedStatement.setString(3, transaction.getTransactionType());
                preparedStatement.setDate(4, new Date(transaction.getDate().getTime()));

                transaction.setDestAccount(-1);

            } else {
                query = "INSERT INTO transaction (amount, srcAccount, destAccount, transactionType, date) VALUES (?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDouble(1, transaction.getAmount());
                preparedStatement.setInt(2, transaction.getSrcAccount());
                preparedStatement.setInt(3, transaction.getDestAccount());
                preparedStatement.setString(4, transaction.getTransactionType());
                preparedStatement.setDate(5, new Date(transaction.getDate().getTime()));
            }
            preparedStatement.executeUpdate(); // execute the query
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                transaction.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Error while creating transaction");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while creating transaction" + e.getMessage());
        }
        return transaction;
    }

    public static TransactionDao getInstance() {
        if (instance == null)
            instance = new TransactionDaoJDBC(JDBCconnection.getConnection());
        return instance;
    }

    @Override
    public List<Transaction> getAllTransactionsOfAnAccount(Account account) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String query = "SELECT * FROM transaction WHERE destAccount=" + account.getId() + " OR srcAccount=" + account.getId();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setSrcAccount(resultSet.getInt("srcAccount"));
                transaction.setDestAccount(resultSet.getInt("destAccount"));
                transaction.setTransactionType(resultSet.getString("transactionType"));
                transaction.setDate(resultSet.getDate("date"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting transactions of an account");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while getting transactions of an account" + e.getMessage());
        }
        return transactions;

    }

    @Override
    public List<Transaction> getAllTransactionsOfAUser(User user) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String query = "SELECT * FROM transaction WHERE destAccount.id = " + user.getId() + " OR srcAccount.id = " + user.getId();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("transaction.id"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setSrcAccount(resultSet.getInt("srcAccount"));
                transaction.setDestAccount(resultSet.getInt("destAccount"));
                transaction.setTransactionType(resultSet.getString("transactionType"));
                transaction.setDate(resultSet.getDate("date"));
                transaction.setId(user.getId());
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting transactions of a user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while getting transactions of a user" + e.getMessage());
        }
        return transactions;
    }

}
