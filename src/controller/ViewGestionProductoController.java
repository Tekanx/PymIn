/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static main.main.DataBase;
import model.Producto;
/**
 * FXML Controller class
 *
 * @author Tekan
 */
public class ViewGestionProductoController implements Initializable {

    
    
    /* Buttons */
    @FXML
    private Button btnBuscarScan;
    
    @FXML
    private Button btnBuscarManual;
    
    @FXML
    private Button btnEliminarProducto;
    
    @FXML
    private Button btnModificarProd;
    
    @FXML
    private Button btnAgregarProd;
    
    @FXML
    private Button btnVolverGestionI;
    
    @FXML
    private Button btnAgregarStock;
    /*END Buttons */
    @FXML
    private void eventKey(KeyEvent event){
    }
 
    /* Text Field */
    @FXML 
    private TextField tfCodigoProd = new TextField("");
    
    @FXML
    private TextField tfNombreProd = new TextField("");
    
    @FXML
    private TextField tfStockProd = new TextField("");
    
    @FXML
    private TextField tfCostoProd = new TextField("");
    
    @FXML
    private TextField tfPrecioProd = new TextField("");
    
    /* ComboBox*/
    @FXML
    private ComboBox comboxCategoria;
    
    /* TextArea*/
    @FXML
    private TextArea taDescripcionProd = new TextArea("");
    
    
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();       
        
        if(evt.equals(btnVolverGestionI)){
            loadStage("/view/ViewAdministracion.fxml", event);
        }
        
        if(evt.equals(btnBuscarScan)){
            if(tfCodigoProd.getText().equals("")){
                JOptionPane.showMessageDialog(null, "??C??digo no ingresado! Ingrese un codigo de producto", "Sin C??digo", JOptionPane.ERROR_MESSAGE);
            }else{
                mostrarProducto(tfCodigoProd.getText());
            }
        }
        
        if(evt.equals(btnBuscarManual)){
            if(tfCodigoProd.getText().equals("")){
                JOptionPane.showMessageDialog(null, "??C??digo no ingresado! Ingrese un codigo de producto", "Sin C??digo", JOptionPane.ERROR_MESSAGE);
            }else{
                mostrarProducto(tfCodigoProd.getText());
            }
        }
        
        if(evt.equals(btnAgregarProd)){
            if(comprobarCampos()){
               agregarProducto(); 
            }            
        }
        
        if(evt.equals(btnModificarProd)){
            if(comprobarCampos()){
               modificarProducto(); 
            }
        }
        
        if(evt.equals(btnEliminarProducto)){
            if(tfCodigoProd.getText().equals("")){
                JOptionPane.showMessageDialog(null, "??C??digo no ingresado! Ingrese un codigo de producto", "Sin C??digo", JOptionPane.ERROR_MESSAGE);
            }else{
                eliminarProducto(tfCodigoProd.getText());
            }
        }
        if(evt.equals(btnAgregarStock)){
            if(tfCodigoProd.getText().equals("")){
                JOptionPane.showMessageDialog(null, "??C??digo no ingresado! Ingrese un codigo de producto", "Sin C??digo", JOptionPane.ERROR_MESSAGE);
            }else{
                agregarStock(tfCodigoProd.getText());
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
        cargarCategorias();
    }    
    
    
    /*Methods*/
    public void cargarCategorias(){
        ArrayList<String> categorias = DataBase.getCategorias();
        ObservableList dataCategorias = FXCollections.observableList(categorias);
        comboxCategoria.setItems(dataCategorias);
    }
    
    public void mostrarProducto(String codigo){
        Producto producto= DataBase.getProducto(codigo);
        if(producto.getNombre().equals("none")){
            JOptionPane.showConfirmDialog(null, "??C??digo inv??lido! Este c??digo no est?? guardado", "C??digo no existente", JOptionPane.WARNING_MESSAGE);
        }else{
            tfPrecioProd.setText(String.valueOf(producto.getPrecio()));
            tfCostoProd.setText(String.valueOf(producto.getCosto()));
            tfStockProd.setText(String.valueOf(producto.getStock()));
            tfNombreProd.setText(producto.getNombre());
            comboxCategoria.getSelectionModel().select(producto.getCategoria());
            taDescripcionProd.setText(producto.getDescripcion());
            
        }
    }
    
    
    public boolean comprobarCampos(){
        ArrayList<String> faltantes = new ArrayList();
        if(tfCodigoProd.getText().equals("")){
            faltantes.add("-Codigo");
        }
        if(tfNombreProd.getText().equals("")){
            faltantes.add("-Nombre");
        }
        if(tfPrecioProd.getText().equals("")){
          faltantes.add("-Precio");
        }
        if(tfCostoProd.getText().equals("")){
            faltantes.add("-Costo");
        }
        if(tfStockProd.getText().equals("")){
            faltantes.add("-Stock");
        }    
        if(faltantes.isEmpty()){
            return true;
        }else{
            String mensaje="";
            for(String atributo:faltantes){
                mensaje+="\n"+atributo;
            }
            JOptionPane.showConfirmDialog(null, "Complete los campos:"+mensaje, "Informaci??n Incompleta", JOptionPane.WARNING_MESSAGE);
            return false;
        }       
    }
    
    public void agregarProducto(){
        String codigo = tfCodigoProd.getText();
        Producto producto= DataBase.getProducto(codigo);
        if(producto.getNombre().equals("none")){
            String nombre = tfNombreProd.getText();
            double costo =  Double.parseDouble(tfPrecioProd.getText());
            double precio =  Double.parseDouble(tfCostoProd.getText());
            int stock = Integer.valueOf(tfStockProd.getText());
            String categoria = (String)comboxCategoria.getValue();
            String descripcion = taDescripcionProd.getText();
            producto=new Producto(codigo,nombre,costo,precio,stock,categoria,descripcion);
            DataBase.addProducto(producto);
            JOptionPane.showConfirmDialog(null, "??Producto Agregado exitosamente!", "Producto A??adido", JOptionPane.WARNING_MESSAGE);
            vaciarCampos();            
        }else{
           JOptionPane.showConfirmDialog(null, "Este producto ya existe", "Producto existente", JOptionPane.WARNING_MESSAGE); 
        }
    }
    
    public void vaciarCampos(){
        tfCodigoProd.setText("");
        tfPrecioProd.setText("");
        tfCostoProd.setText("");
        tfStockProd.setText("");
        tfNombreProd.setText("");
        comboxCategoria.getSelectionModel().select("Categoria");
        taDescripcionProd.setText("");
    }
     
    
    public void modificarProducto(){
        String codigo = tfCodigoProd.getText();
        Producto producto= DataBase.getProducto(codigo);
        String mensaje="";
        
        if(producto.getNombre().equals("none")){            
            JOptionPane.showConfirmDialog(null, "Este producto no existe", "Producto NO existente", JOptionPane.WARNING_MESSAGE); 
            
        }else{
            String nombre = tfNombreProd.getText();
            double costo =  Double.parseDouble(tfCostoProd.getText());                   
            double precio =  Double.parseDouble(tfPrecioProd.getText());
            int stock = Integer.valueOf(tfStockProd.getText()); //falta lo de stock
            String categoria = (String)comboxCategoria.getValue();
            String descripcion = taDescripcionProd.getText();
            
            if(!producto.getNombre().equals(nombre)){
                mensaje+="\n-Nombre";
                DataBase.updateNombreProducto(codigo, nombre);
            }           
            if(producto.getCosto()!=costo){
                mensaje+="\n-Costo "+producto.getCosto()+costo;
                DataBase.updateCosto(codigo, costo);
            }
            if(producto.getPrecio()!=precio){
                mensaje+="\n-Precio";
                DataBase.updatePrecio(codigo, precio);
            }
            if(!producto.getCategoria().equals(categoria)){
                mensaje+="\n-Categoria";
                DataBase.updateCategoria(codigo, categoria);
            }  
            if(!producto.getDescripcion().equals(descripcion)){
                mensaje+="\n-Descripci??n";
                DataBase.updateDescripcion(codigo, descripcion);
            }        
            if(!mensaje.equals("")){
                JOptionPane.showConfirmDialog(null, "Se han modificado los siguientes atributos:"+mensaje, "Producto Modificado", JOptionPane.WARNING_MESSAGE);
                vaciarCampos(); 
            }
        }      
    }
    
    public void eliminarProducto(String codigo){
        Producto producto= DataBase.getProducto(codigo);
        if(producto.getNombre().equals("none")){            
            JOptionPane.showConfirmDialog(null, "Este producto no existe", "Producto NO existente", JOptionPane.WARNING_MESSAGE);          
        }else{
            DataBase.updateStock(codigo, -(producto.getStock()+1));
            JOptionPane.showConfirmDialog(null, "El producto se ha eliminado\n"+"Este seguir?? existiendo pero no se mostrar?? en el inventario", "Producto Eliminado", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void agregarStock(String codigo){
        Producto producto= DataBase.getProducto(codigo);
        int cantidad;
        if(producto.getNombre().equals("none")){            
            JOptionPane.showConfirmDialog(null, "Este producto no existe", "Producto NO existente", JOptionPane.WARNING_MESSAGE);          
        }else{
            cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad del Producto a agregar", "Cantidad a agregar Stock", JOptionPane.PLAIN_MESSAGE));
            DataBase.updateStock(codigo,cantidad);
        }
    }
    
}