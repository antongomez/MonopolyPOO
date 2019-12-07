package xogadores;

import estrutura.*;
import xogo.*;

import java.util.ArrayList;

public class Esfinxe extends Avatar {

    private ArrayList<String> historial;

    //Exemplo: alquiler 150 Anton Lugo    /*sendo Victor o xogador deste avatar*/
    //Exemplo: compra 150 Lugo 
    //Exemplo: hipoteca 150 Lugo 
    /*Se se edifica nun turno varios edificios van en comandos distintos.
      Asi, so se pode edificar ou 4-casas, 1-hotel, 1-piscina ou 1-pista,
      como moito
     */
    //Exemplo: edificar 150 Lugo casa-3 
    //         edificar 150 Lugo hotel 
    //Exemplo: pago 100 
    //Exemplo: pago 50 Anton-Victor  /*sendo Pedro o xogador deste avatar e tendolles que pagar 25 a cada un*/
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
            Xogador banca) {

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
            desfacerCambios(taboleiro, banca);
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

    private void desfacerCambios(Taboleiro taboleiro, Xogador banca) {
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
                            + "pagara pola compra de " + comando2 + ".");
                    break;
                case "edificar":
                    String[] edificios = comando3.split("-");
                    String tipoEdificio = "";
                    
                    desfacerConstrucion(Float.parseFloat(comando1), comando2, tipoEdificio,
                            taboleiro);
                    
                    
                    
                    Xogo.consola.imprimir("O xogador " + getXogador().getNome()
                            + " recibiu de volta " + comando1 + " GM que "
                            + "pagara pola construción de " + tipoEdificio + " en "
                            + comando2 + ".");
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
            String tipoEdificio, Taboleiro taboleiro){
        
    }

    public void desfacerPago(float cantidade) {
        if (cantidade > 0) {
            this.getXogador().modificarFortuna(cantidade);
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
