package estrutura;

import xogadores.*;
import xogo.*;

public class Servizo extends Propiedade {

    public Servizo(String nome, int posicion, Grupo grupo, Xogador xogador) {
        super(nome, grupo, posicion, xogador, Constantes.CASILLA_SERVIZO);
    }

    //Métodos
    /*Funcion que calcula o numero de casilla de servizo 
    que ten un xogador*/
    private int calculoNPropServ(Xogador dono) {
        int nTransportes = 0;

        for (Propiedade propiedade : dono.getPropiedades()) {
            if (propiedade instanceof Servizo) {
                nTransportes++;
            }
        }

        return nTransportes;
    }

    /*Funcion que calcula o factor polo que se multiplica o valor
    do alquiler do servizo. Chama a calculoNPropTrans.*/
    private float factorServizo(Xogador dono) {
        float factor = 0;
        int nServizos = calculoNPropServ(dono);

        switch (nServizos) {
            case 1:
                factor = 4f;
                break;
            case 2:
                factor = 10f;
                break;
        }

        return factor;
    }

    @Override
    public float calculoAlquiler() {
        //Falta multiplicalo pola suma dos dados. Faise en xogo
        float valor = factorServizo(this.getDono())
                * Constantes.ALQUILER_SERVIZO;

        return valor;

        //return valor;
    }

    @Override
    public String imprimirCasilla() {
        String texto ="\tnome: " + this.getNome() + "\n"
                + "\ttipo: Servizo\n"
                + "\tdono: " + this.getDono().getNome() + "\n"
                + "\talquiler: " + this.calculoAlquiler() + " GM\n"
                + "\txogadores: [" + "]\n";

        return texto;
    }

}
