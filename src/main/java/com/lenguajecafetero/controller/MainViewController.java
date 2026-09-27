package com.lenguajecafetero.controller;

import com.lenguajecafetero.factory.CursoFactory;
import com.lenguajecafetero.model.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainViewController {

    private Academia academia;

    // --- Pestaña Estudiantes ---
    @FXML private TextField txtEstDocumento;
    @FXML private TextField txtEstNombre;
    @FXML private TextField txtEstTelefono;
    @FXML private TextField txtEstCorreo;
    @FXML private TextField txtEstEdad;
    @FXML private TableView<Estudiante> tblEstudiantes;
    @FXML private TableColumn<Estudiante, String> colEstDocumento;
    @FXML private TableColumn<Estudiante, String> colEstNombre;
    @FXML private TableColumn<Estudiante, String> colEstTelefono;
    @FXML private TableColumn<Estudiante, String> colEstCorreo;

    // --- Pestaña Cursos ---
    @FXML private TextField txtCurCodigo;
    @FXML private TextField txtCurNombre;
    @FXML private TextField txtCurIdioma;
    @FXML private ComboBox<String> cbCurTipo;
    @FXML private TextField txtCurDuracion;
    @FXML private TextField txtCurValor;
    @FXML private TableView<Curso> tblCursos;
    @FXML private TableColumn<Curso, String> colCurCodigo;
    @FXML private TableColumn<Curso, String> colCurNombre;
    @FXML private TableColumn<Curso, String> colCurIdioma;
    @FXML private TableColumn<Curso, String> colCurTipo;
    @FXML private TableColumn<Curso, Double> colCurValor;

    public void setAcademia(Academia academia) {
        this.academia = academia;
        refrescarTablas();
    }

    @FXML
    public void initialize() {
        // Configuración columnas Estudiantes
        colEstDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colEstNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colEstTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEstCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        // Configuración columnas Cursos
        colCurCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colCurNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCurIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colCurTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        colCurValor.setCellValueFactory(new PropertyValueFactory<>("valorMensual"));

        // Opciones del ComboBox de tipos de curso
        if (cbCurTipo != null) {
            cbCurTipo.setItems(FXCollections.observableArrayList("Regular", "Intensivo"));
            cbCurTipo.getSelectionModel().selectFirst();
        }
    }

    @FXML
    private void handleRegistrarEstudiante() {
        try {
            String doc = txtEstDocumento.getText().trim();
            String nom = txtEstNombre.getText().trim();
            String tel = txtEstTelefono.getText().trim();
            String correo = txtEstCorreo.getText().trim();
            int edad = Integer.parseInt(txtEstEdad.getText().trim());

            if (doc.isEmpty() || nom.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Documento y Nombre son obligatorios.");
                return;
            }

            Estudiante estudiante = new Estudiante(doc, nom, tel, correo, edad);
            academia.registrarEstudiante(estudiante);
            refrescarTablas();
            limpiarCamposEstudiante();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudiante registrado correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "La edad debe ser un número entero válido.");
        }
    }

    @FXML
    private void handleRegistrarCurso() {
    try {
        String cod = txtCurCodigo.getText().trim();
        String nom = txtCurNombre.getText().trim();
        String idioma = txtCurIdioma.getText().trim();
        
        // 1. Convertir el texto seleccionado del ComboBox al Enum correspondiente
        String tipoTexto = cbCurTipo.getValue().toUpperCase(); // Convertir "Regular" -> "REGULAR"
        CursoFactory.TipoCurso tipoEnum = CursoFactory.TipoCurso.valueOf(tipoTexto);

        int duracion = Integer.parseInt(txtCurDuracion.getText().trim());
        double valor = Double.parseDouble(txtCurValor.getText().trim());

        if (cod.isEmpty() || nom.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Código y Nombre de curso son obligatorios.");
            return;
        }

        // 2. Pasar 'tipoEnum' en lugar de la cadena de texto
        Curso curso = CursoFactory.crearCurso(
                tipoEnum, 
                cod, 
                nom, 
                idioma, 
                "Curso de " + idioma, 
                duracion, 
                valor, 
                EstadoCurso.ACTIVO
        );

        academia.registrarCurso(curso);
        refrescarTablas();
        limpiarCamposCurso();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Curso registrado mediante Factory Method.");

    } catch (IllegalArgumentException e) {
        mostrarAlerta(Alert.AlertType.ERROR, "Error de Datos", "Tipo de curso no válido o error de formato en números.");
    }
}
    private void refrescarTablas() {
        if (academia != null) {
            tblEstudiantes.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
            tblCursos.setItems(FXCollections.observableArrayList(academia.getCursos()));
        }
    }

    private void limpiarCamposEstudiante() {
        txtEstDocumento.clear();
        txtEstNombre.clear();
        txtEstTelefono.clear();
        txtEstCorreo.clear();
        txtEstEdad.clear();
    }

    private void limpiarCamposCurso() {
        txtCurCodigo.clear();
        txtCurNombre.clear();
        txtCurIdioma.clear();
        txtCurDuracion.clear();
        txtCurValor.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}