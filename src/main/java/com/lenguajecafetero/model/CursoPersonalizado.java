package com.lenguajecafetero.model;

public class CursoPersonalizado extends Curso {

    private int cantidadSesiones;
    private Nivel nivelReferencia;
    private String objetivos;

    public CursoPersonalizado(
            String codigo,
            String nombre,
            String idioma,
            String descripcion,
            int duracionMeses,
            double valorMensual,
            EstadoCurso estado,
            int cantidadSesiones,
            Nivel nivelReferencia,
            String objetivos) {

        super(codigo, nombre, idioma, descripcion,
              duracionMeses, valorMensual, estado);

        this.cantidadSesiones = cantidadSesiones;
        this.nivelReferencia = nivelReferencia;
        this.objetivos = objetivos;
    }

    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses();
    }

    @Override
    public String obtenerBeneficios() {
        return "Clases personalizadas y seguimiento con profesor";
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public Nivel getNivelReferencia() {
        return nivelReferencia;
    }

    public String getObjetivos() {
        return objetivos;
    }
}