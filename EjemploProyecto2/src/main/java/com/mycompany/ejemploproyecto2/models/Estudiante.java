
package com.mycompany.ejemploproyecto2.models;

import com.mycompany.ejemploproyecto2.abstracto.Usuario;
import com.mycompany.ejemploproyecto2.utils.Genero;
import com.mycompany.ejemploproyecto2.utils.Rol;
import java.util.Date;

/**
 *
 * @author leonel
 */
public class Estudiante extends Usuario{
    
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private Genero genero;
    
    public Estudiante(String nombre, String apellido, Date fechaNacimiento, Genero genero,String codigo, String password, Rol rol, boolean online) {
        super(codigo, password, rol, online);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    /*@Override
    public boolean isOnline(){
        return true;
    }*/
    
    
}
