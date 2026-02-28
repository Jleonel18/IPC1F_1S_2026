
package ejemplo4.controllers;

import ejemplo4.models.MedicamentoModel;
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
}
