package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.Carro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/carro/actualizar")
public class ActualizarCarroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Carro carro = (Carro) session.getAttribute("carro");

        if (carro == null) {
            carro = new Carro();
            session.setAttribute("carro", carro);
        }

        updateProductos(req, carro);
        updateCantidades(req, carro);

        // Redireccionar a donde corresponda después de actualizar
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }

    private void updateProductos(HttpServletRequest request, Carro carro) {
        String[] deleteIds = request.getParameterValues("deleteProductos");

        if (deleteIds != null && deleteIds.length > 0) {
            List<Long> productoIds = new ArrayList<>();
            for (String idStr : deleteIds) {
                try {
                    productoIds.add(Long.parseLong(idStr));
                } catch (NumberFormatException e) {
                    // Puedes loguear el error si lo deseas
                }
            }
            // Borramos los productos del carrito
            carro.borrarProductos(productoIds);
        }
    }


    private void updateCantidades(HttpServletRequest request, Carro carro) {

        Enumeration<String> enumer = request.getParameterNames();

        // Iteramos a traves de los parámetros y buscamos los que empiezan con
        // "cant_". El campo cant en la vista fueron nombrados "cant_" + productoId.
        // Obtenemos el id de cada producto y su correspondiente cantidad ;-).
        while (enumer.hasMoreElements()) {
            String paramName = enumer.nextElement();
            if (paramName.startsWith("cant_")) {
                Long id = Long.valueOf(paramName.substring(5));
                String cantidad = request.getParameter(paramName);
                if (cantidad != null) {
                    carro.actualizarCantidad(id, Integer.parseInt(cantidad));
                }
            }
        }
    }
}
