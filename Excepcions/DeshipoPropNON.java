package Excepcions;

public class DeshipoPropNON extends Deshipotecar {
    public DeshipoPropNON(String mensaxe) {
        super(mensaxe);
        System.out.println("Par poder deshipotecar debe ser unha propieade hipotecada.");
    }
}
