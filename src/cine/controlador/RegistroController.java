/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.controlador;

import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.persistencia.Repositorio;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author verra
 */
public class RegistroController {
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
    private void registrar() {
        String email  = txtEmail.getText();
        String pass   = txtPass.getText();

        if (email.isBlank() || pass.isBlank()) {
            lblError.setText("Todos los campos son obligatorios");
            return;
        }

        if (!emailValido(email)) {
            lblError.setText("Email no válido");
            return;
        }

        for (Cliente c : cine.getClientes()) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                lblError.setText("El email ya está registrado");
                return;
            }
        }

        Cliente nuevo = new Cliente(email, pass);
        cine.agregarCliente(nuevo);
        repo.guardar(cine);

        
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Registro");
        alert.setHeaderText(null);
        alert.setContentText("Registro exitoso. Ya podés iniciar sesión.");
        alert.showAndWait();

        volverAlLogin();
    }

    private boolean emailValido(String email) {
        return email.contains("@") && email.contains(".") && email.indexOf('@') < email.lastIndexOf('.');
    }

    @FXML
    private void volverAlLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/vista/Login.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            LoginController loginController = loader.getController();
            loginController.inicializar(cine, repo);

            Stage stage = (Stage) txtEmail.getScene().getWindow();
            utils.UiConfig.aplicarCss(scene);

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
