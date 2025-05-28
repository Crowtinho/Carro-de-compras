package org.example.services;

import org.example.models.Usuario;
import org.example.repositories.UsuarioRepository;
import org.example.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(Connection conn) {
        this.usuarioRepository = new UsuarioRepositoryImpl(conn);
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.username(username)).filter(e -> e.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }
}
