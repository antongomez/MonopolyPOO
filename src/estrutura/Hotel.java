package estrutura;

import xogadores.*;

public class Hotel extends Edificio {

    public Hotel(Solar solar) {
        super(solar, "Hotel-" + solar.getPosicion() + solar.getNHoteis(), 0.6f);
    }

    @Override
    public void destruir(Solar Solar) {
        return;
    }
}
