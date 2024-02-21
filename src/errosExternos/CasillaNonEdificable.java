package errosExternos;

public class CasillaNonEdificable extends ErroExterno {

    public CasillaNonEdificable(String nomeCasilla) {
        super("Na casilla " + nomeCasilla + " non se pode edificar.");
    }

    public CasillaNonEdificable(String nomeXogador, String nomeCasilla) {
        super("O xogador " + nomeXogador + " non reúne os requisitos para "
                + "edificar na casilla " + nomeCasilla + ".");
    }
}
