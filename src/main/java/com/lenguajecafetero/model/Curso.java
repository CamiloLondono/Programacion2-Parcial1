package com.lenguajecafetero.model;

public abstract class Curso {

    private String codigo;
    private String nombre;
    private String idioma;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private EstadoCurso estado;

    public Curso(String codigo, String nombre, String idioma,
                 String descripcion, int duracionMeses,
                 double valorMensual, EstadoCurso estado) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
    }

    public abstract double calcularValor();

    public abstract String obtenerBeneficios();

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public double getValorMensual() {
        return valorMensual;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoCurso estado) {
        this.estado = estado;
    }
}