package Excepcions;

public class FalsaPropiedade extends MonoExcep {

    public FalsaPropiedade(String mensaxe) {
        super(mensaxe);
    }

    public FalsaPropiedade() {
        super("A casilla non é da túa propiedade");
    }
}
