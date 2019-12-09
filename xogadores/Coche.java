package xogadores;

import estrutura.Taboleiro;

import java.util.ArrayList;

public class Coche extends Avatar {

    private int bloqueado;

    public Coche(char Id, Xogador xogador, Taboleiro taboleiro) {
        super(Id, xogador, taboleiro);
        this.bloqueado = 0;
    }

    @Override
    public void moverEnAvanzado(int sumaDados, Taboleiro taboleiro,
            Xogador banca) {
        moverEnBasico(sumaDados, taboleiro);
    }

    @Override
    public String toString() {
        String texto = super.toString();
        
        texto += "\n\ttipo: coche" + "\n}";
        
        return texto;
    }

}
