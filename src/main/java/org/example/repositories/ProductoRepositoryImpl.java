package org.example.repositories;

import org.example.models.Categoria;
import org.example.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements Repository<Producto> {

    private final Connection conn;

    public ProductoRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql ="SELECT p.*,c.nombre AS categoria FROM productos AS p INNER JOIN categorias AS c ON(p.categoria_id= c.id) ORDER BY p.id ASC";
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                productos.add(crearProducto(resultSet));
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        String sql ="SELECT p.*,c.nombre AS categoria FROM productos AS p INNER JOIN categorias AS c ON(p.categoria_id= c.id) WHERE p.id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setLong(1,id);
            try(ResultSet rs = statement.executeQuery()){
                if (rs.next()){
                    producto = crearProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId()>0){
            sql = "UPDATE productos SET nombre=?,precio=?,categoria_id=?,imagen=? WHERE id=?";
        }else {
            sql = "INSERT INTO productos(nombre,precio,categoria_id,imagen,fecha_registro) VALUES(?,?,?,?,?)";
        }
        try (PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, producto.getNombre());
            statement.setBigDecimal(2, producto.getPrecio());
            statement.setLong(3,producto.getCategoria().getId());
            statement.setString(4,producto.getImagen());

            if (producto.getId() != null && producto.getId()>0){
                statement.setLong(5, producto.getId());
            }else {
                statement.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setLong(1,id);
            statement.executeUpdate();
        }
    }

    private static Producto crearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getBigDecimal("precio"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria(rs.getLong("categoria_id"),rs.getString("categoria"));
        p.setCategoria(c);
        p.setImagen(rs.getString("imagen"));
        return p;
    }
}
