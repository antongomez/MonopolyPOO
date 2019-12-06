package xogadores;

import estrutura.*;
import xogo.*;

import java.util.ArrayList;

public class Esfinxe extends Avatar {

    private ArrayList<String> historial;

    //Exemplo: alquiler Anton 150  /*sendo Victor o xogador deste avatar*/
    //Exemplo: compra Lugo 150
    //Exemplo: hipoteca Lugo 150
    /*Se se edifica nun turno varios edificios van en comandos distintos.
      Asi, so se pode edificar ou 4-casas, 1-hotel, 1-piscina ou 1-pista,
      como moito
     */
    //Exemplo: edificar Lugo casa-3 150
    //         edificar Lugo hotel 150
    //Exemplo: pago imposto 100
    //Exemplo: pago 2 Anton Victor 25 /*sendo Pedro o xogador deste avatar*/
    //Exemplo: cobro Parking 150
    public Esfinxe(char Id, Xogador xogador, Taboleiro taboleiro) {
        super(Id, xogador, taboleiro);

        this.historial = new ArrayList<>();
    }

    //Getters e Setters
    public ArrayList<String> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }

    public void sumarHistorial(String accion) {
        this.historial.add(accion);
        System.err.println("Linha engadida: \n" + accion + "\n");
    }

    public void resetHistorial() {
        this.historial.clear();
    }

    //Métodos
    //Utiliza moverEnBasico
    @Override
    public void moverEnAvanzado(int sumaDados, Taboleiro taboleiro) {

        if (sumaDados > 4) {

            switch (this.getLado(this.getPosicion().getPosicion())) {
                case 'e':
                    //Facemos o primeiro movemento
                    Xogo.consola.imprimir("");
                    desplazarseEste(this.getPosicion(), taboleiro);
                    sumaDados--;

                case 's':
                    for (int i = 1; i == sumaDados; i++) {
                        desplazarseSur(this.getPosicion(), taboleiro);
                    }
                    break;
                case 'o':
                    //Facemos o primeiro movemento
                    desplazarseOeste(this.getPosicion(), taboleiro);
                    sumaDados--;
                case 'n':
                    for (int i = 1; i == sumaDados; i++) {
                        desplazarseNorte(this.getPosicion(), taboleiro);
                    }
                    break;

            }

        } else if (sumaDados == 4) {
            this.moverEnBasico(sumaDados, taboleiro);
        } else {
            desfacerCambios();
        }
    }

    private void desplazarseEste(Casilla casilla, Taboleiro taboleiro) {
        if ((casilla != null) && (taboleiro != null)) {
            Casilla destino = null;

            casilla.eliminarAvatar(this);

            destino = taboleiro.getCasilla("Santa Cruz");
            destino.engadirAvatar(this);

            this.setPosicion(destino);
        } else {
            //Excepcion
        }
    }

    private void desplazarseOeste(Casilla casilla, Taboleiro taboleiro) {
        if ((casilla != null) && (taboleiro != null)) {
            Casilla destino = null;

            casilla.eliminarAvatar(this);

            destino = taboleiro.getCasilla("Verin");
            destino.engadirAvatar(this);

            this.setPosicion(destino);
        } else {
            //Excepcion
        }
    }

    private void desplazarseSur(Casilla casilla, Taboleiro taboleiro) {
        if ((casilla != null) && (taboleiro != null)) {
            Casilla destino;

            //De Verin pasase á saída
            switch (casilla.getNome()) {
                case "Verin":
                    casilla.eliminarAvatar(this);
                    destino = taboleiro.getCasilla("Saida");
                    destino.engadirAvatar(this);
                    this.setPosicion(destino);
                    break;
                case "Parking":
                    casilla.eliminarAvatar(this);
                    destino = taboleiro.getCasilla("Santa Cruz");
                    destino.engadirAvatar(this);
                    this.setPosicion(destino);
                    break;
                default:
                    casilla.eliminarAvatar(this);
                    if (casilla.getPosicion() > 19) {
                        destino = taboleiro.getCasilla(10 - (casilla.getPosicion() - 20) + 1);
                    } else {
                        destino = taboleiro.getCasilla(30 - casilla.getPosicion() - 1);
                    }
                    if (destino != null) {
                        destino.engadirAvatar(this);
                        this.setPosicion(destino);
                    } else {
                        //Excepcion
                    }
                    break;
            }
        } else {
            //Excepcion
        }
    }

    private void desplazarseNorte(Casilla casilla, Taboleiro taboleiro) {
        if ((casilla != null) && (taboleiro != null)) {
            Casilla destino;

            //De Verin pasase á saída
            switch (casilla.getNome()) {
                case "Santa Cruz":
                    casilla.eliminarAvatar(this);
                    destino = taboleiro.getCasilla("Parking");
                    destino.engadirAvatar(this);
                    this.setPosicion(destino);
                    break;
                case "Saida":
                    casilla.eliminarAvatar(this);
                    destino = taboleiro.getCasilla("Verin");
                    destino.engadirAvatar(this);
                    this.setPosicion(destino);
                    break;
                default:
                    casilla.eliminarAvatar(this);
                    if (casilla.getPosicion() > 19) {
                        destino = taboleiro.getCasilla(10 - (casilla.getPosicion() - 20) - 1);
                    } else {
                        destino = taboleiro.getCasilla(30 - casilla.getPosicion() + 1);
                    }
                    if (destino != null) {
                        destino.engadirAvatar(this);
                        this.setPosicion(destino);
                    } else {
                        //Excepcion
                    }
                    break;
            }
        } else {
            //Excepcion
        }
    }

    private void desfacerCambios() {
        String[] partes;
        String accion, casilla;
        for (int i = 0; i < historial.size(); i++) {
            partes = historial.get(i).split(" ");
            accion = partes[0];

            switch (accion) {
                case "alquiler":
                    break;
                case "compra":
                    break;
                case "edificar":
                    break;
                case "hipoteca":
                    break;
                case "deshipoteca":
                    break;
                case "venta":
                    break;
                case "trato":
                    break;
                case "pago":

                    break;
            }

        }
    }

    private char getLado(int posicion) {
        char lado;
        if ((posicion >= 0) && (posicion <= 9)) {
            lado = 's';
        } else if ((posicion >= 10) && (posicion <= 19)) {
            lado = 'o';
        } else if ((posicion >= 20) && (posicion <= 29)) {
            lado = 'n';
        } else if ((posicion >= 30) && (posicion <= 39)) {
            lado = 'e';
        } else {
            //Excepcion
            //Polo de agora
            lado = 'f';
        }

        return lado;
    }
}
