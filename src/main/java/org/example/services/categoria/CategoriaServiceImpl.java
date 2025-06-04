package org.example.services.categoria;

import org.example.models.Categoria;
import org.example.repositories.CategoriaRepositoryImpl;
import org.example.repositories.Repository;
import org.example.services.ServiceExeption;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaServiceImpl implements CategoriaService{
    private Repository<Categoria> categoriaRepository;

    public CategoriaServiceImpl(Connection conn) {
        categoriaRepository = new CategoriaRepositoryImpl(conn);
    }

    @Override
    public List<Categoria> listar() {
        try {
            return categoriaRepository.listar();
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Categoria> porId(Long id) {
        try {
            return Optional.ofNullable(categoriaRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Categoria categoria) {
        try {
            categoriaRepository.guardar(categoria);
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            categoriaRepository.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceExeption(e.getMessage(),e.getCause());
        }
    }
}
