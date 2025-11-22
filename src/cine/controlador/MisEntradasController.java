/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.controlador;

import cine.modelo.Butaca;
import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.modelo.Entrada;
import cine.persistencia.Repositorio;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author verra
 */
public class MisEntradasController {

    @FXML
    private VBox rootMisEntradas;

    @FXML
    private ListView<String> listaEntradas;

    @FXML
    private Button btnVolver;

    private Cine cine;
    private Repositorio<Cine> repo;
    private Cliente clienteLogueado;

    private List<Entrada> entradasCliente = new ArrayList<>();

    public void inicializar(Cine cine, Repositorio<Cine> repo, Cliente clienteLogueado) {
        this.cine = cine;
        this.repo = repo;
        this.clienteLogueado = clienteLogueado;

        cargarEntradas();
        configurarDobleClick();
    }

    private void cargarEntradas() {
        if (clienteLogueado == null ||
            clienteLogueado.getEntradas() == null ||
            clienteLogueado.getEntradas().isEmpty()) {

            listaEntradas.setItems(
                    FXCollections.observableArrayList("No tenés entradas compradas.")
            );
            entradasCliente.clear();
            return;
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        entradasCliente = new ArrayList<>(clienteLogueado.getEntradas());

        var items = entradasCliente.stream()
                .map(e -> {
                    String butacas = e.getButacas().stream()
                            .map(Butaca::toString)
                            .collect(Collectors.joining(", "));

                    return String.format(
                            "[%s] %s - Sala %d - Butacas: %s - Total: $ %.2f",
                            e.getFechaHora().format(fmt),
                            e.getPelicula(),
                            e.getNumeroSala(),
                            butacas,
                            e.getTotal()
                    );
                })
                .collect(Collectors.toList());

        listaEntradas.setItems(FXCollections.observableArrayList(items));
    }

    private void configurarDobleClick() {
        listaEntradas.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                int index = listaEntradas.getSelectionModel().getSelectedIndex();
                if (index < 0 || index >= entradasCliente.size()) {
                    return;
                }
                Entrada seleccionada = entradasCliente.get(index);
                abrirTicket(seleccionada);
            }
        });
    }

    private void abrirTicket(Entrada entrada) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/cine/vista/Ticket.fxml")
            );
            Parent root = loader.load();

            TicketController ticketController = loader.getController();

            String textoButacas = entrada.getButacas().stream()
                    .map(Butaca::toString)
                    .sorted()
                    .collect(Collectors.joining(", "));

            String fechaHora = entrada.getFechaHora()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            ticketController.inicializar(
                    entrada.getPelicula(),
                    entrada.getNumeroSala(),
                    textoButacas,
                    entrada.getTotal(),
                    fechaHora
            );

            Stage ticketStage = new Stage();
            ticketStage.initOwner(rootMisEntradas.getScene().getWindow());
            ticketStage.initModality(Modality.WINDOW_MODAL);
            ticketStage.setResizable(false);
            ticketStage.setTitle("Detalle de entrada");

            Scene scene = new Scene(root, 600, 500);
            utils.UiConfig.aplicarCss(scene);
            ticketStage.setScene(scene);

            ticketStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void volverACartelera() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/cine/vista/Cartelera.fxml")
            );
            Parent root = loader.load();

            CarteleraController carteleraController = loader.getController();
            carteleraController.inicializar(cine, repo, clienteLogueado);

            Stage stage = (Stage) rootMisEntradas.getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            utils.UiConfig.aplicarCss(scene);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
