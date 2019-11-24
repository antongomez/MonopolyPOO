package estrutura;

import xogadores.Xogador;

public class Piscina extends Edificio {


    public Piscina(Solar solar) {
        super(solar, "Piscina-" + solar.getPosicion() + solar.getNPiscinas(), 0.4f);
    }

    @Override
    public void destruir(Solar Solar) {
        return;
    }
}