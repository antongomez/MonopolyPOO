package xogo;

import estrutura.*;
import xogadores.*;
import consola.*;

import java.util.Scanner;

public class Xogo implements Comando{
    
    private static ConsolaNormal consola = new ConsolaNormal();
    
    public Xogo() {
        Dado dado1 = new Dado();
        Dado dado2 = new Dado();
        boolean sair = false;


        Xogador xogador;
        Avatar avatar;

        //Creamos os xogadores
        Xogador banca = new Xogador();
        Xogador hipotecar = new Xogador("Hipotecar");
        int nXogadores = 0;
        do {
            nXogadores = Integer.parseInt(consola.ler("Cantos xogadores sodes? "));
            if ((nXogadores < 2) || (nXogadores > 7)) {
                consola.imprimir("O numero de xogadores non e valido.\n");
            }
        } while ((nXogadores < 2) || (nXogadores > 7));


        Partida partida = new Partida(nXogadores);
        Taboleiro taboleiro = new Taboleiro(banca);


        //Pedimos nome e avatar para inicializar todos os xogadores menos a banca
        for (int i = 0; i < nXogadores; i++) {
            String nomeXogador = "";
            do {
                //Pedimos a información ao usuario
                consola.imprimir("Introduce o nome do xogador " + (i + 1) + ": ");
                Scanner scan = new Scanner(System.in);
                nomeXogador = scan.next();
                if (nomeIgual(nomeXogador, partida)) {
                    consola.imprimir("\nXa existe un xogador con ese nome."
                            + " Introduce outro nome.\n");
                }
            } while (nomeIgual(nomeXogador, partida));
            String avatarXogador = consola.ler("Introduce o tipo de avatar"
                    + " do xogador " + (i + 1) + ": ");

            //Creamos cada xogador
            partida.setXogador(new Xogador(nomeXogador, avatarXogador, taboleiro, partida));
            partida.getXogador(i).CrearAvatarXogador(avatarXogador, taboleiro, partida);

            consola.imprimir("O avatar do xogador e: " 
                    + partida.getXogador(i).getAvatar().getId() + "\n");
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


            xogador = partida.getXogador(partida.getTurno() - 1);
            avatar = xogador.getAvatar();

            ///FALTAN UN MONETE DE COUSAS AQUI


            //Facemos as declaracións e imos lendo do caso que sexa
            String orde = "";
            Scanner scan = new Scanner(System.in);
            orde = scan.nextLine();

            String comando0;
            String comando1;
            String comando2;

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
                case "acabar":
                    if (comando1.equals("partida")) {
                        sair = true;
                    }
                    break;
                case "describir":
                    break;

            }

        }
    }

    //Métodos auxiliares para a funcionalidade
    private boolean nomeIgual(String nome, Partida partida) {
        if (!partida.getXogadores().isEmpty()) {
            for (Xogador xogador : partida.getXogadores()) {
                if (xogador.getNome().equals(nome)) {
                    return true;
                }
            }
        }
        return false;
    }
}
