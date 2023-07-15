public class CartaEspecial extends Carta{
    private String tipo;

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
}
