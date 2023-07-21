import java.util.Random;
import java.util.Scanner;

public class Juego {
    private final int NUMERO_MAXIMO_JUGADORES = 5;
    private final String[] LISTA_COLORES = {"rojo", "verde", "amarillo", "azul"};
    private Jugador[] jugadores;
    private int numeroJugadores;
    private String colorActual;
    Carta cartaActual;
    private Scanner scanner;
    private Random random;
    StringBuilder sb;


    public Juego(){
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.sb = new StringBuilder();
        System.out.print("Bienvenido al juego del UNO. Cuantos jugadores van a haber?: ");
        this.numeroJugadores = scanner.nextInt();
        if (this.numeroJugadores > this.NUMERO_MAXIMO_JUGADORES) {
            this.numeroJugadores = this.NUMERO_MAXIMO_JUGADORES;
        }
        this.jugadores = new Jugador[this.numeroJugadores];
        this.cartaActual = new CartaNormal(this.LISTA_COLORES[random.nextInt(4)], random.nextInt(10));
        this.colorActual = this.cartaActual.getColor();
        System.out.println("Asignando las cartas a los jugadores...");
        asignarJugadores();
        asignarCartas();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Listo, que empieze el juego!");
        empezarJuego();
    }

    private void asignarJugadores() {
        for (int i = 0; i < this.numeroJugadores; i++) {
            this.jugadores[i] = new Jugador("Jugador" + (i + 1));
        }
    }

    private void asignarCartas() {
        for (int i = 0; i < this.jugadores.length; i++) {
            for (int j = 0; j < 7; j++) {
                this.jugadores[i].setCartas(this.LISTA_COLORES[random.nextInt(4)], random.nextInt(15));
            }
        }
    }

    private boolean comprobarFinPartida() {
        boolean output = false;
        for (int i = 0; i < this.jugadores.length; i++) {
            if (this.jugadores[i].getNumeroCartas() == 0) {
                output = true;
                break;
            }
        }
        return output;
    }

    public void empezarJuego() {
        do{
            sb = new StringBuilder();
            sb.append("--------------------------------------\n").append("Carta Actual: ").append("\n").append(this.cartaActual).append("\n").append("--------------------------------------\n");
            System.out.print(sb.toString());
            for (int i = 0; i < this.jugadores.length; i++) {
                sb = new StringBuilder();
                if (i == 0) {
                    sb.append("Tus cartas:").append("\n").append(this.jugadores[i].getCartas()).append("\nQue carta deseas usar?: ");
                    System.out.print(sb.toString());
                    int cartaSeleccionada = scanner.nextInt();
                    if ((cartaSeleccionada < 0) || (cartaSeleccionada > this.jugadores[i].getNumeroCartas())){
                        System.out.println("Selección invalida.");
                        break;
                    }
                    if (this.jugadores[i].getCarta(cartaSeleccionada) instanceof CartaNormal) {
                        CartaNormal cartaSeleccionadaNormal = (CartaNormal) this.jugadores[i].getCarta(cartaSeleccionada);
                        if (cartaSeleccionadaNormal.getColor().equals(this.cartaActual.getColor()) || cartaSeleccionadaNormal.getNumero() == ((CartaNormal) this.cartaActual).getNumero()){
                            this.cartaActual = this.jugadores[i].getCarta(cartaSeleccionada);
                            this.jugadores[i].usarCartaUsuario(cartaSeleccionada);
                            System.out.println(this.cartaActual);
                        } else {
                            System.out.println("La carta es incompatible.");
                            break;
                        }
                    } else {
                        //TODO Hacer todo el algoritmo con las cartas especiales y hacer el algoritmo de cada carta especial (como los cambia color o dirección, por ejemplo).
                    }
                } else {
                    //TODO Hacer toda el algoritmo de los bots. Que sea parecido al usuario pero automatizado y mas simplificado.
                }
            }
        } while (/*!comprobarFinPartida()*/ true);
    }
}