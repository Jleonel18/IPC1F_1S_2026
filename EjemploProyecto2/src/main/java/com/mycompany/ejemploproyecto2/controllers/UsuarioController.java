package com.mycompany.ejemploproyecto2.controllers;

import com.mycompany.ejemploproyecto2.abstracto.Usuario;
import com.mycompany.ejemploproyecto2.models.Estudiante;
import com.mycompany.ejemploproyecto2.utils.GeneradorCodigo;
import com.mycompany.ejemploproyecto2.utils.Genero;
import com.mycompany.ejemploproyecto2.utils.Rol;
import java.util.Date;

/**
 *
 * @author leonel
 */
public class UsuarioController {
    
    private static Usuario[] usuarios = new Usuario[100];
    private static int contadorUsuarios = 0;
    
    public void agregarUsuario(String nombre, String apellido, Rol rol, Genero genero, Date fechaNacimiento, String password){
        
        if(contadorUsuarios >= usuarios.length){
            System.out.println("No hay espacio para más usuarios");
            return;
        }
        
        String codigo = GeneradorCodigo.generarCodigo(rol);
        
        Usuario nuevoUsuario = null;
        
        switch(rol){
            case ESTUDIANTE:
                Estudiante estudiante = new Estudiante(
                        nombre,
                        apellido,
                        fechaNacimiento,
                        genero,
                        codigo,
                        password,
                        rol,
                        false
                );
                nuevoUsuario = estudiante;
                break;
            default:
                System.out.println("Rol no sportado");
                return;
        }
        
        usuarios[contadorUsuarios] = nuevoUsuario;
        contadorUsuarios++;
        
        System.out.println("Usuario agregado: "+codigo);
        
    }
    
}
