public class CartaNormal extends Carta{
    private int numero;

    public CartaNormal(String color, int numero){
        super(color);
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    @Override
    protected String getColor() {
        return super.getColor();
    }
}
