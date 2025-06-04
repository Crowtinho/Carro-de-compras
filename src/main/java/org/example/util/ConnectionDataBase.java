package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {
    private final static String URL = "jdbc:mysql://localhost:3307/curso_java?serverTimezone=UTC";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "sasa";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

}
