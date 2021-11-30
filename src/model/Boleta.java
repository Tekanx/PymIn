
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import static main.main.DataBase;

/**
 *
 * @author Tekan
 */
public class Boleta {
    
    private int id;
    private double totalVenta;
    private LocalDate fecha;
    private LocalTime hora;
    private String medioPago;
    private ArrayList<ProductoVendido> listaProductos = new ArrayList();

    public Boleta() {
    }

    public Boleta(int id, double totalVenta, LocalDate fecha, LocalTime hora, String medioPago) { //desde bd
        this.id = id;
        this.totalVenta = totalVenta;
        this.fecha = fecha;
        this.hora = hora;
        this.medioPago = medioPago;
    }
    public Boleta(int id, LocalDate fecha, LocalTime hora, String medioPago) { // cuando recien se crea y se le agregaran productos
        this.id = id;
        this.totalVenta = 0.0;
        this.fecha = fecha;
        this.hora = hora;
        this.medioPago = medioPago;
    }

    
    /* Methods */
    
    /* Getters & Setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }
    
    public void addProductoVendido(Producto producto,int cantidad){ 
        for(int i=0; i <listaProductos.size(); i++){ //revisando si ya esta 
            if(listaProductos.get(i).getCodigoP() == producto.getCodigo()){ //si está

                ProductoVendido pv = listaProductos.get(i);
                int aux = listaProductos.get(i).getCantidad()  + cantidad;
                if(aux < 0){
                    System.out.print("error al restar cantidad de producto");
                }else if(aux == 0){
                   listaProductos.remove(i);
                }else{
                    if(pv.compatibilidadStock(aux)){
                        totalVenta-= pv.getTotalParcial();
                        pv.setTotalParcial(aux);
                        totalVenta+= pv.getTotalParcial();

                    }else{
                        System.out.print("cantidad mayor al stock disponible");
                    }                    
                }
                return;
            }        
        }// si no está
        ProductoVendido prodVendido= new ProductoVendido(id,producto,cantidad);
        if(prodVendido.compatibilidadStock(cantidad)){       
            listaProductos.add(prodVendido);
            totalVenta+= prodVendido.getTotalParcial();  

        }else{
            //mensaje que no se pudo
        }
    }

    
    public void addPVendidoBD(ProductoVendido prodVendido){ //añadido desde BD
        prodVendido.setIdBoleta(DataBase.getUltimoIdBoleta() + 1);
        listaProductos.add(prodVendido);
    }
    
    public ArrayList<ProductoVendido> getListaProductos(){
        return listaProductos;
    }
    public void setListaProductos(ArrayList<ProductoVendido> listaProductos) {
        this.listaProductos=listaProductos;
    }

    public void addProductoVendido(ProductoVendido prodVendido){ //falta revisar si ya esta

        prodVendido.setIdBoleta(id);
        listaProductos.add(prodVendido);
    }
}
