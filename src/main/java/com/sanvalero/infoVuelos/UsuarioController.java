package com.sanvalero.infoVuelos;

import com.sanvalero.infoVuelos.DAO.UsuarioDAO;
import com.sanvalero.infoVuelos.domain.Usuario;
import com.sanvalero.infoVuelos.util.AlertUtils;
import com.sanvalero.infoVuelos.util.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Creado por @author: Javier
 * el 02/12/2020
 */
public class UsuarioController {

    public TextField tfRegistroNombre, tfRegistroApellidos, tfRegistroNombreUsuario, tfRegistroEmail;
    public PasswordField pfRegistroPassword;
    public Button btRegistrar;

    private UsuarioDAO usuarioDAO;
    private Optional<ButtonType> action;

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

    @FXML
    public void registrarUsuario(ActionEvent event){
        String nombre = tfRegistroNombre.getText();
        String apellidos = tfRegistroApellidos.getText();
        String user = tfRegistroNombreUsuario.getText();
        String email = tfRegistroEmail.getText();
        String password = pfRegistroPassword.getText();
        Usuario usuario = new Usuario(nombre, apellidos, user, email, password);
        try {
            action = AlertUtils.mostrarConfirmacion("Confirmación registro");
            if (action.get() == ButtonType.OK){
                if (nombre.equals("") || apellidos.equals("") || user.equals("") || email.equals("")){
                    AlertUtils.mostrarError("Debe rellenar todos los campos");
                    return;
                } else if (usuarioDAO.existeUsuario(user)){
                    AlertUtils.mostrarError("El nombre de usuario elegido ya existe");
                    return;
                }
                    usuarioDAO.registrarUsuario(usuario);
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(R.getUI("login.fxml"));
                    loader.setController(new LoginController());
                    VBox vBox = loader.load();

                    Scene scene = new Scene(vBox);
                    stage.setScene(scene);
                    stage.show();

                    cerrarVentana(event);

            }

        } catch (SQLException | IOException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void loginRegistro(ActionEvent event){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("login.fxml"));
        loader.setController(new LoginController());
        VBox vBox = null;
        try {
            vBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();

        cerrarVentana(event);
    }

    public void cerrarVentana(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage1 = (Stage) source.getScene().getWindow();
        stage1.close();
    }

}
