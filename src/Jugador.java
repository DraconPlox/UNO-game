public class Jugador {
    private String nombre;
    private int numeroCartas;
    private final int NUMERO_MAXIMO_CARTAS = 20;
    private Carta[] cartas;

    public Jugador(String nombre, int numeroCartas){
        this.nombre = nombre;
        this.numeroCartas = numeroCartas;
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

    private String getCartas(){
        String output = "";
        for(int i = 0; i < this.numeroCartas; i++){
            output += this.cartas[i] + " ";
        }
        return output;
    }
    @Override
    public String toString(){
        return this.nombre + " " + getCartas();
    }
}
