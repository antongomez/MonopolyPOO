package xogadores;

import estrutura.*;
import xogo.*;

import java.util.ArrayList;

public class Chapeu extends Avatar {

    private ArrayList<String> historial;

    //Exemplo: alquiler/150/Anton/A Guarda    /*sendo Victor o xogador deste avatar*/
    //Exemplo: compra/150/Lugo 
    //Exemplo: hipoteca/150/Lugo 
    /*Se se edifica nun turno varios edificios van en comandos distintos.
      Asi, so se pode edificar ou 4-casas, 1-hotel, 1-piscina ou 1-pista,
      como moito
     */
    //Exemplo: edificar/150/Lugo/casas-3 
    //         edificar/150/Lugo/hotel 
    //Exemplo: pago/100 
    //Exemplo: pago/50/Anton-Victor  /*sendo Pedro o xogador deste avatar e tendolles que pagar 25 a cada un*/
    //Exemplo: cobro/150/Parking
    //         cobro/160
    //Exemplo: venta/50/Lugo/casa
    //Exemplo: hipoteca/100/Lugo
    //Exemplo: dehipoteca/100/Lugo
    //Exemplo: trato/Anton/Brais/Lugo/100/Vigo/100/Meanho /*sendo Meanho a casilla na que non se paga o alquiler*/
    public Chapeu(char Id, Xogador xogador, Taboleiro taboleiro) {
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

    public void imprimirHistorial() {
        Xogo.consola.imprimir("---------------------------------------");
        Xogo.consola.imprimir("---------------------------------------");
        Xogo.consola.imprimir("Historial de " + getId() + "\n");

        for (int i = 0; i < historial.size(); i++) {
            Xogo.consola.imprimir(historial.get(i));
        }

        Xogo.consola.imprimir("---------------------------------------");
        Xogo.consola.imprimir("---------------------------------------");
    }

    //Métodos
    //Utiliza moverEnBasico
    @Override
    public void moverEnAvanzado(int sumaDados, Taboleiro taboleiro,
            Xogador banca) {

        if (sumaDados > 4) {
            String texto = "O xogador " + getXogador().getNome() + " avanza "
                    + sumaDados + " posicións, partindo dende "
                    + getPosicion().getNome() + " e pasando por";
            switch (this.getLado(this.getPosicion().getPosicion())) {
                case 's':
                    //Facemos o primeiro movemento
                    Xogo.consola.imprimir("Lado: e");
                    desplazarseSur(this.getPosicion(), taboleiro);
                    sumaDados--;
                    texto += " " + getPosicion().getNome() + ",";

                case 'o':
                    Xogo.consola.imprimir("Lado: s");
                    for (int i = 1; i <= sumaDados; i++) {
                        desplazarseOeste(this.getPosicion(), taboleiro);
                        if (i == (sumaDados)) {
                            texto += " ata chegar a " + getPosicion().getNome() + ".";
                        } else if (i == (sumaDados - 1)) {
                            texto += " " + getPosicion().getNome();
                        } else {
                            texto += " " + getPosicion().getNome() + ",";
                        }
                    }
                    break;
                case 'n':
                    Xogo.consola.imprimir("Lado: o");
                    //Facemos o primeiro movemento
                    desplazarseNorte(this.getPosicion(), taboleiro);
                    sumaDados--;
                    texto += " " + getPosicion().getNome() + ",";
                case 'e':
                    Xogo.consola.imprimir("Lado: n");
                    for (int i = 1; i <= sumaDados; i++) {
                        desplazarseEste(this.getPosicion(), taboleiro);
                        if (i == (sumaDados)) {
                            texto += " ata chegar a " + getPosicion().getNome() + ".";
                        } else if (i == (sumaDados - 1)) {
                            texto += " " + getPosicion().getNome();
                        } else {
                            texto += " " + getPosicion().getNome() + ",";
                        }
                    }
                    break;

            }
            Xogo.consola.imprimir(texto);
        } else if (sumaDados == 4) {
            this.moverEnBasico(sumaDados, taboleiro);
        } else {
            desfacerCambios(taboleiro, banca);
        }
    }

    private void desplazarseSur(Casilla casilla, Taboleiro taboleiro) {
        if ((casilla != null) && (taboleiro != null)) {
            Casilla destino = null;

            casilla.eliminarAvatar(this);

            destino = taboleiro.getCasilla("O Carballinho");

            this.setPosicion(destino);
        } else {
            //Excepcion
        }
    }

    private void desplazarseNorte(Casilla casilla, Taboleiro taboleiro) {
        if ((casilla != null) && (taboleiro != null)) {
            Casilla destino = null;

            casilla.eliminarAvatar(this);

            destino = taboleiro.getCasilla("Santiago");

            this.setPosicion(destino);
        } else {
            //Excepcion
        }
    }

    private void desplazarseOeste(Casilla casilla, Taboleiro taboleiro) {
        if ((casilla != null) && (taboleiro != null)) {
            Casilla destino;

            //De Verin pasase á saída
            switch (casilla.getNome()) {
                case "Ribadeo":
                    casilla.eliminarAvatar(this);
                    destino = taboleiro.getCasilla("Saida");

                    this.setPosicion(destino);
                    break;
                case "Parking":
                    casilla.eliminarAvatar(this);
                    destino = taboleiro.getCasilla("A Corunha");

                    this.setPosicion(destino);
                    break;
                default:
                    casilla.eliminarAvatar(this);
                    if (casilla.getPosicion() > 30) {
                        destino = taboleiro.getCasilla(20 - (casilla.getPosicion() - 30) + 1);
                    } else {
                        destino = taboleiro.getCasilla(40 - (casilla.getPosicion() - 10) - 1);
                    }
                    if (destino != null) {
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

    private void desplazarseEste(Casilla casilla, Taboleiro taboleiro) {
        if ((casilla != null) && (taboleiro != null)) {
            Casilla destino;

            //De Verin pasase á saída
            switch (casilla.getNome()) {
                case "A Corunha":
                    casilla.eliminarAvatar(this);
                    destino = taboleiro.getCasilla("Parking");

                    this.setPosicion(destino);
                    break;
                case "Saida":
                    casilla.eliminarAvatar(this);
                    destino = taboleiro.getCasilla("Ribadeo");

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

    private void desfacerCambios(Taboleiro taboleiro, Xogador banca) {
        String[] partes;
        String accion;
        for (int i = 0; i < historial.size(); i++) {
            String comando1 = "", comando2 = "", comando3 = "";
            partes = historial.get(i).split("/");
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
                    desfacerPago(Float.parseFloat(comando1));
                    Xogo.consola.imprimir("O xogador " + getXogador().getNome()
                            + " recibiu de volta " + comando1 + " GM que "
                            + "pagara polo alquiler de " + comando3 + " a "
                            + comando2 + ".");
                    break;
                case "compra":
                    desfacerCompra(Float.parseFloat(comando1), comando2,
                            taboleiro, banca);
                    Xogo.consola.imprimir("O xogador " + getXogador().getNome()
                            + " recibiu de volta " + comando1 + " GM que "
                            + "pagara pola compra de " + comando2 + ". A "
                            + "propiedade volve estar á venta.");
                    break;
                case "edificar":
                    String[] edificios = comando3.split("-");
                    String nCasas = "1 ";
                    if (edificios.length == 2) {
                        nCasas = edificios[1] + " ";
                    }

                    desfacerConstrucion(Float.parseFloat(comando1), comando2, edificios,
                            taboleiro);

                    Xogo.consola.imprimir("O xogador " + getXogador().getNome()
                            + " recibiu de volta " + comando1 + " GM que "
                            + "pagara pola construción de " + nCasas + edificios[0] + " en "
                            + comando2 + ". Tiráronse abaixo as construcións.");
                    break;
                case "hipoteca":
                    break;
                case "deshipoteca":
                    break;
                case "venta":
                    desfacerVenta(Float.parseFloat(comando1), comando2, comando3, taboleiro);
                    Xogo.consola.imprimir("O xogador " + getXogador().getNome()
                            + " construíu de novo 1 " + comando3 + " en "
                            + comando2 + ". Cobráronselle " + comando1 + " GM.");
                    break;
                case "trato":

                    break;
                case "pago":
                    String nomes = "";
                    if (partes.length == 3) {
                        nomes += " a";
                        String[] xogs = comando2.split("-");
                        for (int j = 0; j < xogs.length; j++) {
                            nomes += " " + xogs[j];
                        }
                    }
                    desfacerPago(Float.parseFloat(comando1));
                    Xogo.consola.imprimir("O xogador " + getXogador().getNome()
                            + " recibiu de volta " + comando1 + " GM que "
                            + "pagara" + nomes + ".");

                    break;
                case "cobro":
                    if (comando2.equals("Parking")) {
                        desfacerCobroParking(Float.parseFloat(comando1), taboleiro);
                        Xogo.consola.imprimir("Devolveuse o bote cunha"
                                + " cantidade de" + comando1 + " GM do Parking "
                                + "que recibiu o xogador "
                                + getXogador().getNome() + " no turno anterior.");
                    } else {
                        desfacerCobro(Float.parseFloat(comando1));
                        Xogo.consola.imprimir("Devolveuse o cobro de "
                                + comando1 + " GM que recibiu o xogador "
                                + getXogador().getNome() + " no turno anterior.");
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

    public void desfacerCompra(float cantidade, String nomeCasilla,
            Taboleiro taboleiro, Xogador banca) {
        if ((nomeCasilla != null) && (cantidade > 0)) {
            desfacerPago(cantidade);
            Propiedade casilla = (Propiedade) taboleiro.getCasilla(nomeCasilla);
            if (casilla instanceof Solar) {
                if (!(((Solar) casilla).getEdificios().isEmpty())) {
                    for (int i = 0; i < ((Solar) casilla).getEdificios().size(); i++) {

                    }
                }
            }
            casilla.setDono(banca);
        }
    }

    public void desfacerConstrucion(float cantidade, String nomeCasilla,
            String[] edificio, Taboleiro taboleiro) {

        desfacerPago(cantidade);
        if ((nomeCasilla != null) && (cantidade > 0) && (taboleiro != null)
                && (edificio.length > 0)) {
            if (taboleiro.getCasilla(nomeCasilla) instanceof Solar) {
                Solar solar = (Solar) taboleiro.getCasilla(nomeCasilla);
                if (edificio.length == 2) {
                    solar.destruirEdificio(Integer.parseInt(edificio[1]));
                } else if (edificio.length == 1) {
                    solar.destruirEdificio(edificio[0]);
                }
            }
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

    public void desfacerVenta(float cantidade, String nomeCasilla, String tipoEdificio, Taboleiro taboleiro) {
        if ((nomeCasilla != null) && (cantidade > 0) && (taboleiro != null)) {
            desfacerCobro(cantidade);
            if (taboleiro.getCasilla(nomeCasilla) instanceof Solar) {
                Solar solar = (Solar) taboleiro.getCasilla(nomeCasilla);
                solar.edificar(tipoEdificio);
            } else {

            }

        } else {

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
        String texto = super.toString();

        texto += "\n\ttipo: chapeu" + "\n}";

        return texto;
    }
}
