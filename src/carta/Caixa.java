package carta;

import xogadores.*;
import estrutura.*;
import static xogo.Xogo.consola;
import java.util.ArrayList;
import errosExternos.*;

public class Caixa extends Carta {

    public Caixa(String nome) {
        super(nome);
    }

    @Override
    public void accion(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {

        String partes[] = super.getNome().split("-");
        switch (partes[1]) {
            case "1":
                this.Carta1(xogador);
                break;
            case "2":
                this.Carta2(xogador, taboleiro);
                break;
            case "3":
                this.Carta3(xogador, taboleiro);
                break;
            case "4":
                this.Carta4(xogador);
                break;
            case "5":
                this.Carta5(xogador, xogadores);
                break;
            case "6":
                this.Carta6(xogador, xogadores);
                break;
            case "7":
                this.Carta7(xogador);
                break;
            case "8":
                this.Carta8(xogador, taboleiro, xogadores);
                break;
            case "9":
                this.Carta9(xogador);
                break;
            case "10":
                this.Carta10(xogador, taboleiro, xogadores);
                break;
        }
    }

    public void Carta1(Xogador xogador) {
        String accion = "Carta de Caixa 1:\n";
        accion += "Paga 60 GM por un fin de semana nun balneario de 5 estrelas "
                + "en Corrubedo.\n";
        consola.imprimir(accion);
        xogador.modificarFortuna(-60);
        consola.imprimir("O xogador " + xogador.getNome() + " pagou 60 GM. "
                + "A súa fortuna actual é " + xogador.getFortuna() + " GM.\n");

    }

    public void Carta2(Xogador xogador, Taboleiro taboleiro) {
        String accion = "Carta de Caixa 2:\n";
        accion += "Investigante por fraude de identidade. Vai ao Cárcere "
                + "directamente sen pasar pola casilla de Saída e sen cobrar as "
                + "250 GM.\n";
        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(taboleiro.getCasilla("Carcere"));
        xogador.setEstadoPreso(4);
    }

    public void Carta3(Xogador xogador, Taboleiro taboleiro) {
        String accion = "Carta de Caixa 3:\n";
        accion += "A última operación saíche ben e conseguiches escapar da "
                + "Garda Civil. Colócate na casilla de Saída. Cobra 250 GM.\n";
        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(taboleiro.getCasilla("Saida"));
        xogador.modificarFortuna(Constantes.VALOR_VOLTA);
    }

    public void Carta4(Xogador xogador) {
        String accion = "Carta de Caixa 4:\n";
        accion += "Conseguiches sacar máis beneficios do que esperabas pola "
                + "venta das túas leira en Cedofeita. Recibe 250 GM.\n";
        consola.imprimir(accion);

        xogador.modificarFortuna(250);
        consola.imprimir("O xogador " + xogador.getNome()
                + " ganhou 250 GM. A súa fortuna actual é "
                + xogador.getFortuna() + " GM.\n");
    }

    public void Carta5(Xogador xogador, ArrayList<Xogador> xogadores) {
        String accion = "Carta de Caixa 5:\n";
        accion += "Paga 125 GM por invitar a todos os teus amigos a unha "
                + "viaxe a Ribadeo.\n";
        consola.imprimir(accion);

        for (int i = 0; i < xogadores.size(); i++) {
            if (!xogadores.get(i).equals(xogador)) {
                xogador.modificarFortuna(-125);
                xogadores.get(i).modificarFortuna(125);
            }
        }

        consola.imprimir("O xogador " + xogador.getNome()
                + " pagou " + 125 * xogadores.size() + " GM. A súa fortuna "
                + "actual é " + xogador.getFortuna() + " GM.\n");
    }

    public void Carta6(Xogador xogador, ArrayList<Xogador> xogadores) {
        String accion = "Carta de Caixa 6:\n";
        accion += "Alquilas aos teus companheiros o castelo de Santa Cruz "
                + "durante unha semana. Paga 25 GM a cada xogador.\n";
        consola.imprimir(accion);

        for (int i = 0; i < xogadores.size(); i++) {
            if (!xogadores.get(i).equals(xogador)) {
                xogador.modificarFortuna(-25);
                xogadores.get(i).modificarFortuna(25);
            }
        }

        consola.imprimir("O xogador " + xogador.getNome()
                + " pagou " + 25 * xogadores.size() + " GM. A súa fortuna "
                + "actual é " + xogador.getFortuna() + " GM.\n");
    }

    public void Carta7(Xogador xogador) {
        String accion = "Carta de Caixa 7:\n";
        accion += "Devolución de Facenda. Cobra 60 GM.\n";
        consola.imprimir(accion);

        xogador.modificarFortuna(60);
        consola.imprimir("O xogador " + xogador.getNome()
                + " ganhou 60 GM. A súa fortuna actual é "
                + xogador.getFortuna() + " GM.\n");
    }

    public void Carta8(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {
        String accion = "Carta de Caixa 8:\n";
        accion += "Hora punta de tráfico! Retrocede tres casillas.\n";
        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        Casilla destino;

        if (avatar.getPosicion().getPosicion() - 3 < 0) {
            destino = taboleiro.getCasilla(
                    avatar.getPosicion().getPosicion() - 3 + 40);
        } else {
            destino = taboleiro.getCasilla(
                    avatar.getPosicion().getPosicion() - 3);
        }
        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(destino);

        /*Ao retroceder 3 casillas só pode caer nun solar ou na casilla IrCarcere*/
        comprobarCasilla(xogador, destino, xogadores, taboleiro);
    }

    public void Carta9(Xogador xogador) {
        String accion = "Carta de Caixa 9:\n";
        accion += "Recibe 125 GM de beneficios por alquilar os servicios do "
                + "teu Jet Privado.\n";
        consola.imprimir(accion);

        xogador.modificarFortuna(125);
        consola.imprimir("O xogador " + xogador.getNome()
                + " ganhou 125 GM. A súa fortuna actual é "
                + xogador.getFortuna() + " GM.\n");
    }

    public void Carta10(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes {

        String accion = "Carta de Caixa 10:\n";
        accion += "Vai a Viveiro para disfrutar do Resurrection Fest. "
                + "Se pasas pola Saída cobra as 250 GM.\n";
        consola.imprimir(accion);

        Avatar avatar = xogador.getAvatar();
        Solar destino = (Solar) taboleiro.getCasilla("Viveiro");

        if (avatar.getPosicion().getPosicion() > destino.getPosicion()) {
            xogador.modificarFortuna(Constantes.VALOR_VOLTA);
            consola.imprimir("O xogador " + xogador.getNome() + " pasou pola "
                    + "Saída. Cobrou 250 GM.\n");
        }

        avatar.getPosicion().eliminarAvatar(avatar);
        avatar.setPosicion(destino);

        comprobarCasilla(xogador, destino, xogadores, taboleiro);

    }

    //Fin Clase
}
