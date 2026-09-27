package com.lenguajecafetero.model;

public class CursoRegular extends Curso {

    // 1. Constructor
    public CursoRegular(String codigo, String nombre, String idioma,
            String descripcion, int duracionMeses,
            double valorMensual, EstadoCurso estado) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado);
    }

    // 2. Cálculo del valor para curso regular
    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses();
    }

    // 3. Beneficios específicos
    @Override
    public String obtenerBeneficios() {
        return "Acceso a plataforma virtual, material PDF y club de conversación semanal.";
    }
}