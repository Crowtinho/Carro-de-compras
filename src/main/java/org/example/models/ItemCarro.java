package org.example.models;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemCarro {

    private int cantidad;
    private Producto item;

    public ItemCarro() {
    }

    public ItemCarro(int cantidad, Producto item) {
        this.cantidad = cantidad;
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getItem() {
        return item;
    }

    public void setItem(Producto item) {
        this.item = item;
    }

    public BigDecimal getImporte(){
        return this.item.getPrecio().multiply(BigDecimal.valueOf(this.cantidad));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarro itemCarro = (ItemCarro) o;
        return Objects.equals(item, itemCarro.item);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(item);
    }
}
