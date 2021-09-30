/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.*;
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
    
    public void getDatos(){
        
        try{
            String query = "SELECT * FROM productos";        
            rs= st.executeQuery(query);
            while(rs.next()){
                String codigo = rs.getString("codigo");
                String nombreProducto = rs.getString("nombreProducto");
                Float precio= rs.getFloat("precio");
                Integer stock = rs.getInt("stock");
                String descripcion = rs.getString("descripcion");
                System.out.println("DAtos: "+codigo+"  "+nombreProducto+"  "+precio+" "+stock+" ");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                con.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
