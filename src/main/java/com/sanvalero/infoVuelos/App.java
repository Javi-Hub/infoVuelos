package com.sanvalero.infoVuelos;

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
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("infoVuelos.fxml"));
        loader.setController(new AppController());
        VBox vBox = loader.load();

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();

        /*Stage stage1 = new Stage();
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(R.getUI("index.fxml"));
        loader.setController(new AppController());
        VBox vBox1 = loader2.load();

        Scene scene2 = new Scene(vBox1);
        stage1.setScene(scene2);
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.show();*/
    }

    @Override
    public void stop() throws Exception{
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
