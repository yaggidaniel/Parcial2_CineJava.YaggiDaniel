/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author verra
 */
public class TicketController {

    @FXML
    private Label lblPelicula;

    @FXML
    private Label lblSala;

    @FXML
    private Label lblButacas;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblFecha;

    @FXML
    private Button btnCerrar;

    public void inicializar(String pelicula,
                            int numeroSala,
                            String butacas,
                            double total,
                            String fechaHora) {

        lblPelicula.setText("Película: " + pelicula);
        lblSala.setText("Sala: " + numeroSala);
        lblButacas.setText("Butacas: " + butacas);
        lblTotal.setText(String.format("Total: $ %.2f", total));
        lblFecha.setText("Fecha y hora: " + fechaHora);
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}