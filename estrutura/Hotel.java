package estrutura;

import xogadores.Xogador;

public class Hotel extends Edificio {
    final static String tipo= "Hotel";

    public Hotel(Solar solar) {
        super(solar);
    }

    @Override
    public void destruir(Solar Solar) {
        return;
    }
}