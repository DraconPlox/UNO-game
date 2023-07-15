public class Carta {
    protected String color;

    public Carta(String color){
        this.color = color;
    }

    protected String getColor() {
        return this.color;
    }

    public static String[] getListaCartasEspeciales(){
        String[] output = new String[] {"bloqueo", "cambiar dirección", "sumar dos", "sumar cuatro", "cambiar color"};
        return output;
    }

}
