/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

import java.util.ArrayList;

/**
 *
 * @author Anton
 */
public class Solar extends Propiedade {

    private ArrayList<Edificio> edificios;

    public Solar(String nome, int posicion, float valor) {
        super(nome, posicion, valor);
        edificios = new ArrayList<>();
    }

    //Getters e Setters
    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    public void engadirEdificio(Edificio edificio) {
        edificios.add(edificio);
    }

    public void eliminarEdificio(Edificio edificio) {
        edificios.remove(edificio);
    }

    @Override
    public void calculoAlquiler() {
        //Falta ter en conta o numero de edificios
        this.setAlquiler(this.getValor() * 0.1f);
    }
}
