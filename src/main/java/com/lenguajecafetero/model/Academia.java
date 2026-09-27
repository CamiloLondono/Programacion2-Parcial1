package com.lenguajecafetero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Academia {

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private String paginaWeb;

    // Colecciones según el diagrama UML
    private List<Estudiante> estudiantes;
    private List<Profesor> profesores;
    private List<Curso> cursos;
    private List<ServicioAdicional> servicios;
    private List<Matricula> matriculas;

    public Academia(
            String nombreComercial,
            String nit,
            String direccion,
            String telefono,
            String correo,
            String paginaWeb) {

        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.paginaWeb = paginaWeb;

        // Inicialización de todas las listas
        this.estudiantes = new ArrayList<>();
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.matriculas = new ArrayList<>();
    }

    // ==========================================
    // MÉTODOS DE REGISTRO (UML)
    // ==========================================

    public void registrarEstudiante(Estudiante estudiante) {
        if (estudiante != null && buscarEstudiante(estudiante.getDocumento()) == null) {
            estudiantes.add(estudiante);
        }
    }

    public void registrarProfesor(Profesor profesor) {
        if (profesor != null) {
            profesores.add(profesor);
        }
    }

    public void registrarCurso(Curso curso) {
        if (curso != null) {
            cursos.add(curso);
        }
    }

    public void registrarServicio(ServicioAdicional servicio) {
        if (servicio != null) {
            servicios.add(servicio);
        }
    }

    public void registrarMatricula(Matricula matricula) {
        if (matricula != null) {
            matriculas.add(matricula);
        }
    }

    // ==========================================
    // MÉTODOS DE BÚSQUEDA Y CÁLCULO (UML)
    // ==========================================

    public Estudiante buscarEstudiante(String documento) {
        for (Estudiante e : estudiantes) {
            if (e.getDocumento().equalsIgnoreCase(documento)) {
                return e;
            }
        }
        return null;
    }

    public double calcularIngresos(LocalDate fechaInicial, LocalDate fechaFinal) {
        double ingresosTotales = 0.0;
        for (Matricula m : matriculas) {
            if (m.estaEnPeriodo(fechaInicial, fechaFinal)) {
                ingresosTotales += m.calcularValorTotal();
            }
        }
        return ingresosTotales;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<ServicioAdicional> getServicios() {
        return servicios;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }
}