package xogadores;

import InterfazGrafica.InterfazGrafica;
import estrutura.Taboleiro;

import java.util.ArrayList;

public class Pelota extends Avatar {

    private int avances;
    private InterfazGrafica interfaz;

    public Pelota(char Id, Xogador xogador, Taboleiro taboleiro, InterfazGrafica interfaz) {
        super(Id, xogador, taboleiro, interfaz);
        this.avances = 0;
        this.interfaz = interfaz;
    }

    @Override
    public void moverEnAvanzado(int sumaDados, Taboleiro taboleiro,
            Xogador banca, Xogador hipoteca) {
        moverEnBasico(sumaDados, taboleiro);
    }

    @Override
    public String toString() {
        String texto = super.toString();

        texto += "\n\ttipo: pelota" + "\n}";

        return texto;
    }
}
