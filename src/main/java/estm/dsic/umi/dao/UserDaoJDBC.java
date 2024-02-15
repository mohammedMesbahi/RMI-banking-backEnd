package estm.dsic.umi.dao;

import java.sql.*;

import estm.dsic.umi.beans.User;

public class UserDaoJDBC implements UserDao {
    private Connection connection;
    private static UserDaoJDBC instance;

    private UserDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    public static UserDaoJDBC getInstance() {
        if (instance == null)
            instance = new UserDaoJDBC(JDBCconnection.getConnection());
        return instance;
    }


    @Override
    public User getById(Integer id) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE user.id = " + id;
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAccounts(
                        AccountDaoJDBC.getInstance().getAccountsOfAUser(user)
                );
            }
        } catch (SQLException e) {
            System.out.println("Error while getting user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User create(User user) {
        try {
            // creat a prepared statement
            String query = "INSERT INTO user (email, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Error while creating user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User get(User user) {
        User user1 = null;
        try {
            String query = "SELECT * FROM user WHERE email = ? AND password = ?";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                user1 = new User();
                user1.setId(resultSet.getInt("id"));
                user1.setEmail(resultSet.getString("email"));
                user1.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println("Error while getting user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }
}
