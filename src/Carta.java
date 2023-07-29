public abstract class Carta{
    protected String color;

    public Carta(String color){
        this.color = color;
    }

    protected String getColor(){
        return this.color;
    }

    public static String[] getListaColores(){
        return new String[] {"rojo", "verde", "amarillo", "azul"};
    }


}
