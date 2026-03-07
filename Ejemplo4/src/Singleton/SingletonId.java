
package Singleton;

/**
 *
 * @author leonel
 */
public class SingletonId {
    
    private static final SingletonId instancia = new SingletonId();
    private int idMedicamento = 0;
    
    private SingletonId(){}
    
    public static SingletonId getInstancia(){
        return instancia;
    }
    
    public int generarId(){
        return idMedicamento++;
    }
    
}
