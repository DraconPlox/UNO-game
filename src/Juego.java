import java.util.Random;
import java.util.Scanner;

public class Juego {
    private final int NUMERO_MAXIMO_JUGADORES = 5;
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
        this.cartaActual = new CartaNormal(Carta.getListaColores()[random.nextInt(4)], random.nextInt(10));
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
        juego();
    }

    private void asignarJugadores() {
        for (int i = 0; i < this.numeroJugadores; i++) {
            this.jugadores[i] = new Jugador("Jugador" + (i + 1));
        }
    }

    private void asignarCartas() {
        for (int i = 0; i < this.jugadores.length; i++) {
            for (int j = 0; j < 7; j++) {
                this.jugadores[i].setCarta(Carta.getListaColores()[random.nextInt(4)], random.nextInt(15));
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

    private boolean jugadorTieneCartaValida(){
        return this.jugadores[0].tieneCartaValida(this.cartaActual);
    }

    private void turnoJugador(){
        int a;
        do {
            a = 1;
            int cartaSeleccionada = scanner.nextInt();
            if ((cartaSeleccionada < 0) || (cartaSeleccionada > this.jugadores[0].getNumeroCartas())){
                System.out.println("Selección invalida.");
            }
            if (this.jugadores[0].getCarta(cartaSeleccionada - 1) instanceof CartaNormal) {
                CartaNormal cartaSeleccionadaNormal = (CartaNormal) this.jugadores[0].getCarta( cartaSeleccionada - 1);
                if (cartaSeleccionadaNormal.getColor().equals(this.cartaActual.getColor()) || cartaSeleccionadaNormal.getNumero() == ((CartaNormal) this.cartaActual).getNumero()){
                    this.cartaActual = this.jugadores[0].getCarta(cartaSeleccionada - 1);
                    this.jugadores[0].usarCartaUsuario(cartaSeleccionada);
                    System.out.println(this.cartaActual);
                    a--;
                } else {
                    System.out.println("La carta es incompatible.");
                }
            } else {
                //TODO Hacer todo el algoritmo con las cartas especiales y hacer el algoritmo de cada carta especial (como los cambia color o dirección, por ejemplo).
                CartaEspecial cartaSeleccionadaEspecial = (CartaEspecial) this.jugadores[0].getCarta(cartaSeleccionada - 1);
            }
        } while (a >= 1);
    }

    public void juego() {
        do {
            sb = new StringBuilder();
            sb.append("--------------------------------------\n").append("Carta Actual: ").append("\n").append(this.cartaActual).append("\n").append("--------------------------------------\n");
            System.out.print(sb.toString());
            int turnoJugadorFinalizado;
            do {
                turnoJugadorFinalizado = 1;
                sb = new StringBuilder();
                if (jugadorTieneCartaValida()) {
                    sb.append("Tus cartas:\n").append(this.jugadores[0].getCartas()).append("\nQue carta deseas usar?: ");
                    System.out.print(sb.toString());
                    turnoJugador();
                    turnoJugadorFinalizado--;
                } else {
                    sb.append("Tus cartas:\n").append(this.jugadores[0].getCartas()).append("No tienes cartas validas, añadiendo..\n");
                    System.out.print(sb.toString());
                    sb = new StringBuilder();
                    for (int j = 0; j < 2; j++) {
                        if (!jugadores[0].llegaLimiteCartas()) {
                            this.jugadores[0].setCarta(Carta.getListaColores()[random.nextInt(4)], random.nextInt(15));
                            if (jugadorTieneCartaValida()) {
                                break;
                            }
                        } else {
                            System.out.println("No puedes agarrar mas cartas, estas en el limite.");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(4000);
                        if (jugadorTieneCartaValida()) {
                            sb.append("--------------------------------------\n").append("Carta Actual: ").append("\n").append(this.cartaActual).append("\n").append("--------------------------------------\n").append("Tus cartas:\n").append(this.jugadores[0].getCartas()).append("\nQue carta deseas usar?: ");
                            System.out.print(sb.toString());
                            turnoJugador();
                            turnoJugadorFinalizado--;
                        } else {
                            System.out.println("Añadidas dos cartas, sigues sin cartas validas. Pasando turno..");
                            turnoJugadorFinalizado--;
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            } while (turnoJugadorFinalizado >= 1);
            //TODO Hacer toda el algoritmo de los bots. Que sea parecido al usuario pero automatizado y mas simplificado.
            for (int i = 1; i < this.jugadores.length; i++){
                System.out.println("Soy " + i);
            }
        } while (/*!comprobarFinPartida()*/ true);
    }
}