/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author verra
 */
public class Entrada implements Serializable {

    private String pelicula;
    private int numeroSala;
    private List<Butaca> butacas;
    private double total;
    private LocalDateTime fechaHora;

    public Entrada(String pelicula, int numeroSala, List<Butaca> butacas, double total, LocalDateTime fechaHora) {
        this.pelicula = pelicula;
        this.numeroSala = numeroSala;
        this.butacas = butacas;
        this.total = total;
        this.fechaHora = fechaHora;
    }

    

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public List<Butaca> getButacas() {
        return butacas;
    }

    public void setButacas(List<Butaca> butacas) {
        this.butacas = butacas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    
}