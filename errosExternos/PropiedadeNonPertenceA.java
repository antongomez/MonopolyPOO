package errosExternos;

public class PropiedadeNonPertenceA extends ErroExterno {

    public PropiedadeNonPertenceA(String nomePropiedade, String nomeXogador) {
        super("A propiedade " + nomePropiedade + " non pertence ao xogador "
                + nomeXogador + ".");
    }
}
