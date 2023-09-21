public class Jugador {
    private String nombre;
    private int numeroCartas;
    private final int NUMERO_MAXIMO_CARTAS = 20;
    private Carta[] cartas;
    StringBuilder sb = new StringBuilder();

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.numeroCartas = 0;
        this.cartas = new Carta[NUMERO_MAXIMO_CARTAS];
    }

    public void setCarta(String color, int numero) {
        if (numero < 10) {
            this.cartas[this.numeroCartas] = new CartaNormal(color, numero);
            this.numeroCartas++;
        } else {
            this.cartas[this.numeroCartas] = new CartaEspecial(color, CartaEspecial.getListaCartasEspeciales()[numero - 10]);
            this.numeroCartas++;
        }
    }

    public String getCartas() {
        sb = new StringBuilder();
        for (int i = 0; i < this.numeroCartas; i++) {
            sb.append(i + 1).append(") ").append(this.cartas[i]).append("\n");
        }
        return sb.toString();
    }

    public Carta getCarta(int cartaSeleccionada) {
        return this.cartas[cartaSeleccionada];
    }

    public int getNumeroCartas() {
        return this.numeroCartas;
    }

    @Override
    public String toString() {
        sb = new StringBuilder();
        return this.nombre + " " + getCartas();
    }

    public void usarCartaUsuario(int cartaSeleccionada) {
        for (int i = cartaSeleccionada; i < this.numeroCartas; i++) {
            this.cartas[i] = this.cartas[i + 1];
        }
        this.numeroCartas--;
    }

    public boolean tieneCartaValida(Carta cartaActual){
        boolean output = false;
        if (cartaActual instanceof CartaNormal){
            CartaNormal cartaNormalActual = (CartaNormal) cartaActual;
            for (int i = 0; i < this.numeroCartas; i++){
                if (this.cartas[i] instanceof CartaNormal){
                    if (cartaNormalActual.getColor().equals(this.cartas[i].getColor()) || cartaNormalActual.getNumero() == ((CartaNormal) this.cartas[i]).getNumero()){
                        output = true;
                        break;
                    }
                } else {
                    if (cartaNormalActual.getColor().equals(this.cartas[i].getColor()) || this.cartas[i].getColor().equals("-")){
                        output = true;
                        break;
                    }
                }
            }
        } else {
            CartaEspecial cartaEspecialActual = (CartaEspecial) cartaActual;
            for (int i = 0; i < this.numeroCartas; i++){
                if (this.cartas[i] instanceof CartaEspecial){
                    if (cartaEspecialActual.getColor().equals(this.cartas[i].getColor()) || cartaEspecialActual.getTipo().equals(((CartaEspecial) this.cartas[i]).getTipo()) || (this.cartas[i].getColor().equals("-"))){
                        output = true;
                        break;
                    }
                } else {
                    if (cartaEspecialActual.getColor().equals(this.cartas[i].getColor())){
                        output = true;
                        break;
                    }
                }
            }
        }
        return output;
    }

    public boolean llegaLimiteCartas() {
        return this.numeroCartas >= this.NUMERO_MAXIMO_CARTAS;
    }
}

