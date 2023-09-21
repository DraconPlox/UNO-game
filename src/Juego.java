import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Juego{
    private final int NUMERO_MAXIMO_JUGADORES = 5;
    private Jugador[] jugadores;
    private int numeroJugadores;
    private int pos;
    private Carta cartaActual;
    private boolean bloqueo;
    private int sumarCartas;
    private boolean sumar;
    private boolean cambiarDireccion;
    private Scanner scanner;
    private Random random;
    private StringBuilder sb;


    public Juego(){
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.sb = new StringBuilder();
        this.bloqueo = false;
        this.sumarCartas = 0;
        this.cambiarDireccion = false;
        this.sumar = false;
        System.out.print("Bienvenido al juego del UNO. Cuantos jugadores van a haber?: ");
        this.numeroJugadores = scanner.nextInt();
        if (this.numeroJugadores > this.NUMERO_MAXIMO_JUGADORES) {
            this.numeroJugadores = this.NUMERO_MAXIMO_JUGADORES;
        }
        this.jugadores = new Jugador[this.numeroJugadores];
        this.cartaActual = new CartaNormal(Carta.getListaColores()[random.nextInt(4)], random.nextInt(10));
        System.out.println("Asignando las cartas a los jugadores...");
        asignarJugadores();
        asignarCartas();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Listo, que empieze el juego!");
        jugar();
    }

    private void asignarJugadores(){
        for (int i = 0; i < this.numeroJugadores; i++) {
            this.jugadores[i] = new Jugador("Jugador" + (i + 1));
        }
    }

    private void asignarCartas(){
        for (int i = 0; i < this.jugadores.length; i++) {
            for (int j = 0; j < 7; j++) {
                this.jugadores[i].setCarta(Carta.getListaColores()[random.nextInt(4)], random.nextInt(15));
            }
        }
    }

    private boolean comprobarFinPartida(){
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
            int cartaSeleccionada = scanner.nextInt() - 1;
            if ((cartaSeleccionada < 0) || (cartaSeleccionada > this.jugadores[0].getNumeroCartas())){
                System.out.println("Selección invalida.");
            }
            //TODO Mirar para el futuro el poder optimizar esto y que no se vea una clara repetición en cada parte del if.
            if (this.cartaActual instanceof CartaNormal){
                if (this.jugadores[0].getCarta(cartaSeleccionada) instanceof CartaNormal){
                    CartaNormal cartaSeleccionadaNormal = (CartaNormal) this.jugadores[0].getCarta(cartaSeleccionada);
                    if (cartaSeleccionadaNormal.getColor().equals(this.cartaActual.getColor()) || cartaSeleccionadaNormal.getNumero() == ((CartaNormal) this.cartaActual).getNumero()){
                        this.cartaActual = this.jugadores[0].getCarta(cartaSeleccionada);
                        this.jugadores[0].usarCartaUsuario(cartaSeleccionada);
                        System.out.println(this.cartaActual);
                        a--;
                    } else {
                        System.out.println("La carta es incompatible");
                        System.out.print(sb.toString());
                    }
                } else {
                    CartaEspecial cartaSeleccionadaEspecial = (CartaEspecial) this.jugadores[0].getCarta(cartaSeleccionada);
                    if (cartaSeleccionadaEspecial.getColor().equals(this.cartaActual.getColor()) || cartaSeleccionadaEspecial.getColor().equals("-")){
                        this.cartaActual = this.jugadores[0].getCarta(cartaSeleccionada);
                        this.jugadores[0].usarCartaUsuario(cartaSeleccionada);
                        System.out.println(this.cartaActual);
                        hacerFuncionCartaEspecial(cartaSeleccionadaEspecial.getTipo());
                        a--;
                    } else {
                        System.out.println("La carta es incompatible");
                        System.out.print(sb.toString());
                    }
                }
            } else {
                if (this.jugadores[0].getCarta(cartaSeleccionada) instanceof CartaNormal){
                    CartaNormal cartaSeleccionadaNormal = (CartaNormal) this.jugadores[0].getCarta( cartaSeleccionada);
                    if (cartaSeleccionadaNormal.getColor().equals(this.cartaActual.getColor())){
                        this.cartaActual = this.jugadores[0].getCarta(cartaSeleccionada);
                        this.jugadores[0].usarCartaUsuario(cartaSeleccionada);
                        System.out.println(this.cartaActual);
                        a--;
                    } else {
                        System.out.println("La carta es incompatible");
                        System.out.print(sb.toString());
                    }
                } else {
                    CartaEspecial cartaSeleccionadaEspecial = (CartaEspecial) this.jugadores[0].getCarta( cartaSeleccionada);
                    if (cartaSeleccionadaEspecial.getColor().equals(this.cartaActual.getColor()) || cartaSeleccionadaEspecial.getTipo().equals(((CartaEspecial) this.cartaActual).getTipo()) || cartaSeleccionadaEspecial.getColor().equals("-")){
                        this.cartaActual = this.jugadores[0].getCarta(cartaSeleccionada);
                        this.jugadores[0].usarCartaUsuario(cartaSeleccionada);
                        System.out.println(this.cartaActual);
                        hacerFuncionCartaEspecial(cartaSeleccionadaEspecial.getTipo());
                        a--;
                    } else {
                        System.out.println("La carta es incompatible");
                        System.out.print(sb.toString());
                    }
                }
            }
        } while (a >= 1);
    }

    private void hacerFuncionCartaEspecial(String nombreTipoCarta){
        Scanner scanner = new Scanner(System.in);
        switch (nombreTipoCarta){
            case "bloqueo":
                this.bloqueo = true;
                break;
            case "cambiar dirección":
                this.cambiarDireccion = !this.cambiarDireccion;
                break;
            case "+2":
                this.sumarCartas += 2;
                this.sumar = true;
                break;
            case "+4":
                this.sumarCartas += 4;
                this.sumar = true;
                sb = new StringBuilder();
                sb.append("\n").append(Arrays.toString(Carta.getListaColores())).append("\nA que color deseas cambiar?: ");
                System.out.print(sb);
                String colorMasCuatro = scanner.nextLine();
                this.cartaActual.color = colorMasCuatro.toLowerCase();
                sb = new StringBuilder();
                break;
            case "cambiar color":
                //TODO Tener en cuenta que puede que el usuario no ponga correctamente un color, revisar eso.
                sb = new StringBuilder();
                sb.append("\n").append(Arrays.toString(Carta.getListaColores())).append("\nA que color deseas cambiar?: ");
                System.out.print(sb);
                String color = scanner.nextLine();
                this.cartaActual.color = color.toLowerCase();
                sb = new StringBuilder();
                break;
        }
    }

    private void partidaJugador(){
        sb = new StringBuilder();
        sb.append("--------------------------------------\n").append("Carta Actual: ").append("\n").append(this.cartaActual).append("\n").append("--------------------------------------\n");
        if (!this.bloqueo){
            if (jugadorTieneCartaValida()){
                sb.append("Tus cartas:\n").append(this.jugadores[this.pos].getCartas()).append("\nQue carta deseas usar?: ");
                System.out.print(sb.toString());
                turnoJugador();
            } else {
                sb.append("Tus cartas:\n").append(this.jugadores[this.pos].getCartas()).append("No tienes cartas validas, añadiendo..\n");
                System.out.print(sb.toString());
                sb = new StringBuilder();
                for (int i = 0; i < 2; i++) {
                    if (!jugadores[this.pos].llegaLimiteCartas()){
                        this.jugadores[this.pos].setCarta(Carta.getListaColores()[random.nextInt(4)], random.nextInt(15));
                        if (jugadorTieneCartaValida()){
                            break;
                        }
                    } else {
                        System.out.println("No puedes agarrar mas cartas, estas en el limite.");
                        break;
                    }
                }
                try {
                    Thread.sleep(4000);
                    if (jugadorTieneCartaValida()){
                        sb.append("--------------------------------------\n").append("Carta Actual: ").append("\n").append(this.cartaActual).append("\n").append("--------------------------------------\n").append("Tus cartas:\n").append(this.jugadores[0].getCartas()).append("\nQue carta deseas usar?: ");
                        System.out.print(sb.toString());
                        turnoJugador();
                    } else {
                        System.out.println("Añadidas dos cartas, sigues sin cartas validas. Pasando turno..");
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    System.out.println(e);
                }
            }
        } else {
            this.bloqueo = false;
        }
    }

    private boolean comprobarSumar(){
        boolean output = false;
        for (int i = 0; i < this.jugadores[pos].getNumeroCartas(); i++){
            if (this.jugadores[pos].getCarta(i) instanceof CartaEspecial){
                if (((CartaEspecial) this.jugadores[pos].getCarta(i)).getTipo().equals("+2") || ((CartaEspecial) this.jugadores[pos].getCarta(i)).getTipo().equals("+4")){
                    output = true;
                }
            }
        }
        return output;
    }

    public void jugar(){
        for (this.pos = 0; !comprobarFinPartida();){
            if (this.pos == 0){
                if (this.sumar){
                    if (comprobarSumar()){
                        partidaJugador();
                    } else {
                        System.out.println("No tienes cartas para este evento, sumando cartas..");
                        this.sumar = false;
                        for (int i = 0; i < this.sumarCartas; i++){
                            if (!this.jugadores[this.pos].llegaLimiteCartas()){
                                this.jugadores[this.pos].setCarta(Carta.getListaColores()[random.nextInt(4)], random.nextInt(15));
                            } else {
                                break;
                            }
                        }
                        this.sumarCartas = 0;
                    }
                } else {
                    partidaJugador();
                }
            } else {
                //TODO Hacer todo el algoritmo de los bots. Que sea parecido al usuario pero automatizado y mas simplificado.
                //codigo de bots.
                System.out.println("soy" + this.pos);
            }
            if (this.pos == 0){
                if (this.cambiarDireccion){
                    this.pos = this.jugadores.length - 1;
                } else {
                    this.pos++;
                }
            } else if (this.pos == this.jugadores.length - 1){
                if (this.cambiarDireccion){
                    this.pos--;
                } else {
                    this.pos = 0;
                }
            } else {
                if (this.cambiarDireccion){
                    this.pos--;
                } else {
                    this.pos++;
                }
            }
        }
    }
}