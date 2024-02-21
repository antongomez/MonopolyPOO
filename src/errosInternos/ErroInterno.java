package errosInternos;

import Excepcions.MonoExcep;

public class ErroInterno extends MonoExcep {

    public ErroInterno(String mensaxe) {
        super(mensaxe);
    }

    public ErroInterno() {
        super("Erro interno. O erro pode afectar ao desenvolvemento do xogo.\n");
    }
}
