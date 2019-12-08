package Excepcions;

public class HipoDono extends Hipotecar {
    public HipoDono(String mensaxe, String nome) {
        super(mensaxe);
        System.out.println("Está casilla non é do xogador " + nome);
    }
}
