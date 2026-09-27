package com.lenguajecafetero.controller;

import com.lenguajecafetero.factory.CursoFactory;
import com.lenguajecafetero.model.*;

import java.time.LocalDate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainViewController {

    private Academia academia;

    // --- Pestaña Estudiantes ---
    @FXML
    private TextField txtEstDocumento;
    @FXML
    private TextField txtEstNombre;
    @FXML
    private TextField txtEstTelefono;
    @FXML
    private TextField txtEstCorreo;
    @FXML
    private TextField txtEstEdad;
    @FXML
    private TextField txtBuscarDocumento;
    @FXML
    private TableView<Estudiante> tblEstudiantes;
    @FXML
    private TableColumn<Estudiante, String> colEstDocumento;
    @FXML
    private TableColumn<Estudiante, String> colEstNombre;
    @FXML
    private TableColumn<Estudiante, String> colEstTelefono;
    @FXML
    private TableColumn<Estudiante, String> colEstCorreo;

    // --- Pestaña Cursos ---
    @FXML
    private TextField txtCurCodigo;
    @FXML
    private TextField txtCurNombre;
    @FXML
    private TextField txtCurIdioma;
    @FXML
    private TextField txtCurDuracion;
    @FXML
    private TextField txtCurValor;
    @FXML
    private TextField txtCurSesiones;
    @FXML
    private TextField txtCurObjetivos;
    @FXML
    private ComboBox<String> cbCurTipo;
    @FXML
    private ComboBox<EstadoCurso> cbCurEstado;
    @FXML
    private ComboBox<Nivel> cbCurNivel;
    @FXML
    private TableView<Curso> tblCursos;
    @FXML
    private TableColumn<Curso, String> colCurCodigo;
    @FXML
    private TableColumn<Curso, String> colCurNombre;
    @FXML
    private TableColumn<Curso, String> colCurIdioma;
    @FXML
    private TableColumn<Curso, String> colCurTipo;
    @FXML
    private TableColumn<Curso, String> colCurEstado;
    @FXML
    private TableColumn<Curso, Double> colCurValor;

    // --- Pestaña Profesores ---
    @FXML
    private TextField txtProfId;
    @FXML
    private TextField txtProfNombre;
    @FXML
    private TextField txtProfIdioma;
    @FXML
    private TextField txtProfTelefono;
    @FXML
    private TextField txtProfTarifa;
    @FXML
    private TableView<Profesor> tblProfesores;
    @FXML
    private TableColumn<Profesor, String> colProfId;
    @FXML
    private TableColumn<Profesor, String> colProfNombre;
    @FXML
    private TableColumn<Profesor, String> colProfIdioma;
    @FXML
    private TableColumn<Profesor, Double> colProfTarifa;

    // --- Pestaña Matrículas ---
    @FXML
    private ComboBox<Estudiante> cbMatEstudiante;
    @FXML
    private ComboBox<Curso> cbMatCurso;
    @FXML
    private ComboBox<Profesor> cbMatProfesor;
    @FXML
    private ComboBox<ServicioAdicional> cbMatServicio;
    @FXML
    private TextField txtMatDescuento;
    @FXML
    private Label lblTotalIngresos;
    @FXML
    private TableView<Matricula> tblMatriculas;
    @FXML
    private TableColumn<Matricula, String> colMatEstudiante;
    @FXML
    private TableColumn<Matricula, String> colMatCurso;
    @FXML
    private TableColumn<Matricula, String> colMatFecha;
    @FXML
    private TableColumn<Matricula, Double> colMatTotal;

    public void setAcademia(Academia academia) {
        this.academia = academia;
        refrescarTablas();
    }

    @FXML
    public void initialize() {
        // 1. Mapeo Columnas Estudiantes
        colEstDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colEstNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colEstTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEstCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        // 2. Mapeo Columnas Cursos
        colCurCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colCurNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCurIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colCurTipo.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        colCurEstado
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString()));
        colCurValor.setCellValueFactory(new PropertyValueFactory<>("valorMensual"));

        // 3. Mapeo Columnas Profesores
        colProfId.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        colProfNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colProfIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colProfTarifa.setCellValueFactory(new PropertyValueFactory<>("tarifaPorSesion"));

        // 4. Mapeo Columnas Matrículas (AQUÍ ESTÁ LA CORRECCIÓN DEL PRECIO TOTAL)
        colMatEstudiante.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getEstudiante().getNombreCompleto()));
        colMatCurso.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCurso().getNombre()));
        colMatFecha
                .setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFechaMatricula().toString()));

        // Cálculo e inyección directa del valor total de la matrícula
        colMatTotal.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().calcularValorTotal()));

        // 5. Cargar Datos en ComboBoxes
        if (cbCurTipo != null) {
            cbCurTipo.setItems(FXCollections.observableArrayList("Regular", "Intensivo", "Personalizado"));
            cbCurTipo.getSelectionModel().selectFirst();
        }

        if (cbCurEstado != null) {
            cbCurEstado.setItems(FXCollections.observableArrayList(EstadoCurso.values()));
            cbCurEstado.getSelectionModel().selectFirst();
        }

        if (cbCurNivel != null) {
            cbCurNivel.setItems(FXCollections.observableArrayList(Nivel.values()));
            cbCurNivel.getSelectionModel().selectFirst();
        }

        // Formateo visual de la opción 'null' en Servicio Adicional
        if (cbMatServicio != null) {
            cbMatServicio.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(ServicioAdicional item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText("-- Sin servicio adicional --");
                    } else {
                        setText(item.getNombre() + " (+$" + item.getPrecio() + ")");
                    }
                }
            });
            cbMatServicio.setButtonCell(cbMatServicio.getCellFactory().call(null));
        }
    }

    // --- Manejadores Estudiantes ---

    @FXML
    @SuppressWarnings("unused")
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

            academia.registrarEstudiante(new Estudiante(doc, nom, tel, correo, edad));
            refrescarTablas();
            limpiarCamposEstudiante();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudiante registrado correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "La edad debe ser un número entero válido.");
        }
    }

    @FXML
    @SuppressWarnings("unused")
    private void handleBuscarEstudiante() {
        String doc = txtBuscarDocumento.getText().trim();
        if (doc.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Búsqueda Vacía", "Ingrese un número de documento.");
            return;
        }

        Estudiante est = academia.buscarEstudiante(doc);
        if (est != null) {
            tblEstudiantes.setItems(FXCollections.observableArrayList(est));
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sin Resultados",
                    "No se encontró ningún estudiante con ese documento.");
        }
    }

    @FXML
    @SuppressWarnings("unused")
    private void handleMostrarTodosEstudiantes() {
        if (academia != null) {
            tblEstudiantes.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
            txtBuscarDocumento.clear();
        }
    }

    // --- Manejadores Profesores ---

    @FXML
    @SuppressWarnings("unused")
    private void handleRegistrarProfesor() {
        try {
            String id = txtProfId.getText().trim();
            String nom = txtProfNombre.getText().trim();
            String idio = txtProfIdioma.getText().trim();
            String tel = txtProfTelefono.getText().trim();
            double tarifa = Double.parseDouble(txtProfTarifa.getText().trim());

            if (id.isEmpty() || nom.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "ID y Nombre son obligatorios.");
                return;
            }

            academia.registrarProfesor(new Profesor(id, nom, idio, tel, tarifa));
            refrescarTablas();
            limpiarCamposProfesor();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Profesor registrado correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "La tarifa debe ser un número decimal válido.");
        }
    }

    // --- Manejadores Cursos ---

    @FXML
    @SuppressWarnings("unused")
    private void handleRegistrarCurso() {
        try {
            String cod = txtCurCodigo.getText().trim();
            String nom = txtCurNombre.getText().trim();
            String idioma = txtCurIdioma.getText().trim();
            String tipoSel = cbCurTipo.getValue();
            EstadoCurso estado = cbCurEstado.getValue();
            int duracion = Integer.parseInt(txtCurDuracion.getText().trim());
            double valor = Double.parseDouble(txtCurValor.getText().trim());

            Curso curso;
            if ("Personalizado".equalsIgnoreCase(tipoSel)) {
                int sesiones = txtCurSesiones.getText().trim().isEmpty() ? 0
                        : Integer.parseInt(txtCurSesiones.getText().trim());
                Nivel nivel = cbCurNivel.getValue();
                String objs = txtCurObjetivos != null ? txtCurObjetivos.getText().trim() : "Objetivos generales";

                curso = CursoFactory.crearCursoPersonalizado(cod, nom, idioma, "Curso Personalizado",
                        duracion, valor, estado, sesiones, nivel, objs);
            } else {
                CursoFactory.TipoCurso tipoEnum = CursoFactory.TipoCurso.valueOf(tipoSel.toUpperCase());
                curso = CursoFactory.crearCurso(tipoEnum, cod, nom, idioma, "Curso de " + idioma, duracion, valor,
                        estado);
            }

            academia.registrarCurso(curso);
            refrescarTablas();
            limpiarCamposCurso();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Curso creado exitosamente.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error",
                    "Revise que los números (duración/valor/sesiones) tengan el formato correcto.");
        }
    }

    // --- Manejadores Matrículas ---

    @FXML
    @SuppressWarnings("unused")
    private void handleRegistrarMatricula() {
        try {
            Estudiante estudiante = cbMatEstudiante.getValue();
            Curso curso = cbMatCurso.getValue();
            Profesor profesor = cbMatProfesor.getValue();
            ServicioAdicional servicio = cbMatServicio.getValue();

            String descTexto = txtMatDescuento.getText().trim();
            double descuento = descTexto.isEmpty() ? 0.0 : Double.parseDouble(descTexto);

            if (estudiante == null || curso == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Selección Incompleta", "Selecciona un estudiante y un curso.");
                return;
            }

            Matricula matricula = estudiante.matricularCurso(curso);
            matricula.setPorcentajeDescuento(descuento);

            if (servicio != null) {
                matricula.agregarServicio(servicio);
            }

            // Si se seleccionó un profesor, se le asigna esta matrícula mediante el método
            // del diagrama UML
            if (profesor != null) {
                profesor.asignarEstudiante(matricula);
            }

            academia.registrarMatricula(matricula);
            refrescarTablas();
            limpiarCamposMatricula();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito",
                    "Matrícula realizada. Total a pagar: $" + matricula.calcularValorTotal());
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Matrícula", e.getMessage());
        }
    }

    // --- Métodos de Refresco y Limpieza ---

    private void refrescarTablas() {
        if (academia != null) {
            tblEstudiantes.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
            tblCursos.setItems(FXCollections.observableArrayList(academia.getCursos()));
            tblProfesores.setItems(FXCollections.observableArrayList(academia.getProfesores()));

            cbMatEstudiante.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
            cbMatCurso.setItems(FXCollections.observableArrayList(academia.getCursos()));

            if (cbMatProfesor != null) {
                cbMatProfesor.setItems(FXCollections.observableArrayList(academia.getProfesores()));
            }

            ObservableList<ServicioAdicional> listaServicios = FXCollections.observableArrayList();
            listaServicios.add(null);
            listaServicios.addAll(academia.getServicios());
            cbMatServicio.setItems(listaServicios);

            tblMatriculas.setItems(FXCollections.observableArrayList(academia.getMatriculas()));

            double ingresos = academia.calcularIngresos(LocalDate.now().minusYears(1), LocalDate.now().plusYears(1));
            lblTotalIngresos.setText("$" + ingresos);
        }
    }

    private void limpiarCamposEstudiante() {
        txtEstDocumento.clear();
        txtEstNombre.clear();
        txtEstTelefono.clear();
        txtEstCorreo.clear();
        txtEstEdad.clear();
    }

    private void limpiarCamposProfesor() {
        txtProfId.clear();
        txtProfNombre.clear();
        txtProfIdioma.clear();
        txtProfTelefono.clear();
        txtProfTarifa.clear();
    }

    private void limpiarCamposCurso() {
        txtCurCodigo.clear();
        txtCurNombre.clear();
        txtCurIdioma.clear();
        txtCurDuracion.clear();
        txtCurValor.clear();
        if (txtCurSesiones != null)
            txtCurSesiones.clear();
        if (txtCurObjetivos != null)
            txtCurObjetivos.clear();
    }

    private void limpiarCamposMatricula() {
        cbMatEstudiante.getSelectionModel().clearSelection();
        cbMatCurso.getSelectionModel().clearSelection();
        if (cbMatProfesor != null)
            cbMatProfesor.getSelectionModel().clearSelection();
        cbMatServicio.getSelectionModel().clearSelection();
        txtMatDescuento.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}