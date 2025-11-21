/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.controlador;

import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.persistencia.Repositorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author verra
 */
public class LoginController {
    
    
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPass;
    @FXML private Label lblError;
    
    private Cine cine;
    private Repositorio<Cine> repo;
    
    public void inicializar(Cine cine, Repositorio<Cine> repo) {
        this.cine = cine;
        this.repo = repo;
    }
    
    @FXML
    private void ingresar() {
        String email = txtEmail.getText();
        String pass  = txtPass.getText();

        if (email.isBlank() || pass.isBlank()) {
            lblError.setText("Debe ingresar email y contraseña");
            lblError.setVisible(true);

            return;
        }

        for (Cliente c : cine.getClientes()) {
            if (c.credencialesValidas(email, pass)) {
                lblError.setText("");
                lblError.setVisible(false);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/vista/Cartelera.fxml"));
                    Parent root = loader.load();

                    CarteleraController carteleraController = loader.getController();
                    carteleraController.inicializar(cine, repo);

                    Stage stage = (Stage) txtEmail.getScene().getWindow();
                    Scene scene = new Scene(root, 800, 600);
                    utils.UiConfig.aplicarCss(scene);
                    
                    stage.setScene(scene);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return;
            }
        }

        lblError.setText("Credenciales incorrectas");
        lblError.setVisible(true);

    }

    @FXML
    private void irARegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/vista/Registro.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            utils.UiConfig.aplicarCss(scene);


            RegistroController registroController = loader.getController();
            registroController.inicializar(cine, repo);

            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();    
        }    
    }
    
    

}
