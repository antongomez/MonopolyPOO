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
                this.Carta6(xogador, taboleiro, xogadores);
                break;
            case "7":
                this.Carta7(xogador, taboleiro);
                break;
            case "8":
                this.Carta8(xogador);
                break;
            case "9":
                this.Carta9(xogador);
                break;
            case "10":
                this.Carta10(xogador, taboleiro, xogadores);
                break;
            case "11":
                this.Carta11(xogador, taboleiro, xogadores);
                break;
            case "12":
                this.Carta12(xogador);
                break;
            case "13":
                this.Carta13(xogador);
                break;
            case "14":
                this.Carta14(xogador, taboleiro, xogadores);
                break;
        }
    }

    public void Carta1(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {
        String accion = "Carta de Sorte 1:\n";
        accion += "Oubinha deixache dar unha volta entre as bateas coa súa "
                + "lancha motora. Vai ata a casilla Lancha Motora. Se pasas "
                + "pola Saída cobra 250 GM.\n";
        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        Transporte destino = (Transporte) taboleiro.getCasilla("Lancha Motora");

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
        String accion = "Carta de Sorte 5:\n";
        accion += "Vendes a túa Lancha. Cobra 60 GM.\n";
        System.out.println(accion);
        xogador.modificarFortuna(60);
        System.out.println("O xogador " + xogador.getNome() + " ganhou 60 GM.\n");
    }

    public void Carta6(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {

        String accion = "Carta de Sorte 6:\n";
        accion += "Decides descansar unha fin de semana en Meanho axudando "
                + "na Vendimia. Avanza ata Meanho. "
                + "Se pasas pola Saída cobra 250 GM.\n";

        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        Solar destino = (Solar) taboleiro.getCasilla("Meanho");

        if (avatar.getPosicion().getPosicion() > destino.getPosicion()) {
            xogador.modificarFortuna(Constantes.VALOR_VOLTA);
            consola.imprimir("O xogador " + xogador.getNome() + " pasou pola "
                    + "Saída. Cobrou 250 GM.\n");
        }

        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(destino);

        comprobarCasilla(xogador, destino, xogadores, taboleiro);
    }

    public void Carta7(Xogador xogador, Taboleiro taboleiro) {

        String accion = "Carta de Sorte 7:\n";
        accion += "A Garda Civil colleute no medio dunha operación. "
                + "Vai ao Cárcere directamente sen pasar pola casilla de Saída "
                + "e sen cobrar as 250 GM.\n";
        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        Casilla destino = taboleiro.getCasilla("Carcere");

        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(destino);
        xogador.setEstadoPreso(4);
    }

    public void Carta8(Xogador xogador) {
        String accion = "Carta de Sorte 8:\n";
        accion += "Ganhaches o bote da lotaría! Recibe 125 GM.\n";
        consola.imprimir(accion);
        xogador.modificarFortuna(125);
        consola.imprimir("O xogador " + xogador.getNome() + " ganhou 125 GM.\n");
    }

    public void Carta9(Xogador xogador) {
        String accion = "Carta de Sorte 9:\n";
        accion += "Paga 185 GM pola matrícula do colexio privado.\n";
        consola.imprimir(accion);
        xogador.modificarFortuna(-185);
        consola.imprimir("O xogador " + xogador.getNome() + " pagou 185 GM.\n");

    }

    public void Carta10(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {

        String accion = "Carta de Sorte 10:\n";
        accion += "Decides facer o Caminho de Santiago de Compostela para "
                + "redimirte dos teus pecados. Avanza ata Santiago. "
                + "Se pasas pola casilla de Salida, "
                + "cobra 250 GM.\n";

        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        Solar destino = (Solar) taboleiro.getCasilla("Santiago");

        if (avatar.getPosicion().getPosicion() > destino.getPosicion()) {
            xogador.modificarFortuna(Constantes.VALOR_VOLTA);
            consola.imprimir("O xogador " + xogador.getNome() + " pasou pola "
                    + "Saída. Cobrou 250 GM.\n");
        }

        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(destino);

        comprobarCasilla(xogador, destino, xogadores, taboleiro);
    }

    public void Carta11(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {

        String accion = "Carta de Sorte 11:\n";
        accion += "Esqueciches a túa carteira en Arteixo. Retrocede ata Arteixo"
                + " para recuperala.";

        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        Solar destino = (Solar) taboleiro.getCasilla("Arteixo");

        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(destino);

        comprobarCasilla(xogador, destino, xogadores, taboleiro);
    }

    public void Carta12(Xogador xogador) {
        String accion = "Carta de Sorte 12:\n";
        accion += "Múltante por usar o móbil mentres conduces. Paga 20 GM.\n";
        consola.imprimir(accion);
        xogador.modificarFortuna(-20);
        consola.imprimir("O xogador " + xogador.getNome() + " pagou 20 GM. A "
                + "súa fortuna actual é de " + xogador.getFortuna() + " GM.\n");
    }

    public void Carta13(Xogador xogador) {
        String accion = "Carta de Sorte 13:\n";
        accion += "Beneficio pola venta das túas accións. Recibe 185 GM.\n";
        consola.imprimir(accion);
        xogador.modificarFortuna(185);
        consola.imprimir("O xogador " + xogador.getNome() + " recibiu 185 GM. A "
                + "súa fortuna actual é de " + xogador.getFortuna() + " GM.\n");
    }

    public void Carta14(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {

        String accion = "Carta de Sorte 14:\n";
        accion += "Avanza ata a casilla de transporte maís próxima. Se non ten "
                + "dono podes comprarlla á banca. Se ten dono paga ao dono o "
                + "dobre da operación indicada.\n";
        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        Transporte destino;
        if ((Math.round(avatar.getPosicion().getPosicion() / 10f) * 10 + 5) < 40) {
            destino = (Transporte) taboleiro.getCasilla(
                    Math.round(avatar.getPosicion().getPosicion() / 10f) * 10 + 5);
        } else {
            destino = (Transporte) taboleiro.getCasilla(5);
        }

        if (avatar.getPosicion().getPosicion() > destino.getPosicion()) {
            xogador.modificarFortuna(Constantes.VALOR_VOLTA);
            consola.imprimir("O xogador " + xogador.getNome() + " pasou pola "
                    + "Saída. Cobrou 250 GM.\n");
        }

        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(destino);

        comprobarCasilla(xogador, destino, xogadores, taboleiro);
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
