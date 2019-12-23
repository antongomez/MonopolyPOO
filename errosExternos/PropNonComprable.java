package errosExternos;

public class PropNonComprable extends ErroExterno {

    public PropNonComprable(String nomeCasilla, String tipoOperación) {
        super("A casilla " + nomeCasilla + " non se pode "
                + tipoOperación + ".");
    }
}
