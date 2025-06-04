package org.example.repositories;

import org.example.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario> {
    Usuario porUsername(String username) throws SQLException;
    Usuario PorEmail(String email) throws SQLException;
}
