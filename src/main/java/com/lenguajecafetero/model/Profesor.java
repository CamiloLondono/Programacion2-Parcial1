package com.lenguajecafetero.model;

public class Profesor {

    private final String identificacion;
    private String nombre;
    private String idioma;
    private String telefono;
    private double tarifaPorSesion;

    public Profesor(String identificacion, String nombre, String idioma,
                    String telefono, double tarifaPorSesion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.idioma = idioma;
        this.telefono = telefono;
        this.tarifaPorSesion = tarifaPorSesion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getTarifaPorSesion() {
        return tarifaPorSesion;
    }

    public void setTarifaPorSesion(double tarifaPorSesion) {
        this.tarifaPorSesion = tarifaPorSesion;
    }

    @Override
    public String toString() {
        return nombre + " (" + identificacion + ")";
    }
}
