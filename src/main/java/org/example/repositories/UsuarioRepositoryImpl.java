package org.example.repositories;

import org.example.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository{
    private Connection conn;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE username=?")){
            statement.setString(1, username);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    usuario = getUsuario(resultSet);
                }
            }
        }
        return usuario;
    }

    @Override
    public Usuario PorEmail(String email) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE email=?")){
            statement.setString(1, email);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    usuario = getUsuario(resultSet);
                }
            }
        }
        return usuario;
    }


    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try(Statement statement = conn.createStatement();
            ResultSet resultSet =statement.executeQuery("SELECT * FROM usuarios")){
            while (resultSet.next()){
                Usuario usuario = getUsuario(resultSet);
                usuarios.add(usuario);
            }

        }
        return usuarios;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        try(PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE id=?")){
            statement.setLong(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    usuario = getUsuario(resultSet);
                }
            }
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql;
        if (usuario.getId() != null && usuario.getId()>0){
            sql = "UPDATE usuarios SET username=?, password=?, email=? WHERE id=?";
        }else {
            sql = "INSERT INTO usuarios(username, password,email,rol) VALUES(?,?,?,?)";
        }
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1,usuario.getUsername());
            statement.setString(2,usuario.getPassword());
            statement.setString(3,usuario.getEmail());
            statement.setString(4,usuario.getRol());
            if (usuario.getId() != null && usuario.getId()>0){
                statement.setLong(5, usuario.getId());
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try(PreparedStatement statement = conn.prepareStatement("DELETE FROM usuarios WHERE id=?")){
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private Usuario getUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getLong("id"));
        usuario.setUsername(resultSet.getString("username"));
        usuario.setPassword(resultSet.getString("password"));
        usuario.setEmail(resultSet.getString("email"));
        usuario.setRol(resultSet.getString("rol"));
        return usuario;
    }
}
