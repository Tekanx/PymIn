/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.TextInputDialog;
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
public class ViewVentaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ArrayList<ProductoVendido> listadoProductos;
    
    /* TableView Components */
    @FXML
    private TableView<ProductoVendido> tvVentaProductos = new TableView<>();
    
    @FXML 
    private TableColumn<ProductoVendido, String> colCodigoProducto = new TableColumn<>("Código Producto");
    
    @FXML
    private TableColumn<ProductoVendido, String> colNombreProducto= new TableColumn<>("Nombre Producto");
    
    @FXML
    private TableColumn<ProductoVendido, Double> colValorProducto= new TableColumn<>("Valor Producto");
    
    @FXML
    private TableColumn<ProductoVendido, Integer> colCantidadProducto= new TableColumn<>("Cantidad Producto");
    
    
    /* END TableView Components */
    
    /* Buttons */
    
    @FXML
    private Button btnAtras;
    
    @FXML
    private Button btnPagoEfectivo;
    
    @FXML
    private Button btnPagoTarjeta;
    
    @FXML
    private Button btnPagoTransferencia;
    
    @FXML
    private Button btnAgregarProducto;
    
    @FXML
    private Button btnEliminarProducto;
    
    /* END Buttons*/
    
    /* Labels */
    
    @FXML 
    private Label labelPrecioTotal = new Label("$0");
    
    /* END Labels */
    
    @FXML
    private void eventKey(KeyEvent event){
        
    }
    
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();       
        
        if(evt.equals(btnAtras)){
            loadStage("/view/ViewIngreso.fxml", event);
        }
        
        if(evt.equals(btnPagoEfectivo)){
            //generarBoleta("Efectivo");
            loadStage("/view/ViewVoucher.fxml", event);
        }
        
        if(evt.equals(btnPagoTarjeta)){
            //generarBoleta("Tarjeta");
            loadStage("/view/ViewVoucher.fxml", event);
        }
        
        if(evt.equals(btnPagoTransferencia)){
            //TO DO Confirmación de Pago realizado
            //generarBoleta("Transferencia");
            loadStage("/view/ViewVoucher.fxml", event);
        }
        
        if(evt.equals(btnAgregarProducto)){
            ProductoVendido producto = new ProductoVendido();
 
            if(addProductoAVender(listadoProductos, producto)){
                loadTableView(listadoProductos);
                labelPrecioTotal.setText(calculateVenta(listadoProductos));
            }else{
                JOptionPane.showConfirmDialog(null, "Producto no encontrado", "¡Error!", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if(evt.equals(btnEliminarProducto)){
            if(removeProductoAVender(listadoProductos)){
                loadTableView(listadoProductos);
                labelPrecioTotal.setText(calculateVenta(listadoProductos));
            }else{
                JOptionPane.showConfirmDialog(null, "Producto no encontrado", "¡Error!", JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Boleta boleta = new Boleta();
        listadoProductos = new ArrayList<ProductoVendido>();
        colCodigoProducto.setCellValueFactory(new PropertyValueFactory<ProductoVendido,String>("codigo"));
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<ProductoVendido,String>("nombre"));
        colValorProducto.setCellValueFactory(new PropertyValueFactory<ProductoVendido,Double>("precio"));
        colCantidadProducto.setCellValueFactory(new PropertyValueFactory<ProductoVendido,Integer>("cantidad"));
        
        
    }    
    
    private Boolean addProductoAVender(ArrayList<ProductoVendido> listado, ProductoVendido producto){
        int cantidad;
        String codigo = JOptionPane.showInputDialog(null, "Ingrese código de Producto", "Agregar Producto a venta", JOptionPane.PLAIN_MESSAGE);
        if(DataBase.getProducto(codigo) != null || !codigo.equals("")) {
                producto.setProducto(DataBase.getProducto(codigo));
                do{
                    cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad del Producto", "Cantidad a vender", JOptionPane.PLAIN_MESSAGE)); 
                    
                    if(!producto.compatibilidadStock(cantidad)){
                        JOptionPane.showConfirmDialog(null, "No hay suficiente stock! su stock es: " + producto.getStock(), "¡Error!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                while(!producto.compatibilidadStock(cantidad));
                
            } else {
                return false;
            }
        listado.add(producto);
        return true;
    }
    
    private Boolean removeProductoAVender(ArrayList<ProductoVendido> listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void loadTableView(ArrayList<ProductoVendido> listado){
        ObservableList dataProductos = FXCollections.observableList(listado);
        tvVentaProductos.setItems(dataProductos);
    }
    
    private String calculateVenta(ArrayList<ProductoVendido> listado){
        String valor = "0";
        Double precio = 0.0;
        
        for(ProductoVendido producto : listado){
            precio += producto.getPrecioTotal();
        }
        
        return valor = Double.toString(precio);
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

    private void generarBoleta(String medioPago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String vistaScanner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
