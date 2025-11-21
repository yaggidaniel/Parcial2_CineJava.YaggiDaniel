/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.modelo;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author verra
 */
public class Cliente extends Persona implements Serializable {
    
    private String contraseniaHasheada;

    public Cliente() {
    }

    public Cliente(String email,  String contraseniaEnPlano) {
        super(email);
        this.contraseniaHasheada = generarHash(contraseniaEnPlano);
    }

    public String getContraseniaHasheada() {
        return contraseniaHasheada;
    }

    public void setContraseniaHasheada(String contraseniaHasheada) {
        this.contraseniaHasheada = contraseniaHasheada;
    }
    
    private String generarHash(String texto) {
        if(texto == null){
            return null;
        }
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(texto.getBytes());
            StringBuilder hash = new StringBuilder();
            
            for (byte b: bytes){
                hash.append(String.format("%02x", b));
                
            }
            return hash.toString();
            
           
        } catch (NoSuchAlgorithmException  e) {
            throw new RuntimeException("Error al generar el hash", e);
        }
        
    }
    
    
    public void establecerNuevaContrasenia(String nuevaContrasenia) {
        this.contraseniaHasheada = generarHash(nuevaContrasenia);
    }
    
    
    public boolean credencialesValidas (String emailIngresado, String contraseniaIngresada){
        if (emailIngresado == null || contraseniaIngresada == null) {
            return false;
        } else {
            String hashIngresado = generarHash(contraseniaIngresada);
            
            boolean emailCoincide = emailIngresado.equalsIgnoreCase(getEmail());
            boolean contraseniaCoincide = hashIngresado.equals(contraseniaHasheada);
            
            return emailCoincide && contraseniaCoincide;
            
        }
    }

    
    
}
