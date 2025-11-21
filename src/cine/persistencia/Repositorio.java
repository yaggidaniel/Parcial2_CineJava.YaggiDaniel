/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author verra
 */
public class Repositorio <T extends Serializable> {
    
    private String nombreArchivo;

    public Repositorio(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    public T cargar(){
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()){
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            Object obj =ois.readObject();
            return (T) obj;
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar archivo " + nombreArchivo, e);

        }        
    }
    
     public void guardar(T objeto) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            
            System.out.println("Guardando cine.ser en: " + new File(nombreArchivo).getAbsolutePath());

            oos.writeObject(objeto);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar archivo " + nombreArchivo, e);
        }
    }
    
    
}
