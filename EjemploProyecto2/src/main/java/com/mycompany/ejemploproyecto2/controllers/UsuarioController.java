package com.mycompany.ejemploproyecto2.controllers;

import com.mycompany.ejemploproyecto2.abstracto.Usuario;
import com.mycompany.ejemploproyecto2.models.Estudiante;
import com.mycompany.ejemploproyecto2.utils.GeneradorCodigo;
import com.mycompany.ejemploproyecto2.utils.Genero;
import com.mycompany.ejemploproyecto2.utils.Rol;
import java.io.*;
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
    public static Usuario usuarioActivo = null;

    public UsuarioController(){
        cargarUsuarios();
    }

    // ========================= VALIDACIONES =========================

    public boolean existeCodigo(String codigo) {
        for (int i = 0; i < contadorUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    // ========================= AGREGAR MANUAL =========================

    public boolean agregarUsuario(String nombre, Rol rol, Genero genero, Date fechaNacimiento, String password) {

        if (contadorUsuarios >= usuarios.length) {
            System.out.println("No hay espacio para más usuarios");
            return false;
        }

        if (genero == null) {
            System.out.println("Género no válido");
            return false;
        }

        String codigo;
        do {
            codigo = GeneradorCodigo.generarCodigo();
        } while (existeCodigo(codigo));

        return insertarUsuario(codigo, nombre, rol, genero, fechaNacimiento, password);
    }

    // ========================= AGREGAR DESDE CSV =========================

    public boolean agregarUsuarioConCodigo(String codigo, String nombre, Rol rol, Genero genero, Date fechaNacimiento, String password) {

        if (contadorUsuarios >= usuarios.length) {
            System.out.println("No hay espacio para más usuarios");
            return false;
        }

        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("Código no válido");
            return false;
        }

        if (genero == null) {
            System.out.println("Género no válido");
            return false;
        }

        if (existeCodigo(codigo)) {
            System.out.println("Código duplicado: " + codigo);
            return false;
        }

        return insertarUsuario(codigo, nombre, rol, genero, fechaNacimiento, password);
    }

    // ========================= INSERCIÓN CENTRAL =========================

    private boolean insertarUsuario(String codigo, String nombre, Rol rol, Genero genero, Date fechaNacimiento, String password) {

        Usuario nuevoUsuario = null;

        switch (rol) {
            case ESTUDIANTE:
                nuevoUsuario = new Estudiante(
                        nombre,
                        fechaNacimiento,
                        genero,
                        codigo,
                        password,
                        rol,
                        false,
                        null
                );
                break;

            default:
                System.out.println("Rol no soportado");
                return false;
        }

        usuarios[contadorUsuarios] = nuevoUsuario;
        contadorUsuarios++;

        guardarUsuarios();

        System.out.println("Usuario agregado: " + codigo);
        return true;
    }

    // ========================= ARCHIVOS =========================

    public static void guardarUsuarios() {
        File archivo = new File(NOMBRE_ARCHIVO);

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
                salida.writeObject(usuarios);
                salida.writeInt(contadorUsuarios);
                System.out.println("Usuarios guardados correctamente");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void cargarUsuarios() {
        File archivo = new File(NOMBRE_ARCHIVO);

        if (!archivo.exists() || archivo.length() == 0) {
            usuarios = new Usuario[100];
            contadorUsuarios = 0;
            System.out.println("Sin datos previos");
            return;
        }

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            usuarios = (Usuario[]) entrada.readObject();
            contadorUsuarios = entrada.readInt();
            System.out.println("Usuarios cargados correctamente");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
            usuarios = new Usuario[100];
            contadorUsuarios = 0;
        }
    }

    // ========================= CSV =========================

    public void cargarDesdeCSV(String rutaCSV) {

        File archivo = new File(rutaCSV);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe");
            return;
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);

        int cargados = 0;
        int omitidos = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;
            boolean primera = true;

            while ((linea = br.readLine()) != null) {

                if (primera) {
                    primera = false;
                    continue;
                }

                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(",");

                if (partes.length < 5) {
                    System.out.println("Línea inválida: " + linea);
                    omitidos++;
                    continue;
                }

                String codigo = partes[0].trim();
                String nombre = partes[1].trim();
                String fechaTexto = partes[2].trim();
                String generoTexto = partes[3].trim().toUpperCase();
                String password = partes[4].trim();

                Genero genero = null;

                if (generoTexto.equals("M")) genero = Genero.MASCULINO;
                else if (generoTexto.equals("F")) genero = Genero.FEMENINO;
                else if (generoTexto.equals("I")) genero = Genero.INDEFINIDO;

                try {
                    Date fecha = formatoFecha.parse(fechaTexto);

                    boolean agregado = agregarUsuarioConCodigo(
                            codigo,
                            nombre,
                            Rol.ESTUDIANTE,
                            genero,
                            fecha,
                            password
                    );

                    if (agregado) cargados++;
                    else omitidos++;

                } catch (ParseException e) {
                    System.out.println("Fecha inválida: " + linea);
                    omitidos++;
                }
            }

            System.out.println("Carga completa → Cargados: " + cargados + " | Omitidos: " + omitidos);

        } catch (IOException e) {
            System.out.println("Error leyendo CSV: " + e.getMessage());
        }
    }

    // ========================= OTROS =========================

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public int getContadorUsuarios() {
        return contadorUsuarios;
    }
    
    public void listarUsuarios(){
        if(contadorUsuarios == 0){
            System.out.println("No hay usuarios");
            return;
        }
        
        for(int i=0; i< contadorUsuarios; i++){
            System.out.println("Usuario No. "+(i+1)+":"+usuarios[i].getCodigo());
        }
    }
    
    public Usuario loginPorRol(String codigo, String password){
        if(codigo==null || password == null){
            return null;
        }
        
        for(Usuario u:usuarios){
            if(u==null) continue;
            if(u.getCodigo().equals(codigo) && u.getPassword().equals(password)){
                u.setOnline(true);
                guardarUsuarios();
                return u;
            }
        }
        
        return null;
    }
    
    public void logout(Usuario usuario){
        if(usuario == null){
            return;
        }
        
        cargarUsuarios();
        for(int i=0; i<contadorUsuarios; i++){
            if(usuarios[i] != null && usuarios[i].getCodigo().equals(usuario.getCodigo())){
                usuarios[i].setOnline(false);
                guardarUsuarios();
                return;
            }
        }
    }
    
}