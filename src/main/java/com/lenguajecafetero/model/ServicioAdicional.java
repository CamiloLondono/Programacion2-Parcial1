package com.lenguajecafetero.model;

public class ServicioAdicional {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponible;

    public ServicioAdicional(
            String codigo,
            String nombre,
            String descripcion,
            double precio,
            boolean disponible) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isDisponible() {
    return estaDisponible();
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
