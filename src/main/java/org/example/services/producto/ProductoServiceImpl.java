package org.example.services;

import org.example.models.Categoria;
import org.example.models.Producto;
import org.example.repositories.CategoriaRepositoryImpl;
import org.example.repositories.ProductoRepository;
import org.example.repositories.ProductoRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService{
    private ProductoRepository<Producto> productoRepository;
    private ProductoRepository<Categoria> categoriaProductoRepository;

    public ProductoServiceImpl(Connection conn) {
        productoRepository = new ProductoRepositoryImpl(conn);
        categoriaProductoRepository = new CategoriaRepositoryImpl(conn);
    }

    @Override
    public List<Producto> listarProducto() {
        try {
            return productoRepository.listar();
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Producto> productoPorId(Long id) {
        try {
            return Optional.ofNullable(productoRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardarProducto(Producto producto) {
        try {
            productoRepository.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminarProducto(Long id) {
        try {
            productoRepository.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return categoriaProductoRepository.listar();
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Categoria> categoriaPorId(Long id) {
        try {
            return Optional.ofNullable(categoriaProductoRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        try {
            categoriaProductoRepository.guardar(categoria);
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminarCategoria(Long id) {
        try {
            categoriaProductoRepository.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }
}
