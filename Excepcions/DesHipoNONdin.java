package Excepcions;

public class DesHipoNONdin extends Deshipotecar {
    public DesHipoNONdin(String mensaxe, String nome, float dehipo) {
        super(mensaxe);
        System.out.println(nome + "non ten suficientes GM para pagar" + dehipo + "GM");
    }
}
