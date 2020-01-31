package xogadores;

import InterfazGrafica.InterfazGrafica;
import estrutura.*;
import xogo.*;
import Excepcions.*;
import java.util.ArrayList;
import errosExternos.*;
import errosExternos.errosExistencia.*;

public class Esfinxe extends Avatar {

    private ArrayList<String> historial;
    private InterfazGrafica interfaz;

    //Exemplo: alquiler/150/Anton/A Guarda    /*sendo Victor o xogador deste avatar*/
    //Exemplo: compra/150/Lugo 
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
    public Esfinxe(char Id, Xogador xogador, Taboleiro taboleiro, InterfazGrafica interfaz) {
        super(Id, xogador, taboleiro, interfaz);

        this.historial = new ArrayList<>();
        this.interfaz = interfaz;
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
        System.out.println("Linha engadida: \n" + accion + "\n");
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
            Xogador banca, Xogador hipoteca) throws ErroInicializacion,
            CartosInsuficientes, NonPodeEdificar, CasillaNonExiste,
            PropiedadeNonPertenceA, NonPropiedade, PropiedadeNonHipotecada {

        if (sumaDados > 4) {
            String texto = "O xogador " + getXogador().getNome() + " avanza "
                    + sumaDados + " posicións, partindo dende "
                    + getPosicion().getNome() + " e pasando por";
            switch (this.getLado(this.getPosicion().getPosicion())) {
                case 'e':
                    //Facemos o primeiro movemento
                    Xogo.consola.imprimir("Lado: e");
                    desplazarseEste(this.getPosicion(), taboleiro);
                    sumaDados--;
                    texto += " " + getPosicion().getNome() + ",";

                case 's':
                    Xogo.consola.imprimir("Lado: s");
                    for (int i = 1; i <= sumaDados; i++) {
                        desplazarseSur(this.getPosicion(), taboleiro);
                        if (i == (sumaDados)) {
                            texto += " ata chegar a " + getPosicion().getNome() + ".";
                        } else if (i == (sumaDados - 1)) {
                            texto += " " + getPosicion().getNome();
                        } else {
                            texto += " " + getPosicion().getNome() + ",";
                        }
                    }
                    break;
                case 'o':
                    Xogo.consola.imprimir("Lado: o");
                    //Facemos o primeiro movemento
                    desplazarseOeste(this.getPosicion(), taboleiro);
                    sumaDados--;
                    texto += " " + getPosicion().getNome() + ",";
                case 'n':
                    Xogo.consola.imprimir("Lado: n");
                    for (int i = 1; i <= sumaDados; i++) {
                        desplazarseNorte(this.getPosicion(), taboleiro);
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
            desfacerCambios(taboleiro, banca, hipoteca);
        }
    }

    private void desplazarseEste(Casilla casilla, Taboleiro taboleiro) {
        if ((casilla != null) && (taboleiro != null)) {
            Casilla destino = null;

            casilla.eliminarAvatar(this);

            destino = taboleiro.getCasilla("Santa Cruz");

            this.setPosicion(destino);
        }
    }

    private void desplazarseOeste(Casilla casilla, Taboleiro taboleiro) {
        if ((casilla != null) && (taboleiro != null)) {
            Casilla destino = null;

            casilla.eliminarAvatar(this);

            destino = taboleiro.getCasilla("Verin");

            this.setPosicion(destino);
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

                    this.setPosicion(destino);
                    break;
                case "Parking":
                    casilla.eliminarAvatar(this);
                    destino = taboleiro.getCasilla("Santa Cruz");

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
                        this.setPosicion(destino);
                    }
                    break;
            }
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

                    this.setPosicion(destino);
                    break;
                case "Saida":
                    casilla.eliminarAvatar(this);
                    destino = taboleiro.getCasilla("Verin");

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
        }
    }

    private void desfacerCambios(Taboleiro taboleiro, Xogador banca, Xogador hipoteca)
            throws CartosInsuficientes, NonPodeEdificar, CasillaNonExiste,
            NonPropiedade, PropiedadeNonPertenceA, PropiedadeNonHipotecada {
        String[] partes;
        String accion;
        for (int i = (historial.size() - 1); i >= 0; i--) {
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
                    desfacerHipoteca(Float.parseFloat(comando1), comando2,
                            taboleiro, hipoteca);
                    Xogo.consola.imprimir("Desfíxose a hipoteca da propiedade "
                            + comando2 + ". O xogador " + getXogador().getNome()
                            + " pagou de volta as " + comando1 + " GM e"
                            + " recibiu de volta a propiedade.");
                    break;
                case "deshipoteca":
                    desfacerDeshipoteca(Float.parseFloat(comando1), comando2,
                            taboleiro, hipoteca);
                    Xogo.consola.imprimir("Desfíxose a deshipoteca da propiedade "
                            + comando2 + ". O xogador " + getXogador().getNome()
                            + " recibiu de volta as " + comando1 + " GM e"
                            + " hipotecou de novo a propiedade.");
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
            Xogo.consola.imprimir("O cobro debe ser positivo.\n");
        }
    }

    public void desfacerCobroParking(float bote, Taboleiro taboleiro) {
        if (bote > 0) {
            this.getXogador().modificarFortuna(-bote);
            ((Especial) taboleiro.getCasilla("Parking")).modificarBoteParking(bote);
        } else {
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
                    System.out.println("Mal implementado historial. Orde "
                            + "errónea Edificar-Comprar");
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
                    if (edificio[0].equals("hotel")) {
                        /*No caso de que se destrúa un hotel, 
                        colocamos as catro casas que habia antes*/
                        for (int i = 0; i < 4; i++) {
                            solar.engadirEdificio(new Casa(solar));
                        }
                    }
                }
            }
        }
    }

    public void desfacerDeshipoteca(float cantidade, String nomePropiedade,
            Taboleiro taboleiro, Xogador hipoteca) throws CasillaNonExiste,
            NonPropiedade, PropiedadeNonHipotecada, CartosInsuficientes,
            PropiedadeNonPertenceA {

        if ((nomePropiedade != null) && (hipoteca != null)) {
            if (taboleiro.getCasilla(nomePropiedade) == null) {
                throw new CasillaNonExiste(nomePropiedade);
            }
            if (!(taboleiro.getCasilla(nomePropiedade) instanceof Propiedade)) {
                throw new NonPropiedade(nomePropiedade);
            }

            Propiedade propiedade = (Propiedade) taboleiro.getCasilla(nomePropiedade);
            getXogador().hipotecar(propiedade, hipoteca);

        }
    }

    public void desfacerHipoteca(float cantidade, String nomePropiedade,
            Taboleiro taboleiro, Xogador hipoteca) throws CasillaNonExiste,
            NonPropiedade, PropiedadeNonHipotecada, CartosInsuficientes,
            PropiedadeNonPertenceA {

        if ((nomePropiedade != null) && (hipoteca != null)) {
            if (taboleiro.getCasilla(nomePropiedade) == null) {
                throw new CasillaNonExiste(nomePropiedade);
            }
            if (!(taboleiro.getCasilla(nomePropiedade) instanceof Propiedade)) {
                throw new NonPropiedade(nomePropiedade);
            }

            Propiedade propiedade = (Propiedade) taboleiro.getCasilla(nomePropiedade);
            getXogador().deshipotecar(hipoteca, propiedade);

        }
    }

    public void desfacerPago(float cantidade) {
        if (cantidade > 0) {
            this.getXogador().modificarFortuna(cantidade);
        } else {
            Xogo.consola.imprimir("O pago debe ser positivo.\n");
        }
    }

    public void desfacerVenta(float cantidade, String nomeCasilla,
            String tipoEdificio, Taboleiro taboleiro)
            throws CartosInsuficientes, NonPodeEdificar {
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

    private char getLado(int posicion) throws ErroInicializacion {
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
            throw new ErroInicializacion("Hai casillas que teñen mal introducida"
                    + " a súa posición.");
        }

        return lado;
    }

    @Override
    public String toString() {
        String texto = super.toString();

        texto += "\n\ttipo: esfinxe" + "\n}";

        return texto;
    }
}
