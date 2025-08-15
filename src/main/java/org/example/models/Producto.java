package org.example.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Producto {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private LocalDate fechaRegistro;
    private Categoria categoria;
    private String imagen;

    public Producto() {
    }

    public Producto(Long id, String nombre, BigDecimal precio, LocalDate fechaRegistro, Categoria categoria, String imagen) {
        this.id = id;
        this.nombre = nombre.toLowerCase();
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
//        if (nombre == null || nombre.isEmpty()) {
//            return nombre;
//        }
//        String lower = nombre.toLowerCase();
//        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
        return nombre;
    }


    public void setNombre(String nombre) {
//        this.nombre = nombre.toLowerCase();
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
