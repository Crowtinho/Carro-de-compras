package org.example.services.producto;

import org.example.models.Producto;
import org.example.repositories.Repository;
import org.example.repositories.ProductoRepositoryImpl;
import org.example.services.ServiceExeption;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoSnService {
    private Repository<Producto> repository;


    public ProductoServiceImpl(Connection conn) {
        repository = new ProductoRepositoryImpl(conn);
    }

    @Override
    public List<Producto> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repository.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repository.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }
}
