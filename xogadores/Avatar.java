package xogadores;

import estrutura.*;
import java.util.Random;
import xogo.Xogo;
import static xogo.Xogo.consola;

public abstract class Avatar {

    private char id;
    private Xogador xogador;
    private Casilla posicion;
    private boolean modoAvanzado;

    //Constructores
    public Avatar() {
    }

    public Avatar(char Id, Xogador xogador, Taboleiro taboleiro) {
        this.id = Id;
        this.xogador = xogador;
        this.posicion = taboleiro.getCasilla(0);
        this.modoAvanzado = false;
        this.posicion.engadirAvatar(this);
    }

    //Getters y Setters
    public char getId() {
        return id;
    }

    public Xogador getXogador() {
        return xogador;
    }

    public void setXogador(Xogador xogador) {
        if (xogador != null) {
            this.xogador = xogador;
        } else {
            System.out.println("Erro en setXogador.");
        }
    }

    public Casilla getPosicion() {
        return posicion;
    }

    public void setPosicion(Casilla posicion) {
        if (posicion != null) {
            posicion.engadirAvatar(this);
            this.posicion = posicion;
        } else {
            System.out.println("ERRO en setPosicion.\n");
        }
    }

    public boolean getModoAvanzado() {
        return this.modoAvanzado;
    }

    public void setModoAvanzado(boolean estado) {
        this.modoAvanzado = estado;
    }

    //Metodos
    //Metodo que move ao avatar en modo Basico
    public void moverEnBasico(int sumaDados, Taboleiro taboleiro) {
        if (this.xogador.getEstadoPreso() == 0) {
            if (!this.modoAvanzado) {
                Casilla procedencia = posicion;

                //Eliminase ao avatar da casilla na que esta
                this.posicion.eliminarAvatar(this);
                //Distinguimos o caso que pasa pola saida e o que non
                if (this.posicion.getPosicion() + sumaDados < 40) {
                    //Actualizase a posicion
                    this.posicion = taboleiro.getCasilla(this.posicion.getPosicion() + sumaDados);
                    //Actualizase a casilla
                    this.posicion.engadirAvatar(this);

                    consola.imprimir("O avatar " + id + " avanza " + sumaDados
                            + " posicións, desde " + procedencia.getNome()
                            + " ata " + posicion.getNome() + ".\n");

                } else { //Caso no que o avatar da unha volta
                    this.posicion = taboleiro.getCasilla(this.posicion.getPosicion() + sumaDados - 40);
                    this.posicion.engadirAvatar(this);

                    consola.imprimir("O avatar " + id + " avanza " + sumaDados
                            + " posicións, desde " + procedencia.getNome()
                            + " ata " + posicion.getNome() + ".\n");

                    this.xogador.modificarFortuna(Constantes.VALOR_VOLTA);
                    this.xogador.sumarVolta();
                    Xogo.consola.imprimir("O xogador " + xogador.getNome()
                            + " pasou pola Saída. Cobra "
                            + Constantes.VALOR_VOLTA + " GM.\n");
                }
            } else {

            }
        } else {

        }
    }

    public abstract void moverEnAvanzado(int sumaDados, Taboleiro taboleiro);

    public static char xerarId() {
        char identificador;
        int numero;

        Random ale = new Random(System.currentTimeMillis());
        numero = ale.nextInt(20) + 65;
        identificador = (char) numero;

        return identificador;
    }

    @Override
    public String toString() {
        String texto = "{ \n" + "\tid: " + this.id
                + "\n" + "\tXogador: " + this.xogador.getNome()
                + "\n" + "}";
        return texto;
    }

    @Override
    public boolean equals(Object avatar) {
        if (!(avatar instanceof Avatar)) {
            return false;
        }
        return (this.id == (((Avatar) avatar).getId()));
    }
}
