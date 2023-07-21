public class Jugador {
    private String nombre;
    private int numeroCartas;
    private final int NUMERO_MAXIMO_CARTAS = 20;
    private Carta[] cartas;
    StringBuilder sb = new StringBuilder();

    public Jugador(String nombre){
        this.nombre = nombre;
        this.numeroCartas = 0;
        this.cartas = new Carta[NUMERO_MAXIMO_CARTAS];
    }

    public void setCartas(String color, int numero){
        if (numero < 10){
            this.cartas[this.numeroCartas] = new CartaNormal(color, numero);
            this.numeroCartas++;
        } else {
            this.cartas[this.numeroCartas] = new CartaEspecial(color, Carta.getListaCartasEspeciales()[numero - 10]);
            this.numeroCartas++;
        }
    }

    public String getCartas(){
        sb = new StringBuilder();
        for(int i = 0; i < this.numeroCartas; i++){
            sb.append(i + 1).append(") ").append(this.cartas[i]).append("\n");
        }
        return sb.toString();
    }

    public Carta getCarta(int cartaSeleccionada){
        return this.cartas[cartaSeleccionada - 1];
    }

    public int getNumeroCartas() {
        return this.numeroCartas;
    }

    @Override
    public String toString(){
        sb = new StringBuilder();
        return this.nombre + " " + getCartas();
    }

    public void usarCartaUsuario(int cartaSeleccionada){
        for(int i = cartaSeleccionada - 1; i < this.numeroCartas; i++){
            this.cartas[i] = this.cartas[i + 1];
        }
        this.numeroCartas--;
    }
}
