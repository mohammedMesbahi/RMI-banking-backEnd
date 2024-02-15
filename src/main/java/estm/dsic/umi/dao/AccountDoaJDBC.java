package estm.dsic.umi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import estm.dsic.umi.beans.Account;
import estm.dsic.umi.beans.User;

public class AccountDoaJDBC implements AccountDoa {
    private static AccountDoaJDBC instance;
    private Connection connection;
    public AccountDoaJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Account account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public List<Account> getAllAccountsOfAUser(User user) {
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
                    TransactionDoaJDBC.getInstance().getAllTransactionsOfAnAccount(account)
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

    public static AccountDoaJDBC getInstance() {
        if (instance == null)
            instance = new AccountDoaJDBC(JDBCconnection.getConnection());
        return instance;
    }

}
