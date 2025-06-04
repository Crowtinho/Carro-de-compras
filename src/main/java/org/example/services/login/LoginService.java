package org.example.services.login;

import jakarta.servlet.http.HttpServletRequest;
import org.example.models.Usuario;
import org.example.services.RepoService;

import java.util.Optional;

public interface LoginService  {
    Optional<String> getUsername(HttpServletRequest request);
}
