
package ejemplohilos;

/**
 *
 * @author leonel
 */
public class EjemploHilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PersonajeController control = new PersonajeController();
        PersonajeController control2 = new PersonajeController(1);
        PartidaController pController = new PartidaController();
        
        control.agregarPersonajesQuemados();
        pController.agregarPartidasQuemadas();

        
        VistaPrincipal vPrincipal = new VistaPrincipal();
        vPrincipal.setVisible(true);
        
    }
    
}
