package com.sanvalero.infoVuelos.controller;

import com.sanvalero.infoVuelos.DAO.LoginDAO;
import com.sanvalero.infoVuelos.domain.Login;
import com.sanvalero.infoVuelos.util.AlertUtils;
import com.sanvalero.infoVuelos.util.R;
import com.sun.jdi.event.StepEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Creado por @author: Javier
 * el 03/12/2020
 */
public class LoginController {

    public TextField tfLoginUsuario;
    public PasswordField pfLoginPassword;

    private LoginDAO loginDAO;
    private Optional<ButtonType> action;

    public LoginController(){
        loginDAO = new LoginDAO();
        try {
            loginDAO.conectar();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void login(ActionEvent event){
        String nombre = tfLoginUsuario.getText();
        String password = pfLoginPassword.getText();
        //Login login = new Login(nombre, password);

        try {
            if (!loginDAO.existeUsuario(nombre, password)){
                AlertUtils.mostrarError("Nombre Usuario y/o contraseña incorrectos");
                return;
            }

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("infoVuelos.fxml"));
            loader.setController(new AppController());
            VBox vBox = loader.load();
            Scene scene = new Scene(vBox);
            stage.setScene(scene);
            stage.show();
            cerrarVentana(event);

        } catch (SQLException | IOException sqlException) {
            sqlException.printStackTrace();
        }
    }
    @FXML
    public void volver(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("index.fxml"));
        loader.setController(new UsuarioController());
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        cerrarVentana(event);
    }

    public void cerrarVentana(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
