package carta;

import estrutura.*;
import xogadores.*;
import java.util.ArrayList;
import static xogo.Xogo.consola;
import errosExternos.*;

public class Sorte extends Carta {

    public Sorte(String nome) {
        super(nome);
    }

    @Override
    public void accion(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {

        String partes[] = super.getNome().split("-");
        switch (partes[1]) {
            case "1":
                this.Carta1(xogador, taboleiro, xogadores);
                break;
            case "2":
                this.Carta2(xogador);
                break;
            case "3":
                this.Carta3(xogador, xogadores);
                break;
            case "4":
                this.Carta4(xogador, taboleiro, xogadores);
                break;
            case "5":
                this.Carta5(xogador);
                break;
            case "6":
                this.Carta6(xogador);
                break;
            case "7":
                this.Carta7(xogador);
                break;
            case "8":
                this.Carta8(xogador);
                break;
            case "9":
                this.Carta9(xogador);
                break;
            case "10":
                this.Carta10(xogador);
                break;
            case "11":
                this.Carta11(xogador);
                break;
            case "12":
                this.Carta12(xogador);
                break;
            case "13":
                this.Carta13(xogador);
                break;
            case "14":
                this.Carta14(xogador);
                break;
        }
    }

    /*

case 6:
    accion += "Decides descansar unha fin de semana en Meanho axudando na Vendimia. Avanza ata Meanho. Se pasas pola Saída cobra 250 GM.\n";
    System.out.println(accion);
    avanzar(avatar, casillaProcedencia, taboleiro.getCasilla("Meanho"), taboleiro, partida);
    break;
case 7:
    accion += "A Garda Civil colleute no medio dunha operación. Vai ao Cárcere directamente sen pasar pola casilla de Saída e sen cobrar as 250 GM.\n";
    System.out.println(accion);
    enviarCarcere(avatar, partida, taboleiro);
    break;

case 10:
    accion += "Decides facer o Caminho de Santiago de Compostela. Avanza ata Santiago. Se pasas pola casilla de Salida, cobra 250 GM.\n";
    System.out.println(accion);
    avanzar(avatar, casillaProcedencia, taboleiro.getCasilla("Santiago"), taboleiro, partida);
    break;

case 11:
    accion += "Esqueciches a túa carteira en Arteixo. Retrocede ata Arteixo para recuperala.\n";
    System.out.println(accion);
    retroceder(avatar, taboleiro.getCasilla("Arteixo"), taboleiro, partida, sumaDados, cartas);
    break;
case 12:
    accion += "Múltante por usar o móbil mentres conduces. Paga 20 GM.\n";
    System.out.println(accion);
    avatar.getXogador().modificarFortuna(-20);
    System.out.println("O xogador " + avatar.getXogador().getNome() + " pagou 20 GM.\n");
    break;
case 13:
    accion += "Beneficio pola venta das túas accións. Recibe 185 GM.\n";
    System.out.println(accion);
    avatar.getXogador().modificarFortuna(185);
    System.out.println("O xogador " + avatar.getXogador().getNome() + " ganhou 185 GM.\n");
    break;
case 14:
    accion += "Avanza ata a casilla de transporte maís próxima. Se non ten dono podes comprarlla á banca. Se ten dono paga ao dono o dobre da operación indicada.\n";
    System.out.println(accion);
    //Pagar o dobre
    avanzar(avatar, casillaProcedencia, calcularTransporteProximo(avatar, taboleiro), taboleiro, partida);
     */
    public void Carta1(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {
        String accion = "Carta de Sorte 1:\n";
        accion += "Oubinha deixache dar unha volta entre as bateas coa súa "
                + "lancha motora. Vai ata a casilla Lancha Motora. Se pasas "
                + "pola Saída cobra 250 GM.\n";
        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        Solar destino = (Solar) taboleiro.getCasilla("Lancha Motora");

        if (avatar.getPosicion().getPosicion() > destino.getPosicion()) {
            xogador.modificarFortuna(Constantes.VALOR_VOLTA);
            consola.imprimir("O xogador " + xogador.getNome() + " pasou pola "
                    + "Saída. Cobrou 250 GM.\n");
        }

        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(destino);

        comprobarCasilla(xogador, destino, xogadores, taboleiro);
    }

    public void Carta2(Xogador xogador) {
        String accion = "Carta de Sorte 2:\n";
        accion += "O aumento do imposto sobre bens inmobles afecta a todas as túas propiedades. "
                + "Paga 50 GM por  casa, 145 GM por hotel, 25 GM por piscina e 90 GM por pista de deporte.\n";
        consola.imprimir(accion);
        this.pagarImpostoInmoble(xogador);
    }

    public void Carta3(Xogador xogador, ArrayList<Xogador> xogadores) {
        String accion = "Carta de Sorte 3:\n";
        accion += "Fuches escollido presidente da xunta directiva. Paga a cada"
                + " xogador 30 GM.\n";
        consola.imprimir(accion);

        for (int i = 0; i < xogadores.size(); i++) {
            if (!xogadores.get(i).equals(xogador)) {
                xogador.modificarFortuna(-30);
                xogadores.get(i).modificarFortuna(30);
            }
        }

        consola.imprimir("O xogador " + xogador.getNome()
                + " pagou " + 30 * xogadores.size() + " GM. A súa fortuna "
                + "actual é " + xogador.getFortuna() + " GM.\n");
    }

    public void Carta4(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {

        String accion = "Carta de Sorte 4:\n";
        accion += "Decides ir facer certos recados por ti mesmo. Avanza ata "
                + "Cambados. Se pasas pola Saída cobra 250 GM.\n";

        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        Solar destino = (Solar) taboleiro.getCasilla("Cambados");

        if (avatar.getPosicion().getPosicion() > destino.getPosicion()) {
            xogador.modificarFortuna(Constantes.VALOR_VOLTA);
            consola.imprimir("O xogador " + xogador.getNome() + " pasou pola "
                    + "Saída. Cobrou 250 GM.\n");
        }

        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(destino);

        comprobarCasilla(xogador, destino, xogadores, taboleiro);
    }

    public void Carta5(Xogador xogador) {
        String accion = "Carta de Sorte 2:\n";
        accion += "Vendes a túa Lancha. Cobra 60 GM.\n";
        System.out.println(accion);
        xogador.modificarFortuna(60);
        System.out.println("O xogador " + xogador.getNome() + " ganhou 60 GM.\n");
    }

    public void Carta8(Xogador xogador) {
        String accion = "Carta de Sorte 8:\n";
        accion += "Ganhaches o bote da lotaría! Recibe 125 GM.\n";
        System.out.println(accion);
        xogador.modificarFortuna(125);
        System.out.println("O xogador " + xogador.getNome() + " ganhou 125 GM.\n");
    }

    public void Carta9(Xogador xogador) {
        String accion = "Carta de Sorte 9:\n";
        accion += "Paga 185 GM pola matrícula do colexio privado.\n";
        System.out.println(accion);
        xogador.modificarFortuna(-185);
        System.out.println("O xogador " + xogador.getNome() + " pagou 185 GM.\n");

    }

    private void pagarImpostoInmoble(Xogador xogador) {
        if (xogador != null) {
            int casas = 0, hoteis = 0, pistas = 0, piscinas = 0;
            float total;
            for (Propiedade propiedade : xogador.getPropiedades()) {
                if (propiedade instanceof Solar) {
                    casas += ((Solar) propiedade).getNCasas();
                    hoteis += ((Solar) propiedade).getNHoteis();
                    piscinas += ((Solar) propiedade).getNPiscinas();
                    pistas += ((Solar) propiedade).getNPistas();
                }

            }
            total = 50 * casas + 145 * hoteis + 25 * piscinas + 90 * pistas;
            xogador.modificarFortuna(-total);
            xogador.setPagoTasasEImportos(total);
            System.out.println("\nO xogador " + xogador.getNome() + " pagou " + total
                    + " GM, xa que ten " + casas + " casas, " + hoteis
                    + " hoteis, " + piscinas + " piscinas e " + pistas + " pistas de deporte.\n");
        } else {
            consola.imprimir("\nErro en pagarImpostoInmoble.\n");
        }
    }
}
