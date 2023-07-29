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
            } else {
                System.out.println("La carta es incompatible.");
            }
        } else {
            //TODO Hacer todo el algoritmo con las cartas especiales y hacer el algoritmo de cada carta especial (como los cambia color o dirección, por ejemplo).
            CartaEspecial cartaSeleccionadaEspecial = (CartaEspecial) this.jugadores[0].getCarta(cartaSeleccionada - 1);
        }
    }

    public void empezarJuego() {
        do {
            sb = new StringBuilder();
            sb.append("--------------------------------------\n").append("Carta Actual: ").append("\n").append(this.cartaActual).append("\n").append("--------------------------------------\n");
            System.out.print(sb.toString());
            for (int i = 0; i < this.jugadores.length; i++) {
                sb = new StringBuilder();
                //TODO Cambiar el for y hacer que empieze en el 1 ya que es redundante repetir el bucle del usuario, mejor automatizar en un for solo lo de los bots.
                if (i == 0) {
                    if (jugadorTieneCartaValida()){
                        sb.append("Tus cartas:\n").append(this.jugadores[i].getCartas()).append("\nQue carta deseas usar?: ");
                        System.out.print(sb.toString());
                        turnoJugador();
                    } else {
                        sb.append("Tus cartas:\n").append(this.jugadores[i].getCartas()).append("No tienes cartas validas, añadiendo..\n");
                        System.out.print(sb.toString());
                        sb = new StringBuilder();
                        for (int j = 0; j < 2; j++){
                            if(!jugadores[i].llegaLimiteCartas()){
                                this.jugadores[i].setCarta(Carta.getListaColores()[random.nextInt(4)], random.nextInt(15));
                                if (jugadorTieneCartaValida()){
                                    break;
                                }
                            } else {
                                System.out.println("No puedes agarrar mas cartas, estas en el limite.");
                            }
                        }
                        //MIRAR ESTA PARTE
                        try {
                            Thread.sleep(4000);
                            if (jugadorTieneCartaValida()){
                                sb.append("--------------------------------------\n").append("Carta Actual: ").append("\n").append(this.cartaActual).append("\n").append("--------------------------------------\n").append("Tus cartas:\n").append(this.jugadores[i].getCartas()).append("\nQue carta deseas usar?: ");
                                System.out.print(sb.toString());
                                turnoJugador();
                            } else {
                                System.out.println("Añadidas dos cartas, sigues sin cartas validas. Pasando turno..");
                            }
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                } else {
                    //TODO Hacer toda el algoritmo de los bots. Que sea parecido al usuario pero automatizado y mas simplificado.
                }
            }
        } while (/*!comprobarFinPartida()*/ true);
    }
}