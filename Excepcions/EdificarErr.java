package Excepcions;

public class EdificarErr extends MonoExcep {
    public EdificarErr(String mensaxe) {
        super(mensaxe);
        System.out.println(" Error en Edificar ");
    }
}
