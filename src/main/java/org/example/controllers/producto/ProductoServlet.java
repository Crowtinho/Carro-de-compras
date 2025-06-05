package org.example.controllers.producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.login.LoginService;
import org.example.services.login.LoginServiceImpl;
import org.example.services.producto.ProductoServiceImpl;
import org.example.services.producto.ProductoSnService;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoSnService service = new ProductoServiceImpl(conn);
        req.setAttribute("productos",service.listar());

        LoginService username = new LoginServiceImpl();
        req.setAttribute("username",username.getUsername(req));

        req.setAttribute("titulo", "Productos");
        req.setAttribute("contenido", "/listar.jsp");
        getServletContext().getRequestDispatcher("/layout/layout.jsp").forward(req, resp);



    }
}
