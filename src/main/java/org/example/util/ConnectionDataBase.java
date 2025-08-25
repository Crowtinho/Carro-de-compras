package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

    private static final String URL = System.getProperty("DB_URL", "jdbc:mysql://localhost:3307/curso_java?serverTimezone=UTC");
    private static final String USERNAME = System.getProperty("DB_USER", "root");
    private static final String PASSWORD = System.getProperty("DB_PASS", "sasa");

    // Cargar driver MySQL al inicializar la clase
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver no encontrado", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
