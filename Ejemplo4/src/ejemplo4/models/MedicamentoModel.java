
package ejemplo4.models;

import java.util.Date;

/**
 *
 * @author leonel
 */
public class MedicamentoModel {
    
    private int id;
    private String nombre;
    private int cantidad;
    private Date caducidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }
    
    
    
}
