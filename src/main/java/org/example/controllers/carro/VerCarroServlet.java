package org.example.controllers.carro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/carro/ver")
public class VerCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configurar atributos para la plantilla
        req.setAttribute("titulo", "Carro de Compras");
        req.setAttribute("contenido", "/carro.jsp");

        // Reenviar a la plantilla general
        getServletContext().getRequestDispatcher("/layout/layout.jsp").forward(req, resp);


//        <a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/home">Menú principal</a>

    }
}
