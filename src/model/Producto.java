
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
    private int stock;
    private String categoria;
    private String descripcion;

    public Producto() {
        this.codigo = "0";
        this.nombre = "none";
        this.costo = 0.0;
        this.precio = 0.0;
        this.stock = 0;
        this.categoria = "ninguna";

        this.descripcion = "";
    }
    
    /*public Producto(String codigo, String nombre, String descripcion, int stock){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
    }*/

    public Producto(String codigo, String nombre, Double costo, Double precio, Integer stock, String categoria, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public Producto(Double precio, String nombre, String codigo, int stock) {
        this.precio = precio;
        this.nombre = nombre;
        this.codigo = codigo;
        this.stock = stock;
    }
    
    /* Methods */

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", costo=" + costo + ", precio=" + precio + ", stock=" + stock + ", categoria=" + categoria + ", descripcion=" + descripcion + '}';
    }

    
    /* Getters & Setters */
    
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getCosto() {
        return this.costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }      
}