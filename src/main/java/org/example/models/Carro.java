package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Carro {

    private List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public void setItems(List<ItemCarro> items) {
        this.items = items;
    }

    public void agregarItemCarro(ItemCarro item){
        this.items.stream().filter(i -> i.equals(item)).findAny().ifPresentOrElse(
                i -> i.setCantidad(i.getCantidad() + 1),
                () -> items.add(item)
        );
    }

    public void borrarProducto(Long id){
        this.items.removeIf(i -> i.getItem().getId().equals(id));
    }

    public void borrarProductos(List<Long> ids){
        if (ids != null){
            ids.forEach(this::borrarProducto);
        }
    }

    public void actualizarCantidad(Long id, int cantidad){
        items.stream().filter(i -> i.getItem().getId().equals(id)).findAny().ifPresent(i -> i.setCantidad(cantidad));
    }
}
