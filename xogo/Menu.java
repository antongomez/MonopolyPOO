package xogo;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import xogadores.Xogador;
import xogadores.Avatar;
import estrutura.Constantes;
import estrutura.Casilla;
import estrutura.Grupo;
import estrutura.Taboleiro;
import estrutura.Edificio;
import java.util.Collections;

public class Menu {

    public Menu() {
        //Creamos o xogador Banca e o taboleiro
        Xogador banca = new Xogador();
        Xogador hipotecar = new Xogador("hipotecas");
        Taboleiro taboleiro = new Taboleiro(banca);

        //Dados
        Dado dado1 = new Dado();
        Dado dado2 = new Dado();

        //Variables para xestionar as funcionalidades do xogo
        int sumaDados = 0;             //Almacenase a suma dos dados.
        boolean dadosLanzados = false; //Utilizase para saber se se lanzaron os dados.
        boolean dobres = false;                //Utilizase para saber se se sacou dobres.
        boolean sair = false;          //Utilizase para rematar a partida.
        boolean avanzando = false;     //Utilizase para os movementos avanzados
        int avanceRestante = 0;        //Utilizase nos movementos avanzados para saber canto falta por avanzar
        boolean atopado;               //Utilizase para saber se existe a casillaProcedencia, xogador ou avatar que se meteu por comandos.
        int nDobres = 0;               //Utilizase para saber cantas veces seguidas tirou dobres.

        //Cartas de sorte e caixa da comunidade
        ArrayList<ArrayList<String>> cartas = new ArrayList<>();
        //Creamos as cartas
        crearCartas(cartas);

        //Variables para simplificar o codigo
        Xogador xogador;
        Casilla casillaDestino;
        Casilla casillaProcedencia;
        Avatar avatar;

        char[] avatarescompro = new char[6];

        //Variables para recoller os comandos
        String comando0, comando1, comando2;

        System.out.println("Benvido ao MonopolyETSE.\n");

        //Inicialización
        int nXogadores = 0;//Pedimos o numero de xogadores e comprobamos que sexa valido
        do {
            System.out.println("Cantos xogadores sodes? ");
            Scanner sc = new Scanner(System.in);
            nXogadores = sc.nextInt();
            if ((nXogadores < 2) || (nXogadores > 7)) {
                System.out.println("O numero de xogadores non e valido.\n");
            }
        } while ((nXogadores < 2) || (nXogadores > 7));

        //Inicializamos a partida co numero de xogadores introucido e metemos o xogador banca
        Partida partida = new Partida(nXogadores);
        partida.setBanca(banca);

        //Pedimos nome e avatar para inicializar todos os xogadores menos a banca
        for (int i = 0; i < nXogadores; i++) {
            String nomeXogador = "";
            do {
                //Pedimos a información ao usuario
                System.out.println("Introduce o nome do xogador " + (i + 1) + ": ");
                Scanner nom = new Scanner(System.in);
                nomeXogador = nom.next();
                if (nomeIgual(nomeXogador, partida)) {
                    System.out.println("\nXa existe un xogador con ese nome. Introduce outro nome.\n");
                }
            } while (nomeIgual(nomeXogador, partida));
            System.out.println("Introduce o tipo de avatar do xogador " + (i + 1) + ": ");
            Scanner avt = new Scanner(System.in);
            String avatarXogador = avt.next();

            //Creamos cada xogador
            partida.setXogador(new Xogador(nomeXogador, avatarXogador, taboleiro));
            partida.setAvatar(partida.getXogador(i).getAvatar());

            System.out.println("O avatar do xogador e: " + partida.getXogador(i).getAvatar().getId() + "\n");

            //Comprobacion de que o Id dos avatares sexa unico
            comprobarID(i, avatarescompro, partida);
            avatarescompro[i] = partida.getXogador(i).getAvatar().getId();
        }

        taboleiro.imprimirTaboeiro(nXogadores);

        //Control por arquivo
        System.out.println("\nDesexa que introducir comandos ou leer arquivo? s para arquivo.");
        Scanner lido = new Scanner(System.in);
        String opcion = lido.next();
        Scanner scanner = new Scanner(System.in);

        //Intenta leer o arquivo e senon devolve un erro asique hai que telo sempre ainda que sexa  baleiro.
        BufferedReader buffRead = null;
        try {
            //Victor
            //String directorio = "/home/victorxbd/Documentos/";
            //Anton
            String directorio = "/home/victorxbd/Documentos/uni/poo/monopolydomingo/Entrega1/out/production/Entrega1/xogo/";
            FileReader fileRead = new FileReader(directorio + "comandos.txt");
            buffRead = new BufferedReader(fileRead);
        } catch (FileNotFoundException notFound) {
            System.out.print(notFound.getMessage());
            //System.exit(0);
        }

        //Incio xogo
        String menuimpreso = "=========== MENU ==========\n"
                + "1. listar xogadores\n" + "2. listar avatares\n" + "3. listar enventa\n"
                + "4. lanzar dados\n" + "5. rematar turno\n" + "6. xogador (indica o turno)\n"
                + "7. describir casilla\n" + "8. describir xogador\n" + "9. describir avatar\n"
                + "10. comprar\n" + "11. ver taboleiro\n" + "12. rematar partida\n"
                + "===========================\n";

        while (!sair) {
            //Imprimimos o menu
            System.out.println(menuimpreso);

            //Facilitamos o xogador que ten o turno e o seu avatar
            xogador = partida.getXogador(partida.getTurno() - 1);
            avatar = xogador.getAvatar();

            /*Comprobamos se a casilla na que esta o xogador e o carcere e, se o
             * e, cantos turnos leva. No caso de que esgotara todas as
             * oportunidades de sacar dobres, o xogador debe pagar obrigatoriamente.
             */
            if (estarPreso(xogador, partida)) {
                System.out.println("\nO xogador " + xogador.getNome() + " esta preso. ");
                avanzando = false;
                avanceRestante = 0;
                if ((xogador.getEstarPreso() != 1) && (!dadosLanzados)) {
                    System.out.println("Este é o " + ordinal(5 - xogador.getEstarPreso())
                            + " turno no Cárcere.\n");
                    System.out.println("Tes dúas opcións. \n\t- Pagar (sair carcere) \n\t- Intentar sacar dobres (lanzar dados)\n");
                } else if ((xogador.getEstarPreso() == 1)) {
                    System.out.println("Esgotaches os teus intentos para sacar dobres. Tes que pagar obrigatoriamente.\n");
                    pagoCarcere(xogador, taboleiro);
                }
            }
            if (avanzando) {
                if (avanceRestante == 0) {
                    System.out.println("ERRO: avanzando e avanceRestante non están coordinados.");
                } else if (avanceRestante != 0) {
                    switch (avatar.getTipo()) {
                        case "pelota":
                            if (avanceRestante > 0) {
                                if (avanceRestante == 1) {
                                    System.out.println("Avanzaches " + (sumaDados - avanceRestante)
                                            + " casillas. Tes que avanzar unha vez máis.");
                                } else {
                                    System.out.println("Avanzaches " + (sumaDados - avanceRestante)
                                            + " casillas. Aínda tes que rebotar "
                                            + (int) Math.floor(avanceRestante / 2) + " veces, "
                                            + "é dicir, avanzar dúas casillas "
                                            + "por cada vez que se rebota.");
                                    if (dobres) {
                                        System.out.println(" Despois tes que volver a lanzar"
                                                + " os dados xa que lanzaches dobres.");
                                    }
                                }
                            } else {
                                if (avanceRestante == -1) {
                                    System.out.println("Retrocediches " + (sumaDados + avanceRestante)
                                            + " casillas. Tes que retroceder unha vez máis.");
                                } else {
                                    System.out.println("Retrocediches 1 casilla. "
                                            + "Tes que rebotar 1 vez máis cara atrás, "
                                            + "é dicir, retroceder dúas casillas.");
                                }
                            }
                            if (dobres) {
                                System.out.println(" Despois tes que volver a lanzar"
                                        + " os dados xa que lanzaches dobres.");
                            }
                            System.out.println("\n");
                            break;
                        case "coche":
                            break;
                        default:
                            break;
                    }
                }
            }
            if (avatar.getBloqueado() > 0) {
                dadosLanzados = true;
                avatar.restarTurnoBloqueo();
                System.out.println("\nO xogador " + xogador.getNome() + " non pode "
                        + "lanzar os dados neste turno. ");
                switch (avatar.getBloqueado()) {
                    case 1:
                        System.out.println("Quédalle un turno bloqueado.\n");
                        break;
                    case 0:
                        System.out.println("No seguinte turno xa poderá lanzar.\n");
                        break;
                    default:
                        System.out.println("Quédanlle " + avatar.getBloqueado() + " turnos bloqueado.\n");
                        break;
                }
            }
            if (avatar.getArrancado()) {
                System.out.println("Tes " + avanceRestante + " lanzamentos máis.\n");
            }

            //Turno do xogador
            System.out.println("Turno de " + xogador.getNome());
            System.out.print("$> ");

            //Facemos as declaracións e imos lendo do caso que sexa
            String orde = "";

            if ((opcion.equals("s")) || (opcion.equals("S"))) {
                try { //En caso de que se lea o arquivo
                    orde = buffRead.readLine();
                } catch (IOException io) {
                    System.out.println(io.getMessage());
                }
            } else { //En caso de que se lea por comandos
                orde = scanner.nextLine();
            }

            //Xestion do comando
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
                    } else if (comando1.equals("turno")) {
                        if (dadosLanzados) {
                            //Calculamos o turno seguinte
                            partida.calcularTurnoSeguinte();
                            //Imprimimos o xogador ao que lle toca xogar.
                            System.out.println("\nO xogador actual e " + partida.getXogador(partida.getTurno() - 1).getNome() + (".\n"));
                            //Ponse a 0 o contador de dobres e a false a variable que controla se se lanzaron os dados
                            dadosLanzados = false;
                            nDobres = 0;
                        } else {
                            System.out.println("\nPrimeiro debes lanzar os dados.\n");
                        }
                    }
                    break;

                case "avanzar":
                    if (avatar.getTipo().equals("pelota")) {
                        if (avatar.getModo().equals("avanzado")) {
                            if (dadosLanzados) {
                                if (avanzando) {
                                    if (avanceRestante >= 0) {
                                        if (avanceRestante == 1) {
                                            avanzando(avatar, taboleiro, partida, 1, sumaDados, cartas);
                                            avanceRestante = 0;
                                            avanzando = false;
                                        } else {
                                            avanzando(avatar, taboleiro, partida, 2, sumaDados, cartas);
                                            avanceRestante -= 2;
                                        }
                                    } else {
                                        System.out.println("\nDebes usar o comando "
                                                + "retroceder.\n");
                                    }
                                } else {
                                    System.out.println("\nXa remataches de avanzar.\n");
                                }
                            } else {
                                System.out.println("\nPrimeiro debes lanzar os dados.\n");
                            }
                        } else {
                            System.out.println("\nO comando avanzar só se pode utilizar "
                                    + "se o avatar está en modo avanzado. Senón avánzase"
                                    + "automáticamente.\n");
                        }
                    } else {
                        System.out.println("\nO comando avanazar só se pode "
                                + "usar co tipo de avatar pelota.\n");
                    }
                    break;

                case "cambiar":
                    if (comando1.equals("modo")) {
                        //Esta comprobacion e so para a entrega 2
                        if ((avatar.getTipo().equals("pelota")) || (avatar.getTipo().equals("coche"))) {
                            if (avatar.getModo().equals("normal")) {
                                avatar.setModo("avanzado");
                                System.out.println("\nA partir de agora o avatar " + avatar.getId() + ", de tipo " + avatar.getTipo() + ", moverase en modo avanzado.\n");
                            } else {
                                avatar.setModo("normal");
                                System.out.println("\nA partir de agora o avatar " + avatar.getId() + ", de tipo " + avatar.getTipo() + ", moverase en modo normal.\n");
                            }
                        }
                    }
                    break;
                case "comprar":
                    if (dadosLanzados) {
                        if (taboleiro.existeCasilla(comando1) || taboleiro.existeCasilla(comando1 + " " + comando2) || (comando2.equals(""))) {
                            casillaDestino = avatar.getPosicion();

                            if (taboleiro.casillaComprable(casillaDestino)) {
                                if (casillaDestino.getNome().equals(comando1) || casillaDestino.getNome().equals(comando1 + " " + comando2)) {
                                    if (casillaDestino.getDono().getNome().equals("Banca")) {

                                        //Chamamos a funcion comprar
                                        comprarCasilla(banca, xogador, casillaDestino, taboleiro);

                                    } else {
                                        System.out.println("\nA casilla " + casillaDestino.getNome() + " non se pode comprar, o seu dono e " + casillaDestino.getDono().getNome() + ".\n");
                                    }
                                } else {
                                    System.out.println("\nSo esta permitido comprar a casilla na que caiches.\n");
                                }
                            } else {
                                System.out.println("\nAs casillas de tipo " + casillaDestino.getTipo() + " non se poden comprar.\n");
                            }
                        } else {
                            //eliminar o espazo entre comando1 e comando2 se comando2 = "";
                            System.out.println("\nA casilla " + comando1 + " " + comando2 + " non existe.\n");
                        }
                    } else {
                        System.out.println("\nPrimeiro debes lanzar os dados.\n");
                    }
                    break;

                case "describir":
                    atopado = false; //Por defecto iniciase como que non se atopou o que se pedia
                    switch (comando1) {
                        case "xogador":
                            for (int i = 0; i < nXogadores; i++) {
                                if (partida.getXogador(i).getNome().equals(comando2)) {
                                    System.out.println(partida.getXogador(i));
                                    //Se o atopamos ponhemos a variable a true.
                                    atopado = true;
                                }
                            }
                            if (!atopado) {
                                System.out.println("\nO xogador " + comando2 + " non existe.\n");
                            }
                            break;
                        case "avatar":
                            for (int i = 0; i < nXogadores; i++) {
                                if (partida.getAvatar(i).getId() == (comando2.charAt(0))) { //Convirte unha cadea nun char
                                    System.out.println(partida.getAvatar(i));
                                    atopado = true;
                                }
                            }
                            if (!atopado) {
                                System.out.println("\nO avatar " + comando2 + " non existe.\n");
                            }
                            break;
                        default: {
                            //Caso no que o nome da casilla non ten mais dunha palabra
                            if (taboleiro.existeCasilla(comando1)) {
                                taboleiro.getCasilla(comando1).imprimirCasilla();
                                System.out.println(taboleiro.getCasilla(comando1).imprimirCasilla());

                                //Caso no que o nome da casilla ten duas palabras
                            } else if (taboleiro.existeCasilla(comando1 + " " + comando2)) {
                                taboleiro.getCasilla(comando1 + " " + comando2).imprimirCasilla();
                                System.out.println(taboleiro.getCasilla(comando2 + " " + comando2).imprimirCasilla());
                            } else {
                                System.out.println("\nA casilla " + comando1 + " " + comando2 + " non existe.\n");
                            }
                        }
                    }
                    break;

                case "edificar":

                    break;

                case "estatisticas":
                case "estadisticas":
                    if (partes.length > 1) {
                        estatisticasXogador(partida, comando1);
                    } else {
                        System.out.println("kaka");
                        estadisticas(taboleiro, partida);

                    }
                    break;

                case "hipotecar":
                    hipotecar(comando1, taboleiro, xogador, hipotecar);
                    break;

                case "deshipotecar":
                    deshipotecar(comando1, taboleiro, xogador, hipotecar);
                    break;

                case "lanzar":
                    if (comando1.equals("dados")) {
                        if (!avanzando) {
                            if (!dadosLanzados) {

                                //Lanzanse os dados e calculase a suma
                                //dado1.setDado(3);
                                dado1.tirardado();
                                //dado2.setDado(1);
                                dado2.tirardado();
                                sumaDados = dado1.getDado() + dado2.getDado();
                                xogador.setVecesDados(1);

                                if (sumaDados != 0) {
                                    System.out.println("\nDado1:" + dado1.getDado() + "\n");
                                    System.out.println("\nDado2:" + dado2.getDado() + "\n");
                                    System.out.println("\nSuma:" + sumaDados + "\n");

                                    if (sonDobles(dado1.getDado(), dado2.getDado())) {
                                        nDobres++;

                                        switch (nDobres) {
                                            case 3:
                                                dadosLanzados = true;
                                                System.out.println("\nO xogador " + xogador.getNome() + " tirou tres veces seguidas dobres.\n");

                                                //Enviamos o avatar ao carecere
                                                enviarCarcere(avatar, partida, taboleiro);

                                                break;

                                            case 2:
                                                if (avatar.getModo().equals("avanzado") && avatar.getTipo().equals("pelota")) {
                                                    System.out.println("É a segunda vez que tiras dobres. "
                                                            + "Á próxima vas para o Cárcere.");
                                                    System.out.println("\nDebes volver lanzar despois"
                                                            + " de avanzar.\n");
                                                    dobres = true;
                                                } else {
                                                    System.out.println("\nTiraches dobres.\n");
                                                    System.out.println("É a segunda vez que tiras dobres. "
                                                            + "Á próxima vas para o Cárcere. Volves lanzar.\n");
                                                }
                                                break;

                                            case 1:
                                                if (estarPreso(xogador, partida)) {
                                                    xogador.setEstarPreso(0);
                                                    if (avatar.getModo().equals("avanzado") && avatar.getTipo().equals("pelota")) {
                                                        System.out.println("\nO xogador " + xogador.getNome()
                                                                + " conseguiu tirar dobres e saiu do Cárcere."
                                                                + " Debes volver lanzar despois de rematar"
                                                                + " de avanzar.\n");
                                                        dobres = true;
                                                    } else {
                                                        System.out.println("\nO xogador " + xogador.getNome()
                                                                + " conseguiu tirar dobres e saiu do Cárcere."
                                                                + " Debes volver lanzar.\n");
                                                    }
                                                } else {
                                                    if (avatar.getModo().equals("avanzado") && avatar.getTipo().equals("pelota")) {
                                                        System.out.println("\nTirasches dobres,"
                                                                + " debes volver lanzar despois"
                                                                + " de avanzar.\n");
                                                        dobres = true;
                                                    } else {
                                                        System.out.println("\nTirasches dobres, debes volver lanzar.\n");
                                                    }
                                                }
                                                break;
                                        }
                                    } else {
                                        if (estarPreso(xogador, partida)) {
                                            xogador.setEstarPreso(xogador.getEstarPreso() - 1);
                                            System.out.println("\nNon conseguiches tirar dobres. Tes "
                                                    + (xogador.getEstarPreso() - 1)
                                                    + " oportunidades para lanzar dobres. Senón teras que pagar.\n");
                                        }
                                        dadosLanzados = true;
                                    }
                                    if (!estarPreso(xogador, partida)) {

                                        //Chamamos a unha funcion para que avance
                                        if (!avatar.getArrancado()) {
                                            avanceRestante = avanzar(avatar, taboleiro, partida, sumaDados, cartas);

                                        } else {
                                            avanzar(avatar, taboleiro, partida, sumaDados, cartas);
                                        }
                                        switch (avatar.getTipo()) {
                                            case "pelota":
                                                if (avanceRestante != 0) {
                                                    avanzando = true;
                                                }
                                                break;
                                            case "coche":
                                                if (avanceRestante == 4) {
                                                    dadosLanzados = false;
                                                    avatar.setArrancado(true);
                                                    System.out.println("\nConseguiches unha "
                                                            + "puntuacion maior "
                                                            + "que 4. Podes voler"
                                                            + " lanzar.\n");
                                                    avanceRestante--;
                                                } else if ((avanceRestante > 0) && (avanceRestante < 4)) {
                                                    if (sumaDados > 4) {
                                                        avanceRestante--;
                                                        if (avanceRestante != 1) {
                                                            dadosLanzados = false;
                                                        }
                                                        System.out.println("\nLanzaches unha puntuación "
                                                                + "maior que 4. Tes " + avanceRestante
                                                                + " lanzamentos máis.\n");
                                                    } else {
                                                        avanceRestante = 0;
                                                        avatar.setArrancado(false);
                                                        System.out.println("\nLanzaches unha puntuación "
                                                                + "menor que 4. Deixas de lanzar.\n");

                                                    }
                                                } else if (avanceRestante == 0) {
                                                    avatar.setArrancado(false);
                                                } else if (avanceRestante == -2) {
                                                    avatar.setBloqueado(2);
                                                    System.out.println("\nLanzaches unha puntuación "
                                                            + "menor que 4. Quedas "
                                                            + "dous turnos inmóbil.\n");
                                                }
                                                break;
                                        }
                                    }
                                } else {
                                    System.out.println("\nErro ao lanzar os dados.\n");
                                }
                            } else {
                                System.out.println("\nXa lanzaches os dados.\n");
                            }
                        } else {
                            if (dobres) {
                                System.out.println("\nPrimeiro debes rematar de "
                                        + "avanzar. Despois podes lanzar os dados.\n");
                            } else {
                                System.out.println("\nXa lanzaches os dados.\n");
                            }
                        }
                    }
                    break;

                case "listar":
                    switch (comando1) {
                        case "xogadores":
                            for (int i = 0; i < nXogadores; i++) {
                                System.out.println(partida.getXogador(i));
                            }
                            break;
                        case "avatares":
                            for (int i = 0; i < nXogadores; i++) {
                                System.out.println(partida.getAvatar(i));
                            }
                            break;
                        case "enventa":
                            System.out.println(banca.imprimirPropiedades());
                            break;
                        case "edificios":
                            if (partes.length < 3) {
                                listarEdificios(taboleiro);
                                break;
                            }

                            switch (comando2) {
                                case "Laranxa":
                                case "Cian":
                                case "Rosa":
                                case "Gris":
                                case "Vermello":
                                case "Amarelo":
                                case "Azul":
                                case "Negro":
                                    listarEdificiosGrupo(taboleiro, comando2);
                                    break;
                                default:
                                    System.out.println("Non introduciu ningún grupo valido.");
                                    listarEdificios(taboleiro);
                                    break;
                            }
                            break;

                        default:
                            System.out.println("\nComando listar incorrecto.\n");
                            break;
                    }
                    break;

                case "rematar":
                    if (comando1.equals("partida")) {
                        sair = true;
                    } else if (comando1.equals("turno")) {
                        if (dadosLanzados) {
                            if (!avanzando) {
                                //Calculamos o turno seguinte
                                partida.calcularTurnoSeguinte();
                                //Imprimimos o xogador ao que lle toca xogar.
                                System.out.println("\nO xogador actual e " + partida.getXogador(partida.getTurno() - 1).getNome() + (".\n"));
                                //Ponse a 0 o contador de dobres e a false a variable que controla se se lanzaron os dados
                                dadosLanzados = false;
                                nDobres = 0;
                            } else {
                                System.out.println("\nPrimeiro debes rematar"
                                        + " de avanzar.\n");
                            }
                        } else {
                            System.out.println("\nPrimeiro debes lanzar os dados.\n");
                        }
                    }
                    break;

                case "retroceder":
                    if (avatar.getTipo().equals("pelota")) {
                        if (avatar.getModo().equals("avanzado")) {
                            if (dadosLanzados) {
                                if (avanzando) {
                                    if (avanceRestante <= 0) {
                                        switch (avanceRestante) {
                                            case -2:
                                                retroceder(avatar, taboleiro, partida, sumaDados, 2, cartas);
                                                avanceRestante = 0;
                                                avanzando = false;
                                                break;
                                            case -3:
                                                retroceder(avatar, taboleiro, partida, sumaDados, 2, cartas);
                                                avanceRestante = -1;
                                                break;
                                            case -1:
                                                retroceder(avatar, taboleiro, partida, sumaDados, 1, cartas);
                                                avanceRestante = 0;
                                                avanzando = false;
                                                break;
                                            default:
                                                break;
                                        }

                                    } else {
                                        System.out.println("\nDebes usar o comando avanzar.\n");
                                    }
                                } else {
                                    System.out.println("\nXa remataches de retroceder.\n");
                                }
                            } else {
                                System.out.println("\nXa lanzaches os dados.\n");
                            }
                        } else {
                            System.out.println("\nO comando retroceder só se pode "
                                    + "usar con movementos avanzados.\n");
                        }
                    } else {
                        System.out.println("\nO comando retroceder só se pode "
                                + "usar co tipo de avatar pelota.\n");
                    }
                    break;
                case "sair":
                    if (comando1.equals("carcere")) {
                        if (estarPreso(xogador, partida)) {
                            if (!dadosLanzados) {

                                pagoCarcere(xogador, taboleiro);

                            } else {
                                System.out.println("\nNon podes pagar agora porque xa lanzaches os dados.\n");
                            }
                        } else {
                            System.out.println("\nO xogador " + avatar.getId() + " non esta preso.\n");
                        }
                    } else {
                        System.out.println("\nComando sair carcere incorrecto.\n");
                    }
                    break;

                case "stop":
                    new Scanner(System.in).nextLine();
                    break;

                case "vender":
                    if (partes.length > 3) {
                        vender(partes, taboleiro, xogador);
                    }
                    break;

                case "ver":
                    if (comando1.equals("taboleiro")) {
                        taboleiro.imprimirTaboeiro(nXogadores);
                    }
                    break;

                case "xogador":
                    System.out.println(partida.getXogador(partida.getTurno() - 1));
                    break;

                case "Teletransport":
                    sumaDados = 6;
                    if (taboleiro.existeCasilla(comando1) || taboleiro.existeCasilla(comando1 + " " + comando2)) {

                        if (taboleiro.existeCasilla(comando1)) {
                            casillaDestino = taboleiro.getCasilla(comando1);
                        } else if (taboleiro.existeCasilla(comando1 + " " + comando2)) {
                            casillaDestino = taboleiro.getCasilla(comando1 + " " + comando2);
                        } else {
                            casillaDestino = taboleiro.getCasilla(2);
                            System.out.println("\nFallo en nome de casilla.\n");
                        }

                        casillaProcedencia = taboleiro.getCasilla(avatar.getPosicion().getPosicion()); //Casilla de procedencia
                        avatar.setPosicion(casillaDestino);
                        //Engadimos a casillaProcedencia na que caeu o xogador o avatar.
                        casillaDestino.engadirAvatar(avatar);

                        //Eliminamos o avatar da casillaProcedencia anterior.
                        casillaProcedencia.eliminarAvatar(avatar);
                        //Indicamos o movemento
                        System.out.println("\nO avatar " + avatar.getId() + " teletransportase dende " + casillaProcedencia.getNome().replace(" ", "") + " ata " + casillaDestino.getNome().replace(" ", "") + ".\n");

                        //Chamamos a un metodo que comproba se o avatar ten que facer algo por caer nesa casilla.
                        comprobarCasilla(avatar, casillaDestino, partida, taboleiro, sumaDados, cartas);

                        dadosLanzados = true;

                    }
                    break;

                default:
                    System.out.println("\nComando incorrecto.");
                    break;
            }
        }
    }

    //Funcion que calcula a accion dunha carta
    private void accionCarta(Avatar avatar, Casilla casillaProcedencia, Taboleiro taboleiro, Partida partida, int sumaDados, ArrayList<ArrayList<String>> cartas, String carta) {
        String accion = "";
        String partes[] = carta.split("-");
        //Realizar a accion.
        switch (partes[0]) {
            case "sorte":
                switch (Integer.parseInt(partes[1])) {
                    //Colocar as 6 cartas que queremos nas 6 primeiras posicions
                    case 1:
                        accion += "Oubinha deixache dar unha volta entre as bateas coa súa lancha motora. Vai ata a casilla Lancha Motora. Se pasas pola Saída cobra 250 GM.\n";
                        System.out.println(accion);
                        /*Como sabemos que a casilla de Destino non e nin de Sorte nin de Caixa podemos pasar como ultimo
                             * argumento un ArrayList baleiro xa que non o imos usar.
                         */
                        avanzar(avatar, casillaProcedencia, taboleiro.getCasilla("Lancha Motora"), taboleiro, partida);
                        break;
                    case 2:
                        accion += "O aumento do imposto sobre bens inmobles afecta a todas as túas propiedades. "
                                + "Paga 50 GM por  casa, 145 GM por hotel, 25 GM por piscina e 90 GM por pista de deporte.\n";
                        System.out.println(accion);
                        pagarImpostoInmoble(avatar.getXogador());
                        break;
                    case 3:
                        accion += "Fuches escollido presidente da xunta directiva. Paga a cada xogador 30 GM.\n";
                        System.out.println(accion);
                        pagarRestoXogadores(avatar.getXogador(), 30, partida);
                        break;
                    case 4:
                        accion += "Decides ir facer certos recados por ti mesmo. Avanza ata Cambados. Se pasas pola Saída cobra 250 GM.\n";
                        System.out.println(accion);
                        avanzar(avatar, casillaProcedencia, taboleiro.getCasilla("Cambados"), taboleiro, partida);
                        break;
                    case 5:
                        accion += "Vendes a túa Lancha. Cobra 60 GM.\n";
                        System.out.println(accion);
                        avatar.getXogador().modificarFortuna(60);
                        System.out.println("O xogador " + avatar.getXogador().getNome() + " ganhou 60 GM.\n");
                        break;
                    case 6:
                        accion += "Decides descansar unha fin de semana en Meanho axudando na Vendimia. Avanza ata Meanho. Se pasas pola Saída cobra 250 GM.\n";
                        System.out.println(accion);
                        avanzar(avatar, casillaProcedencia, taboleiro.getCasilla("Meanho"), taboleiro, partida);
                        break;
                    case 7:
                        accion += "A Garda Civil colleute no medio dunha operación. Vai ao Cárcere directamente sen pasar pola casilla de Saída e sen cobrar as 250 GM.\n";
                        System.out.println(accion);
                        enviarCarcere(avatar, partida, taboleiro);
                        break;
                    case 8:
                        accion += "Ganhaches o bote da lotaría! Recibe 125 GM.\n";
                        System.out.println(accion);
                        avatar.getXogador().modificarFortuna(125);
                        System.out.println("O xogador " + avatar.getXogador().getNome() + " ganhou 125 GM.\n");
                        break;
                    case 9:
                        accion += "Paga 185 GM pola matrícula do colexio privado.\n";
                        System.out.println(accion);
                        avatar.getXogador().modificarFortuna(-185);
                        System.out.println("O xogador " + avatar.getXogador().getNome() + " pagou 185 GM.\n");
                        break;

                    case 10:
                        accion += "Decides facer o Caminho de Santiago de Compostela. Avanza ata Santiago. Se pasas pola casilla de Salida, cobra 250 GM.\n";
                        System.out.println(accion);
                        avanzar(avatar, casillaProcedencia, taboleiro.getCasilla("Santiago"), taboleiro, partida);
                        break;

                    case 11:
                        accion += "Esqueciches a túa carteira en Arteixo. Retrocede ata Arteixo para recuperala.\n";
                        System.out.println(accion);
                        retroceder(avatar, taboleiro.getCasilla("Arteixo"), taboleiro, partida, sumaDados, cartas);
                        break;
                    case 12:
                        accion += "Múltante por usar o móbil mentres conduces. Paga 20 GM.\n";
                        System.out.println(accion);
                        avatar.getXogador().modificarFortuna(-20);
                        System.out.println("O xogador " + avatar.getXogador().getNome() + " pagou 20 GM.\n");
                        break;
                    case 13:
                        accion += "Beneficio pola venta das túas accións. Recibe 185 GM.\n";
                        System.out.println(accion);
                        avatar.getXogador().modificarFortuna(185);
                        System.out.println("O xogador " + avatar.getXogador().getNome() + " ganhou 185 GM.\n");
                        break;
                    case 14:
                        accion += "Avanza ata a casilla de transporte maís próxima. Se non ten dono podes comprarlla á banca. Se ten dono paga ao dono o dobre da operación indicada.\n";
                        System.out.println(accion);
                        //Pagar o dobre
                        avanzar(avatar, casillaProcedencia, calcularTransporteProximo(avatar, taboleiro), taboleiro, partida);
                        break;
                    default:
                        System.out.println("\nNúmero de carta de sorte erróneo.\n");
                        break;
                }
                break;
            case "caixa":

                switch (Integer.parseInt(partes[1])) {
                    case 1:
                        accion += "Paga 60 GM por un fin de semana nun balneario de 5 estrelas en Corrubedo.\n";
                        System.out.println(accion);
                        avatar.getXogador().modificarFortuna(-60);
                        System.out.println("O xogador " + avatar.getXogador().getNome() + " pagou 60 GM.\n");

                        break;
                    case 2:
                        accion += "Investigante por fraude de identidade. Vai ao Cárcere directamente sen pasar pola casilla de Saída e sen cobrar as 250 GM.\n";
                        System.out.println(accion);
                        enviarCarcere(avatar, partida, taboleiro);
                        break;
                    case 3:
                        accion += "A última operación saíche ben e conseguiches escapar da Garda Civil. Colócate na casilla de Saída. Cobra 250 GM.\n";
                        System.out.println(accion);
                        avanzar(avatar, casillaProcedencia, taboleiro.getCasilla("Saida"), taboleiro, partida);
                        break;
                    case 4:
                        accion += "Conseguiches sacar máis beneficios do que esperabas pola venta das túas leira en Cedofeita. Recibe 250 GM.\n";
                        System.out.println(accion);
                        avatar.getXogador().modificarFortuna(250);
                        System.out.println("O xogador " + avatar.getXogador().getNome() + " ganhou 250 GM.\n");
                        break;
                    case 5:
                        accion += "Paga 125 GM por invitar a todos os teus amigos a unha viaxe a Ribadeo.\n";
                        System.out.println(accion);
                        pagarRestoXogadores(avatar.getXogador(), 125, partida);
                        break;
                    case 6:
                        accion += "Alquilas aos teus compañeiros o castelo de Santa Cruz durante unha semana. Paga 25 GM a cada xogador.\n";
                        System.out.println(accion);
                        pagarRestoXogadores(avatar.getXogador(), 25, partida);
                        break;
                    case 7:
                        accion += "Devolución de Facenda. Cobra 60 GM.\n";
                        System.out.println(accion);
                        avatar.getXogador().modificarFortuna(60);
                        System.out.println("O xogador " + avatar.getXogador().getNome() + " ganhou 60 GM.\n");
                        break;
                    case 8:
                        accion += "Hora punta de tráfico! Retrocede tres casillas.\n";
                        System.out.println(accion);
                        retroceder(avatar, taboleiro, partida, sumaDados, 3, cartas);
                        break;

                    case 9:
                        accion += "Recibe 125 GM de beneficios por alquilar os servicios do teu Jet Privado.\n";
                        System.out.println(accion);
                        avatar.getXogador().modificarFortuna(125);
                        System.out.println("O xogador " + avatar.getXogador().getNome() + " ganhou 125 GM.\n");
                        break;
                    case 10:
                        accion += "Vai a Viveiro para disfrutar do Resurrection Fest. Se pasas pola Saída cobra as 250 GM.\n";
                        System.out.println(accion);
                        avanzar(avatar, casillaProcedencia, taboleiro.getCasilla("Viveiro"), taboleiro, partida);
                        break;

                    default:
                        System.out.println("\nNúmero de carta de caixa erróneo.\n");
                        break;
                }
                break;

            default:
                System.out.println("\nIsto non é unha carta de Caixa e Comunidade nin de Sorte.\n");
                break;
        }
    }

    //Funcion que aumenta o prezo das propiedades enventa cada vez que os xogadores completan 4 voltas
    private void actualizarPrezo(Partida partida) {
        if (partida != null) {
            int njugadores = partida.getXogadores().size();
            int proba = 0;

            //Avaliamso cantos xogadores deron catro voltas ou máis
            for (int j = 0; j < njugadores; j++) {
                if (partida.getXogador(j).getnVoltas() > 3) {
                    proba++;
                }
            }

            if (njugadores == proba) {
                Xogador banca = partida.getBanca();

                //Actualizamos o prezo de todas as casilllas a venda
                for (Casilla propiedade : banca.getPropiedades()) {
                    propiedade.setValor((float) (propiedade.getValor() * 1.05));
                }
                //Restamos catro aos contadores de todos os xogadores
                for (int j = 0; j < njugadores; j++) {
                    partida.getXogador(j).setnVoltas(partida.getXogador(j).getnVoltas() - 4);
                }
                System.out.println("\nOs prezos das propiedades a venta aumentaron un 5%.\n");

            }
        } else {
            System.out.println("\nErro en actualizar prezo.\n");
        }
    }

    //Funcion que realiza a funcion de avanzando a aprtir da suma dos dados. Chama a funcion comprobar casilla
    private void avanzando(Avatar avatar, Taboleiro taboleiro, Partida partida, int sumaDados, int avance, ArrayList<ArrayList<String>> cartas) {
        if ((avatar != null) && (taboleiro != null) && ((partida != null))) {
            if (!estarPreso(avatar.getXogador(), partida)) {
                Casilla casillaDestino;
                Casilla casillaProcedencia = avatar.getPosicion();
                if (avatar.getPosicion().getPosicion() + avance > 39) {
                    casillaDestino = taboleiro.getCasilla((avatar.getPosicion().getPosicion() + avance) - 40); //Casilla de destino
                } else {
                    casillaDestino = taboleiro.getCasilla((avatar.getPosicion().getPosicion() + avance)); //Casilla de destino
                }

                //Movemos o avatar a casilla de destino
                avatar.setPosicion(casillaDestino);
                casillaDestino.engadirAvatar(avatar);
                casillaProcedencia.eliminarAvatar(avatar);
                casillaDestino.setFrecuentada(1);

                //Indicamos o movemento
                System.out.println("\nO avatar " + avatar.getId() + " avanza " + avance + " posicions, dende " + casillaProcedencia.getNome() + " ata " + casillaDestino.getNome() + ".\n");

                //No caso de que de unha volta sumamoslle a fortuna do xogador a Constante VALOR_VOLTA
                if (casillaProcedencia.getPosicion() > casillaDestino.getPosicion()) {
                    avatar.getXogador().modificarFortuna(Constantes.VALOR_VOLTA);
                    System.out.println("\nO xogador " + avatar.getXogador().getNome() + " pasou por saida e cobra " + Constantes.VALOR_VOLTA + " €.\n");
                    //Suma un as voltas que levaba o xogador
                    avatar.getXogador().sumarnVoltas();
                    avatar.getXogador().setPasarPorSalida(Constantes.VALOR_VOLTA);
                    actualizarPrezo(partida);
                }

                //Chamamos a un metodo que comproba se o avatar ten que facer algo por caer nesa casilla.
                comprobarCasilla(avatar, casillaDestino, partida, taboleiro, sumaDados, cartas);
            } else {
                System.out.println("\nO xogador esta preso, non se pode mover do cárcere.\n");
            }

        } else {
            System.out.println("\nErro en avanzando.\n");
        }
    }

    //Funcion que avanza a partir da casilla de Destino. Chama a funcion avanzando
    private void avanzar(Avatar avatar, Casilla casillaProcedencia, Casilla casillaDestino, Taboleiro taboleiro, Partida partida) {
        if ((casillaProcedencia != null) && (casillaDestino != null)) {
            int sumaDados;

            if (casillaProcedencia.getPosicion() > casillaDestino.getPosicion()) {
                sumaDados = casillaDestino.getPosicion() - casillaProcedencia.getPosicion() + 40;
            } else {
                sumaDados = casillaDestino.getPosicion() - casillaProcedencia.getPosicion();
            }
            //Pasamos un ArrayList baleiro xa que nn o imos a usar.
            avanzando(avatar, taboleiro, partida, sumaDados, sumaDados, new ArrayList<>());

        } else {
            System.out.println("\nErro en avanzar a partir da casilla de destino.\n");
        }
    }

    //Funcion que realiza os movementos avanzados
    private int avanzar(Avatar avatar, Taboleiro taboleiro, Partida partida, int sumaDados, ArrayList<ArrayList<String>> cartas) {
        int avanceRestante = 0;
        if (avatar != null) {
            if (avatar.getModo().equals("avanzado")) {

                switch (avatar.getTipo()) {
                    case "pelota":
                        if (sumaDados > 4) { //Sabemos que a suma dos dados vai estar entre 2 e 12
                            /*Se a suma e maior que 4, o caso no que avanza ata
                             * a 5 casilla sempre se da*/
                            avanzando(avatar, taboleiro, partida, 5, sumaDados, cartas);
                            avanceRestante = sumaDados - 5;
                        } else { //Sabemos que a suma dos dados vai estar entre 2 e 12
                            //Sempre retrocede como minimo unha casilla
                            retroceder(avatar, taboleiro, partida, sumaDados, 1, cartas);
                            avanceRestante = -(sumaDados - 1);

                        }
                        break;
                    case "coche":
                        if (sumaDados > 4) {
                            avanzando(avatar, taboleiro, partida, sumaDados, sumaDados, cartas);
                            avanceRestante = 4;

                        } else if (sumaDados == 4) {
                            avanzando(avatar, taboleiro, partida, sumaDados, sumaDados, cartas);
                            avanceRestante = 0;

                        } else {
                            if (avatar.getArrancado()) {
                                avanceRestante = 0;
                            } else {
                                retroceder(avatar, taboleiro, partida, sumaDados, sumaDados, cartas);
                                avanceRestante = -2;
                            }

                        }
                        break;
                }
            } else {
                //avanzamos normal
                avanzando(avatar, taboleiro, partida, sumaDados, sumaDados, cartas);
                avanceRestante = 0;
            }
        } else {
            System.out.println("\nErro en avanzar a partir da suma dos dados.\n");
        }
        return avanceRestante;
    }

    //Metodo que calcula o factor polo que se ten que multiplicar o factor de servizo/transporte.
    private float calcularST(Casilla casilla, int sumaDados) {
        if (casilla != null) {
            int x = calcularnServTrans(casilla);
            switch (casilla.getTipo()) {
                case "Servizo":
                    //No caso de que so tenha unha casilla de servizos multiplicase por 4 o factor de servizo
                    if (x == 1) {
                        return 4 * Constantes.FACTOR_SERVIZOS * sumaDados;
                        //No caso de que tenha as duas casillas de servizos multiplicase por 10 o factor de servizo
                    } else if (x == 2) {
                        return 10 * Constantes.FACTOR_SERVIZOS * sumaDados;
                    }
                    break;
                case "Transporte":
                    switch (x) {
                        //No caso de que so tenha unha casilla de transporte multiplicase por 0.25 o factor de servizo
                        case 1:
                            return (1f / 4f) * Constantes.FACTOR_TRANSPORTE;
                        //No caso de que tenha duas casillas de transporte multiplicase por 0.50 o factor de servizo
                        case 2:
                            return (1f / 2f) * Constantes.FACTOR_TRANSPORTE;
                        //No caso de que tenha tres casillas de transporte multiplicase por 0.75 o factor de servizo
                        case 3:
                            return (3f / 4f) * Constantes.FACTOR_TRANSPORTE;
                        //No caso de que tenha todas as casillas de transporte pagase enteiro o factor de servizo
                        case 4:
                            return Constantes.FACTOR_TRANSPORTE;
                        default:
                            break;
                    }
                    break;

                default:
                    System.out.println("\nERRO: a casilla non e nin de servizos nin de transportes.\n");
                    break;
            }
            return x;
        } else {
            System.out.println("\nErro en CalcularST. Casilla = NULL.\n");
        }
        return 0;
    }

    //Metodo que calcula o numero de casillas de transporte ou servizos dun xogador.
    private int calcularnServTrans(Casilla casilla) {
        if (casilla != null) {
            int x = 0;

            switch (casilla.getTipo()) {
                case "Servizo":

                    for (Casilla cas : casilla.getDono().getPropiedades()) {
                        if (cas.getTipo().equals("Servizo")) {
                            x++;
                        }
                    }
                    break;
                case "Transporte":

                    for (Casilla cas : casilla.getDono().getPropiedades()) {
                        if (cas.getTipo().equals("Transporte")) {
                            x++;
                        }
                    }
                    break;
                default:
                    System.out.println("\nERRO: a casilla non e nin de servizos nin de transportes.\n");
                    break;
            }
            return x;
        } else {
            System.out.println("Erro en calcularnServTrans.\n");
        }
        return 0;
    }

    //Funcion que calcula a casilla de Transporte mais proxima no sentido de avance dos xogadores. En caso de que falle devolve a casilla de saida
    private Casilla calcularTransporteProximo(Avatar avatar, Taboleiro taboleiro) {
        if ((avatar != null) && (taboleiro != null)) {
            /*En funcion da posicion do avatar calculamos a casilla de transporte
             * sabendo que estas estan en casillas cuxa posicion e multiplo de 5.
             */
            if ((avatar.getPosicion().getPosicion() < 5) && (avatar.getPosicion().getPosicion() > 35)) {
                return taboleiro.getCasilla(5);
            } else if (avatar.getPosicion().getPosicion() < 15) {
                return taboleiro.getCasilla(15);
            } else if (avatar.getPosicion().getPosicion() < 25) {
                return taboleiro.getCasilla(25);
            } else {
                return taboleiro.getCasilla(35);
            }
        } else {
            System.out.println("Erro en calcularTransporteProximo.\n");
        }
        return taboleiro.getCasilla(0);
    }

    //Funcion que xestiona as accions de sorte
    private void casillaCartas(ArrayList<ArrayList<String>> cartas, Taboleiro taboleiro, Avatar avatar, Casilla casillaProcedencia, Partida partida, int sumaDados) {
        if ((cartas != null) && (taboleiro != null) && (avatar != null) && (casillaProcedencia != null) && (partida != null)) {

            //Calculamos o tipo de carta que temos que seleccionar
            String partes[] = casillaProcedencia.getNome().split("-");
            if (partes[0].equals("Sorte")) {
                Collections.shuffle(cartas.get(0));
            } else if (partes[0].equals("Caixa")) {
                Collections.shuffle(cartas.get(1));
            }
            int numCarta;
            do {
                System.out.println("Escolle unha carta, do " + 1 + " ao " + 6 + ": ");
                Scanner cart = new Scanner(System.in);
                numCarta = Integer.parseInt(cart.next()); //Convertimos un string nun int
                if ((numCarta < 1) || (numCarta > 6)) {
                    System.out.print("\nEscolla non válida.\n");
                }
            } while ((numCarta < 1) || (numCarta > 6));

            switch (partes[0]) {
                case "Sorte":
                    accionCarta(avatar, casillaProcedencia, taboleiro, partida, sumaDados, cartas, cartas.get(0).get(numCarta - 1));
                    break;
                case "Caixa":
                    accionCarta(avatar, casillaProcedencia, taboleiro, partida, sumaDados, cartas, cartas.get(1).get(numCarta - 1));
                    break;
                default:
                    System.out.println("\nIsto non é unha carta de Caixa e Comunidade nin de Sorte.\n");
                    break;
            }
        }
    }

    //Funcion que compra unha casilla se o xogador ten os cartos suficientes
    private void comprarCasilla(Xogador banca, Xogador xogador, Casilla casilla, Taboleiro taboleiro) {

        if (xogador.getFortuna() >= casilla.getValor()) {
            //Cambiamos o dono da casilla
            casilla.setDono(xogador);
            //Descontamos o valor da casilla a fortuna do xogador
            xogador.modificarFortuna(-casilla.getValor());
            //Sumamos o valor da casilla aos cartos gastados do xogador
            xogador.modificarCartosGastados(casilla.getValor());
            //Sumamos o valor da casilla aos gastos invertidos
            xogador.setCartosInvertidos(casilla.getValor());
            //Engadimos a casilla as propiedades do xogador e sacamola das da banca
            xogador.introducirPropiedade(casilla);
            banca.eliminarPropiedade(casilla);

            System.out.println("O xogador " + xogador.getNome() + " compra a casilla " + casilla.getNome() + " por " + casilla.getValor() + "€. A sua fortuna actual e " + xogador.getFortuna() + "€.\n");

            //Comprobamos se se duplica o alquiler ao comprar a casilla.
            Grupo grupo = casilla.getGrupo();

            //Caso para os grupos 1 e 8
            if ((grupo.getId() == 1) || (grupo.getId() == 8)) {
                if (xogador.nPropiedadesGrupo(grupo.getId()) == 2) {
                    switch (grupo.getId()) {
                        case 1:
                            taboleiro.getCasilla(1).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(3).setAlquiler(casilla.getAlquiler() * 2);
                            break;
                        case 8:
                            taboleiro.getCasilla(37).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(39).setAlquiler(casilla.getAlquiler() * 2);
                            break;
                    }
                    System.out.println("A partir deste momento, o xogador " + xogador.getNome() + " cobrara o dobre polo alquiler das suas casillas do grupo " + grupo.getId() + ".\n");
                }

                //Caso para o resto dos grupo de tipo Solar
            } else if (casilla.getTipo().equals("Solar")) {
                if (xogador.nPropiedadesGrupo(casilla.getGrupo().getId()) == 3) {
                    switch (grupo.getId()) {
                        case 2:
                            taboleiro.getCasilla(6).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(8).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(9).setAlquiler(casilla.getAlquiler() * 2);
                            break;
                        case 3:
                            taboleiro.getCasilla(11).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(13).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(14).setAlquiler(casilla.getAlquiler() * 2);
                            break;
                        case 4:
                            taboleiro.getCasilla(16).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(18).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(19).setAlquiler(casilla.getAlquiler() * 2);
                            break;
                        case 5:
                            taboleiro.getCasilla(21).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(23).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(24).setAlquiler(casilla.getAlquiler() * 2);
                            break;
                        case 6:
                            taboleiro.getCasilla(26).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(27).setAlquiler(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(29).setAlquiler(casilla.getAlquiler() * 2);
                            break;
                        case 7:
                            taboleiro.getCasilla(31).setImposto(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(32).setImposto(casilla.getAlquiler() * 2);
                            taboleiro.getCasilla(34).setImposto(casilla.getAlquiler() * 2);
                            break;
                    }
                    System.out.println("A partir deste momento, o xogador " + xogador.getNome() + " cobrara o dobre polo alquiler das suas casillas do grupo " + grupo.getId() + ".\n");
                }
            }

        } else {
            System.out.println("\nO xogador " + xogador.getNome() + " non ten cartos suficientes para comprar" + casilla.getNome() + ".\n");
            System.out.println("A fortuna de " + xogador.getNome() + " e " + xogador.getFortuna() + " e o valor da casilla e " + casilla.getValor() + ".\n");
        }

    }

    //Funcion que realiza as accions necesarias ao caer nunha casilla. Chamase cando un avatar cambia de posicion
    private boolean comprobarCasilla(Avatar avatar, Casilla casilla, Partida partida, Taboleiro taboleiro, int sumaDados, ArrayList<ArrayList<String>> cartas) {
        boolean estarBancarrota = false;
        if ((avatar != null) && (casilla != null) && (partida != null) && (taboleiro != null)) {
            switch (casilla.getTipo()) {
                case "Solar":
                    if (casilla.getDono().getNome().equals("Banca")) {
                        System.out.println("\nA casila " + casilla.getNome() + " non ten dono.\n");

                        //Preguntamos se quere comprar a casilla
                        Scanner compr = new Scanner(System.in);
                        char resp;
                        do {
                            System.out.println("Queres comprar a casilla "
                                    + casilla.getNome() + " por " + casilla.getValor() + " GM? (s/n)\n");

                            resp = compr.next().charAt(0);
                            if ((resp == 's') || (resp == 'S')) {
                                comprarCasilla(partida.getBanca(), avatar.getXogador(), casilla, taboleiro);
                            } else if ((resp != 'n') && (resp != 'N')) {
                                System.out.println("\nOpcion incorrecta.\n");
                            }
                        } while ((resp != 's') && (resp != 'S') && (resp != 'n') && (resp != 'N'));
                    } else if (casilla.getDono().getNome().equals(avatar.getXogador().getNome())) {
                        System.out.println("\nO xogador " + avatar.getXogador().getNome()
                                + " caeu nunha casilla da súa propiedade.\n");
                    } else {

                        //Realizase o pago do alquiler.
                        estarBancarrota = realizarPago(avatar.getXogador(), casilla, taboleiro.getCasilla("Parking"));
                        if (estarBancarrota) {
                            System.out.println("\nImplementar Bancarrota.\n");
                        } else {
                            System.out.println("\nPagaronse " + casilla.getAlquiler() + " GM de alquiler a " + casilla.getDono().getNome() + ".\n");
                        }
                    }
                    break;

                case "Servizo":
                case "Transporte":
                    if (casilla.getDono().getNome().equals("Banca")) {
                        System.out.println("\nA casila " + casilla.getNome() + " non ten dono.\n");

                        //Preguntamos se quere comprar a casilla
                        Scanner compr = new Scanner(System.in);
                        char resp;
                        do {
                            System.out.println("Queres comprar a casilla " + casilla.getNome() + " por " + casilla.getValor() + "€? (s/n)\n");

                            resp = compr.next().charAt(0);
                            if ((resp == 's') || (resp == 'S')) {
                                comprarCasilla(partida.getBanca(), avatar.getXogador(), casilla, taboleiro);
                            } else if ((resp != 'n') && (resp != 'N')) {
                                System.out.println("\nOpcion incorrecta.\n");
                            }
                        } while ((resp != 's') && (resp != 'S') && (resp != 'n') && (resp != 'N'));

                    } else if (casilla.getDono().getNome().equals(avatar.getXogador().getNome())) {
                        System.out.println("\nO xogador " + avatar.getXogador().getNome()
                                + " caeu nunha casilla da súa propiedade.\n");
                    } else {
                        //Realizar pago.
                        estarBancarrota = realizarPago(avatar.getXogador(), casilla, taboleiro.getCasilla("Parking"));
                        if (estarBancarrota) {
                            System.out.println("\nImplementar Bancarrota.\n");
                        } else {
                            System.out.println("\nPagaronse " + calcularST(casilla, sumaDados)
                                    + " GM a " + casilla.getDono().getNome() + " xa que ten "
                                    + calcularnServTrans(casilla) + " casillas de " + casilla.getTipo() + ".\n");
                        }
                    }
                    break;
                case "Caixa e Sorte":
                    if (cartas != null) {
                        casillaCartas(cartas, taboleiro, avatar, casilla, partida, sumaDados);
                    } else {
                        System.out.println("\nAs cartas non foron creadas.\n");
                    }
                    break;
                //Modificar. Deixar para Anton. Hai cousas que se fan duas veces.
                case "Impostos":
                    estarBancarrota = realizarPago(avatar.getXogador(), casilla, taboleiro.getCasilla("Parking"));
                    break;
                case "Especial":
                    if (casilla.getNome().equals("Parking")) {
                        //O xogador que cae no parking recibe o bote se non e 0
                        if (casilla.getValor() != 0) {
                            avatar.getXogador().modificarFortuna(casilla.getValor());
                            //Actualizar ao introducir bancarrota
                            System.out.println("\nO xogador " + avatar.getXogador().getNome()
                                    + " recibiu o bote do parking: " + casilla.getValor() + " GM.\n");
                            //O bote ponse a 0
                            casilla.setValor(0);
                        } else {
                            System.out.println("\nO bote do Parking é de " + casilla.getValor() + " GM.\n");
                        }

                        //Hai que gardar o recadado no valor de parking
                    } else if (casilla.getNome().equals("IrCarcere")) {

                        //Enviamos o avatar ao carcere
                        //Recalculamos a posicion do avatar.
                        //Engadimos a casillaProcedencia na que caeu o xogador o avatar.
                        Casilla casillaProcedencia;
                        casillaProcedencia = taboleiro.getCasilla(avatar.getPosicion().getPosicion()); //Recalculamos a casillaProcedencia na que estaba o avatar
                        casillaProcedencia.eliminarAvatar(avatar); //Eliminamos o avatar da casillaProcedencia anterior.

                        Casilla casillaDestino;
                        casillaDestino = taboleiro.getCasilla("Carcere"); //Casilla de destino
                        avatar.setPosicion(casillaDestino);

                        //Engadimos a casillaProcedencia na que caeu o xogador o avatar.
                        casillaDestino.engadirAvatar(avatar);

                        //Activamos o atributo
                        avatar.getXogador().setEstarPreso(4);
                        System.out.println("\nO avatar " + avatar.getId() + " foi enviado a casilla do Carcere.\n");
                    }
                    break;
            }
        }
        return estarBancarrota;
    }

    //Funcion que comproba os ID dos avatares son unicos
    private void comprobarID(int i, char[] avatarescompro, Partida partida) {
        for (int j = 0; j < i; j++)//Imos accer a un arrai onde almacenamos os I para esta comprobación pero so os que empregamos ata o de agora
        {
            if (avatarescompro[j] == partida.getXogador(i).getAvatar().getId()) {
                partida.getXogador(i).getAvatar().xerarId();
                comprobarID(i, avatarescompro, partida);
            }
        }
    }

    //Funcion que envia un avatar ao carcere usando as funcions avanzando ou retroceder
    private void enviarCarcere(Avatar avatar, Partida partida, Taboleiro taboleiro) {
        if (taboleiro != null) {
            /*Non fai falta comprobar que partida e avatar son distintos  de null
              porque xa o comprobamos dentro das funcions nas que os usamos. A
              suma dos dados aqui non o imos usar, asi que mandamos calquera numero
             */
            retroceder(avatar, taboleiro.getCasilla("IrCarcere"), taboleiro, partida, 3, new ArrayList<>());
            avatar.getXogador().setVecesNoCarcere(1);
        }

    }

    //Funcion que as crea
    private void crearCartas(ArrayList<ArrayList<String>> cartas) {
        if (cartas != null) {
            ArrayList<String> sorte = new ArrayList<>();
            sorte.add("sorte-1");
            sorte.add("sorte-2");
            sorte.add("sorte-3");
            sorte.add("sorte-4");
            sorte.add("sorte-5");
            sorte.add("sorte-6");

            cartas.add(sorte);

            ArrayList<String> caixa = new ArrayList<>();
            caixa.add("caixa-1");
            caixa.add("caixa-2");
            caixa.add("caixa-3");
            caixa.add("caixa-4");
            caixa.add("caixa-5");
            caixa.add("caixa-6");

            cartas.add(caixa);
        }
    }

    private void deshipotecar(String casilla, Taboleiro taboleiro, Xogador xogador, Xogador hipotecas) {
        int a = 0;
        for (int i = 0; i < hipotecas.getPropiedades().size(); i++) {
            if (hipotecas.getPropiedades().get(i).getNome().equals(casilla)) {
                a = 1;
                taboleiro.getCasilla(casilla).setDono(xogador);
                hipotecas.eliminarPropiedade(taboleiro.getCasilla(casilla));
                xogador.setFortuna((float) (xogador.getFortuna() - taboleiro.getCasilla(casilla).getValor() * 0.5));
                System.out.println("Deshipotecou Lugo.");
                System.out.println(xogador);

                break;
            }
        }
        if (a == 0) {
            System.out.println("Esta casilla non está hipotecada.");
        }
    }

    //Funcion que comproba se un xogador esta no carcere
    private boolean estarPreso(Xogador xogador, Partida partida) {
        if ((partida != null) && (xogador != null)) {
            if (xogador.getAvatar().getPosicion().getNome().equals("Carcere")) {
                if (xogador.getEstarPreso() > 0) {
                    return true;
                }
            }
        } else {
            System.out.println("\nErro en estarPreso.\n");
        }

        return false;
    }

    private void estatisticasXogador(Partida partida, String nome) {
        for (int i = 0; i < partida.getXogadores().size(); i++) {
            if (partida.getXogadores().get(i).getNome().equals(nome)) {
                System.out.println(partida.getXogadores().get(i).estatisticasImprimir());
            }
        }
    }

    private void hipotecar(String casilla, Taboleiro taboleiro, Xogador xogador, Xogador hipotecas) {
        String[] partes = new String[4];

        if (taboleiro.getCasilla(casilla).getDono().equals(xogador)) {
            for (int i = taboleiro.getCasilla(casilla).getEdificios().size() - 1; i > -1; i--) {
                switch (taboleiro.getCasilla(casilla).getEdificios().get(i).getTipo()) {
                    case "casa":
                        partes[1] = "casa";
                        partes[2] = casilla;
                        partes[3] = "1";
                        vender(partes, taboleiro, xogador);
                        break;
                    case "hotel":
                        partes[1] = "hotel";
                        partes[2] = casilla;
                        partes[3] = "1";
                        vender(partes, taboleiro, xogador);
                        break;
                    case "piscina":
                        partes[1] = "piscina";
                        partes[2] = casilla;
                        partes[3] = "1";
                        vender(partes, taboleiro, xogador);
                        break;
                    case "pista de deporte":
                        partes[1] = "pista";
                        partes[2] = casilla;
                        partes[3] = "1";
                        vender(partes, taboleiro, xogador);
                        break;
                }
            }
            taboleiro.getCasilla(casilla).setDono(hipotecas);
            hipotecas.introducirPropiedade(taboleiro.getCasilla(casilla));
            xogador.setFortuna((float) (xogador.getFortuna() + taboleiro.getCasilla(casilla).getValor() * 0.5));

            System.out.println("Hipotecou Lugo.");
            System.out.println(xogador);
        } else {
            System.out.println("Non pode hipotecar unha casilla que non é súa.");
        }
    }

    private void listarEdificios(Taboleiro taboleiro) {
        System.out.println("Os edificios existentes son:");
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {

            for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {

                if (taboleiro.getCasillas().get(i).get(j).getTipo().equals("Solar")) {

                    for (int k = 0; k < taboleiro.getCasillas().get(i).get(j).getEdificios().size(); k++) {

                        System.out.println("________________");
                        System.out.println("ID: " + taboleiro.getCasillas().get(i).get(j).getEdificios().get(k).getId());
                        System.out.println("Propietario: " + taboleiro.getCasillas().get(i).get(j).getDono().getNome());
                        System.out.println("Casilla: " + taboleiro.getCasillas().get(i).get(j).getNome());
                        System.out.println("Grupo: " + taboleiro.getCasillas().get(i).get(j).getGrupo().getId());
                        switch (taboleiro.getCasillas().get(i).get(j).getEdificios().get(k).getTipo()) {
                            case "casa":
                                System.out.println("Coste: " + taboleiro.getCasillas().get(i).get(j).getValorinicial() * 0.6);
                                break;
                            case "hotel":
                                System.out.println("Coste: " + taboleiro.getCasillas().get(i).get(j).getValorinicial() * 0.6);
                                break;
                            case "piscina":
                                System.out.println("Coste: " + taboleiro.getCasillas().get(i).get(j).getValorinicial() * 0.4);
                                break;
                            case "pista de deporte":
                                System.out.println("Coste: " + taboleiro.getCasillas().get(i).get(j).getValorinicial() * 1.25);
                                break;
                        }
                        System.out.println("________________");
                    }
                }
            }
        }
    }

    private void listarEdificiosGrupo(Taboleiro taboleiro, String corgrupo) {
        System.out.println("Os edificios existentes son:");
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {

            for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {

                if (taboleiro.getCasillas().get(i).get(j).getTipo().equals("Solar")) {
                    if (taboleiro.getCasillas().get(i).get(j).getGrupo().getCor().equals(corgrupo)) {

                        for (int k = 0; k < taboleiro.getCasillas().get(i).get(j).getEdificios().size(); k++) {

                            System.out.println("________________");
                            System.out.println("ID: " + taboleiro.getCasillas().get(i).get(j).getEdificios().get(k).getId());
                            System.out.println("Propietario: " + taboleiro.getCasillas().get(i).get(j).getDono().getNome());
                            System.out.println("Casilla: " + taboleiro.getCasillas().get(i).get(j).getNome());
                            System.out.println("Grupo: " + taboleiro.getCasillas().get(i).get(j).getGrupo().getId());
                            switch (taboleiro.getCasillas().get(i).get(j).getEdificios().get(k).getTipo()) {
                                case "casa":
                                    System.out.println("Coste: " + taboleiro.getCasillas().get(i).get(j).getValorinicial() * 0.6);
                                    break;
                                case "hotel":
                                    System.out.println("Coste: " + taboleiro.getCasillas().get(i).get(j).getValorinicial() * 0.6);
                                    break;
                                case "piscina":
                                    System.out.println("Coste: " + taboleiro.getCasillas().get(i).get(j).getValorinicial() * 0.4);
                                    break;
                                case "pista de deporte":
                                    System.out.println("Coste: " + taboleiro.getCasillas().get(i).get(j).getValorinicial() * 1.25);
                                    break;
                            }
                            System.out.println("________________");
                        }
                    }
                }
            }
        }
    }

    /*Funcion que comproba se o nome dun xogador xa existe na partida.
     * O xogador non pode estar xa incluido na partida.
     */
    private boolean nomeIgual(String nomeXogador, Partida partida) {
        if (!partida.getXogadores().isEmpty()) {
            for (Xogador xogador : partida.getXogadores()) {
                if (xogador.getNome().equals(nomeXogador)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Metodo que paga o alquiler dun solar, transporte ou servizo do xogador do parametro 1 ao dono da casilla
    private void pagarAlquiler(Xogador xogador, Casilla casilla) {

        xogador.modificarFortuna(-casilla.getAlquiler());
        xogador.setPagoDeAlquileres(casilla.getAlquiler());
        casilla.getDono().modificarFortuna(casilla.getAlquiler());
        casilla.getDono().setCobroDeAlquileres(casilla.getAlquiler());
        casilla.setDineroMovido(casilla.getAlquiler());
        casilla.getGrupo().setDineroMovido(casilla.getAlquiler());

    }

    //Metodo que realiza o pago dun xogador a banca
    private void pagarBanca(Xogador xogador, float pago) {

        xogador.modificarFortuna(-pago);

    }

    //Metodo que paga o imposto de imnoble dun xogador
    private void pagarImpostoInmoble(Xogador xogador) {
        if (xogador != null) {
            int casas = 0, hoteis = 0, pistas = 0, piscinas = 0;
            float total;
            for (Casilla casilla : xogador.getPropiedades()) {
                for (Edificio edificio : casilla.getEdificios()) {
                    switch (edificio.getTipo()) {
                        case "casa":
                            casas++;
                            break;
                        case "hotel":
                            hoteis++;
                            break;
                        case "piscina":
                            piscinas++;
                            break;
                        case "pista":
                            pistas++;
                            break;
                    }
                }
            }
            total = 50 * casas + 145 * hoteis + 25 * piscinas + 90 * pistas;
            xogador.modificarFortuna(-total);
            xogador.setPagoTasasEImportos(total);
            System.out.println("\nO xogador " + xogador.getNome() + " pagou " + total
                    + " GM, xa que ten " + casas + " casas, " + hoteis
                    + " hoteis, " + piscinas + " piscinas e " + pistas + " pistas de deporte.\n");
        } else {
            System.out.println("\nErro en pagarImpostoInmoble.\n");
        }
    }

    //Metodo que realiza o pago do xogador que se recibe como argumento ao resto e paga a cantidade que recibe
    private void pagarRestoXogadores(Xogador xogador, int pago, Partida partida) {
        if ((xogador != null) && (partida != null)) {
            for (Xogador cobrador : partida.getXogadores()) {
                xogador.modificarFortuna(-pago);
                xogador.setPagoTasasEImportos(pago);
                cobrador.modificarFortuna(pago);
            }
            System.out.println("\nO xogador " + xogador.getNome() + " pagoulle " + pago + "GM ao resto de xogadores.\n");
        } else {
            System.out.println("\nErro en pagarRestoXogadores.\n");
        }
    }

    //Funcion que realiza o pago do carcere
    private void pagoCarcere(Xogador xogador, Taboleiro taboleiro) {
        if (xogador.getFortuna() >= Constantes.SAIR_CARCERE) {
            xogador.modificarFortuna(-Constantes.SAIR_CARCERE);
            System.out.println("\nO xogador " + xogador.getNome() + " pagou " + Constantes.SAIR_CARCERE + " € para sair do carcere.\n");

            //Mandamos a cantidade pagada ao Parking
            taboleiro.getCasilla("Parking").setValor(Constantes.SAIR_CARCERE + taboleiro.getCasilla("Parking").getValor());
            System.out.println("\nOs cartos foron ao Parking.\n");
        } else {
            System.out.println("\nO xogador " + xogador.getNome() + " non ten cartos suficientes para pagar.\n");
        }
        //O xogador deixa de estar preso
        xogador.setEstarPreso(0);
        System.out.println("\nO xogador " + xogador.getNome() + " saiu do carcere.\n");
    }

    //Metodo que decide a que funcion chama para pagar o alquiler. Devolve false se o xogador caeu en bancarrota
    private boolean realizarPago(Xogador xogador, Casilla casilla, Casilla parking) {
        switch (casilla.getTipo()) {
            case "Solar":
            case "Servizo":
            case "Transporte":
                if (xogador.getFortuna() < casilla.getValor()) {
                    return true;
                }
                pagarAlquiler(xogador, casilla);

                break;

            case "Impostos":
                if (xogador.getFortuna() < casilla.getValor()) {
                    return true;
                }
                switch (casilla.getNome()) {
                    case "IRPF":
                        pagarBanca(xogador, Constantes.IMPOSTO1);
                        System.out.println("\nTes que facer a declaracion da renta. O xogador paga " + Constantes.IMPOSTO1 + " €.\n");
                        parking.setValor(Constantes.IMPOSTO1 + parking.getValor());
                        xogador.setPagoTasasEImportos(Constantes.IMPOSTO1);
                        casilla.setDineroMovido(Constantes.IMPOSTO1);
                        casilla.getGrupo().setDineroMovido(Constantes.IMPOSTO1);

                        break;
                    case "SubidaPension":
                        pagarBanca(xogador, Constantes.IMPOSTO2);
                        System.out.println("\nFeijoo subiu as pensions. O xogador paga " + Constantes.IMPOSTO2 + " €.\n");
                        parking.setValor(Constantes.IMPOSTO2 + parking.getValor());
                        xogador.setPagoTasasEImportos(Constantes.IMPOSTO2);
                        casilla.setDineroMovido(Constantes.IMPOSTO2);
                        casilla.getGrupo().setDineroMovido(Constantes.IMPOSTO2);
                        break;
                    default:
                        System.out.println("\nNon existe esta casilla de Impostos.\n");
                        break;
                }
                break;

            default:
                System.out.println("\nNon se realizou ningun pago.\n");
                break;
        }
        return false;
    }

    //Funcion que retrocede a partir do nome da casilla
    private void retroceder(Avatar avatar, Casilla casilla, Taboleiro taboleiro, Partida partida, int sumaDados, ArrayList<ArrayList<String>> cartas) {
        if ((avatar != null) && (casilla != null) && (partida != null) && (taboleiro != null)) {

            //Eliminamos ao avatar da Casilla de Procedencia
            Casilla casillaProcedencia = avatar.getPosicion();
            int retroceso;
            //Calculamos o numero de casillas que retrocede
            if ((casillaProcedencia.getPosicion() - casilla.getPosicion()) >= 0) {
                retroceso = casilla.getPosicion() - casillaProcedencia.getPosicion();
            } else {
                retroceso = Math.abs(casillaProcedencia.getPosicion() - casilla.getPosicion() + 40);
            }
            casillaProcedencia.eliminarAvatar(avatar);

            //Engadimos ao avatar na Casilla de Destino
            casilla.engadirAvatar(avatar);
            avatar.setPosicion(casilla);

            if (!casilla.getNome().equals("IrCarcere")) {
                System.out.println("\nO avatar " + avatar.getId() + " retrocede "
                        + retroceso + " posicions, dende " + casillaProcedencia.getNome()
                        + " ata " + casilla.getNome() + ".\n");
            }

            comprobarCasilla(avatar, casilla, partida, taboleiro, sumaDados, cartas);
        }
    }

    //Funcion que retrocede a partir do numero de retroceso
    private void retroceder(Avatar avatar, Taboleiro taboleiro, Partida partida, int sumaDados, int retroceso, ArrayList<ArrayList<String>> cartas) {
        if ((avatar != null) && (partida != null) && (taboleiro != null)) {
            if (avatar.getPosicion().getPosicion() >= retroceso) {
                retroceder(avatar, taboleiro.getCasilla(avatar.getPosicion().getPosicion() - retroceso), taboleiro, partida, sumaDados, cartas);
            } else {
                retroceder(avatar, taboleiro.getCasilla(avatar.getPosicion().getPosicion() - retroceso + 40), taboleiro, partida, sumaDados, cartas);
            }
        }
    }

    //Funcion que comproba se dous ints son iguais.
    private boolean sonDobles(int a, int b) {
        return a == b;
    }

    //Funcion que devolve o ordinal dun numero
    private String ordinal(int numero) {
        String ordinal = "";
        if (numero < 0) {
            System.out.println("\nERRisimo.\n");
        } else {
            switch (numero) {
                case 0:
                    ordinal = "CERISIMO";
                    break;
                case 1:
                    ordinal = "primeiro";
                    break;
                case 2:
                    ordinal = "segundo";
                    break;
                case 3:
                    ordinal = "terceiro";
                    break;
                case 4:
                    ordinal = "cuarto";
                    break;
                case 5:
                    ordinal = "quinto";
                    break;
                default:
                    ordinal = "Non hai mais";
                    break;

            }
        }
        return ordinal;
    }

    private void vender(String[] partes, Taboleiro taboleiro, Xogador xogador) {
        if ((partes[1].equals("casa")) || (partes[1].equals("casas")) || (partes[1].equals("hotel")) || (partes[1].equals("hoteles")) || (partes[1].equals("hoteis")) || (partes[1].equals("piscina")) || (partes[1].equals("piscinas")) || (partes[1].equals("pista")) || (partes[1].equals("pistas"))) {
            if (taboleiro.getCasilla(partes[2]).getDono() == xogador) {
                int num = Integer.parseInt(partes[3]);
                switch (partes[1]) {
                    case "casa":
                    case "casas":
                        if (taboleiro.getCasilla(partes[2]).getCasas() >= num) {
                            //Destruimos as casas
                            taboleiro.getCasilla(partes[2]).destruirCasa(num);
                            //Engadimos o diñeiro
                            float money = (float) (taboleiro.getCasilla(partes[2]).getValorinicial() * 0.3);
                            xogador.setFortuna((float) (xogador.getFortuna() + money));

                            //Ajustamos los contadores
                            taboleiro.getCasilla(partes[2]).setCasas(taboleiro.getCasilla(partes[2]).getCasas() - num);
                        } else {
                            System.out.println("Non hai suficientes edificacións deste tipo.");
                        }
                        break;
                    case "hotel":
                    case "hoteles":
                    case "hoteis":
                        if (taboleiro.getCasilla(partes[2]).getHoteis() >= num) {
                            //Destruimos as casas
                            taboleiro.getCasilla(partes[2]).destruirHotel(num);
                            //Engadimos o diñeiro
                            float money = (float) (taboleiro.getCasilla(partes[2]).getValorinicial() * 0.3);
                            xogador.setFortuna((float) (xogador.getFortuna() + money));
                            //Ajustamos los contadores
                            taboleiro.getCasilla(partes[2]).setHoteis(taboleiro.getCasilla(partes[2]).getHoteis() - num);

                        } else {
                            System.out.println("Non hai suficientes edificacións deste tipo.");
                        }
                        break;
                    case "piscina":
                    case "piscinas":
                        if (taboleiro.getCasilla(partes[2]).getPiscinas() >= num) {
                            //Destruimos as casas
                            taboleiro.getCasilla(partes[2]).destruirPiscina(num);
                            //Engadimos o diñeiro
                            float money = (float) (taboleiro.getCasilla(partes[2]).getValorinicial() * 0.2);
                            xogador.setFortuna((float) (xogador.getFortuna() + money));

                            //Ajustamos los contadores
                            taboleiro.getCasilla(partes[2]).setPiscinas(taboleiro.getCasilla(partes[2]).getPiscinas() - num);
                        } else {
                            System.out.println("Non hai suficientes edificacións deste tipo.");
                        }
                        break;
                    case "pista":
                    case "pistas":
                        if (taboleiro.getCasilla(partes[2]).getPistas() >= num) {
                            //Destruimos as casas
                            taboleiro.getCasilla(partes[2]).destruirPistaDeporte(num);
                            //Engadimos o diñeiro
                            float money = (float) (taboleiro.getCasilla(partes[2]).getValorinicial() * 0.62);
                            xogador.setFortuna((float) (xogador.getFortuna() + money));
                            //Ajustamos los contadores
                            taboleiro.getCasilla(partes[2]).setPistas(taboleiro.getCasilla(partes[2]).getPistas() - num);
                        } else {
                            System.out.println("Non hai suficientes edificacións deste tipo.");
                        }
                        break;
                }
            } else {
                System.out.println("A casilla a que intenta acceder non é da súa propiedade.");
            }
        } else {
            System.out.println("Non introduxo un tipo de propiedad valido");
        }
    }

    private void estadisticas(Taboleiro taboleiro, Partida partida) {
        Casilla casilla;
        casilla = taboleiro.getCasillas().get(1).get(1);
        //Casilla mas rentable
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {
                if (taboleiro.getCasillas().get(i).get(j).getDineroMovido() > casilla.getDineroMovido()) {
                    casilla = taboleiro.getCasillas().get(i).get(j);
                }
            }
        }
        //Grupo máis rentable
        Grupo grupo;
        grupo = taboleiro.getCasillas().get(1).get(1).getGrupo();
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {
                if (taboleiro.getCasillas().get(i).get(j).getGrupo().getDineroMovido() > casilla.getGrupo().getDineroMovido()) {
                    grupo = taboleiro.getCasillas().get(i).get(j).getGrupo();
                }
            }
        }
        Casilla frecuentada;
        frecuentada = taboleiro.getCasillas().get(1).get(1);
        //Casilla mas rentable
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {
                if (taboleiro.getCasillas().get(i).get(j).getFrecuentada() > frecuentada.getFrecuentada()) {
                    frecuentada = taboleiro.getCasillas().get(i).get(j);
                }
            }
        }
        Xogador masvueltas;
        masvueltas = partida.getXogadores().get(0);
        //Casilla mas rentable
        for (int i = 0; i < partida.getXogadores().size(); i++) {
            if (partida.getXogadores().get(i).getnVoltas() > masvueltas.getnVoltas()) {
                masvueltas = partida.getXogadores().get(i);
            }
        }
        Xogador masvecesdados;
        masvecesdados = partida.getXogadores().get(0);
        //Casilla mas rentable
        for (int i = 0; i < partida.getXogadores().size(); i++) {
            if (partida.getXogadores().get(i).getVecesDados() > masvecesdados.getVecesDados()) {
                masvecesdados = partida.getXogadores().get(i);
            }
        }
        Xogador encabeza;
        encabeza = partida.getXogadores().get(0);
        //Casilla mas rentable
        for (int i = 0; i < partida.getXogadores().size(); i++) {
            if (partida.getXogadores().get(i).getAvatar().getPosicion().getPosicion() > encabeza.getAvatar().getPosicion().getPosicion()) {
                encabeza = partida.getXogadores().get(i);
            }
        }

        String texto;
        texto = "!!!!!!!!!!!!!!!!!!!!!!!\n"
                + "Casilla mais rentable: " + casilla.getNome() + "\n"
                + "Grupo mais rentable: " + grupo.getCor() + "\n"
                + "Casilla mais frecuentada: " + frecuentada.getNome() + "\n"
                + "Xugador mais vueltas: " + masvueltas.getNome() + "\n"
                + "Xogador mas rentable: " + masvecesdados.getNome() + "\n"
                + "Xogador en cabeza: " + encabeza.getNome() + "\n"
                + "¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡\n";

        System.out.println(texto);

    }

//Fin clase Menu
}
