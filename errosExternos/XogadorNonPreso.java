package errosExternos;

public class XogadorNonPreso extends ErroExterno {

    public XogadorNonPreso(String nomeXogador) {
        super("O xogador " + nomeXogador + " non está preso.");
    }
}
