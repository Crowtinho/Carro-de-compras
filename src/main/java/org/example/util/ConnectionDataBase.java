package org.example.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

    private final static String URL = System.getenv("DB_URL") != null
            ? System.getenv("DB_URL")
            : "jdbc:mysql://localhost:3307/curso_java?serverTimezone=UTC";

    private final static String USERNAME = System.getenv("DB_USER") != null
            ? System.getenv("DB_USER")
            : "root";

    private final static String PASSWORD = System.getenv("DB_PASS") != null
            ? System.getenv("DB_PASS")
            : "sasa";

    public static Connection getConnection() throws SQLException {
        //            Class.forName("com.mysql.cj.jdbc.Driver"); // asegura registro del driver
        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
