
package model;

/**
 *
 * @author Tekan
 */
public class ProductoVendido {
    private int idBoleta;  
    private Producto producto;
    private int cantidad;
    private Double precioP;
    private String nombreP;
    private String codigoP;
    private Double totalParcial;

    public ProductoVendido() {
        this.idBoleta = 0;
        this.cantidad = 0;
    }

    public ProductoVendido(int idBoleta,Producto producto, int cantidad) {    
        this.idBoleta = idBoleta;
        this.producto= producto;
        this.cantidad = cantidad;
        this.codigoP= producto.getCodigo();
        this.nombreP = producto.getNombre();
        this.precioP = producto.getPrecio();
        this.totalParcial= this.precioP*cantidad;
    }

    /* Methods */

    @Override
    public String toString() {
        return "ProductoVendido{" /*+ "idVenta=" + idVenta*/ + " idBoleta=" + idBoleta + ", codigoProducto=" + producto.getCodigo() + ", cantidad=" + cantidad + '}';
    }
    
    /* Getters & Setters */
    
    public int getIdBoleta() {
        return idBoleta;
    }
    
    /**@param idBoleta the idBoleta to set */
    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Producto Producto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        this.codigoP= producto.getCodigo();
        this.nombreP = producto.getNombre();
        this.precioP = producto.getPrecio();
        
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.totalParcial= this.precioP*cantidad;
    }
    
    public String getCodigoProducto() {
       return this.codigoP;
    }
    
    public String getoNmbreProducto() {
       return this.nombreP;
    }
    
    public Double getPrecioProducto() {
       return this.precioP;
    }
    
    public int getStock() {
        return this.producto.getStock();
    }
    
    public double getPrecioTotal(){
        return totalParcial;
    }
    public boolean compatibilidadStock(int cantidad){
        if(cantidad > producto.getStock()){
           return false; 
        }
        return true;
        
    }
}