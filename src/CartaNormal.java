import java.util.Arrays;
import java.util.Scanner;

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
    public String toString(){
        return "color: " + super.color + ", numero: " + this.numero;
    }

}
