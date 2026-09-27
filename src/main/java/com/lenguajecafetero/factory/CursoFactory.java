package com.lenguajecafetero.factory;

import com.lenguajecafetero.model.*;

public class CursoFactory {

    public enum TipoCurso {
        REGULAR, INTENSIVO
    }

    public static Curso crearCurso(
            TipoCurso tipo,
            String codigo,
            String nombre,
            String idioma,
            String descripcion,
            int duracionMeses,
            double valorMensual,
            EstadoCurso estado) {

        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de curso no puede ser nulo");
        }

        return switch (tipo) {
            case REGULAR -> new CursoRegular(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado);
            case INTENSIVO ->
                new CursoIntensivo(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado);
        };
    }

    public static CursoPersonalizado crearCursoPersonalizado(
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

        return new CursoPersonalizado(
                codigo, nombre, idioma, descripcion,
                duracionMeses, valorMensual, estado,
                cantidadSesiones, nivelReferencia, objetivos);
    }
}