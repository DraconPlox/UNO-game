public class Carta_normal extends Carta{
    private int numero;

    public Carta_normal(String color, int numero){
        super(color);
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }
}
