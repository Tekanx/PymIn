/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static main.main.DataBase;
import model.Boleta;
import model.ProductoVendido;

/**
 * FXML Controller class
 *
 * @author Tekan
 */
public class ViewReporteVentaController implements Initializable {
    
    /* TableView Components */
    @FXML
    private TableView<ProductoVendido> tvReporte = new TableView<>();
    
    @FXML 
    private TableColumn<ProductoVendido, String> colCodigoProd = new TableColumn<>("Código Producto");
    
    @FXML
    private TableColumn<ProductoVendido, String> colNombreProd= new TableColumn<>("Nombre Producto");
    
    @FXML
    private TableColumn<ProductoVendido, Integer> colCantidadProd= new TableColumn<>("Cantidad Producto");
    
    @FXML
    private TableColumn<ProductoVendido, Double> colTotalProd= new TableColumn<>("Total Producto");
    
    
    /* END TableView Components */
    
    

    /* Buttons*/
    @FXML
    private Button btnVolverMenu;
    
    @FXML
    private Button btnBuscarFecha;
    
    /* END Buttons*/
    
    /* Labels */
    
    @FXML 
    private Label  labelTotalRecaudado= new Label("0");
    
    @FXML 
    private Label  labelEfectivo= new Label("0");
    
    @FXML 
    private Label  labelTarjeta= new Label("0");
    
    @FXML 
    private Label  labelTransferencia= new Label("0");
    
    /* END Labels */
    
    
    /* DatePicker */
    @FXML
    private DatePicker datePInicio;
    
    @FXML
    private DatePicker datePTermino;
    
    /* END DatePicker */
    
    
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();       
        
        if(evt.equals(btnVolverMenu)){
            loadStage("/view/ViewIngreso.fxml", event);
        }
        
        if(evt.equals(btnBuscarFecha)){
            if((datePInicio.getValue().toString().equals(""))||(datePTermino.getValue().toString().equals(""))){ //no se si funciona esto :/
                JOptionPane.showMessageDialog(null, "¡Fecha no ingresada! Debe ingresar ambas fechas", "Sin Fechas", JOptionPane.ERROR_MESSAGE);
            }else{
                generarReporte();
            }
        }
        
    }
    
    
    private void loadStage(String url, Event event){
        try{
            Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Error de carga de Escena: \n" + ex,"ERROR DE CARGA",JOptionPane.WARNING_MESSAGE);
        }         
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void generarReporte(){
        LocalDate inicio = datePInicio.getValue();
        /*LocalDate fin = datePTermino.getValue();
        ArrayList<Boleta> boletas = DataBase.getBoletasRange(inicio, fin);*/
        //falta trabajar con las boletas <-----------------------------------
        
        //labelTarjeta.setText(inicio.toString());
    }
    
    
    private void loadTableView(ArrayList<ProductoVendido> listado){
        try{
            colCodigoProd.setCellValueFactory(new PropertyValueFactory<>("codigoP"));
            colNombreProd.setCellValueFactory(new PropertyValueFactory<>("nombreP"));    
            colCantidadProd.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            colTotalProd.setCellValueFactory(new PropertyValueFactory<>("totalParcial"));

            ObservableList dataProductos = FXCollections.observableList(listado);
            tvReporte.setItems(dataProductos);                
        }catch(Exception Ex){
            Ex.printStackTrace();
        }
    }
}