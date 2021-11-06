
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Tekan
 */
public class RegistroVentas {
    
    private HashMap<String,Boleta> hmBoleta = new HashMap();

    public RegistroVentas() {
    }

    public void addBoleta(){}

    public Boleta getBoleta(){ return null; }
    
    public ArrayList getBoletas(){ return null; }

    
    
    @Override
    public String toString() {
        return "RegistroVentas{" + "hmBoleta=" + hmBoleta + '}';
    }
    
}
