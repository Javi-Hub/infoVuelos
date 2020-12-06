package com.sanvalero.infoVuelos;

import com.sanvalero.infoVuelos.controller.AppController;
import com.sanvalero.infoVuelos.controller.UsuarioController;
import com.sanvalero.infoVuelos.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Creado por @author: Javier
 * el 01/11/2020
 */
public class App extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar la ventana de inicio para registro de usuario o loggear usuario
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("index.fxml"));
        loader.setController(new UsuarioController());
        VBox vBox = loader.load();
        primaryStage.setTitle("InfoVuelos");
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception{
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
