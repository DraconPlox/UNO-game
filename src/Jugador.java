public class Jugador {
    private String nombre;
    private int numeroCartas;
    private final int NUMERO_MAXIMO_CARTAS = 20;
    private Carta[] cartas;

    public Jugador(String nombre, int numeroCartas, Carta[] cartas){
        this.nombre = nombre;
        this.numeroCartas = numeroCartas;
        this.cartas = new Carta[NUMERO_MAXIMO_CARTAS];
    }

}
