public class Carta_especial extends Carta{
    private String tipo;

    public Carta_especial(String color, String tipo){
        super(color);
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }
}
