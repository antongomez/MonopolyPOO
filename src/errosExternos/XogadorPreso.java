package errosExternos;

public class XogadorPreso extends ErroExterno {

    public XogadorPreso(String nomeXogador) {
        super("O xogador " + nomeXogador + " está preso, non se pode mover.");
    }
}
