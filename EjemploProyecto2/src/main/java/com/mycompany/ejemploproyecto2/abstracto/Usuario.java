
package com.mycompany.ejemploproyecto2.abstracto;

import com.mycompany.ejemploproyecto2.utils.Rol;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author leonel
 */
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String password;
    private Rol rol;
    private boolean online;
    private Date ultimaConexion;

    public Usuario(String codigo, String password, Rol rol, boolean online, Date ultimaConexion) {
        this.codigo = codigo;
        this.password = password;
        this.rol = rol;
        this.online = online;
        this.ultimaConexion = ultimaConexion;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online  = online;
        if(!online){
            Date horaActualizada = new Date();
            setUltimaConexion(horaActualizada);
        }
    }
    
    
}
