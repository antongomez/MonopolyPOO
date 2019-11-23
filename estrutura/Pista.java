package estrutura;

import xogadores.Xogador;

public class Pista extends Edificio {
    final static String tipo= "Pista";

    public Pista(Solar solar) {
        super(solar);
    }

    @Override
    public void destruir(Solar Solar) {
        return;
    }
}