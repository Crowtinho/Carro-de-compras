package org.example.controllers.usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Usuario;
import org.example.services.usuario.UsuarioService;
import org.example.services.usuario.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);
        List<Usuario> usuarios = service.listar();

        req.setAttribute("usuarios", usuarios);
        req.setAttribute("titulo", "Listado de usuarios");
        req.setAttribute("contenido", "/usuariosListar.jsp");
        getServletContext().getRequestDispatcher("/layout/layout.jsp").forward(req, resp);
    }
}
