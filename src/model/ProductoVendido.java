
package model;

/**
 *
 * @author Tekan
 */
public class ProductoVendido {
   // private int idVenta;
    private int idBoleta;
    //private String codigoProducto;
    private Producto producto;
    private int cantidad;

    public ProductoVendido() {
        this.idBoleta = 0;
        //this.codigoProducto = "0";
        this.cantidad = 0;
    }

    public ProductoVendido(/*int idVenta,*/ int idBoleta, /*String codigoProducto*/ Producto producto, int cantidad) {
        //this.idVenta = idVenta;
        this.idBoleta = idBoleta;
        //this.codigoProducto = codigoProducto;
        this.producto= producto;
        this.cantidad = cantidad;
    }

    /* Methods */

    @Override
    public String toString() {
        //return "ProductoVendido{" /*+ "idVenta=" + idVenta*/ + ", idBoleta=" + idBoleta + ", codigoProducto=" + codigoProducto + ", cantidad=" + cantidad + '}';
        return "ProductoVendido{" /*+ "idVenta=" + idVenta*/ + ", idBoleta=" + idBoleta + ", codigoProducto=" + producto.getCodigo() + ", cantidad=" + cantidad + '}';
    }
    
    /* Getters & Setters */
    
    /*public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }*/

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    /*public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }*/
    public Producto Producto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getCodigoProducto() {
       return producto.getCodigo();
    }
    
    public Integer getStock() {
        return producto.getStock();
    }
    
    public double getPrecioTotal(){
        double precioTotal;
        precioTotal= producto.getPrecio()*cantidad;
        return precioTotal;
    }
    
}
