/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

import xogadores.*;

/**
 *
 * @author Anton
 */
public class Transporte extends Propiedade {

    public Transporte(String nome, int posicion, float valor, Grupo grupo, Xogador xogador) {
        super(nome, grupo, posicion, xogador, valor);
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
    public void calculoAlquiler() {
        float valor = factorTransporte(this.getDono()) * Constantes.ALQUILER_TRANSPORTE;

        this.setAlquiler(valor);

        //return valor;
    }

}
