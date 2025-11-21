package cine.vista.recursos;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import cine.controlador.LoginController;
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

        for (Sala sala : cine.getSalas()) {
            String ruta = sala.getPelicula().getRutaImagen();

            URL url = null;

            if (ruta != null && !ruta.isBlank()) {
                url = getClass().getResource(ruta);
            }

            if (url == null) {
                System.out.println("WARNING: Imagen no encontrada → " + ruta);
                url = getClass().getResource("/cine/vista/recursos/notfound.png");
            }

            Image imagen = new Image(url.toExternalForm(), 160, 220, true, true);

            VBox tarjeta = new VBox();
            tarjeta.setSpacing(8);
            tarjeta.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: #ddd; -fx-border-radius: 6; -fx-background-radius: 6;");

            ImageView imgView = new ImageView(imagen);

            Label titulo = new Label(sala.getPelicula().getTitulo());
            titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

            Label sinopsis = new Label(sala.getPelicula().getSinopsis());
            sinopsis.setWrapText(true);

            tarjeta.getChildren().addAll(imgView, titulo, sinopsis);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
