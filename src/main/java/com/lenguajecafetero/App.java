package com.lenguajecafetero;

import com.lenguajecafetero.controller.MainViewController;
import com.lenguajecafetero.factory.CursoFactory;
import com.lenguajecafetero.model.*;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private Academia academia;

    @Override
    public void start(Stage primaryStage) throws Exception {
        inicializarModelo();

        String fxmlPath = "/com/lenguajecafetero/view/MainView.fxml";
        URL fxmlUrl = getClass().getResource(fxmlPath);

        if (fxmlUrl == null) {
            throw new RuntimeException("No se encontró el archivo FXML en la ruta: " + fxmlPath +
                    "\nAsegúrate de que el archivo esté guardado en 'src/main/resources/com/lenguajecafetero/view/MainView.fxml'");
        }

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        MainViewController controller = loader.getController();
        controller.setAcademia(academia);

        primaryStage.setTitle("Lenguaje Cafetero - Sistema de Gestión");
        primaryStage.setScene(new Scene(root, 950, 650));
        primaryStage.show();
    }

    private void inicializarModelo() {
        academia = new Academia(
                "Lenguaje Cafetero", "900123456-1", "Armenia, Quindío",
                "3001234567", "contacto@lenguajecafetero.com", "www.lenguajecafetero.com");

        Curso c1 = CursoFactory.crearCurso(CursoFactory.TipoCurso.REGULAR, "ENG-101", "Inglés Básico", "Inglés",
                "Nivel A1", 4, 150000.0, EstadoCurso.ACTIVO);
        Curso c2 = CursoFactory.crearCurso(CursoFactory.TipoCurso.INTENSIVO, "FRA-201", "Francés Intensivo", "Francés",
                "Programa B1", 2, 250000.0, EstadoCurso.ACTIVO);
        academia.registrarCurso(c1);
        academia.registrarCurso(c2);

        Estudiante e1 = new Estudiante("1094123456", "Juan Pérez", "3109876543", "juan@email.com", 22);
        Estudiante e2 = new Estudiante("1094987654", "Maria Gomez", "3201234567", "maria@email.com", 20);
        academia.registrarEstudiante(e1);
        academia.registrarEstudiante(e2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}