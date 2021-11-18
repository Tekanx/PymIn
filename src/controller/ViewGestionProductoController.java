/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
    private void eventKey(KeyEvent event){
    }
    
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();       
        
        if(evt.equals(btnVolverGestionI)){
            loadStage("/view/ViewAdministracion.fxml", event);
        }
        
        if(evt.equals(btnBuscarScan)){
            //loadStage("/view/ViewVenta.fxml", event);
        }
        
        if(evt.equals(btnBuscarManual)){
            
        }
        
        if(evt.equals(btnAgregarProd)){
            
        }
        
        if(evt.equals(btnModificarProd)){
            
        }
        
        if(evt.equals(btnVolverGestionI)){
            
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
    
}