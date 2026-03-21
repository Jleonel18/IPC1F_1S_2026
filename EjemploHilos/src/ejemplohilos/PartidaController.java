
package ejemplohilos;

import static ejemplohilos.PersonajeController.personajes;

/**
 *
 * @author leonel
 */
public class PartidaController {
    
    public static PartidaModel[] partidas = new PartidaModel[100];
    
    public void agregarPartidasQuemadas(){
        PartidaModel partida1 = new PartidaModel();
        PartidaModel partida2 = new PartidaModel();
        
        partida1.setIdPartida(1);
        partida1.setJugador1(personajes[0]);
        partida1.setJugador2(personajes[1]);
        
        partida1.setPunteoJugador1(100);
        partida1.setPunteoJugador2(200);
        
        partida2.setIdPartida(2);
        partida2.setJugador1(personajes[0]);
        partida2.setJugador2(personajes[2]);
        
        partida2.setPunteoJugador1(200);
        partida2.setPunteoJugador2(50);
        
        
        partidas[0] = partida1;
        partidas[1] = partida2;
     
      
    }
    
    public String[][] datosGraficaPersonaje(){
        String[] nombres = new String[100];
        int[] puntajesTotales = new int[100];
        int cantidadJugadores = 0;
        
        //Recorrer todas las partidas
        for(PartidaModel p: partidas){
            
            if(p == null) continue;
            
            //Jugador 1
            String nombre1 = p.getJugador1().getNombre();
            int puntos1 = p.getPunteoJugador1();
            
            int posicion1 = -1;
            
            for(int j= 0; j<cantidadJugadores; j++){
                if(nombres[j].equals(nombre1)){
                    posicion1 = j;
                    break;
                }
            }
            
            if(posicion1 == -1){
                nombres[cantidadJugadores] = nombre1;
                puntajesTotales[cantidadJugadores] = puntos1;
                cantidadJugadores++;
            }else{
                puntajesTotales[posicion1] += puntos1;
            }
            
            //Jugador2
            String nombre2 = p.getJugador2().getNombre();
            int puntos2 = p.getPunteoJugador2();
            int posicion2 = -1;
            
            for(int j = 0; j<cantidadJugadores; j++){
                if(nombres[j].equals(nombre2)){
                    posicion2 = j;
                    break;
                }
            }
            
            if(posicion2 == -1){
                nombres[cantidadJugadores] = nombre2;
                puntajesTotales[cantidadJugadores] = puntos2;
                cantidadJugadores++;
            }else{
                puntajesTotales[posicion2] += puntos2;
            }
        }
        
        // Ordenar de mayor a menor
        for(int i = 0; i<cantidadJugadores -1; i++){
            for(int j = 0; j < cantidadJugadores - 1 - i; j++){
                if(puntajesTotales[j] < puntajesTotales[j+1]){
                    
                    int auxPuntos = puntajesTotales[j];
                    puntajesTotales[j] = puntajesTotales[j+1];
                    puntajesTotales[j+1] = auxPuntos;
                    
                    String auxNombre = nombres[j];
                    nombres[j] = nombres[j+1];
                    nombres[j+1] = auxNombre;
                    
                }
            }
        }
        
        //Creamos la matriz de resultado
        String [][] resultado = new String[cantidadJugadores][2];
        for(int i=0; i< cantidadJugadores; i++){
            resultado[i][0] = nombres[i];
            resultado[i][1] = String.valueOf(puntajesTotales[i]);
        }
        return resultado;
    }
    
}
