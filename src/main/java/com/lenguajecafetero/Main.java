package com.lenguajecafetero;

import com.lenguajecafetero.factory.CursoFactory;
import com.lenguajecafetero.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 1. Crear la Academia
        Academia academia = new Academia("Lenguaje Cafetero", "900123456-1", "Armenia, Quindío", "3001234567",
                "contacto@lenguajecafetero.com", "www.lenguajecafetero.com");

        // 2. Crear Cursos con la Factory creada
        // Cambia "regular" e "intensivo" por las opciones del Enum:
        Curso inglesRegular = CursoFactory.crearCurso(
                CursoFactory.TipoCurso.REGULAR,
                "ENG-01",
                "Inglés Básico",
                "Inglés",
                "Curso de inglés general",
                4,
                150000.0,
                EstadoCurso.ACTIVO);

        Curso francesIntensivo = CursoFactory.crearCurso(
                CursoFactory.TipoCurso.INTENSIVO,
                "FRA-01",
                "Francés Intensivo",
                "Francés",
                "Acelerado B1",
                2,
                250000.0,
                EstadoCurso.ACTIVO);
        academia.registrarCurso(inglesRegular);
        academia.registrarCurso(francesIntensivo);

        // 3. Crear Servicios Adicionales
        ServicioAdicional clubConversacion = new ServicioAdicional("SERV-01", "Club de Conversación",
                "Sesión extra con nativos", 30000.0, true);
        academia.registrarServicio(clubConversacion);

        // 4. Crear Estudiante y Profesor
        Estudiante estudiante = new Estudiante("1094123456", "Juan Pérez", "3109876543", "juan@email.com", 22);
        Profesor profesor = new Profesor("1095000111", "Carlos Gómez", "Inglés", "3201112233", 40000.0);

        academia.registrarEstudiante(estudiante);
        academia.registrarProfesor(profesor);

        // 5. Crear Matrícula
        Matricula matricula = estudiante.matricularCurso(inglesRegular);
        matricula.setPorcentajeDescuento(10.0); // 10% de descuento
        matricula.agregarServicio(clubConversacion);

        profesor.asignarEstudiante(matricula);
        academia.registrarMatricula(matricula);

        // 6. Probar cálculos e ingresos
        System.out.println("--- REGISTRO LENGUAJE CAFETERO ---");
        System.out.println("Estudiante: " + matricula.getEstudiante().getNombreCompleto());
        System.out.println("Curso: " + matricula.getCurso().getNombre());
        System.out.println("Beneficios: " + matricula.getCurso().obtenerBeneficios());
        System.out.println("Valor Total Matrícula: $" + matricula.calcularValorTotal());

        double ingresos = academia.calcularIngresos(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        System.out.println("Ingresos totales de la academia: $" + ingresos);
    }
}