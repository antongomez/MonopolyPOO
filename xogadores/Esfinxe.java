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
    //Exemplo: pago 100 
    //Exemplo: pago 25 2 Anton-Victor  /*sendo Pedro o xogador deste avatar*/
    //Exemplo: cobro 150 Parking
    //         cobro 160
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
    public void moverEnAvanzado(int sumaDados, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) {

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
            desfacerCambios(taboleiro);
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

    private void desfacerCambios(Taboleiro taboleiro) {
        String[] partes;
        String accion, casilla;
        for (int i = 0; i < historial.size(); i++) {
            String comando1 = "", comando2 = "", comando3 = "";
            partes = historial.get(i).split(" ");
            accion = partes[0];

            if (partes.length > 1) {
                comando1 = partes[1];
            }
            if (partes.length > 2) {
                comando2 = partes[2];
            }
            if (partes.length > 3) {
                comando3 = partes[3];
            }

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
                    if (comando2.matches("12345")) {
                        desfacerPagoXogador(Float.parseFloat(comando1), comando2);
                    } else {
                        desfacerPago(Float.parseFloat(comando1));
                    }
                    break;
                case "cobro":
                    if (comando2.equals("Parking")) {
                        desfacerCobroParking(Float.parseFloat(comando1), taboleiro);
                    } else {
                        desfacerCobro(Float.parseFloat(comando1));
                        Xogo.consola.imprimir("Desfíxose o cobro de "
                                + comando1 + " Gm que recibiu o xogador "
                                + getXogador().getNome() + " no truno anterior.");
                    }
                    break;
            }

        }
    }

    public void desfacerCobro(float cantidade) {
        if (cantidade > 0) {
            this.getXogador().modificarFortuna(-cantidade);
        } else {
            //Excepcion
            Xogo.consola.imprimir("O cobro debe ser positivo.\n");
        }
    }

    public void desfacerCobroParking(float bote, Taboleiro taboleiro) {
        if (bote > 0) {
            this.getXogador().modificarFortuna(-bote);
            ((Especial) taboleiro.getCasilla("Parking")).modificarBoteParking(bote);
        } else {
            //Excepcion
            Xogo.consola.imprimir("O bote debe ser positivo.\n");
        }
    }

    public void desfacerPago(float cantidade) {
        if (cantidade > 0) {
            this.getXogador().modificarFortuna(cantidade);
        } else {
            //Excepcion
            Xogo.consola.imprimir("O pago debe ser positivo.\n");
        }
    }

    public void desfacerPagoXogador(float cantidade, String nomes) {
        if (cantidade > 0) {
            String[] xogadores = nomes.split("-");

            for (int i = 0; i < xogadores.length; i++) {
                this.getXogador().modificarFortuna(cantidade);
                this.getXogador().modificarFortuna(cantidade);
            }

        } else {
            //Excepcion
            Xogo.consola.imprimir("O pago debe ser positivo.\n");
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

    @Override
    public String toString() {
        String texto = "{ \n" + "\tid: " + this.getId()
                + "\n" + "\tTipo: esfinxe"
                + "\n" + "\tXogador: " + this.getXogador().getNome()
                + "\n" + "}";
        return texto;
    }
}
