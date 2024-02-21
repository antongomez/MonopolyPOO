package errosExternos;

public class PropiedadeNonHipotecada extends ErroExterno {

    public PropiedadeNonHipotecada(String nomePropiedade) {
        super("A propiedade " + nomePropiedade + " non está hipotecada.");
    }
}
