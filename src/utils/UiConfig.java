/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javafx.scene.Scene;

/**
 *
 * @author verra
 */
public class UiConfig {
    private static final String CSS_PATH = "/cine/vista/cine.css";

    private UiConfig() {
        // evitar instanciación
    }

    public static void aplicarCss(Scene scene) {
        scene.getStylesheets().add(
            UiConfig.class.getResource(CSS_PATH).toExternalForm()
        );
    }
}
