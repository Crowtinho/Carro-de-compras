package org.example.controllers.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.Usuario;
import org.example.services.login.LoginService;
import org.example.services.login.LoginServiceImpl;
import org.example.services.usuario.UsuarioService;
import org.example.services.usuario.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

//    final static String USERNAME = "admin";
//    final static String PASSWORD = "12345";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        LoginService login = new LoginServiceImpl();
        Optional<String> username = login.getUsername(req);
        if (username.isPresent()){
            resp.sendRedirect(req.getContextPath()+"/home");
        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String error = "error, el usuario y la contraseña no coinciden";


        UsuarioService service = new UsuarioServiceImpl((Connection) req.getAttribute("conn"));
        Optional<Usuario> usuario = service.login(username,password);
        if (usuario.isPresent()){
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("usuario", usuario.get());
            resp.sendRedirect(req.getContextPath() + "/login");
        }else {
            req.setAttribute("error",error);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);

//            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"no tiene autorización");
        }
    }
}
