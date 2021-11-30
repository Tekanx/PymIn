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
    Boleta boleta = new Boleta();
    
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
    private Label labelPrecioTotal = new Label("0");
    
    /* END Labels */
    
    @FXML
    private void eventKey(KeyEvent event){
        
    }
    
    @FXML
    private void eventAction(ActionEvent event) throws CloneNotSupportedException{
        Object evt = event.getSource();       
        
        if(evt.equals(btnAtras)){
            loadStage("/view/ViewIngreso.fxml", event);
        }
        
        if(evt.equals(btnPagoEfectivo)){
            generarBoleta("Efectivo");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        
        if(evt.equals(btnPagoTarjeta)){
            generarBoleta("Tarjeta");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        
        if(evt.equals(btnPagoTransferencia)){
            //TO DO Confirmación de Pago realizado
            generarBoleta("Transferencia");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        
        if(evt.equals(btnAgregarProducto)){
            if(addProductoAVender(listadoProductos)){
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
        listadoProductos = new ArrayList<ProductoVendido>();
        
    }    
    
    private Boolean addProductoAVender(ArrayList<ProductoVendido> listado) throws CloneNotSupportedException{
        ProductoVendido producto = new ProductoVendido();
        int cantidad;
        String codigo = JOptionPane.showInputDialog(null, "Ingrese código de Producto", "Agregar Producto a venta", JOptionPane.PLAIN_MESSAGE);
        boolean existe=false;
        int i=0;  
        if(DataBase.getProducto(codigo) != null || !codigo.equals("")) {
            Producto productoDB;
            productoDB = DataBase.getProducto(codigo);
            producto.setProducto(productoDB);         
            for(ProductoVendido prod: listado){
                if(prod.getCodigoP().equals(producto.getCodigoP())){
                    existe=true;
                    producto.setCantidad(prod.getCantidad());
                    break;
                }
                i++;
            }        
            do{
                cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad del Producto", "Cantidad a vender", JOptionPane.PLAIN_MESSAGE)); 
                if(existe){
                    cantidad+=producto.getCantidad();
                }    
                if(!producto.compatibilidadStock(cantidad)){
                    JOptionPane.showConfirmDialog(null, "No hay suficiente stock! su stock es: " + producto.getStock(), "¡Error!", JOptionPane.WARNING_MESSAGE);
                }
            }
            while(!producto.compatibilidadStock(cantidad));     
            } else {
                return false;
            }
        /* Para actualizar producto existente en el listado, TO DO, no funca :(
        for(ProductoVendido prod : listado){
            if(prod.getCodigoP().equals(codigo)){
                producto.setCantidad(producto.getCantidad() + cantidad);
                listado.remove(prod);
                listado.add(prod);
                return true;
            }
        }
        */
         
           
        if(existe){
            /*System.out.println("\n\n\n "+i+"\n\n\n");
            listadoProductos.remove(i);*/
            listado.get(i).setCantidad(cantidad);
            tvVentaProductos.refresh();
        }else{
            producto.setCantidad(cantidad);
            listado.add(producto);
            
        }
        return true;
    }
    
    private Boolean removeProductoAVender(ArrayList<ProductoVendido> listado) {
        ProductoVendido prodVenta = new ProductoVendido();
        String codigo = JOptionPane.showInputDialog(null, "Ingrese código de Producto a remover de la venta", "Remover Producto de venta", JOptionPane.PLAIN_MESSAGE);

        for(int i = 0 ; i < listado.size() ; i++){
            prodVenta = listado.get(i);
            if(prodVenta.getCodigoP().equals(codigo)){
                listado.remove(i);
                return true;
            }
        }
        return false;
    }
    
    private void loadTableView(ArrayList<ProductoVendido> listado){
        try{
            colCodigoProducto.setCellValueFactory(new PropertyValueFactory<>("codigoP"));
            colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreP"));
            colValorProducto.setCellValueFactory(new PropertyValueFactory<>("precioP"));
            colCantidadProducto.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

            ObservableList dataProductos = FXCollections.observableList(listado);
            tvVentaProductos.setItems(dataProductos);                
        }catch(Exception Ex){
            Ex.printStackTrace();
        }
    }
    
    private String calculateVenta(ArrayList<ProductoVendido> listado){
        String valor = "0";
        Double precio = 0.0;
        
        for(ProductoVendido producto : listado){
            precio += producto.getTotalParcial();
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
    
    private void loadStage(Parent url){
        try{
            Stage stage = new Stage();
           
            Scene scene = new Scene(url);
            stage.setScene(scene);
            stage.show();
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error de carga de Escena: \n" + ex,"ERROR DE CARGA",JOptionPane.WARNING_MESSAGE);
        }
    } 

    private void generarBoleta(String medioPago) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewVoucher.fxml"));
            Parent root = loader.load();
            ViewVoucherController voucherController = loader.getController();
            
            for(ProductoVendido producto : listadoProductos){
                boleta.addProductoVendido(producto.getProducto(), producto.getCantidad());
            }
            boleta.setMedioPago(medioPago);
            boleta.setTotalVenta(Double.parseDouble(labelPrecioTotal.getText()));
            boleta.setFecha(LocalDate.now());
            boleta.setHora(LocalTime.now());
            boleta.setId(DataBase.getUltimoIdBoleta() + 1);
            DataBase.addBoleta(boleta);
            voucherController.loadBoleta(boleta);
            
            loadStage(root);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error de carga de Escena: \n" + ex,"ERROR DE CARGA",JOptionPane.WARNING_MESSAGE);
        }
        
    }


}
