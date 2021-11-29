/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author diana
 */
public class ConexionBD {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public ConexionBD(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario", "root", "4248");
            st = con.createStatement();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    /*---------------------------------------------Productos---------------------------------------------*/
    
    
    public void getDatosProductos(){       //modificar a future
        try{
            String query = "SELECT * FROM productos";        
            rs = st.executeQuery(query);
            while(rs.next()){
                String codigo = rs.getString("codigo");
                String nombreProducto = rs.getString("nombreProducto");
                Integer costo = rs.getInt("costo");
                Double precio = rs.getDouble("precio");
                Integer stock = rs.getInt("stock");
                Integer categoria = rs.getInt("categoria");
                String descripcion = rs.getString("descripcion");
                System.out.println("DAtos: "+codigo+"  "+nombreProducto+"  "+costo+"  "+precio+" "+stock+" "+categoria+"  "+descripcion);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /** Añade un producto a la base de datos
     * @param Producto 
     **/
    public void addProducto(Producto prod){
        try{
            String categoria = "0";
            String query = "SELECT idCategoria FROM categorias WHERE nombreCategoria='"+prod.getCategoria()+"'";
            rs = st.executeQuery(query);
            while(rs.next()){
                categoria = rs.getString("idCategoria");
            }
            query = "INSERT INTO productos (codigo,nombreProducto,costo,precio,stock,categoria,descripcion) values('"+prod.getCodigo()+"','"+prod.getNombre()+"','"+prod.getCosto()+"','"+prod.getPrecio()+"','"+prod.getStock()+"','"+categoria+"','"+prod.getDescripcion()+"')";        
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /** Busca el codigo entregado en la base de datos y retorna el producto que coincida con este
     * @param codigo
     * @return producto 
     **/
    public Producto getProducto(String codigo){
        Producto producto = new Producto();
        try{
            String query = "SELECT * FROM productos p INNER JOIN categorias c ON p.categoria = c.idCategoria WHERE codigo= '"+codigo+"' ";        
            rs = st.executeQuery(query);
            
            while(rs.next()){
                String nombreProducto = rs.getString("nombreProducto");
                Double costo = rs.getDouble("costo");
                Double precio = rs.getDouble("precio");
                Integer stock = rs.getInt("stock");
                String descripcion = rs.getString("descripcion");
                String categoria = rs.getString("nombreCategoria");   
                producto= new Producto(codigo,nombreProducto,costo,precio,stock,categoria,descripcion);
                return producto;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
       return producto; 
    }
    
    /**Retorna una Arraylist con todos los productos existentes en la base de datos
     * @return list  ArrayList con productos
     **/
    public ArrayList getProductos(){
        ArrayList list = new ArrayList();
        try{
            String query = "SELECT * FROM productos p\n" +
                            "INNER JOIN categorias c\n" +
                            "ON p.categoria = c.idCategoria;";
            this.rs = this.st.executeQuery(query);
            
            while(rs.next()){
                String codigo = rs.getString("codigo");
                String nombreProducto = rs.getString("nombreProducto");
                Double costo = rs.getDouble("costo");
                Double precio = rs.getDouble("precio");
                Integer stock = rs.getInt("stock");
                String descripcion = rs.getString("descripcion");
                String categoria = rs.getString("nombreCategoria");                
                Producto prod= new Producto(codigo,nombreProducto,costo,precio,stock,categoria,descripcion);
                if (stock>0){
                    list.add(prod);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }     
        return list;
    }
    
    /**Retorna una Arraylist con todos los productos existentes en la base de datos que coincidan con la categoria dada
     * @param cat   nombre de la categoria
     * @return list  ArrayList con productos
     **/
    public ArrayList getProductosForCategoria(String cat){
        ArrayList list = new ArrayList();
        try{
            String query = "SELECT * FROM productos p\n" +
                            "INNER JOIN categorias c\n" +
                            "ON p.categoria = c.idCategoria "+
                            "WHERE nombreCategoria = '"+cat+"' ;" ;
            this.rs = this.st.executeQuery(query);
            
            while(rs.next()){
                String codigo = rs.getString("codigo");
                String nombreProducto = rs.getString("nombreProducto");
                Double costo = rs.getDouble("costo");
                Double precio = rs.getDouble("precio");
                Integer stock = rs.getInt("stock");
                String descripcion = rs.getString("descripcion");
                String categoria = rs.getString("nombreCategoria");                
                Producto prod= new Producto(codigo,nombreProducto,costo,precio,stock,categoria,descripcion);            
                if (stock>0){
                    list.add(prod);
                }
                System.out.println(prod.toString());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }     
        return list;
    }
    
    /**Cambia en la base de datos el nombre del producto que coincida con el codigo entregado
     * @param codigo
     * @param nombreProducto
     **/
    public void updateNombreProducto(String codigo,String nombreProducto){
        try{
            String query = "UPDATE `productos` SET nombreProducto='"+nombreProducto+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**Cambia en la base de datos el costo del producto que coincida con el codigo entregado
     * @param codigo
     * @param costo
     **/
    public void updateCosto(String codigo,Double costo){
        try{
            String query = "UPDATE `productos` SET costo='"+costo+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**Cambia en la base de datos el precio del producto que coincida con el codigo entregado
     * @param codigo
     * @param precio
     **/
    public void updatePrecio(String codigo,Double precio){
        try{
            String query = "UPDATE `productos` SET precio='"+precio+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**Cambia en la base de datos el stock del producto que coincida con el codigo entregado
     * Se añade la cantidad existente a la entregada
     * @param codigo
     * @param stock
     **/
    public void updateStock(String codigo,int stock){
        try{
            String query1 = "SELECT stock FROM productos WHERE codigo= '"+codigo+"' ";        
            rs = st.executeQuery(query1);       
            while(rs.next()){
                Integer stockInicial = rs.getInt("stock");
                stock += stockInicial;
            }
            String query2 = "UPDATE `productos` SET stock='"+stock+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query2);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**Cambia en la base de datos la descripcion del producto que coincida con el codigo entregado
     * @param codigo
     * @param descripcion
     **/
    public void updateDescripcion(String codigo,String descripcion){
        try{
            String query = "UPDATE `productos` SET descripcion='"+descripcion+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**Cambia en la base de datos la categoria del producto que coincida con el codigo entregado
     * @param codigo
     * @param categoria
     **/
    public void updateCategoria(String codigo,String categoria){
        try{
            String query = "SELECT idCategoria FROM categorias WHERE nombreCategoria='"+categoria+"'";
            rs = st.executeQuery(query);
            while(rs.next()){
                categoria = rs.getString("idCategoria");
            }
            query = "UPDATE `productos` SET categoria='"+categoria+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     
    
    /*---------------------------------------------Categorias---------------------------------------------*/
    
    
    /**Retorna una Arraylist con todos los productos existentes en la base de datos
     * @return list  ArrayList con productos
     **/
     public ArrayList getCategorias(){
        ArrayList<String> list = new ArrayList();
        try{
            String query = "SELECT nombreCategoria FROM categorias";        
            rs = st.executeQuery(query);
            while(rs.next()){
                String nombreCategoria = rs.getString("nombreCategoria");
                list.add(nombreCategoria);
            }           
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;      
    }
     
     public void addCategoria(String nombreCategoria){
        try{
            Integer id = 0 ;
            String query = "SELECT MAX(idCategoria) AS idCategoria FROM categorias";
            rs = st.executeQuery(query);    
            while(rs.next()){
                id = rs.getInt("idCategoria");
                id += 1;
            }           
            String query2 = "INSERT INTO categorias (idCategoria,nombreCategoria) values('"+id+"','"+nombreCategoria+"')";        
            st.executeUpdate(query2);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     
     
     /*---------------------------------------------Boleta---------------------------------------------*/
     
     
     /**Añade una boleta a la base de datos junto a sus productos vendidos y descuenta lo vendido al stock
     * @param boleta
      **/
     public void addBoleta(Boleta boleta){ 
        try{
            //Se guarda la boleta en la base de Datos
            String query = "INSERT INTO boletas (total,fecha,hora,medioPago) values('"+boleta.getTotalVenta()+"','"+boleta.getFecha()+"','"+boleta.getHora()+"','"+boleta.getMedioPago()+"')";        
            st.executeUpdate(query);         
            query = "SELECT MAX(idboletas) AS id FROM boletas";        
            rs = st.executeQuery(query);
            int id = 0;
            while(rs.next()){
                id = rs.getInt("id");
            }
            //Se guardan los productos vendidos en la base de datos con el idboleta correspondiente
            for(int i=0 ; i<boleta.getListaProductos().size() ; i++) {
                ProductoVendido prodVendido = boleta.getListaProductos().get(i);
                query = "INSERT INTO prodvendido (idBoleta,codigoP,cantidad) values('"+id+"','"+prodVendido.getCodigoP()+"','"+prodVendido.getCantidad()+"')"; 
                st.executeUpdate(query);
                //Se descuenta la cantidad vendida al stock del producto
                updateStock(prodVendido.getCodigoP(),-prodVendido.getCantidad());
            }      
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
     /**Retorna el id de la última boleta guardada en la base de datos
      * @return id
      **/
    public int getUltimoIdBoleta(){
        int id =0;
        try{
            String query = "SELECT MAX(idboletas) AS id FROM boletas";        
                rs = st.executeQuery(query);
                while(rs.next()){
                    id =rs.getInt("id");
                }
        }catch(Exception ex){
            ex.printStackTrace();
        }   
        return id;
    } 
    
    /**Retorna una Arraylist con todos las boletas pertenecientes a un rango de fechas entregado
     * @param inicio  fecha inicial del rango
     * @param fin     fecha final del rango
     * @return list  ArrayList con boletas
     **/
    public ArrayList getBoletasRange(LocalDate inicio,LocalDate fin){
        ArrayList<Boleta> list = new ArrayList();
        try{
            String query = "SELECT * FROM boletas WHERE fecha BETWEEN '"+inicio.toString()+"' AND '"+fin.toString()+"'  ";        
            rs = st.executeQuery(query);
            while(rs.next()){            
                int id= rs.getInt("idboletas");
                Double total = rs.getDouble("total");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                LocalTime hora = LocalTime.parse(rs.getString("hora"));
                String medioPago = rs.getString("medioPago");
                Boleta boleta = new Boleta(id,total,fecha,hora,medioPago);
                list.add(boleta);
            }
            for(Boleta boleta:list){
                boleta.setListaProductos(getProductosVendidosForBoleta(boleta.getId()));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
    
    /**Retorna ArrayList con todos las productos vendidos pertenecientes al id de boleta entregado
     * @param id    id de boleta
     * @return list lista de productos vendidos
     **/
    public ArrayList<ProductoVendido> getProductosVendidosForBoleta(int id){
        ArrayList<ProductoVendido> list = new ArrayList();
        try{       
            String query = "SELECT * FROM prodvendido WHERE idBoleta='"+id+"'";        
            rs = st.executeQuery(query);
            while(rs.next()){            
                int idv= rs.getInt("idventa");
                int cantidad = rs.getInt("cantidad");
                String codigo = rs.getString("codigoP");
                ProductoVendido pv = new ProductoVendido(idv,codigo,cantidad);
                list.add(pv);
            }
            for(ProductoVendido pv: list){
                pv.setProducto(getProducto(pv.getCodigoP()));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        } 
        return list;
    }
}