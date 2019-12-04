package xogo;

import estrutura.*;
import xogadores.*;
import consola.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Xogo implements Comando {

    public static final ConsolaNormal consola = new ConsolaNormal();
    private ArrayList<Xogador> xogadores;
    private ArrayList<Avatar> avatares;
    private Taboleiro taboleiro;
    private HashMap<String, Dado> dados;
    private int turno = 0;
    private Xogador banca;

    public Xogo() {

        xogadores = new ArrayList<>();
        avatares = new ArrayList<>();
        dados = new HashMap<>();
        banca = new Xogador();
        taboleiro = new Taboleiro(banca);

        int nXogadores = 0;
        Boolean sair = false;
        Xogador hipotecar = new Xogador("Hipotecar"); //Xogador ao que se lle hipoteca

        do {
            nXogadores = Integer.parseInt(consola.ler("Cantos xogadores sodes? "));
            if ((nXogadores < 2) || (nXogadores > 7)) {
                consola.imprimir("O número de xogadores non é válido.\n");
            }
        } while ((nXogadores < 2) || (nXogadores > 7));

        //Partida partida = new Partida(nXogadores);
        //Pedimos nome e avatar para inicializar todos os xogadores menos a banca
        for (int i = 0; i < nXogadores; i++) {
            //Creamos o xogador
            String nomeXogador = "";
            do {
                //Pedimos a información ao usuario
                nomeXogador = consola.ler("Introduce o nome do xogador " + (i + 1) + ": ");

                if (nomeIgualXogador(nomeXogador)) {
                    consola.imprimir("\nXa existe un xogador con ese nome."
                            + " Introduce outro nome.\n");
                }
            } while (nomeIgualXogador(nomeXogador));

            //Engadimos o xogador
            xogadores.add(new Xogador(nomeXogador, taboleiro));

            String tipoAvatar = "";
            char IdAvatar;

            //Creamos o avatar
            tipoAvatar = consola.ler("Introduce o tipo de avatar"
                    + " do xogador " + (i + 1) + ": ");
            IdAvatar = Avatar.xerarId();

            while (IdIgualAvatar(IdAvatar)) {
                IdAvatar = Avatar.xerarId();
            }
            //Engadimos o avatar
            switch (tipoAvatar) {
                case "Sombreiro":
                case "sombreiro":
                case "Chapeu":
                case "chapeu":
                    avatares.add(new Chapeu(IdAvatar, xogadores.get(i), taboleiro));
                    xogadores.get(i).setAvatar(avatares.get(i));
                    break;
                case "Coche":
                case "coche":
                    avatares.add(new Coche(IdAvatar, xogadores.get(i), taboleiro));
                    break;
                case "Esfinge":
                case "esfinge":
                case "esfinxe":
                case "Esfinxe":
                    avatares.add(new Esfinxe(IdAvatar, xogadores.get(i), taboleiro));
                    break;
                case "pelota":
                case "Pelota":
                    avatares.add(new Pelota(IdAvatar, xogadores.get(i), taboleiro));
                    break;
                default:
                    avatares.add(new Pelota(IdAvatar, xogadores.get(i), taboleiro));
                    consola.imprimir("Introduciuse Pelota por defecto.\n");
                    break;
            }

            consola.imprimir("O avatar do xogador e: "
                    + avatares.get(i).getId() + "\n");
        }

        taboleiro.imprimirTaboeiro();

        //Incio xogo
        String menuimpreso = "=========== MENU ==========\n"
                + "1. listar xogadores\n" + "2. listar avatares\n" + "3. listar enventa\n"
                + "4. lanzar dados\n" + "5. rematar turno\n" + "6. xogador (indica o turno)\n"
                + "7. describir casilla\n" + "8. describir xogador\n" + "9. describir avatar\n"
                + "10. comprar\n" + "11. ver taboleiro\n" + "12. rematar partida\n"
                + "===========================\n";

        while (!sair) {
            consola.imprimir(menuimpreso);

            Xogador xogador = xogadores.get(turno);
            Avatar avatar = xogador.getAvatar();
            String comando0;
            String comando1;
            String comando2;

            //Facemos as declaracións e imos lendo do caso que sexa
            String orde = consola.ler("Lendo");

            //Xestion de comando
            String[] partes = orde.split(" ");
            comando0 = partes[0];

            if (partes.length > 1) {
                comando1 = partes[1];
            } else {
                comando1 = "";
            }
            if (partes.length > 2) {
                comando2 = partes[2];
            } else {
                comando2 = "";
            }

            switch (comando0) {
                case "cambiar":
                    if (comando1.equals("modo")) {
                        consola.imprimir("Merda");
                        cambiarModo(avatar);
                    }
                    break;
                case "comprar":
                    if (avatar.getPosicion() instanceof Propiedade) {
                        comprar((Propiedade) avatar.getPosicion(), xogador);
                    } else {
                        //Excepcion
                    }
                    break;
                case "describir":
                    describir(comando1);
                    break;
                case "rematar":
                    switch (comando1) {
                        case "partida":
                            rematarPartida(sair);
                            break;
                    }
                    break;

            }

        }
    }

    //Métodos auxiliares para a funcionalidade
    @Override
    public final void cambiarModo(Avatar avatar) {
        if (!avatar.getModoAvanzado()) {
            avatar.setModoAvanzado(true);
        } else {
            avatar.setModoAvanzado(false);
        }
    }

    @Override
    public final void comprar(Propiedade propiedade, Xogador xogador) {
        if (propiedadeComprable(propiedade)) {
            if (xogador.getFortuna() > propiedade.getValor()) {
                propiedade.comprar(xogador);
                consola.imprimir("O xogador " + xogador.getNome() + " compra a "
                        + "casilla " + propiedade.getNome() + " por "
                        + propiedade.getValor() + ". A súa fortuna actual é "
                        + xogador.getFortuna() + " GM.\n");
            }
        }
    }

    @Override
    public final void describir(String nomeCasilla) {
        if (existeCasilla(nomeCasilla)) {
            consola.imprimir(taboleiro.getCasilla(nomeCasilla).imprimirCasilla());
        } else {
            //Excepcion
        }
    }

    private boolean existeCasilla(String nomeCasilla) {

        return taboleiro.getCasilla(nomeCasilla) != null;

    }

    private boolean IdIgualAvatar(char id) {
        if (!avatares.isEmpty()) {
            for (Avatar avatar : avatares) {
                if (avatar.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean nomeIgualXogador(String nome) {
        if (xogadores.isEmpty()) {
            for (Xogador xogador : xogadores) {
                if (xogador.getNome().equals(nome)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean propiedadeComprable(Propiedade propiedade) {
        return propiedade.getDono().equals(banca);
    }

    @Override
    public final void rematarPartida(Boolean sair) {
        sair = true;
    }

    //Fin clase
}
