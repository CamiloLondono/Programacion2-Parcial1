package com.lenguajecafetero.model;

import com.lenguajecafetero.factory.CursoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AcademiaTest {

    private Academia academia;
    private Estudiante estudiante;
    private Curso curso;

    @BeforeEach
    void setUp() {
        academia = new Academia("Lenguaje Cafetero", "900123456-1", "Armenia, Quindío", "3001234567",
                "contacto@lenguajecafetero.com", "www.lenguajecafetero.com");
        estudiante = new Estudiante("1094123456", "Maria Gomez", "3209876543", "maria@email.com", 20);
        curso = CursoFactory.crearCurso(
                CursoFactory.TipoCurso.REGULAR,
                "ENG-01",
                "Inglés Básico",
                "Inglés",
                "Curso general",
                2,
                100000.0,
                EstadoCurso.ACTIVO);
    }

    @Test
    @DisplayName("Debe registrar y buscar un estudiante por documento")
    void testRegistrarYBuscarEstudiante() {
        academia.registrarEstudiante(estudiante);

        Estudiante encontrado = academia.buscarEstudiante("1094123456");
        assertNotNull(encontrado);
        assertEquals("Maria Gomez", encontrado.getNombreCompleto());
    }

    @Test
    @DisplayName("No debe duplicar estudiantes con el mismo documento")
    void testEvitarEstudiantesDuplicados() {
        academia.registrarEstudiante(estudiante);
        academia.registrarEstudiante(new Estudiante("1094123456", "Maria Duplicada", "0000000", "dup@email.com", 20));

        assertEquals(1, academia.getEstudiantes().size());
        assertEquals("Maria Gomez", academia.buscarEstudiante("1094123456").getNombreCompleto());
    }

    @Test
    @DisplayName("Debe calcular los ingresos totales en un periodo de fechas")
    void testCalcularIngresosEnPeriodo() {
        academia.registrarEstudiante(estudiante);
        academia.registrarCurso(curso);

        Matricula matricula = estudiante.matricularCurso(curso); // Valor: 2 * 100000 = 200000
        academia.registrarMatricula(matricula);

        LocalDate inicio = LocalDate.now().minusDays(1);
        LocalDate fin = LocalDate.now().plusDays(1);

        double ingresos = academia.calcularIngresos(inicio, fin);
        assertEquals(200000.0, ingresos, 0.001);
    }
}