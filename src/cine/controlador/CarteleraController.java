/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.controlador;

import cine.modelo.Cine;
import cine.modelo.Sala;
import cine.persistencia.Repositorio;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author verra
 */
public class CarteleraController {
    @FXML
    private ListView<String> listaSalas;
    @FXML 
    private HBox carruselBox;

    @FXML
    private Button btnPrev;

    @FXML
    private Button btnNext;
    @FXML
    private ScrollPane scrollCarrusel;

    private Cine cine;
    private Repositorio<Cine> repo;

    public void inicializar(Cine cine, Repositorio<Cine> repo) {
        this.cine = cine;
        this.repo = repo;

        mostrarCarrusel();
    }

    private void mostrarCarrusel() {
    carruselBox.getChildren().clear();

    final double ANCHO_TARJETA = 220;
    final double ALTO_TARJETA  = 320;

    for (Sala sala : cine.getSalas()) {
        String ruta = sala.getPelicula().getRutaImagen();

        URL url = null;
        if (ruta != null && !ruta.isBlank()) {
            url = getClass().getResource(ruta);
        }
        if (url == null) {
            url = getClass().getResource("/cine/vista/recursos/notfound.png");
        }

        Image imagen = new Image(url.toExternalForm(), 160, 220, true, true);

        VBox tarjeta = new VBox();
        tarjeta.setSpacing(8);
        tarjeta.setPrefWidth(ANCHO_TARJETA);
        tarjeta.setMaxWidth(ANCHO_TARJETA);
        tarjeta.setPrefHeight(ALTO_TARJETA);
        tarjeta.getStyleClass().add("tarjeta-pelicula");

        ImageView imgView = new ImageView(imagen);
        imgView.setFitWidth(ANCHO_TARJETA - 20);
        imgView.setPreserveRatio(true);

        Label titulo = new Label(sala.getPelicula().getTitulo());
        titulo.getStyleClass().add("titulo-pelicula");

        Label sinopsis = new Label(sala.getPelicula().getSinopsis());
        sinopsis.setWrapText(true);
        sinopsis.setMaxWidth(ANCHO_TARJETA - 20);
        sinopsis.getStyleClass().add("sinopsis-pelicula");

        tarjeta.getChildren().addAll(imgView, titulo, sinopsis);

        tarjeta.setOnMouseClicked(e -> seleccionarSala(sala));

        carruselBox.getChildren().add(tarjeta);
    }
}




    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/cine/vista/Login.fxml")
            );
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.inicializar(cine, repo);

            Stage stage = (Stage) carruselBox.getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            utils.UiConfig.aplicarCss(scene);
            stage.setScene(scene);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void seleccionarSala(Sala sala) {
        try {
               FXMLLoader loader = new FXMLLoader(
                       getClass().getResource("/cine/vista/Sala.fxml")
               );
               Parent root = loader.load();

               SalaController salaController = loader.getController();
               salaController.inicializar(cine, sala, repo);

               Stage stage = (Stage) carruselBox.getScene().getWindow();
               Scene scene = new Scene(root, 800, 600);
               utils.UiConfig.aplicarCss(scene);
               stage.setScene(scene);

           } catch (Exception ex) {
               ex.printStackTrace();
        }

    }
    
    
    @FXML
    private void moverIzquierda() {
        double v = scrollCarrusel.getHvalue();
        scrollCarrusel.setHvalue(Math.max(0.0, v - 0.3));
    }
    @FXML
    private void moverDerecha() {
        double v = scrollCarrusel.getHvalue();
        scrollCarrusel.setHvalue(Math.min(1.0, v + 0.3));
    }
}
