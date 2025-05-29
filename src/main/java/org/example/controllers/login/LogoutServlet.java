package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.services.login.LoginService;
import org.example.services.login.LoginServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        LoginService service = new LoginServiceImpl();
        Optional<String> username = service.getUsername(req);
        if (username.isPresent()){
        HttpSession session =req.getSession();
        session.invalidate();
        }
        resp.sendRedirect(req.getContextPath()+ "/login");
    }
}
