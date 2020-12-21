package com.sanvalero.infoVuelos;

import com.sanvalero.infoVuelos.controller.AppController;
import com.sanvalero.infoVuelos.controller.UsuarioController;
import com.sanvalero.infoVuelos.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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

        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("infoVuelos.fxml"));
        loader.setController(new AppController());
        VBox vBox = loader.load();
        primaryStage.setTitle("InfoVuelos");
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();

        Stage stage = new Stage();
        FXMLLoader loaderSecondary = new FXMLLoader();
        loaderSecondary.setLocation(R.getUI("index.fxml"));
        loaderSecondary.setController(new UsuarioController());
        VBox vBoxSecondary = loaderSecondary.load();

        Scene sceneSecondary = new Scene(vBoxSecondary);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(sceneSecondary);
        stage.show();
        stage.setOnCloseRequest(t -> primaryStage.close());*/

    }

    @Override
    public void stop() throws Exception{
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
