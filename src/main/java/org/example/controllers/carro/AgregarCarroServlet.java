package org.example.controllers.carro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.Carro;
import org.example.models.ItemCarro;
import org.example.models.Producto;
import org.example.services.producto.ProductoServiceImpl;
import org.example.services.producto.ProductoSnService;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idItem = Long.valueOf(req.getParameter("id"));
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoSnService productoService = new ProductoServiceImpl(conn);
        Optional<Producto> producto = productoService.porId(idItem);
        if (producto.isPresent()){
            ItemCarro itemCarro = new ItemCarro(1,producto.get());
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            if (carro ==null){
                carro = new Carro();
                session.setAttribute("carro", carro);
            }
            carro.agregarItemCarro(itemCarro);
        }
        resp.sendRedirect(req.getContextPath()+ "/carro/ver");
    }
}
