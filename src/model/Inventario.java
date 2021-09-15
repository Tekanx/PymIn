/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Tekan
 */
public class Inventario {
    //Key = Código?, Value = Producto
    private HashMap hmProducto = new HashMap();

    public Inventario() {
    }
    
    public Producto getProducto(String codigo){
        
        return null;
    }
    
    public void addProducto(Producto producto){
        if(hmProducto.containsKey(producto.getCodigo())){
            //Confirmación de querer hacer **cambios** (¿cuales?) a producto existente
        }
        else{
           hmProducto.put(producto.getCodigo(), producto);
        }
    }
    
    public void addProducto(Producto producto, int cantidad){
        if(hmProducto.containsKey(producto.getCodigo())){
            //Confirmación de querer hacer cambios a producto existente
        }
        else{
        }
    }
    
    public void eliminarProducto(Producto producto){
        
    }
    
    public void eliminarProducto(int id){
        
    }
    
    public Producto conseguirProducto(){
        return null;
    }
    
    public ArrayList<Producto> conseguirProductos(){
        ArrayList<Producto> alProductos = new ArrayList<>();
        try{
            if(hmProducto.isEmpty()){
                JOptionPane.showMessageDialog(null, "La lista de productos no pudo generarse debido a que no existen productos en el programa.", "ERROR EN LISTAR PRODUCTOS", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            else{
                hmProducto.forEach(null);
            }
        }
        catch(Exception ex){
            System.out.println("Excepcción: " + ex);
        }
        return alProductos;
    }
    
    
    
}
