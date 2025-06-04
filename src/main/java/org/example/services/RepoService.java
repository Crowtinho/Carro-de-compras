package org.example.services;

import org.example.models.Producto;

import java.util.List;
import java.util.Optional;

public interface RepoService<T> {
    List<T> listar();
    Optional<T> porId(Long id);
    void guardar(T t);
    void eliminar(Long id);
}
