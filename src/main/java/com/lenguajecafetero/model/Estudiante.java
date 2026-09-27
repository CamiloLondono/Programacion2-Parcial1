package com.lenguajecafetero.model;

import java.time.LocalDate;

public class Estudiante {

    private final String documento;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private int edad;
    private final LocalDate fechaRegistro;

    public Estudiante(String documento, String nombreCompleto, String telefono,
            String correo, int edad) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.edad = edad;
        this.fechaRegistro = LocalDate.now();
    }

    // Métodos del diagrama UML
    public Matricula matricularCurso(Curso curso) {
        return new Matricula(this, curso, 0.0);
    }

    public void solicitarServicio(ServicioAdicional servicio) {
        // Método de extensión si el estudiante tiene una matrícula activa
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    @Override
    public String toString() {
        return nombreCompleto + " (" + documento + ")";
    }
}