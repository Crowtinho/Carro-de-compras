package org.example.services.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.models.Usuario;
import org.example.repositories.Repository;
import org.example.repositories.UsuarioRepositoryImpl;
import org.example.services.ServiceExeption;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LoginServiceImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username !=null){
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
