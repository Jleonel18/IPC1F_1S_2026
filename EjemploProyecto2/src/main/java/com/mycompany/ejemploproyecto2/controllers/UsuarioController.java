package com.mycompany.ejemploproyecto2.controllers;

import com.mycompany.ejemploproyecto2.abstracto.Usuario;
import com.mycompany.ejemploproyecto2.models.Estudiante;
import com.mycompany.ejemploproyecto2.utils.GeneradorCodigo;
import com.mycompany.ejemploproyecto2.utils.Genero;
import com.mycompany.ejemploproyecto2.utils.Rol;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author leonel
 */
public class UsuarioController {
    
    private static Usuario[] usuarios = new Usuario[100];
    private static int contadorUsuarios = 0;
    private static final String NOMBRE_ARCHIVO = "estudiantes.ser";
    
    public UsuarioController(){
        cargarUsuarios();
    }
    
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
        guardarUsuarios();
        
        System.out.println("Usuario agregado: "+codigo);
        
    }
    
    public static void guardarUsuarios(){
        File archivo = new File(NOMBRE_ARCHIVO);
        
        try{
            if(!archivo.exists()){
                archivo.createNewFile();
            }
            try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))){
                salida.writeObject(usuarios);
                salida.writeInt(contadorUsuarios);
                System.out.println("Usuarios guardados correctamente en "+NOMBRE_ARCHIVO);
            }
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public static void cargarUsuarios(){
        File archivo = new File(NOMBRE_ARCHIVO);
        
        if(!archivo.exists()){
            usuarios = new Usuario[100];
            contadorUsuarios = 0;
            System.out.println("No existe el archivo.ser, datos vacíos");
            return;
        }
        
        if(archivo.length() == 0){
            usuarios = new Usuario[100];
            contadorUsuarios = 0;
            System.out.println("El archivo existe pero está vacío, datos vacíos");
            return;
        }
        
        try(ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))){
            usuarios = (Usuario[]) entrada.readObject();
            contadorUsuarios = entrada.readInt();
            System.out.println("Estudiantes cargados exitosamente");
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Error al cargar los usuarios: "+ e.getMessage());
            usuarios = new Usuario[100];
            contadorUsuarios = 0;
        }
    }
    
    public void listarUsuarios(){
        if(contadorUsuarios == 0){
            System.out.println("No hay estudiantes usuarios");
            return;
        }
        
        for(int i=0; i< contadorUsuarios; i++){
            System.out.println("Usuario No. "+(i+1)+":"+usuarios[i].getCodigo());
        }
    }
    
    public Usuario[] getUsuarios(){
        return usuarios;
    }
    
    public int getContadorUsuarios(){
        return contadorUsuarios;
    }
    
    public void cargarDesdeCSV(String rutaCSV){
        File archivo = new File(rutaCSV);
        
        if(!archivo.exists()){
            System.out.println("El archivo no existe");
            return;
        }
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);
        
        int cargados = 0;
        
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String linea;
            boolean primeraLinea = true;
            
            while((linea = br.readLine()) != null){
                if(primeraLinea){
                    primeraLinea = false;
                    continue;
                }
                
                if(linea.trim().isEmpty()){
                    continue;
                }
                String[] partes = linea.split(",");
                
                if(partes.length < 5){
                    System.out.println("Linea no valida"+linea);
                    continue;
                }
                
                String nombre = partes[0].trim();
                String apellido = partes[1].trim();
                String fechaTexto = partes[2].trim();
                String generoTexto = partes[3].trim().toUpperCase();
                String password = partes[4].trim();
                
                Genero nuevoGenero = null;
                
                try{
                    Date fechaNacimiento = formatoFecha.parse(fechaTexto);
                    if(generoTexto.equals("MASCULINO")) nuevoGenero = Genero.MASCULINO;
                    if(generoTexto.equals("FEMENINO")) nuevoGenero = Genero.FEMENINO;
                    if(generoTexto.equals("INDEFINIDO")) nuevoGenero = Genero.INDEFINIDO;
                    
                    agregarUsuario(nombre,apellido,Rol.ESTUDIANTE, nuevoGenero, fechaNacimiento, password);
                    cargados++;
                }catch(ParseException e){
                    System.out.println("Fecha inválida en línea: "+linea);
                }
                
            }
        }catch(IOException e){
            System.out.println("Error al leer el CSV: "+e.getMessage());
        }
    }
    
}
