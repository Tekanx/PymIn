
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
    private int categoria;
    private String descripcion;

    public Producto() {
        this.codigo = "0";
        this.nombre = "none";
        this.costo = 0.0;
        this.precio = 0.0;
        this.stock = 0;
        this.categoria = 0;
        this.descripcion = "";
    }
    
    /*public Producto(String codigo, String nombre, String descripcion, int stock){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
    }*/

    public Producto(String codigo, String nombre, Double costo, Double precio, Integer stock, int categoria, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }
    
    /* Methods */

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", costo=" + costo + ", precio=" + precio + ", stock=" + stock + ", categoria=" + categoria + ", descripcion=" + descripcion + '}';
    }

    
    /* Getters & Setters */
    
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

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
   
    
}
