package errosExternos;

public class PropNonComprable extends ErroExterno {

    public PropNonComprable(String nomeCasilla, String tipoOperacion) {
        super("A casilla " + nomeCasilla + " non se pode "
                + tipoOperacion + ".");
    }
}
