/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.controlador;

import cine.modelo.Butaca;
import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.modelo.Sala;
import cine.persistencia.Repositorio;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author verra
 */
public class SalaController {
    @FXML
    private Label lblTituloSala;

    @FXML
    private GridPane gridButacas;

    @FXML
    private Label lblMensaje;

    private Cine cine;
    private Sala sala;
    private Repositorio<Cine> repo;
    private Cliente clienteLogueado;

    private final Map<Butaca, Button> mapaButacaBoton = new HashMap<>();
    private final Set<Butaca> butacasSeleccionadas = new HashSet<>();

    private Image imgButaca;

    public void inicializar(Cine cine, Sala sala, Repositorio<Cine> repo, Cliente clienteLogueado) {
        this.cine = cine;
        this.sala = sala;
        this.repo = repo;
        this.clienteLogueado = clienteLogueado;

        this.imgButaca = new Image(
                getClass().getResource("/cine/vista/recursos/butaca.png").toExternalForm()
        );
        lblTituloSala.setText("Sala " + sala.getNumero() + " - " + sala.getPelicula().getTitulo());
        dibujarButacas();
    }

    private void dibujarButacas() {
        gridButacas.getChildren().clear();
        mapaButacaBoton.clear();
        butacasSeleccionadas.clear();
        lblMensaje.setText("");

        Butaca[][] matriz = sala.getButacas();

            for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {

                Butaca butaca = matriz[fila][columna];
                Button boton = new Button();

                boton.setMinSize(90, 90);
                boton.setPrefSize(90, 90);
                boton.setMaxSize(90, 90);

                boton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                boton.setAlignment(Pos.CENTER);

                ImageView imagenButaca = new ImageView(imgButaca);
                imagenButaca.setFitWidth(90);
                imagenButaca.setFitHeight(90);

                if (butaca.isEsPasillo()) {
                    boton.setDisable(true);
                    boton.setGraphic(null);
                    boton.getStyleClass().setAll("butaca-pasillo");

                } else if (butaca.estaOcupada()) {
                    boton.setDisable(true);
                    boton.setGraphic(imagenButaca);
                    boton.getStyleClass().setAll("butaca-ocupada");

                } else {
                    boton.setGraphic(imagenButaca);
                    boton.getStyleClass().setAll("butaca-disponible");
                    boton.setOnAction(e -> toggleSeleccion(butaca));
                }

                mapaButacaBoton.put(butaca, boton);
                gridButacas.add(boton, columna, fila);
            }
        }
    }

    private void toggleSeleccion(Butaca b) {
        Button btn = mapaButacaBoton.get(b);
        if (btn == null || b.estaOcupada() || b.isEsPasillo()) return;

        if (butacasSeleccionadas.contains(b)) {
            butacasSeleccionadas.remove(b);
            btn.getStyleClass().setAll("butaca-disponible");
        } else {
            butacasSeleccionadas.add(b);
            btn.getStyleClass().setAll("butaca-seleccionada");
        }

        if (butacasSeleccionadas.isEmpty()) {
            lblMensaje.setText("");
        } else {
            lblMensaje.setText("Butacas seleccionadas: " + butacasSeleccionadas.size());
        }
    }

    @FXML
    private void volverACartelera() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/vista/Cartelera.fxml"));
            Parent root = loader.load();

            CarteleraController carteleraController = loader.getController();
            carteleraController.inicializar(cine, repo, clienteLogueado);

            Stage stage = (Stage) gridButacas.getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            utils.UiConfig.aplicarCss(scene);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void confirmarSeleccion() {
        if (butacasSeleccionadas.isEmpty()) {
            lblMensaje.setText("No hay butacas seleccionadas.");
            return;
        }

        try {
            Set<Butaca> seleccionParaPagar = new HashSet<>(butacasSeleccionadas);

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/cine/vista/Pago.fxml")
            );
            Parent root = loader.load();

            PagoController pagoController = loader.getController();
            pagoController.inicializar(cine, sala, repo, clienteLogueado, seleccionParaPagar);

            Stage pagoStage = new Stage();
            pagoStage.initOwner(gridButacas.getScene().getWindow());
            pagoStage.initModality(Modality.APPLICATION_MODAL);
            pagoStage.setTitle("Confirmar pago");

            Scene scene = new Scene(root, 600, 500);
            utils.UiConfig.aplicarCss(scene);
            pagoStage.setScene(scene);

            pagoStage.showAndWait();

            butacasSeleccionadas.clear();
            dibujarButacas();
            lblMensaje.setText("");

        } catch (IOException e) {
            e.printStackTrace();
            lblMensaje.setText("Ocurrió un error al abrir la pantalla de pago.");
        }
    }



        
}
