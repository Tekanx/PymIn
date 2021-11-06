
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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

    public Boleta(int id, double totalVenta, LocalDate fecha, LocalTime hora, String medioPago) {
        this.id = id;
        this.totalVenta = totalVenta;
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
    
    
    
}
