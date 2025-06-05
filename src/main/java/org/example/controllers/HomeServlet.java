package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/home", "/crudcarro/", ""})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("titulo", "Inicio");
        req.setAttribute("contenido", "/index.jsp"); // asumiendo que mueves index.jsp a /vistas
        getServletContext().getRequestDispatcher("/layout/layout.jsp").forward(req, resp);
    }
}


