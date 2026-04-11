
package com.mycompany.ejemploproyecto2.threads;

import com.mycompany.ejemploproyecto2.abstracto.Usuario;
import com.mycompany.ejemploproyecto2.controllers.UsuarioController;
import com.mycompany.ejemploproyecto2.models.Estudiante;
import java.text.SimpleDateFormat;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author leonel
 */
public class MonitorUsuarios implements Runnable{

    private UsuarioController controller;
    private JTextArea txtArea;
    private boolean ejecutando = true;
    
    public MonitorUsuarios(UsuarioController controller, JTextArea txtArea){
        this.controller = controller;
        this.txtArea = txtArea;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        while(ejecutando){
            UsuarioController.cargarUsuarios();
            
            Usuario[] usuarios = controller.getUsuarios();
            int total = controller.getContadorUsuarios();
            
            StringBuilder sb = new StringBuilder();
            sb.append("Usuarios Del Sistema");
            sb.append("--------------");
            
            for(int i= 0; i< total; i++){
                Usuario u = usuarios[i];
                if(u==null) continue;
                
                sb.append("Codigo: ").append(u.getCodigo()).append("\n");
                sb.append("Rol: ").append(u.getRol()).append("\n");
                
                if(u instanceof Estudiante){
                    Estudiante e = (Estudiante) u;
                    sb.append("Nombre: ").append(e.getNombre()).append("\n");
                    sb.append("Género: ").append(e.getGenero()).append("\n");
                }
                
                if(u.isOnline()){
                    sb.append("Estado: EN LINEA\n");
                    
                }else{
                    sb.append("Estado: DESCONECTADO\n");
                    if(u.getUltimaConexion() != null){
                        sb.append("Ultima conexion: ").append(sdf.format(u.getUltimaConexion())).append("\n");
                        
                    }else{
                        sb.append("Ultima Conexion: Nunca\n");
                    }
                }
                
                sb.append("--------------------");
            }
            
            SwingUtilities.invokeLater(() -> {
                txtArea.setText(sb.toString());
            });
            
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                ejecutando = false;
            }
        }
    }
    
    public void detener(){
        ejecutando = false;
    }
    
}
