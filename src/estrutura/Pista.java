package estrutura;

import xogadores.*;

public class Pista extends Edificio {
    final static String tipo= "Pista";

    public Pista(Solar solar) {
        super(solar, "Pista-" + solar.getPosicion() + solar.getNPistas(), 1.25f);
    }

    @Override
    public void destruir(Solar Solar) {
        return;
    }
}