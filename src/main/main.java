package main;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ConexionBD;
import model.Inventario;
import model.RegistroVentas;
import model.Boleta;
import model.ProductoVendido;

/**
 *
 * @author Tekan
 */
public class main extends Application{
    
    public static Inventario MapaProductos = new Inventario();
    public static RegistroVentas MapaVentas = new RegistroVentas();
    public static ConexionBD DataBase= new ConexionBD();

    @Override
    public void start(Stage stage) throws Exception {
       
        /*
        URL fxml = getClass().getClassLoader().getResource("view/ViewIngreso.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxml);
        */
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewIngreso.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Ingreso PymIn");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args){
        DataBase.getProducto("7802408015241");
        DataBase.getDatosProductos();  
        launch(args);
    }
}
