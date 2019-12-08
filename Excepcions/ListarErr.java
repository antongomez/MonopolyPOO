package Excepcions;

public class ListarErr extends MonoExcep {
    public ListarErr(String mensaxe) {
        super(mensaxe);
        System.out.println( "ERROR: Lisstar");
    }
}
