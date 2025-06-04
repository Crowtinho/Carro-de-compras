package org.example.services.usuario;

import org.example.models.Usuario;
import org.example.repositories.UsuarioRepository;
import org.example.repositories.UsuarioRepositoryImpl;
import org.example.services.ServiceExeption;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(Connection conn) {
        this.usuarioRepository = new UsuarioRepositoryImpl(conn);
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
//            return Optional.ofNullable(usuarioRepository.username());
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(e -> e.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Usuario> username(String username) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username));
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());

        }
    }

    @Override
    public Optional<Usuario> email(String email) {
        try {
            return Optional.ofNullable(usuarioRepository.PorEmail(email));
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Usuario> listar() {
        try {
            return usuarioRepository.listar();
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try {
            return Optional.ofNullable(usuarioRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try {
            usuarioRepository.guardar(usuario);
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            usuarioRepository.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }
}
