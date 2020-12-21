package com.sanvalero.infoVuelos.controller;

import com.sanvalero.infoVuelos.DAO.UsuarioDAO;
import com.sanvalero.infoVuelos.domain.Usuario;
import com.sanvalero.infoVuelos.util.AlertUtils;
import com.sanvalero.infoVuelos.util.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Creado por @author: Javier
 * el 02/12/2020
 */
public class UsuarioController{
    //Declaración de variables de los campos de la ventana
    public TextField tfRegistroNombre, tfRegistroApellidos, tfRegistroNombreUsuario, tfRegistroEmail;
    public Label lbConfirmado;
    public PasswordField pfRegistroPassword;

    private UsuarioDAO usuarioDAO;
    private Optional<ButtonType> action;

    // Conexion con la BBDD
    public UsuarioController(){
        usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.conectar();
        } catch (ClassNotFoundException cnfe){
            AlertUtils.mostrarError("ERROR al cargar la aplicación");
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("ERROR al conectar con la Base de Datos");
        } catch (IOException io){
            AlertUtils.mostrarError("ERRROR al conectar con la Base de Datos");
        }
    }

    //Método para registrar un usuario nuevo
    @FXML
    public void registrarUsuario(ActionEvent event){
        // Recoger datos de los campos rellenados
        String nombre = tfRegistroNombre.getText();
        String apellidos = tfRegistroApellidos.getText();
        String user = tfRegistroNombreUsuario.getText();
        String email = tfRegistroEmail.getText();
        String password = pfRegistroPassword.getText();

        Usuario usuario = new Usuario(nombre, apellidos, user, email, password);
        try {
            action = AlertUtils.mostrarConfirmacion("Confirmación registro");
            if (action.get() == ButtonType.OK){
                if (nombre.equals("") || apellidos.equals("") || user.equals("") || email.equals("") || password.equals("")){
                    AlertUtils.mostrarError("Debe rellenar todos los campos");
                    return;
                } else if (usuarioDAO.existeUsuario(user)){
                    AlertUtils.mostrarError("El nombre de usuario elegido ya existe");
                    return;
                }
                usuarioDAO.registrarUsuario(usuario);
                lbConfirmado.setText("* Usuario guardado correctamente. Acccede a la aplicación");
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Método para acceder a la ventana de Login en caso de que ya esté registrado
    public void loginRegistro(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setTitle("InfoVuelos");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("login.fxml"));
            loader.setController(new LoginController());
            VBox vBox = loader.load();

            Scene scene = new Scene(vBox);
            stage.setScene(scene);
            stage.show();
            cerrarVentana(event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void cerrarVentana(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
