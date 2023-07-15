public class CartaEspecial extends Carta{
    protected String tipo;

    public CartaEspecial(String color, String tipo){
        super(color);
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    @Override
    protected String getColor() {
        return super.getColor();
    }

    @Override
    public String toString(){
        return "color: " + super.color + ", tipo: " + this.tipo;
    }

}
