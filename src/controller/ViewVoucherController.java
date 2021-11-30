/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static main.main.DataBase;
import model.Boleta;
import model.Producto;
import model.ProductoVendido;

/**
 * FXML Controller class
 *
 * @author Tekan
 */
public class ViewVoucherController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnVolverVentas;
    
    @FXML 
    private Label labelIdBoleta;
    
    @FXML
    private Label labelFecha;
    
    @FXML 
    private Label labelHora;
    
    @FXML 
    private Label labelTotalBoleta;
    
    @FXML
    private TableView<ProductoVendido> tvProdVendidos = new TableView();
    
    @FXML
    private TableColumn<ProductoVendido, Integer> colCantidadProd = new TableColumn();
    
    @FXML
    private TableColumn<ProductoVendido, String> colNombreProd = new TableColumn();
    
    @FXML
    private TableColumn<ProductoVendido, Double> colTotalParcialProd = new TableColumn();
    
     @FXML
    private void eventKey(KeyEvent event){
        
    }
    
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();       
        
        if(evt.equals(btnVolverVentas)){
            loadStage("/view/ViewVenta.fxml", event);
        }
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    private void loadStage(String url, Event event){
        try{
            Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            /*
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            */
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Error de carga de Escena: \n" + ex,"ERROR DE CARGA",JOptionPane.WARNING_MESSAGE);
        }
    } 
    
    public void loadBoleta(Boleta boleta){
        labelIdBoleta.setText(Integer.toString(boleta.getId()));
        labelTotalBoleta.setText("$"+Double.toString(boleta.getTotalVenta()));
        LocalDate hoy = boleta.getFecha();
        LocalTime hora = boleta.getHora();
        labelFecha.setText(hoy.getDayOfMonth() + "/" + hoy.getMonthValue() + "/" + hoy.getYear());
        labelHora.setText(hora.getHour() + ":" + hora.getMinute());
        try{
            ArrayList<ProductoVendido> productos = new ArrayList();
            productos = boleta.getListaProductos();
            colCantidadProd.setCellValueFactory(new PropertyValueFactory<ProductoVendido,Integer>("cantidad"));
            colNombreProd.setCellValueFactory(new PropertyValueFactory<ProductoVendido,String>("nombreP"));
            colTotalParcialProd.setCellValueFactory(new PropertyValueFactory<ProductoVendido,Double>("precioP"));
            
            ObservableList dataProductos = FXCollections.observableList(productos); 
            tvProdVendidos.setItems(dataProductos);
        }catch(Exception SQLEx){
            SQLEx.printStackTrace();
        }
    }
}
