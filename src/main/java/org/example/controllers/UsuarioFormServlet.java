package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Usuario;
import org.example.services.login.LoginService;
import org.example.services.login.LoginServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/login/form")
public class LoginFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        LoginService loginService = new LoginServiceImpl(conn);
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e ){
            id = 0L;
        }
        Usuario usuario = new Usuario();
        if (id>0){
            Optional<Usuario> o = loginService.porId(id);
            if (o.isPresent()){
                usuario = o.get();
            }
        }
        req.setAttribute("usuario",usuario);
        getServletContext().getRequestDispatcher("/loginForm.jsp").forward(req,resp);
    }
}
