
package com.mycompany.ejemploproyecto2.abstracto;

import com.mycompany.ejemploproyecto2.utils.Rol;

/**
 *
 * @author leonel
 */
public class Usuario {
    
    private String codigo;
    private String password;
    private Rol rol;
    private boolean online;

    public Usuario(String codigo, String password, Rol rol, boolean online) {
        this.codigo = codigo;
        this.password = password;
        this.rol = rol;
        this.online = online;
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
        this.online = online;
    }
    
    
}
