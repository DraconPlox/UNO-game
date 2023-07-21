public class CartaEspecial extends Carta{
    private String tipo;

    public CartaEspecial(String color, String tipo){
        super(color);
        this.tipo = tipo;
        if(this.tipo == "cambiar color"){
            this.color = "-";
        }
    }

    public String getTipo() {
        return this.tipo;
    }

    @Override
    public String toString(){
        return "color: " + super.color + ", tipo: " + this.tipo;
    }

}
