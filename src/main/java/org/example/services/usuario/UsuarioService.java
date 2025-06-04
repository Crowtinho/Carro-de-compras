package org.example.services.usuario;

import org.example.models.Usuario;
import org.example.services.RepoService;

import java.util.Optional;

public interface UsuarioService extends RepoService<Usuario> {
    Optional<Usuario> login(String username, String password);
    Optional<Usuario> username(String username);
    Optional<Usuario> email(String email);
}
