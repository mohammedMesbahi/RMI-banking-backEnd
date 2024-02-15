package estm.dsic.umi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.Transaction;

public class TransactionDoaJDBC implements TransactionDoa{
    private static TransactionDoaJDBC instance ;
    private Connection connection;
    private TransactionDoaJDBC(Connection connection) {
        this.connection=connection;       
    }


    @Override
    public void create(Transaction transaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    public static TransactionDoa getInstance() {
        if(instance==null)
            instance=new TransactionDoaJDBC(JDBCconnection.getConnection());
        return instance;
    }

    @Override
    public List<Transaction> getAllTransactionsOfAnAccount(Account account) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String query = "SELECT * FROM transaction WHERE desAccount.id = " + account.getId() + " OR srcAccount.id = " + account.getId();
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
                transaction.setId(account.getId());
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

}
