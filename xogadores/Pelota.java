package xogadores;

import estrutura.Taboleiro;

import java.util.ArrayList;

public class Pelota extends Avatar {
    final String tipo="Pelota";

    public Pelota(ArrayList<Avatar> avatares, Xogador xogador, Taboleiro taboleiro) {
        super(avatares, xogador, taboleiro);
    }


    @Override
    public void moverEnBasico() {

    }

    @Override
    public void moverEnAvanzado() {

    }
}