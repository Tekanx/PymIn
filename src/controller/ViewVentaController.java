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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
    
    @FXML
    private Button btnAtras;
    
    @FXML
    private void eventKey(KeyEvent event){
        
    }
    
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();       
        
        if(evt.equals(btnAtras)){
            loadStage("/view/ViewIngreso.fxml", event);
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    private int checkQuantity(Producto productoEscaneado){
        Boolean isValid = false;
        Optional<String> quantityProduct;
        TextInputDialog quantityDialog = new TextInputDialog("Ingrese cantidad del producto");
        quantityDialog.setTitle("");
        quantityDialog.setHeaderText("");
        quantityDialog.setContentText("");
        do{
            quantityProduct = quantityDialog.showAndWait();
            if(quantityProduct.isPresent()){
            }
        
        }while(quantityProduct.isEmpty() && !isValid);
        
        
        
            return Integer.parseInt(quantityProduct.get());
        
    }
    
    private void loadStage(String url, Event event, ArrayList<ProductoVendido> listaProductos){
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
}
