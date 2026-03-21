
package ejemplohilos;

/**
 *
 * @author leonel
 */
public class PartidaModel {
    
    private int idPartida;
    private PersonajeModel jugador1;
    private PersonajeModel jugador2;
    private int punteoJugador1;
    private int punteoJugador2;

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public PersonajeModel getJugador1() {
        return jugador1;
    }

    public void setJugador1(PersonajeModel jugador1) {
        this.jugador1 = jugador1;
    }

    public PersonajeModel getJugador2() {
        return jugador2;
    }

    public void setJugador2(PersonajeModel jugador2) {
        this.jugador2 = jugador2;
    }

    public int getPunteoJugador1() {
        return punteoJugador1;
    }

    public void setPunteoJugador1(int punteoJugador1) {
        this.punteoJugador1 = punteoJugador1;
    }

    public int getPunteoJugador2() {
        return punteoJugador2;
    }

    public void setPunteoJugador2(int punteoJugador2) {
        this.punteoJugador2 = punteoJugador2;
    }

    @Override
    public String toString() {
        return "PartidaModel{" + "idPartida=" + idPartida + ", jugador1=" + jugador1 + ", jugador2=" + jugador2 + ", punteoJugador1=" + punteoJugador1 + ", punteoJugador2=" + punteoJugador2 + '}';
    }
    
    
    
    
}
