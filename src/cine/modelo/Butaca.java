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
public class Butaca implements Serializable {
    
    private int fila;
    private int numero;
    private boolean ocupada;
    private boolean esPasillo;

    public Butaca() {
    }

    public Butaca(int fila, int numero, boolean ocupada, boolean esPasillo) {
        if (fila <= 0 || numero <= 0) {
            throw new IllegalArgumentException("fila y numero deben ser > 0");
        }
        if (esPasillo && ocupada) {
            throw new IllegalStateException("Un pasillo no puede estar ocupado");
        }
        this.fila = fila;
        this.numero = numero;
        this.ocupada = ocupada;
        this.esPasillo = esPasillo;
    }
    
    
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean estaOcupada() {
        return ocupada;
    }

    public void ocupar() {
        if (esPasillo) {
            throw new IllegalStateException("No se puede ocupar un pasillo");
        }
        this.ocupada = true;
    }
    
    public void liberar() {
        if(esPasillo) {
            return;
        }
        this.ocupada = false;
    }

    public boolean isEsPasillo() {
        return esPasillo;
    }

    public void setEsPasillo(boolean esPasillo) {
        this.esPasillo = esPasillo;
    }
    
    
    
    

    @Override
    public String toString() {
        char filaLetra = (char) ('A' + (fila - 1));
        return filaLetra + String.valueOf(numero);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.fila;
        hash = 29 * hash + this.numero;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Butaca other = (Butaca) obj;
        if (this.fila != other.fila) {
            return false;
        }
        return this.numero == other.numero;
    }
    
    
    
}
