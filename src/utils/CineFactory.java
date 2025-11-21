/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import cine.modelo.Butaca;
import cine.modelo.Cine;
import cine.modelo.Pelicula;
import cine.modelo.Sala;

/**
 *
 * @author verra
 */
public class CineFactory {
    public static Cine crearCineDefault() {
        Cine cine = new Cine();

        Pelicula p1 = new Pelicula();
        p1.setTitulo("Cinema Paradiso");
        p1.setGenero("Drama");
        p1.setClasificacionEdad(13);
        p1.setSinopsis("No te dejes llevar por la nostalgia. Olvídate de todos nosotros.");
        p1.setRutaImagen("/cine/vista/recursos/cinema_paradiso.jpg");

        Pelicula p2 = new Pelicula();
        p2.setTitulo("Interstellar");
        p2.setGenero("Ciencia Ficción");
        p2.setClasificacionEdad(13);
        p2.setSinopsis("No entres dócilmente en esa buena nochen.");
        p2.setRutaImagen("/cine/vista/recursos/interstellar.jpg");

        Pelicula p3 = new Pelicula();
        p3.setTitulo("Toy Story");
        p3.setGenero("Animación");
        p3.setClasificacionEdad(0);
        p3.setSinopsis("Al infinito y más allá.");
        p3.setRutaImagen("/cine/vista/recursos/toy_story.jpg");
        
        Pelicula p4 = new Pelicula();
        p4.setTitulo("Gran Torino");
        p4.setGenero("Drama");
        p4.setClasificacionEdad(16);
        p4.setSinopsis("La muerte es agridulce, agria por el dolor y dulce por la salvación. Eso es lo que sabes sobre la vida y la muerte y es patético.");
        p4.setRutaImagen("/cine/vista/recursos/gran_torino.jpg");

        Pelicula p5 = new Pelicula();
        p5.setTitulo("Spider-Man: Into the Spider-Verse");
        p5.setGenero("Superhéroes / Animación");
        p5.setClasificacionEdad(13);
        p5.setSinopsis("Cualquiera puede usar una máscara, pero lo importante es cómo la usas.");
        p5.setRutaImagen("/cine/vista/recursos/spiderverse.jpg");

        Pelicula p6 = new Pelicula();
        p6.setTitulo("Forrest Gump");
        p6.setGenero("Drama");
        p6.setClasificacionEdad(13);
        p6.setSinopsis("La vida es como una caja de bombones, nunca sabes lo que te va a tocar.");
        p6.setRutaImagen("/cine/vista/recursos/forrest_gump.jpg");

        Pelicula p7 = new Pelicula();
        p7.setTitulo("Hackers");
        p7.setGenero("Tecnología / Crimen");
        p7.setClasificacionEdad(13);
        p7.setSinopsis("Un grupo de jóvenes hackers en problemas con una corporación.");
        p7.setRutaImagen("/cine/vista/recursos/hackers.jpg");
        
        Pelicula p8 = new Pelicula();
        p8.setTitulo("Ups!!");
        p8.setGenero("A confirmar");
        p8.setClasificacionEdad(13);
        p8.setSinopsis("No disponible, compra pochoclos");
        p8.setRutaImagen(null);

        String[] esquemaSala1 = {
            "OOOOOOOO",
            "OOOXXOOO",
            "OOOXXOOO",
            "OOOXXOOO"
        };

        String[] esquemaSala2 = {
            "OOXOOXOO",
            "OOXOOXOO",
            "OOXOOXOO",
            "OOXOOXOO",
        };

        String[] esquemaSala3 = {
            "XOOOOOOO",
            "XOOOOOOO",
            "XOOOOOOO",
            "XOOOOOOO"
        };

        Sala sala1 = new Sala(1, 2500.0, p1, crearMatrizButacas(esquemaSala1));
        Sala sala2 = new Sala(2, 2800.0, p2, crearMatrizButacas(esquemaSala2));
        Sala sala3 = new Sala(3, 2000.0, p3, crearMatrizButacas(esquemaSala3));
        Sala sala4 = new Sala(4, 2000.0, p4, crearMatrizButacas(esquemaSala1));
        Sala sala5 = new Sala(5, 2000.0, p5, crearMatrizButacas(esquemaSala2));
        Sala sala6 = new Sala(6, 2000.0, p6, crearMatrizButacas(esquemaSala3));
        Sala sala7 = new Sala(7, 2000.0, p7, crearMatrizButacas(esquemaSala1));
        Sala sala8 = new Sala(8, 2000.0, p8, crearMatrizButacas(esquemaSala1));


        cine.agregarSala(sala1);
        cine.agregarSala(sala2);
        cine.agregarSala(sala3);
        cine.agregarSala(sala4);
        cine.agregarSala(sala5);
        cine.agregarSala(sala6);
        cine.agregarSala(sala7);
        cine.agregarSala(sala8);


        return cine;
    }

    private static Butaca[][] crearMatrizButacas(String[] esquema) {
        int filas = esquema.length;
        int columnas = esquema[0].length();
        Butaca[][] butacas = new Butaca[filas][columnas];

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                char simbolo = esquema[f].charAt(c);
                boolean esPasillo = simbolo == 'X';
                butacas[f][c] = new Butaca(f + 1, c + 1, false, esPasillo);
            }
        }

        return butacas;
    }
}
