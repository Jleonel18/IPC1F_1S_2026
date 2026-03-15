
package ejemplohilos;

/**
 *
 * @author leonel
 */
public class PersonajeModel {
    
    int idPersonaje;
    String nombre;
    Casa casa;
    EscobaModel escoba;

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public EscobaModel getEscoba() {
        return escoba;
    }

    public void setEscoba(EscobaModel escoba) {
        this.escoba = escoba;
    }
    
}
