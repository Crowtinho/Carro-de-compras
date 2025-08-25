package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

    // Leer las variables de entorno que Railway inyecta automáticamente
    private static final String URL = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3307/curso_java?serverTimezone=UTC");
    private static final String USERNAME = System.getenv().getOrDefault("DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASS", "sasa");


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver no encontrado", e);
        }

        // Validación básica para alertar si no se leen las variables
        if (URL == null || USERNAME == null || PASSWORD == null) {
            throw new RuntimeException(
                    "Las variables de entorno DB_URL, DB_USER o DB_PASS no están definidas correctamente."
            );
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
