package com.lenguajecafetero.model;

import com.lenguajecafetero.factory.CursoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatriculaTest {

    private Estudiante estudiante;
    private Curso cursoRegular;
    private ServicioAdicional servicioDisponible;
    private ServicioAdicional servicioNoDisponible;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante("1094123456", "Juan Pérez", "3109876543", "juan@email.com", 22);
        cursoRegular = CursoFactory.crearCurso(
                CursoFactory.TipoCurso.REGULAR,
                "ENG-01", "Inglés Básico", "Inglés", "Curso regular", 4, 150000.0, EstadoCurso.ACTIVO); // Valor
                                                                                                        // curso:
                                                                                                        // 4
                                                                                                        // *
                                                                                                        // 150000
                                                                                                        // =
                                                                                                        // 600000

        servicioDisponible = new ServicioAdicional("SERV-01", "Club de conversación", "Sábados", 30000.0, true);
        servicioNoDisponible = new ServicioAdicional("SERV-02", "Tutoría privada", "1 a 1", 50000.0, false);
    }

    @Test
    @DisplayName("Debe calcular correctamente el valor total con servicio y descuento")
    void testCalcularValorTotalConDescuentoYServicio() {
        Matricula matricula = estudiante.matricularCurso(cursoRegular);
        matricula.setPorcentajeDescuento(10.0); // 10%
        matricula.agregarServicio(servicioDisponible); // +30000

        // Subtotal: 600000 + 30000 = 630000
        // Descuento (10%): 63000
        // Total esperado: 567000
        assertEquals(567000.0, matricula.calcularValorTotal(), 0.001);
    }

    @Test
    @DisplayName("Debe lanzar excepción al intentar agregar un servicio no disponible")
    void testAgregarServicioNoDisponible() {
        Matricula matricula = estudiante.matricularCurso(cursoRegular);

        assertThrows(IllegalStateException.class, () -> {
            matricula.agregarServicio(servicioNoDisponible);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción al intentar agregar un servicio nulo")
    void testAgregarServicioNulo() {
        Matricula matricula = estudiante.matricularCurso(cursoRegular);

        assertThrows(IllegalArgumentException.class, () -> {
            matricula.agregarServicio(null);
        });
    }
}