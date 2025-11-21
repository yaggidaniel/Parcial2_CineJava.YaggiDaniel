/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author verra
 */
public class Cine implements Serializable {
    
    private List<Sala> salas;
    private List<Cliente> clientes;

    
    public Cine() {
        this.salas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }
    
    
    public Cine(List<Sala> salas, List<Cliente> clientes) {
        this.salas = salas;
        this.clientes = clientes;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public void agregarSala(Sala sala){
        salas.add(sala);
        
    }
    
    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }
        
    
    
}
