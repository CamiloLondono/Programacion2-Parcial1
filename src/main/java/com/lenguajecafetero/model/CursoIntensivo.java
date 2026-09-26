package com.lenguajecafetero.model;

public class CursoIntensivo extends Curso {

    public CursoIntensivo(String codigo, String nombre, String idioma,
                          String descripcion, int duracionMeses,
                          double valorMensual, EstadoCurso estado) {

        super(codigo, nombre, idioma, descripcion,
              duracionMeses, valorMensual, estado);
    }

    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses();
    }

    @Override
    public String obtenerBeneficios() {
        return "Acceso a plataforma virtual, material didáctico y clubes de conversación";
    }
}