package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

    // Tomar valores de las variables de entorno de Railway
    private static final String URL = System.getenv().getOrDefault(
            "DB_URL",
            "jdbc:mysql://shortline.proxy.rlwy.net:17451/railway?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true"
    );
    private static final String USERNAME = System.getenv().getOrDefault("DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASS", "AWjHfmUPMvDeuxoLeGpYVsJeBlMpWxbe");

    // Cargar driver MySQL al inicializar la clase
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver no encontrado", e);
        }
    }

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
