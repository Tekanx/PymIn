/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import model.Producto;
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
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario", "root", "4248");
            st=con.createStatement();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
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
    
    public void addProducto(String codigo,String nombreProducto,Double costo,Double precio,Integer stock,int categoria,String descripcion){
  
        try{
            String query = "INSERT INTO productos (codigo,nombreProducto,costo,precio,stock,categoria,descripcion) values('"+codigo+"','"+nombreProducto+"','"+costo+"','"+precio+"','"+stock+"','"+categoria+"','"+descripcion+"')";        
            st.executeUpdate(query);
            System.out.println("coso añadido");
            getDatosProductos();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Producto getProducto(String codigo){
        Producto producto=new Producto();
        try{
            String query = "SELECT * FROM productos WHERE codigo= '"+codigo+"' ";        
            rs = st.executeQuery(query);
            
            while(rs.next()){
                String nombreProducto = rs.getString("nombreProducto");
                Double costo = rs.getDouble("costo");
                Double precio = rs.getDouble("precio");
                Integer stock = rs.getInt("stock");
                Integer categoria = rs.getInt("categoria");
                String descripcion = rs.getString("descripcion");
               // System.out.println("Datos: "+codigo+"  "+nombreProducto+"  "+precio+" "+stock+" "+descripcion);        
                producto= new Producto(codigo,nombreProducto,costo,precio,stock,categoria,descripcion);
                return producto;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
       return producto; 
    }
    
    public ArrayList getProductos(){
        ArrayList list = new ArrayList();
        try{
            String query = "SELECT * FROM productos";
            this.rs = this.st.executeQuery(query);
            
            while(rs.next()){
                Producto prod = new Producto();
                prod.setCodigo(rs.getString("codigo"));
                prod.setNombre(rs.getString("nombreProducto"));
                prod.setCosto(rs.getDouble("costo"));
                prod.setPrecio(rs.getDouble("precio"));
                prod.setStock(rs.getInt("stock"));
                prod.setCategoria(rs.getInt("categoria"));
                prod.setDescripcion(rs.getString("descripcion"));
                list.add(prod);
                System.out.println(prod.toString());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return list;
    }
    
    public void updateNombreProducto(String codigo,String nombreProducto){
        try{
            String query = "UPDATE `productos` SET nombreProducto='"+nombreProducto+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void updateCosto(String codigo,Double costo){
        try{
            String query = "UPDATE `productos` SET costo='"+costo+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void updatePrecio(String codigo,Double precio){
        try{
            String query = "UPDATE `productos` SET precio='"+precio+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void updateStock(String codigo,int stock){
        try{
            String query1 = "SELECT stock FROM productos WHERE codigo= '"+codigo+"' ";        
            rs = st.executeQuery(query1);       
            while(rs.next()){
                Integer stockInicial=rs.getInt("stock");
                stock += stockInicial;
            }
            System.out.println("Datos: "+stock);
            String query2 = "UPDATE `productos` SET stock='"+stock+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query2);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
     public void updateDescripcion(String codigo,String descripcion){
        try{
            String query = "UPDATE `productos` SET descripcion='"+descripcion+"' WHERE codigo='"+codigo+"'";        
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
     
     //categorias
     public void getCategorias(){
        try{
            String query = "SELECT * FROM categorias";        
            rs = st.executeQuery(query);
            while(rs.next()){
                Integer idCategoria = rs.getInt("idCategoria");
                String nombreCategoria = rs.getString("nombreCategoria");
                System.out.println("categorias: "+idCategoria+"  "+nombreCategoria);
            }           
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     
     public void addCategoria(String nombreCategoria){
        try{
            Integer id=0 ;
            String query= "SELECT MAX(idCategoria) AS idCategoria FROM categorias";
            rs = st.executeQuery(query);    
            while(rs.next()){
                id =rs.getInt("idCategoria");
                id += 1;
            }
            System.out.println("Datos: "+id);
              
            String query2 = "INSERT INTO categorias (idCategoria,nombreCategoria) values('"+id+"','"+nombreCategoria+"')";        
            st.executeUpdate(query2);
            System.out.println("coso añadido");
            getCategorias();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     
     
     
     
     
     
     //boleta
     
     public void addBoleta(Boleta boleta){ 
        try{
            //guardado de boleta en la bd
            String query = "INSERT INTO boletas (total,fecha,hora,medioPago) values('"+boleta.getTotalVenta()+"','"+boleta.getFecha()+"','"+boleta.getHora()+"','"+boleta.getMedioPago()+"')";        
            st.executeUpdate(query);
            
            query = "SELECT MAX(idboletas) AS id FROM boletas";        
            rs = st.executeQuery(query);
            int id=0;
            while(rs.next()){
                id =rs.getInt("id");
            }
            //se guardan los productos vendidos con el idboleta 
            for(int i=0;i<boleta.getListaProductos().size();i++) {
                ProductoVendido prodVendido= boleta.getListaProductos().get(i);
                query = "INSERT INTO prodvendido (idBoleta,codigoP,cantidad) values('"+id+"','"+prodVendido.getCodigoProducto()+"','"+prodVendido.getCantidad()+"')"; 
                st.executeUpdate(query);
                //se descuenta la cantidad del stock del producto
                updateStock(prodVendido.getCodigoProducto(),-prodVendido.getCantidad());
            }
            System.out.println("coso añadido++"+id);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     
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
}
