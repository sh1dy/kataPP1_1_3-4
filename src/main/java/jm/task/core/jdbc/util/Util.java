package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kata_users",
                    "root",
                    "12345678"
            );
        } catch (SQLException | ClassNotFoundException oops) {
            throw new RuntimeException();
        }
        return conn;
    }
}
