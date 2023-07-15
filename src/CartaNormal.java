public class CartaNormal extends Carta{
    protected int numero;

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

    @Override
    public String toString(){
        return "color: " + super.color + ", numero: " + this.numero;
    }

}
