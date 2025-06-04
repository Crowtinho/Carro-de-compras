package org.example.repositories;

import org.example.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryImpl implements Repository<Categoria> {
    private final Connection conn;

    public CategoriaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql= "SELECT * FROM categorias";
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                Categoria c = crearCategoria(resultSet);
                categorias.add(c);
            }
        }
        return categorias;
    }


    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        String sql ="SELECT * FROM categorias WHERE id=?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setLong(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    categoria = crearCategoria(resultSet);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql;
        if(categoria.getId() != null && categoria.getId()>0){
            sql = "UPDATE categorias SET nombre=? WHERE id=?";
        }else {
            sql = "INSERT INTO categorias(nombre) VALUES(?)";
        }
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, categoria.getNombre());
            if(categoria.getId() != null && categoria.getId()>0){
                statement.setLong(2,categoria.getId());
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id=?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setLong(1,id);
            statement.executeUpdate();
        }
    }


    private static Categoria crearCategoria(ResultSet resultSet) throws SQLException {
        Categoria c = new Categoria();
        c.setId(resultSet.getLong("id"));
        c.setNombre(resultSet.getString("nombre"));
        return c;
    }
}
