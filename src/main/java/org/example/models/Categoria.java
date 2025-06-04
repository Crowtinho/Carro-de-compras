package org.example.models;

public class Categoria {

    private Long id;
    private String nombre;

    public Categoria() {
    }

    public Categoria(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre.toLowerCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        if (nombre == null || nombre.isEmpty()) {
            return nombre;
        }
        String lower = nombre.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }


    public void setNombre(String nombre) {
        this.nombre = nombre.toLowerCase();
    }
}
