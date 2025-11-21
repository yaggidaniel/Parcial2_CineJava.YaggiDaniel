/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.vista;

import cine.controlador.LoginController;
import cine.modelo.Cine;
import cine.persistencia.Repositorio;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.CineFactory;

/**
 *
 * @author verra
 */
public class CinemaParadisoApp extends Application {
    
    private Repositorio<Cine> repo;
    private Cine cine; 
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        repo = new Repositorio<>("src/data/cine.ser");
        cine = repo.cargar();
        
        if (cine == null) {
            cine = CineFactory.crearCineDefault();
            repo.guardar(cine);
            System.out.println(">>> Cine creado por primera vez (cine.ser generado)");
        } else {
            System.out.println(">>> Cine cargado desde cine.ser");
        }
        
        ImageView splash = new ImageView(new Image(getClass().getResource("/cine/vista/recursos/telon.gif").toExternalForm()));

        splash.setFitWidth(800);
        splash.setPreserveRatio(true);

        StackPane splashRoot = new StackPane(splash);
        Scene splashScene = new Scene(splashRoot, 800, 600);

        primaryStage.setScene(splashScene);
        primaryStage.setTitle("Cinema Paradiso");
        primaryStage.show();

        PauseTransition pausa = new PauseTransition(Duration.seconds(5));
        pausa.setOnFinished(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent login = loader.load();

                LoginController controller = loader.getController();
                controller.inicializar(cine, repo);

                Scene loginScene = new Scene(login, 800, 600);
                utils.UiConfig.aplicarCss(loginScene);

                primaryStage.setScene(loginScene);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

        pausa.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
