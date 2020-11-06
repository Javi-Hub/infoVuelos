package com.sanvalero.infoVuelos;

import com.sanvalero.infoVuelos.DAO.VueloDAO;
import com.sanvalero.infoVuelos.domain.Vuelo;
import com.sanvalero.infoVuelos.util.R;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Creado por @author: Javier
 * el 01/11/2020
 */
public class AppController implements Initializable {

    public TextField tfCodigo;
    public TextField tfOrigen;
    public TextField tfDestino;
    public TextField tfOperadora;
    public TextField tfFecha;
    public ComboBox<String> cbClase;
    public TableView<Vuelo> tvLista;
    public TableColumn<Vuelo, String> tcCodigo;
    public TableColumn<Vuelo, String> tcOrigen;
    public TableColumn<Vuelo, String> tcDestino;
    public TableColumn<Vuelo, String> tcOperadora;
    public TableColumn<Vuelo, String> tcFecha;
    public TableColumn<Vuelo, String> tcClase;
    public Label lbEstado;
    public ObservableList<Vuelo> listaVuelos;
    public Image image;
    public ImageView imageLogo;

    private VueloDAO vueloDAO;
    private Alert alertWarning = new Alert(Alert.AlertType.WARNING);
    private Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
    private Optional<ButtonType> action;

    public AppController(){
        vueloDAO = new VueloDAO();
        vueloDAO.conectar();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList("First Class", "Bussiness", "Premium Economy", "Economy" );
        cbClase.setItems(items);
        cargarLogo();
        cargarTableView();
    }

    @FXML
    public void cargarLogo(){

        String logo = tfOperadora.getText().toLowerCase();
        condicionalSwitch(logo);

      tfOperadora.focusedProperty().addListener(new ChangeListener<Boolean>() {
          @Override
          public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
               if (oldValue){
                   String logo = tfOperadora.getText().toLowerCase();
                   condicionalSwitch(logo);
               }
          }
      });


    }

    @FXML
    public void configTableView(){
        tcCodigo.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("codigo"));
        tcOrigen.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("origen"));
        tcDestino.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("destino"));
        tcOperadora.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("operadora"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("fecha"));
        tcClase.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("clase"));
    }

    @FXML
    public void cargarTableView(){
        listaVuelos = FXCollections.observableArrayList(vueloDAO.listarVuelos());
        tvLista.setItems(listaVuelos);
        configTableView();
    }

    @FXML
    public void nuevoCodigo(Event event){
        Vuelo vuelo = new Vuelo();
        vuelo.setCodigo();
        tfCodigo.setText(vuelo.getCodigo());
    }

    @FXML
    public void resetFormulario(Event event){
        tfCodigo.setText("");
        tfOrigen.setText("");
        tfDestino.setText("");
        tfOperadora.setText("");
        tfFecha.setText("");
        cbClase.setValue(null);
        lbEstado.setText("");
        imageLogo.setImage(null);
    }

    @FXML
    public void guardarVuelo(Event event){
        String codigo = tfCodigo.getText();
        if (codigo.equals("")){
            alertWarning.setTitle("ATENCIÓN");
            alertWarning.setContentText("Debe generar un código para el vuelo");
            alertWarning.show();
        } else {
            String origen = tfOrigen.getText();
            String destino = tfDestino.getText();
            String operadora = tfOperadora.getText();
            String fecha = tfFecha.getText();
            String clase = cbClase.getSelectionModel().getSelectedItem();
            Vuelo vuelo = new Vuelo(codigo, origen, destino, operadora, fecha, clase);
            alertConfirm.setTitle("Confirmacion");
            alertConfirm.setContentText("¿Está seguro de guardar los datos?");
            action = alertConfirm.showAndWait();
            if (action.get() == ButtonType.OK){
                vueloDAO.guardarVuelo(vuelo);
                lbEstado.setText("Registro guardado correctamente");
            }
            cargarTableView();
        }
    }

    @FXML
    public void modificarVuelo(Event event){
        String codigo = tfCodigo.getText();
        if (codigo.equals("")){
            alertWarning.setTitle("ATENCIÓN");
            alertWarning.setContentText("Introduzca el código de vuelo que desea modificar");
            alertWarning.show();
        } else{
            String origen = tfOrigen.getText();
            String destino = tfDestino.getText();
            String operadora = tfOperadora.getText();
            String fecha = tfFecha.getText();
            String clase = cbClase.getSelectionModel().getSelectedItem();
            Vuelo vuelo = new Vuelo(codigo, origen, destino, operadora, fecha, clase);
            alertConfirm.setTitle("Confirmación");
            alertConfirm.setContentText("¿Está seguro de guardar los cambios?");
            action = alertConfirm.showAndWait();
            if (action.get() == ButtonType.OK){
                vueloDAO.modificarVuelo(vuelo);
                lbEstado.setText("El vuelo ha sido modificado correctamente");
            }
            cargarTableView();
        }
    }

    @FXML
    public void eliminarVuelo(Event event){
        String codigo = tfCodigo.getText();
        if (codigo.equals(null)){
            alertWarning.setTitle("ATENCIÓN");
            alertWarning.setContentText("Introduzca el código del vuelo que desea eliminar");
            alertWarning.show();
        } else {
            Vuelo vuelo = new Vuelo(codigo);
            alertConfirm.setTitle("Confimación");
            alertConfirm.setContentText("¿Está seguro de eliminar el registro seleccionado?");
            action = alertConfirm.showAndWait();
            if (action.get() == ButtonType.OK){
                vueloDAO.eliminarVuelo(vuelo);
                lbEstado.setText("El vuelo ha sido eliminado correctamente");
            }
            cargarTableView();
        }
    }

    @FXML
    public void obtenerVuelos(Event event){
        tfCodigo.setText(tvLista.getSelectionModel().selectedItemProperty().getValue().getCodigo());
        tfOrigen.setText(tvLista.getSelectionModel().selectedItemProperty().getValue().getOrigen());
        tfDestino.setText(tvLista.getSelectionModel().selectedItemProperty().getValue().getDestino());
        tfOperadora.setText(tvLista.getSelectionModel().selectedItemProperty().getValue().getOperadora());
        tfFecha.setText(tvLista.getSelectionModel().selectedItemProperty().getValue().getFecha());
        cbClase.setValue(String.valueOf(tvLista.getSelectionModel().selectedItemProperty().getValue().getClase()));
        cargarLogo();
    }

    @FXML
    public void condicionalSwitch(String name){
        switch (name){
            case "iberia":
                image = new Image(R.getImage("iberia.png"));
                imageLogo.setImage(image);
                break;
            case "alitalia":
                image = new Image(R.getImage("alitalia.png"));
                imageLogo.setImage(image);
                break;
            case "airfrance":
                image = new Image(R.getImage("airfrance.png"));
                imageLogo.setImage(image);
                break;
        }
    }

}
