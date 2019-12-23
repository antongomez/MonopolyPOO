package errosExternos;

import Excepcions.MonoExcep;
import estrutura.Propiedade;

public class NonPropiedade extends ErroExterno {

    public NonPropiedade(String mensaxe) {
        super(mensaxe);
    }

    public NonPropiedade(Propiedade propiedade) {
        super("A propiedade " + propiedade.getNome() + " non é túa.");
    }
}
