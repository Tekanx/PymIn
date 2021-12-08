
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static main.main.DataBase;
import model.Producto;

/**
 * FXML Controller class
 *
 * @author Tekan
 */
public class ViewAdministracionController implements Initializable {
   
    /* TableView Components */   
    @FXML
    private TableView<Producto> tvProductos = new TableView<>();
    
    @FXML 
    private TableColumn<Producto, String> colCodigoProd = new TableColumn<>("Código Producto");
    
    @FXML
    private TableColumn<Producto, String> colNombreProd= new TableColumn<>("Nombre Producto");
    
    @FXML
    private TableColumn<Producto, Integer> colUnidadesProd= new TableColumn<>("Stock");
    
    @FXML
    private TableColumn<Producto, String> colCostoProd = new TableColumn<>("Costo");
    
    @FXML
    private TableColumn<Producto, String> colPrecioProd= new TableColumn<>("Precio");
    
    @FXML
    private TableColumn<Producto, String> colCategoriaProd = new TableColumn<>("Categoria");
    
    @FXML
    private TableColumn<Producto, String> colDescripcionProd= new TableColumn<>("Descripción");
    /*END TableView Components */ 
    
    /* Labels*/    
    @FXML
    private Label labelValorInventario;   
    /* END Label*/
    
    
   /* Buttons */   
    @FXML
    private Button btnGestionarProductos;
    
    @FXML
    private Button btnAgregarCategoria;
    
    @FXML
    private Button btnAtras;

    @FXML
    private Button btnFiltrar;  
    /* END Buttons */
 
    
    /* Text Field */    
    @FXML
    private TextField tfAgregarCategoria = new TextField("");   
    /* END Text Field*/
    
    /* ComboBox*/
    @FXML
    private ComboBox comboxCategoria;
    /*END ComboBox*/
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable(DataBase.getProductos());
        labelValorInventario.setText("$ " + calculateValorInventario());
        cargarCategorias();
    }    
    
    /* Methods*/
    
     
    @FXML
    private void eventKey(KeyEvent event){
        
    }
    
    @FXML
    private void eventAction(ActionEvent event) throws IOException{
        Object evt = event.getSource();       
        
        if(evt.equals(btnGestionarProductos)){
            if(tvProductos.getSelectionModel().getSelectedItem() == null){ 
                loadStage("/view/ViewGestionProducto.fxml", event);
            }else{
                pasarProducto();
                
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        }
        
        if(evt.equals(btnAgregarCategoria)){
            if(tfAgregarCategoria.getText().equals("")){
                JOptionPane.showMessageDialog(null, "¡Texto no ingresado! ingrese un nombre de categoría", "Categoría inválida", JOptionPane.ERROR_MESSAGE);
            }else{
                agregarCategoria();
            }
            tfAgregarCategoria.setText("");
        } 
         
        if(evt.equals(btnFiltrar)){
            filtrar();
        }
        
        if(evt.equals(btnAtras)){
            loadStage("/view/ViewIngreso.fxml", event);
        }
        
    }
    
    
    private String calculateValorInventario() {
        String valor = "0";
        Double contador = 0.0;
        try{
            ArrayList<Producto> productos = new ArrayList();
            productos = DataBase.getProductos();
            for (Producto producto : productos) {
                contador += (producto.getPrecio() * producto.getStock());
            }
        }catch(Exception Ex){
            Ex.printStackTrace();
            return valor;
        }
        return valor = Double.toString(contador);
    }
    
    
    private void loadTable(ArrayList<Producto> productos){

        try{
            /*ArrayList<Producto> productos = new ArrayList();
            productos = DataBase.getProductos();*/
            colCodigoProd.setCellValueFactory(new PropertyValueFactory<Producto,String>("codigo"));
            colNombreProd.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
            colUnidadesProd.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("stock"));
            colCostoProd.setCellValueFactory(new PropertyValueFactory<Producto,String>("costo"));
            colPrecioProd.setCellValueFactory(new PropertyValueFactory<Producto,String>("precio"));
            colCategoriaProd.setCellValueFactory(new PropertyValueFactory<Producto,String>("categoria"));
            colDescripcionProd.setCellValueFactory(new PropertyValueFactory<Producto,String>("descripcion"));
            
            ObservableList dataProductos = FXCollections.observableList(productos); 
            tvProductos.setItems(dataProductos);
        }catch(Exception SQLEx){
            SQLEx.printStackTrace();
        }
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
    
    public void agregarCategoria(){
        ArrayList<String> categorias = DataBase.getCategorias();
        Boolean existe=false;
        for (String categoria: categorias){
            if (tfAgregarCategoria.getText().equals(categoria)){
                existe=true;
                JOptionPane.showConfirmDialog(null, "¡Esta categoria ya existe!La categoria "+tfAgregarCategoria.getText()+" ya existe", "Categoria Existente", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        if(!existe){
            DataBase.addCategoria(tfAgregarCategoria.getText());
            JOptionPane.showConfirmDialog(null, "Has Agregado la categoría "+tfAgregarCategoria.getText(), "Categoria Agregada", JOptionPane.WARNING_MESSAGE);
            
        }        
    }
    
    public void cargarCategorias(){
        ArrayList<String> categorias = DataBase.getCategorias();
        categorias.add("Todas");
        ObservableList dataCategorias = FXCollections.observableList(categorias);
        comboxCategoria.setItems(dataCategorias);
    }
    
    public void filtrar(){
        String cat= (String)comboxCategoria.getValue();
        if (cat.equals("Todas")){
            loadTable(DataBase.getProductos());
            return;
        }
        ArrayList<String> categorias = DataBase.getCategorias();
        for (String categoria:categorias){
            if(cat.equals(categoria)){
                loadTable(DataBase.getProductosForCategoria(categoria));
                return;
            }
        }
    }
    public void pasarProducto(){
        
               

        try{
            Producto prod= tvProductos.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewGestionProducto.fxml"));
            Parent root = loader.load();
            ViewGestionProductoController gestionProdController = loader.getController();
            gestionProdController.loadProducto(prod);            
            loadStage(root);
        }catch(Exception ex){
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
}
