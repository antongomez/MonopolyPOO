package estrutura;

import xogadores.*;
import xogo.*;

public class Transporte extends Propiedade {

    public Transporte(String nome, int posicion, Grupo grupo, Xogador xogador) {
        super(nome, grupo, posicion, xogador, Constantes.CASILLA_TRANSPORTE);
    }

    //Métodos
    /*Funcion que calcula o numero de casilla de transporte 
    que ten un xogador*/
    private int calculoNPropTrans(Xogador dono) {
        int nTransportes = 0;

        for (Propiedade propiedade : dono.getPropiedades()) {
            if (propiedade instanceof Transporte) {
                nTransportes++;
            }
        }

        return nTransportes;
    }

    /*Funcion que calcula o factor polo que se multiplica o valor
    do alquiler do transporte. Chama a calculoNPropTrans.*/
    private float factorTransporte(Xogador dono) {
        float factor = 0;
        int nTransportes = calculoNPropTrans(dono);

        switch (nTransportes) {
            case 1:
                factor = 0.25f;
                break;
            case 2:
                factor = 0.5f;
                break;
            case 3:
                factor = 0.75f;
                break;
            case 4:
                factor = 1f;
                break;
        }

        return factor;
    }

    @Override
    public float calculoAlquiler() {
        float valor = factorTransporte(this.getDono())
                * Constantes.ALQUILER_TRANSPORTE;

        return valor;

        //return valor;
    }

    @Override
    public String imprimirCasilla() {
        String texto = "{\n"
                + "\tnome:" + this.getNome() + ",\n"
                + "\ttipo: Transporte,\n"
                + "\tdono: " + this.getDono().getNome() + ",\n"
                + "\talquiler: " + this.calculoAlquiler() + " GM,\n"
                + "\txogadores: [" + "]\n"
                + "}\n";

        return texto;
    }
}
