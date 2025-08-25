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

@WebServlet("/test-db")
public class TestDBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            try (Connection conn = ConnectionDataBase.getConnection()) {
                out.println("<h2>✅ Conexión a la base de datos exitosa</h2>");
                out.println("<p>URL: " + conn.getMetaData().getURL() + "</p>");
                out.println("<p>Usuario: " + conn.getMetaData().getUserName() + "</p>");
            } catch (SQLException e) {
                out.println("<h2>❌ Error al conectar a la base de datos</h2>");
                out.println("<pre>" + e.getMessage() + "</pre>");
            }

        }
    }
}
