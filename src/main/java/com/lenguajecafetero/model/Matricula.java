package com.lenguajecafetero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Matricula {

    private final Estudiante estudiante;
    private final Curso curso;
    private final LocalDate fechaMatricula;
    private double porcentajeDescuento;
    private final List<ServicioAdicional> serviciosUtilizados = new ArrayList<>();

    public Matricula(Estudiante estudiante, Curso curso, double porcentajeDescuento) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.porcentajeDescuento = porcentajeDescuento;
        this.fechaMatricula = LocalDate.now();
    }

    public Matricula(Estudiante estudiante, Curso curso, double porcentajeDescuento, LocalDate fechaMatricula) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.porcentajeDescuento = porcentajeDescuento;
        this.fechaMatricula = fechaMatricula;
    }

    /**
     * Regla de negocio: solo se pueden agregar servicios que esten disponibles.
     */
    public void agregarServicio(ServicioAdicional servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo");
        }
        if (!servicio.isDisponible()) {
            throw new IllegalStateException("El servicio '" + servicio.getNombre() + "' no esta disponible");
        }
        serviciosUtilizados.add(servicio);
    }

    public double calcularTotalServicios() {
        return serviciosUtilizados.stream()
                .mapToDouble(ServicioAdicional::getPrecio)
                .sum();
    }

    /**
     * Valor total = valor del curso (Curso.calcularValor()) + servicios adicionales - descuento.
     */
    public double calcularValorTotal() {
        double subtotal = curso.calcularValor() + calcularTotalServicios();
        double descuento = subtotal * (porcentajeDescuento / 100.0);
        return subtotal - descuento;
    }

    public boolean estaEnPeriodo(LocalDate inicio, LocalDate fin) {
        return !fechaMatricula.isBefore(inicio) && !fechaMatricula.isAfter(fin);
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public List<ServicioAdicional> getServiciosUtilizados() {
        return List.copyOf(serviciosUtilizados);
    }
}

