/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.modelo;

import java.io.Serializable;

/**
 *
 * @author verra
 */
public class Sala implements Serializable {
    
    private int numero;
    private double precioSala;
    private Pelicula pelicula;
    private Butaca[][] butacas;

    public Sala() {
    }

    public Sala(int numero, double precioSala, Pelicula pelicula, Butaca[][] butacas) {
        if (butacas == null || butacas.length == 0 || butacas[0].length == 0) {
            throw new IllegalArgumentException("la matriz de butacas esta vacia.");
        }
        
        this.numero = numero;
        this.precioSala = precioSala;
        this.pelicula = pelicula;
        this.butacas = butacas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPrecioSala() {
        return precioSala;
    }

    public void setPrecioSala(double precioSala) {
        this.precioSala = precioSala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Butaca[][] getButacas() {
        return butacas;
    }

    public void setButacas(Butaca[][] butacas) {
        this.butacas = butacas;
    }
    
    
    public Butaca obtenerButaca(int fila, int columna){
        if (fila < 1 || columna < 1 || fila > butacas.length || columna  > butacas[0].length) {
            
            throw new IndexOutOfBoundsException("coordenadas de butaca fuera de rango");
            
        }
        
        return butacas[fila - 1][columna -1];
    }

     
    
      
    
}
