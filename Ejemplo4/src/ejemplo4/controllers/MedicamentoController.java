
package ejemplo4.controllers;

import ejemplo4.models.MedicamentoModel;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 *
 * @author leonel
 */
public class MedicamentoController {
    
    public static MedicamentoModel[] datosMedicamentos = new MedicamentoModel[100];
    
    
    public void agregarMedicamento(int id, String nombre, int cantidad, Date caducidad){
        
        //Añadir datos al arreglo
        for(int i = 0; i<datosMedicamentos.length; i++){
            if(datosMedicamentos[i] == null){
                MedicamentoModel med = new MedicamentoModel();
                
                med.setId(id);
                med.setNombre(nombre);
                med.setCantidad(cantidad);
                med.setCaducidad(caducidad);
                datosMedicamentos[i] = med;
                System.out.println("Se agregro el medicamento con id"+id+" exitosamente!");
                
                return;
                        
            }
        }
    }
    
    public void verMedicamentos(){
        for(MedicamentoModel med: datosMedicamentos){
            if(med != null){
                System.out.println("------------------------");
                System.out.println(med.getId());
                System.out.println(med.getNombre());
                System.out.println(med.getCaducidad());
                System.out.println("------------------------");
            }
            
        }
    }
    
    public void generarHTML() throws IOException{
        StringBuilder filas = new StringBuilder();
        
        for(MedicamentoModel m: datosMedicamentos){
            if(m==null) continue;
            
            filas.append("<tr>")
                    .append("<td>").append(m.getId()).append("</td>")
                    .append("<td>").append(m.getNombre()).append("</td>")
                    .append("<td>").append(m.getCantidad()).append("</td>")
                    .append("</td>");
        }
        
        String html =
                """
                <!doctype html>
                <html lang="es">
                    <head><meta charset="utf-8"><title>Reporte</title></head>
                    <body>
                        <h1>Reporte de medicamentos</h1>
                        <p>Generando reportes</p>
                        <table>
                            <thead>
                             <tr><th>ID</th><th>Nombre</th><th>Cantidad</th></tr>
                            </thead>
                            <tbody>"""+
                filas+
                "</tbody>\n"
                +"</table>\n"
                +"</html>\n"
                ;
        
        Files.write(Paths.get("reporte.html"), html.getBytes(StandardCharsets.UTF_8));
        System.out.println("Se generó el html exitosamente!");
    }
}
