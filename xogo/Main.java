package xogo;

import estrutura.Taboleiro;
import xogadores.Xogador;

public class Main {

    public static void main(String[] args) {


        Xogador banca = new Xogador();

        Taboleiro taboleiro = new Taboleiro(banca);

        taboleiro.imprimirTaboeiro();

        //Menu menu = new Menu();
    }
}
