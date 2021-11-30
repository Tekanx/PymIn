
package model;

/**
 *
 * @author Tekan
 */
public class ProductoVendido {
    private int idBoleta;  
    private Producto producto = new Producto();

    private int cantidad;
    private Double precioP;
    private String nombreP;
    private String codigoP;
    private int stockP;
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
        this.totalParcial= this.precioP*this.cantidad;
    }
    public ProductoVendido(int idBoleta,String codigoP, int cantidad) {    
        this.idBoleta = idBoleta;
        this.codigoP= codigoP;
        this.cantidad = cantidad;
        //falta ver si cantidad es posible dependiendo el stock
    }

    /* Methods */

    @Override
    public String toString() {
        return "ProductoVendido{" /*+ "idVenta=" + idVenta*/ + " idBoleta=" + idBoleta + ", codigoProducto=" + producto.getCodigo() + ", cantidad=" + cantidad + '}';
    }
    
    /* Getters & Setters */
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdBoleta() {
        return this.idBoleta;
    }
    
    /**@param idBoleta the idBoleta to set */
    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) throws CloneNotSupportedException {
        this.producto = new Producto(producto.getPrecio(),producto.getNombre(), producto.getCodigo(), producto.getStock());
        instanceProducto(producto);
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setTotalParcial(int cantidad) {
        this.cantidad = cantidad;
        this.totalParcial= this.precioP*this.cantidad;
    }
    
    public void setTotalParcial(Double totalParcial) {
        this.totalParcial = totalParcial;
    }
    
    public int getStock() {
        return this.producto.getStock();
    }
    public int getStockP(){
        return this.stockP;
    }

    public Double getPrecioP() {
        return this.precioP;
    }

    public String getNombreP() {
        return this.nombreP;
    }

    public String getCodigoP() {
        return this.codigoP;
    }

    public Double getTotalParcial() {
        return this.totalParcial = (this.precioP * this.cantidad);
    }
    
    public void setPrecioP(Double precioP) {
        this.precioP = precioP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public void setCodigoP(String codigoP) {
        this.codigoP = codigoP;
    }

    public void setStockP(int stock){
        this.stockP = stock;
    }
    
    public void instanceProducto(Producto prod){
        setPrecioP(prod.getPrecio());
        setNombreP(prod.getNombre());
        setCodigoP(prod.getCodigo());
        setStockP(prod.getStock());
    }
    public boolean compatibilidadStock(int cantidad){
        if(cantidad > producto.getStock()){
           return false; 
        }
        return true;
        
    }
}
