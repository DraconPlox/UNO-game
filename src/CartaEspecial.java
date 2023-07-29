public class CartaEspecial extends Carta{
    private String tipo;

    public CartaEspecial(String color, String tipo){
        super(color);
        this.tipo = tipo;
        if(this.tipo == "cambiar color"){
            this.color = "-";
        }
    }

    public void setColor(String color){
        this.color = color;
    }
    public String getTipo() {
        return this.tipo;
    }

    public static String[] getListaCartasEspeciales(){
        return new String[] {"bloqueo", "cambiar dirección", "sumar dos", "sumar cuatro", "cambiar color"};
    }

    @Override
    public String toString(){
        return "color: " + super.color + ", tipo: " + this.tipo;
    }

}
