package estrutura;

import xogadores.*;

public class Casa extends Edificio {

    public Casa(Solar solar) {
        super(solar, "Casa-" + solar.getPosicion() + solar.getNCasas(), 0.6f);
        System.out.println("proba casa");
    }

    @Override
    public void destruir(Solar Solar) {
        return;
    }
}

