package com.sanvalero.infoVuelos;

import com.sanvalero.infoVuelos.DAO.VueloDAO;
import com.sanvalero.infoVuelos.domain.Vuelo;
import com.sanvalero.infoVuelos.util.AlertUtils;
import com.sanvalero.infoVuelos.util.R;
import com.sun.net.httpserver.Headers;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Creado por @author: Javier
 * el 01/11/2020
 */
public class AppController implements Initializable {

    public TextField tfCodigo, tfOrigen, tfDestino, tfOperadora, tfFecha;
    public ComboBox<String> cbClase;
    public TableView<Vuelo> tvLista;
    public TableColumn<Vuelo, String> tcCodigo, tcOrigen, tcDestino, tcOperadora, tcFecha, tcClase;
    public Button btReset, btGuardar, btModificar, btEliminar;
    public Label lbEstado;
    public ObservableList<Vuelo> listaVuelos;
    public Image image;
    public ImageView imageLogo;

    private VueloDAO vueloDAO;
    private Optional<ButtonType> action;

    public AppController() {
        vueloDAO = new VueloDAO();
        try {
            vueloDAO.conectar();
        } catch (ClassNotFoundException cnfe){
            AlertUtils.mostrarError("ERROR al cargar la aplicación");
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("ERROR al conectar con la Base de Datos");
        } catch (IOException io){
            AlertUtils.mostrarError("ERRROR al conectar con la Base de Datos");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList("<Seleccione Tipo>","First Class", "Bussiness", "Premium Economy", "Economy" );
        cbClase.setItems(items);
        cargarLogo();
        try {
            cargarTableView();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @FXML
    public void resetFormulario(Event event){
        limpiarCajas();
        modoEdicion(true);
    }


    @FXML
    public void guardarVuelo(Event event) throws SQLException{
        String codigo = tfCodigo.getText();
        if (codigo.equals("")){
            AlertUtils.mostrarError("Debe generar un código para el vuelo");
            return;
        }
        try {
                String origen = tfOrigen.getText();
                String destino = tfDestino.getText();
                String operadora = tfOperadora.getText();
                String fecha = tfFecha.getText();
                String clase = cbClase.getSelectionModel().getSelectedItem();
                Vuelo vuelo = new Vuelo(codigo, origen, destino, operadora, fecha, clase);
                action = AlertUtils.mostrarConfirmacion("Está seguro de guardar los datos introducidos");
                if (action.get() == ButtonType.OK){
                    if (vueloDAO.existeVuelo(codigo)){
                        AlertUtils.mostrarError("El código ya está asignado a un vuelo");
                        return;
                    }
                    vueloDAO.guardarVuelo(vuelo);
                    lbEstado.setText("Registro guardado correctamente");
                    cargarTableView();
                    //limpiarCajas();
                    modoEdicion(false);
                }

        } catch (SQLException sqle) {
                AlertUtils.mostrarError("ERROR al guardar el vuelo");
        }
    }

    @FXML
    public void modificarVuelo(Event event) throws SQLException{
        String codigo = tfCodigo.getText();
        if (codigo.equals("")){
            AlertUtils.mostrarError("Introduzca el código de vuelo que desea modificar");
            return;
        }
        try {
                String origen = tfOrigen.getText();
                String destino = tfDestino.getText();
                String operadora = tfOperadora.getText();
                String fecha = tfFecha.getText();
                String clase = cbClase.getSelectionModel().getSelectedItem();
                Vuelo vuelo = new Vuelo(codigo, origen, destino, operadora, fecha, clase);
                action = AlertUtils.mostrarConfirmacion("Está seguro de modificar los cambios");
                    if (action.get() == ButtonType.OK){
                    vueloDAO.modificarVuelo(vuelo);
                    lbEstado.setText("El vuelo ha sido modificado correctamente");
                    cargarTableView();
                    }

        } catch (SQLException sqle) {
            AlertUtils.mostrarError("No se ha podido modificar el vuelo");
        }
    }

    @FXML
    public void eliminarVuelo(Event event){

        String codigo = tfCodigo.getText();
        if (codigo.equals("")){
            AlertUtils.mostrarError("Introduzca el código del vuelo que desea eliminar");
            return;
        }
        try{
                Vuelo vuelo = new Vuelo(codigo);
                action = AlertUtils.mostrarConfirmacion("¿Está seguro de eliminar el registro seleccionado?");
                if (action.get() == ButtonType.OK){
                    vueloDAO.eliminarVuelo(vuelo);
                    lbEstado.setText("El vuelo ha sido eliminado correctamente");
                }
                cargarTableView();
        } catch (SQLException sqle){
            AlertUtils.mostrarError("No se ha podido eliminar el vuelo");
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
    public void importar(ActionEvent event){


    }

    @FXML
    public void exportar(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            File fichero = fileChooser.showSaveDialog(null);
            FileWriter fileWriter = new FileWriter(fichero);

            /*CSVPrinter printer = new CSVPrinter(fileWriter,
                    CSVFormat.newFormat(';').withHeader("Código", "Origen", "Destino", "Operadora", "Fecha", "Clase"));*/

            CSVPrinter printer = CSVFormat.TDF.withHeader("Código;", "Origen;", "Destino;", "Operadora;", "Fecha;", "Clase;").print(fileWriter);
            List<Vuelo> vuelos = vueloDAO.listarVuelos();
            for (Vuelo vuelo : vuelos) {
                printer.printRecord(vuelo.getCodigo(),";", vuelo.getOrigen(), ";", vuelo.getDestino(),
                        ";", vuelo.getOperadora(), ";", vuelo.getFecha(), ";", vuelo.getClase());
            }
            printer.close();
        } catch (SQLException sqle){
            AlertUtils.mostrarError("ERROR al importar los datos");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Errror al exportar los datos");
        }

    }

    @FXML
    private void condicionalSwitch(String name){
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
            case "british airways":
                image = new Image(R.getImage("britishairways.png"));
                imageLogo.setImage(image);
                break;
            case "lufthansa":
                image = new Image(R.getImage("lufthansa.png"));
                imageLogo.setImage(image);
                break;
            case "emirates airlines":
                image = new Image(R.getImage("emirates.png"));
                imageLogo.setImage(image);
                break;
            case "american airlines":
                image = new Image(R.getImage("american.png"));
                imageLogo.setImage(image);
                break;
            case "tap portugal":
                image = new Image(R.getImage("tapportugal.png"));
                imageLogo.setImage(image);
                break;
            case "qatar airways":
                image = new Image(R.getImage("qatar.png"));
                imageLogo.setImage(image);
                break;
            case "japan airlines":
                image = new Image(R.getImage("japan.png"));
                imageLogo.setImage(image);
                break;
            case "":
                image = new Image(R.getImage("avion.png"));
                imageLogo.setImage(image);
                break;
        }
    }

    @FXML
    private void cargarLogo(){

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
    private void configTableView(){
        tcCodigo.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("codigo"));
        tcOrigen.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("origen"));
        tcDestino.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("destino"));
        tcOperadora.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("operadora"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("fecha"));
        tcClase.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("clase"));
    }

    @FXML
    private void cargarTableView() throws SQLException{
        listaVuelos = FXCollections.observableArrayList(vueloDAO.listarVuelos());
        tvLista.setItems(listaVuelos);
        configTableView();
    }


    @FXML
    private void nuevoCodigo(Event event){
        Vuelo vuelo = new Vuelo();
        vuelo.setCodigo();
        tfCodigo.setText(vuelo.getCodigo());
    }

    private void limpiarCajas() {
        tfCodigo.setText("");
        tfOrigen.setText("");
        tfDestino.setText("");
        tfOperadora.setText("");
        tfFecha.setText("");
        cbClase.setValue("<Seleccione Tipo>");
        lbEstado.setText("");
        imageLogo.setImage(null);
    }

    private void modoEdicion (boolean activar){
        btReset.setDisable(activar);
        btGuardar.setDisable(!activar);
        btModificar.setDisable(activar);
        btEliminar.setDisable(activar);
        tfCodigo.requestFocus();
    }

}
