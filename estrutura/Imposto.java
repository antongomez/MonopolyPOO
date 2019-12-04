/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

import xogo.*;

/**
 *
 * @author Anton
 */
public class Imposto extends Casilla {

    private float imposto;

    public Imposto(String nome, int posicion, float imposto) {
        super(nome, posicion);
        this.imposto = imposto;
    }

    @Override
    public String imprimirCasilla() {
        String texto = "{\n"
                + "\tnome:" + this.getNome() + ",\n"
                + "\timposto:" + imposto + ",\n"
                + "\txogadores: [" + "]\n"
                + "}\n";

        return texto;
    }
}
