package xogadores;

import estrutura.Taboleiro;

import java.util.ArrayList;

public class Pelota extends Avatar {

    private int avances;

    public Pelota(char Id, Xogador xogador, Taboleiro taboleiro) {
        super(Id, xogador, taboleiro);
        this.avances = 0;
    }

    @Override
    public void moverEnAvanzado(int sumaDados, Taboleiro taboleiro,
            Xogador banca) {
        moverEnBasico(sumaDados, taboleiro);
    }

    @Override
    public String toString() {
        String texto = super.toString();

        texto += "\n\ttipo: pelota" + "\n}";

        return texto;
    }
}
