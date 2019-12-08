package Excepcions;

public class Hipotecar extends Hipotecas {
    public Hipotecar(String mensaxe) {
        super(mensaxe);
        System.out.println("Error en hipotecar.");
    }
}
