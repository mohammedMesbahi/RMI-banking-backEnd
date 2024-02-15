package estm.dsic.umi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconnection {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_Name = "rmi_banking";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_Name + "?useSSL=false";
    private static final String DB_USERNAME = "user1";
    private static final String DB_PASSWORD = "user1";
    private static Connection connection;

    static {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}
