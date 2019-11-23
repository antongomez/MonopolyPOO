package estrutura;

import xogadores.Xogador;

public class Casa extends Edificio {
    final static String tipo= "Casa";

    public Casa(Solar solar) {
        super(solar);
    }

    @Override
    public void destruir(Solar Solar) {
        return;
    }
}

