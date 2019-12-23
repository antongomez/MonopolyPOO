package errosExternos;

public class ErroSintaxe extends ErroExterno {

    public ErroSintaxe(String mensaxe) {
        super(mensaxe);
    }

    public ErroSintaxe() {
        super("Houbo un erro de sintaxe");
    }
}
