package estrutura;

import xogadores.Xogador;

public class Piscina extends Edificio {
    final static String tipo= "Pisina";

    public Piscina(Solar solar) {
        super(solar);
    }

    @Override
    public void destruir(Solar Solar) {
        return;
    }
}