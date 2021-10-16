
package model;

/**
 *
 * @author Tekan
 */
public class Producto {
    private String codigo;
    private String nombre;
    private Double costo;
    private Double precio;
    private Integer stock;
    private String categoria;
    private String descripcion;

    public Producto() {
    }

    public Producto(String codigo, String nombre,Double costo, Double precio,Integer stock,String categoria,String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    
    @Override
    public String toString() {
        return "Producto{" + " codigo=" + codigo + "\n nombre=" + nombre + "\n precio=" + precio + '}';
    }
    
}
