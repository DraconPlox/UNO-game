public class Juego {
    private String nombre;
    private int numero_jugadores;
    private final int NUMERO_MAXIMO_JUGADORES = 10;
    private Jugador[] jugadores;


    public Juego(String nombre, int numero_jugadores){
        this.nombre = nombre;
        this.numero_jugadores = numero_jugadores;
        this.jugadores = (this.numero_jugadores < this.NUMERO_MAXIMO_JUGADORES) ? new Jugador[this.numero_jugadores] : new Jugador[this.NUMERO_MAXIMO_JUGADORES];
    }


}
