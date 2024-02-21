package errosExternos;

import Excepcions.MonoExcep;

public class ErroExterno extends MonoExcep {

    public ErroExterno(String mensaxe) {
        super(mensaxe);
    }

    public ErroExterno() {
        super("Erro externo. Débese tratar.\n");
    }
}
