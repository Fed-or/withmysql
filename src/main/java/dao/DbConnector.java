package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private final static String dbUrl = "jdbc:mysql://localhost:3307/matedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String name = "root";
    private final static String pass = "root";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, name, pass);
            return connection;
        } catch (SQLException | ClassNotFoundException es) {
            es.printStackTrace();
        }
        return null;
    }
}