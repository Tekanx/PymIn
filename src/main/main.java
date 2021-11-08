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
        Boleta boleta=new Boleta(0, 2036.2, LocalDate.now(), LocalTime.now(), "");
        ProductoVendido pv1= new ProductoVendido(0,"7802215101854",3);
        boleta.addProductoVendido(pv1);
        ProductoVendido pv2= new ProductoVendido(0,"7802640720545",3);
        boleta.addProductoVendido(pv2);
        ProductoVendido pv3= new ProductoVendido(0,"7802408001787",3);
        boleta.addProductoVendido(pv3);
        DataBase.addBoleta(boleta);
        //db.addProducto("6970647061417","Cubo Rubik",4500.0,5990.0,3,0,"cubo chino");
        //db.updateNombreProducto("6970647061419", "Rubik cubo");
        //db.updateStock("6970647061419", -7);
        //db.addCategoria("Galletas");
        
        
        launch(args);
    }
}
