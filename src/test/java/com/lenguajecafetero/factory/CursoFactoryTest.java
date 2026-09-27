package com.lenguajecafetero.factory;

import com.lenguajecafetero.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursoFactoryTest {

    @Test
    @DisplayName("Debe crear una instancia de CursoRegular correctamente")
    void testCrearCursoRegular() {
        Curso curso = CursoFactory.crearCurso(
                CursoFactory.TipoCurso.REGULAR,
                "ENG-01",
                "Inglés Básico",
                "Inglés",
                "Curso inicial",
                4,
                150000.0,
                EstadoCurso.ACTIVO);

        assertNotNull(curso, "El curso creado no debe ser nulo");
        assertTrue(curso instanceof CursoRegular, "La instancia debe ser de tipo CursoRegular");
        assertEquals("ENG-01", curso.getCodigo());
        assertEquals(150000.0, curso.getValorMensual());
    }

    @Test
    @DisplayName("Debe crear una instancia de CursoIntensivo correctamente")
    void testCrearCursoIntensivo() {
        Curso curso = CursoFactory.crearCurso(
                CursoFactory.TipoCurso.INTENSIVO,
                "FRA-01",
                "Francés Avanzado",
                "Francés",
                "Curso acelerado",
                2,
                200000.0,
                EstadoCurso.ACTIVO);

        assertNotNull(curso);
        assertTrue(curso instanceof CursoIntensivo, "La instancia debe ser de tipo CursoIntensivo");
    }

    @Test
    @DisplayName("Debe lanzar IllegalArgumentException al pasar un tipo nulo")
    void testCrearCursoTipoInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CursoFactory.crearCurso(
                    null,
                    "COD-99",
                    "Curso Invalido",
                    "Español",
                    "Test",
                    1,
                    50000.0,
                    EstadoCurso.ACTIVO);
        });

        // Opcional: verificar que el mensaje sea el esperado
        assertEquals("El tipo de curso no puede ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("Debe crear un CursoPersonalizado con atributos específicos")
    void testCrearCursoPersonalizado() {
        CursoPersonalizado curso = CursoFactory.crearCursoPersonalizado(
                "PER-01",
                "Alemán Técnico",
                "Alemán",
                "Alemán para negocios",
                3,
                180000.0,
                EstadoCurso.ACTIVO,
                10,
                Nivel.B2,
                "Aprobar examen de certificación");

        assertNotNull(curso);
        assertEquals(10, curso.getCantidadSesiones());
        assertEquals(Nivel.B2, curso.getNivelReferencia());
    }
}