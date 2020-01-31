package xogadores;

import InterfazGrafica.InterfazGrafica;
import estrutura.Taboleiro;

import java.util.ArrayList;

public class Pelota extends Avatar {

    private int avances;
    private InterfazGrafica interfaz;

    public Pelota(char Id, Xogador xogador, Taboleiro taboleiro) {
        super(Id, xogador, taboleiro);
        this.avances = 0;
        this.interfaz = null;
    }

    @Override
    public void moverEnAvanzado(int sumaDados, Taboleiro taboleiro,
            Xogador banca, Xogador hipoteca) {
        moverEnBasico(sumaDados, taboleiro, interfaz);
    }

    @Override
    public String toString() {
        String texto = super.toString();

        texto += "\n\ttipo: pelota" + "\n}";

        return texto;
    }
}
