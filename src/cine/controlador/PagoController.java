/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.controlador;

import cine.modelo.Butaca;
import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.modelo.Entrada;
import cine.modelo.Sala;
import cine.persistencia.Repositorio;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author verra
 */
public class PagoController {

    @FXML private Label lblPelicula;
    @FXML private Label lblSala;
    @FXML private Label lblButacas;
    @FXML private Label lblTotal;
    @FXML private Button btnConfirmar;
    @FXML private Button btnCancelar;

    private Cine cine;
    private Sala sala;
    private Repositorio<Cine> repo;
    private Set<Butaca> butacasSeleccionadas;
    private Cliente clienteLogueado;

    public void inicializar(Cine cine, Sala sala, Repositorio<Cine> repo, Cliente clienteLogueado, Set<Butaca> butacasSeleccionadas) {

        this.cine = cine;
        this.sala = sala;
        this.repo = repo;
        this.butacasSeleccionadas = butacasSeleccionadas;
        this.clienteLogueado = clienteLogueado;

        lblPelicula.setText("Película: " + sala.getPelicula().getTitulo());
        lblSala.setText("Sala: " + sala.getNumero());

        String textoButacas = butacasSeleccionadas.stream()
                .map(Butaca::toString)
                .sorted()
                .collect(Collectors.joining(", "));

        lblButacas.setText("Butacas: " + textoButacas);

        double total = sala.getPrecioSala() * butacasSeleccionadas.size();
        lblTotal.setText(String.format("Total: $ %.2f", total));
    }

    @FXML
    private void confirmarPago() {
        for (Butaca b : butacasSeleccionadas) {
            if (!b.estaOcupada() && !b.isEsPasillo()) {
                b.ocupar();
            }
        }

        double total = sala.getPrecioSala() * butacasSeleccionadas.size();
        LocalDateTime fechaYhora = LocalDateTime.now();

        Entrada entrada = new Entrada(sala.getPelicula().getTitulo(), sala.getNumero(), new ArrayList<>(butacasSeleccionadas), total, fechaYhora);

        clienteLogueado.agregarEntrada(entrada);
        
        repo.guardar(cine);
        
        mostrarTicket();


        cerrarVentana();
    }

    private void mostrarTicket() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/cine/vista/Ticket.fxml")
            );
            Parent root = loader.load();

            TicketController ticketController = loader.getController();

            String textoButacas = butacasSeleccionadas.stream()
                    .map(Butaca::toString)
                    .sorted()
                    .collect(Collectors.joining(", "));

            double total = sala.getPrecioSala() * butacasSeleccionadas.size();
            String fechaHora = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            ticketController.inicializar(
                    sala.getPelicula().getTitulo(),
                    sala.getNumero(),
                    textoButacas,
                    total,
                    fechaHora
            );

            Stage ticketStage = new Stage();
            ticketStage.initOwner(btnConfirmar.getScene().getWindow());
            ticketStage.initModality(Modality.WINDOW_MODAL);
            ticketStage.setResizable(false);
            ticketStage.setTitle("Tu entrada!!");

            Scene scene = new Scene(root, 600, 500);
            utils.UiConfig.aplicarCss(scene);
            ticketStage.setScene(scene);

            ticketStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}

