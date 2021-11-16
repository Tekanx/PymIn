package main;

import java.time.LocalTime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ConexionBD;
import model.Inventario;
import model.RegistroVentas;

/**
 *
 * @author Tekan
 */
public class Main extends Application{
    
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
        //db.addProducto("6970647061417","Cubo Rubik",4500.0,5990.0,3,0,"cubo chino");
        //db.updateNombreProducto("6970647061419", "Rubik cubo");
        //db.updateStock("6970647061419", -7);
        //db.addCategoria("Galletas");
        launch(args);
    }
}
