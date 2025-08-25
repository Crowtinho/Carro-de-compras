package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.util.ConnectionDataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/env-test")
public class EnvTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        out.println("=== Variables de entorno ===");
        Map<String, String> env = System.getenv();
        out.println("DB_URL = " + env.get("DB_URL"));
        out.println("DB_USER = " + env.get("DB_USER"));
        out.println("DB_PASS = " + env.get("DB_PASS"));
        out.println();

        out.println("=== Prueba de conexión a DB ===");
        try (Connection conn = ConnectionDataBase.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                out.println("✅ Conexión a la base de datos exitosa!");
            } else {
                out.println("❌ No se pudo conectar a la base de datos.");
            }
        } catch (SQLException e) {
            out.println("❌ Error al conectar: " + e.getMessage());
            e.printStackTrace(out);
        }
    }
}
