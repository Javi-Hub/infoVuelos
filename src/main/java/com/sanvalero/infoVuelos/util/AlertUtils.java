package com.sanvalero.infoVuelos.util;

import javafx.scene.control.Alert;

/**
 * Creado por @author: Javier
 * el 09/11/2020
 */
public class AlertUtils {

    public static void mostrarError(String mensaje){
        Alert alertWarning = new Alert(Alert.AlertType.WARNING);
        alertWarning.setTitle("ALERTA");
        alertWarning.setHeaderText("ATENCIÓN");
        alertWarning.setContentText(mensaje);
        alertWarning.show();
    }

}
