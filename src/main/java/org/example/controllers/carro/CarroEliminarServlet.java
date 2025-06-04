package org.example.controllers.carro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.models.Carro;

import java.io.IOException;

@WebServlet("/carro/eliminar")
public class CarroEliminarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Carro carro = (Carro) session.getAttribute("carro");

        String idStr = req.getParameter("id");
        if (carro != null && idStr != null) {
            try {
                Long id = Long.parseLong(idStr);
                carro.borrarProducto(id);
            } catch (NumberFormatException ignored) {}
        }

        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
