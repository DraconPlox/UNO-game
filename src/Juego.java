import java.util.Random;
import java.util.Scanner;

public class Juego {
    private int numeroJugadores;
    private final int NUMERO_MAXIMO_JUGADORES = 5;
    private Jugador[] jugadores;
    private String[] listaColores = {"rojo", "verde", "amarillo", "azul"};
    private String colorActual;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();


    public Juego(){
        System.out.print("Bienvenido al juego del UNO. Cuantos jugadores van a haber?: ");
        this.numeroJugadores = scanner.nextInt();
        if(this.numeroJugadores > this.NUMERO_MAXIMO_JUGADORES){
            this.numeroJugadores = this.NUMERO_MAXIMO_JUGADORES;
        }
        this.jugadores = new Jugador[this.numeroJugadores];
        System.out.println("Asignando las cartas a los jugadores...");
        asignarJugadores();
        asignarCartas();
    }

    private void asignarJugadores(){
        for (int i = 0; i < this.numeroJugadores; i++){
            this.jugadores[i] = new Jugador("Jugador" + (i + 1), 0);
        }
    }

    private void asignarCartas(){
        for (int i = 0; i < this.jugadores.length; i++){
            for (int j = 0; j < 7; j++){
                this.jugadores[i].setCartas(this.listaColores[random.nextInt(3) + 1], random.nextInt(14) + 1);
            }
            //DEBUG. Eliminar mas tarde.
            System.out.println(this.jugadores[i]);
        }
    }

    public void empezarJuego(){

    }


}
