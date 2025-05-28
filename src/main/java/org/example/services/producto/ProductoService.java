package org.example.services;

import org.example.models.Categoria;
import org.example.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listarProducto();
    Optional<Producto> productoPorId(Long id);
    void guardarProducto(Producto producto);
    void eliminarProducto(Long id);

    List<Categoria> listarCategoria();
    Optional<Categoria> categoriaPorId(Long id);
    void guardarCategoria(Categoria categoria);
    void eliminarCategoria(Long id);


}
