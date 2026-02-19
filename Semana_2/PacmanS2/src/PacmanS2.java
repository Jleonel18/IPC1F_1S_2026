import java.util.Scanner;
import java.util.Random;

public class PacmanS2 {
    private static final int FILAS_PEQUENO = 5;
    private static final int COLUMNAS_PEQUENO =6;
    private static final String fantasma = "@";
    private static final String premio = "0";
    private static final String premio_especial = "$";
    private static final String pared = "X";
    private static final String pacman = "<";
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    
    public static void main(String [] args){
        
        System.out.println("Bienvenido a pacman:");
        int opcionInicio;
    
        System.out.println("Elija las opciones");
        System.out.println("Elija 1 para crear el tablero");
        System.out.println("elija 2 para ver los puntos");
        System.out.println("Elija 3 para salir");
        
        opcionInicio = sc.nextInt();
        opcionElegida(opcionInicio);

        System.out.println("Gracias por jugar PAC-MAN :)");
        
    }
    
    public static void opcionElegida(int opcionInicio){
        
            int numeroPremios;
            int numeroParedes;
            int numeroTrampas;
        
        switch (opcionInicio) {
                case 1:
                    System.out.println("Por favor, ingresa los siguientes valores:");
                    
                    //Asigna premios
                    numeroPremios = asignarCantidades("premios", 0.4);
                    
                    //Asigna paredes
                    numeroParedes = asignarCantidades("paredes",0.2);
                    
                    //Asigna trampas
                    numeroTrampas = asignarCantidades("fantasmas",0.4);
                    
                    System.out.println("Creando tablero...");
                    
                    String[][] tablero = new String[FILAS_PEQUENO][COLUMNAS_PEQUENO];
                    
                    //Llenando de espacios la matriz
                    for(int i = 0; i < FILAS_PEQUENO; i++){
                        for(int j = 0; j < COLUMNAS_PEQUENO; j++){
                            tablero[i][j]= " ";
                        }
                    }
                    
                    //Poniendo premios en la matriz
                    colocarElemento(tablero, premio,numeroPremios);
                    
                    //Poniendo las paredes en la matriz
                    colocarElemento(tablero, pared, numeroParedes);
                    
                    //Poniendo fantasmas
                    colocarElemento(tablero,fantasma,numeroTrampas);
                    
                    System.out.println("Tablero de "+FILAS_PEQUENO+"X"+COLUMNAS_PEQUENO+" creado!");
                    
                    imprimirTablero(tablero);
                    System.out.println();
                    
                    int personajeFila;
                    int personajeColumna;
                    
                    do{
                        System.out.println("Escoja donde desea colocar al personaje:");
                        System.out.print("Filas: ");
                        personajeFila = sc.nextInt();
                        System.out.print("Columnas: ");
                        personajeColumna = sc.nextInt();
                        if(verificacionPosicionPersonaje(personajeFila,personajeColumna,tablero) == false){
                            System.out.println("Posición no válida, escoja otro lugar");
                        }
                    }while(verificacionPosicionPersonaje(personajeFila,personajeColumna,tablero) == false);
                    
                    
                    System.out.println("Personaje colocado exitosamente!");
                    tablero[personajeFila-1][personajeColumna-1] = "<";
                    imprimirTablero(tablero);
                    int[] posicion = { personajeFila -1, personajeColumna -1 };
                    
                    String movimientoPersonaje;
                    
                    do{
                        System.out.println("Mueve tu personaje");
                        System.out.println("8. Arriba");
                        System.out.println("5. Abajo");
                        System.out.println("6. Derecha");
                        System.out.println("4. Izquierda");
                        System.out.print(">");
                        
                        movimientoPersonaje = sc.next();
                        
                        if(!movimientoPersonaje.equals("F")){
                            moverPersonaje(tablero,posicion,movimientoPersonaje);
                            imprimirTablero(tablero);
                        }
                    }while(!movimientoPersonaje.equals("F"));
                    

                    break;
                case 2:
                    //TODO: IMPLEMENTAR SECCION DE PUNTOS
                    break;
                case 3:
                    // TODO: IMPLEMENTAR SALIR
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo");
        }
        
    }
    
    public static boolean moverPersonaje(String[][] tablero, int[] posicion, String direccion){
        int filaActual = posicion[0];
        int columnaActual = posicion[1];
        
        int nuevaFila = filaActual;
        int nuevaColumna = columnaActual;
        
        switch(direccion){
            case "8": //Arriba
                nuevaFila--;
                //nuevaFila = nuevaFila-1;
                break;
            case "6": //Derecha
                nuevaColumna++;
                break;
            default:
                System.out.println("Movimiento no válido");
                return false;
        }
        
        //TODO: Verificar Límites
        
        //Verificar Paredes:
        if(tablero[nuevaFila][nuevaColumna].equals(pared)){
            System.out.println("Choque contra una pared");
            return false;
        }
        
        //TODO: Aplicar sistema de premios
        
        if(tablero[nuevaFila][nuevaColumna].equals(fantasma)){
            System.out.println("Te atrapó un fantasma!");
        }
        
        tablero[filaActual][columnaActual]=" ";
        tablero[nuevaFila][nuevaColumna]=pacman;
        
        posicion[0] = nuevaFila;
        posicion[1] = nuevaColumna;
        
        return true;
    }
    
    public static void imprimirTablero(String[][] tablero) {

        // Borde superior
        for (int j = 0; j < COLUMNAS_PEQUENO + 2; j++) {
            System.out.print("--");
        }
        System.out.println();

        // Filas del tablero
        for (int i = 0; i < FILAS_PEQUENO; i++) {
            System.out.print("|");

            for (int j = 0; j < COLUMNAS_PEQUENO; j++) {
                System.out.print(tablero[i][j] + " ");
            }

            System.out.println("|");
        }

        // Borde inferior
        for (int j = 0; j < COLUMNAS_PEQUENO + 2; j++) {
            System.out.print("--");
        }
        System.out.println();
    }

    
    public static boolean verificacionPosicionPersonaje(int x, int y, String[][] tablero){
        
        if(tablero[x-1][y-1].equals(" ")){
            return true;
        }
        
        //Posible método para poder implementar choques con paredes.
        
        return false;
        
    }
    
    public static int asignarCantidades(String objeto, double porcentaje){
        boolean bandera = false;
        int cantidad = 1;
        while(bandera == false){
            System.out.print("Elige la cantidad de "+objeto+": ");
            cantidad = sc.nextInt();
            bandera = true;
            int maxPremios = (int)(FILAS_PEQUENO * COLUMNAS_PEQUENO * porcentaje);
            if (cantidad < 1 || cantidad > maxPremios) {
                System.out.println("Por favor ingresa una cantidad correcta.");
                bandera = false;
            }  
        }
        
        return cantidad;
        
    }
    
    public static void colocarElemento(String[][] tablero, String simbolo, int cantidad) {
        int colocados = 0;

        while (colocados < cantidad) {
            int fila = rand.nextInt(FILAS_PEQUENO);
            int columna = rand.nextInt(COLUMNAS_PEQUENO);

            if (tablero[fila][columna].equals(" ")) {
                tablero[fila][columna] = simbolo;
                colocados++;
            }
        }
    }

}