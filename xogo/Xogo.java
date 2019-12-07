package xogo;

import estrutura.*;
import xogadores.*;
import consola.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Xogo implements Comando {

    public static final ConsolaNormal consola = new ConsolaNormal();
    private ArrayList<Xogador> xogadores;
    private ArrayList<Avatar> avatares;
    private Taboleiro taboleiro;
    private HashMap<Integer, HashMap<String, Dado>> tiradas;
    private int turno = 0;
    private Xogador banca;

    public Xogo() {

        xogadores = new ArrayList<>();
        avatares = new ArrayList<>();
        tiradas = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            HashMap<String, Dado> dados = new HashMap<>();
            dados.put("d1", new Dado());
            dados.put("d2", new Dado());
            tiradas.put(i, dados);
        }
        banca = new Xogador();
        taboleiro = new Taboleiro(banca);

        int nXogadores = 0;
        Boolean sair = false;
        Xogador hipotecar = new Xogador("Hipotecar"); //Xogador ao que se lle hipoteca
        ArrayList<Boolean> avanzado = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            avanzado.add(false);
        }

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

            String tipoAvatar;
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
                    break;
                case "Coche":
                case "coche":
                    avatares.add(new Coche(IdAvatar, xogadores.get(i), taboleiro));
                    break;
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

            xogadores.get(i).setAvatar(avatares.get(i));

            consola.imprimir("O avatar do xogador e: "
                    + avatares.get(i).getId() + "\n");
        }

        taboleiro.imprimirTaboleiro();

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
            String comando0, comando1, comando2, comando3;

            imprimirDados();

            if (poderLanzar()) {
                switch (xogador.getEstadoPreso()) {
                    case 4:
                    case 3:
                        consola.imprimir("O xogador " + xogador.getNome()
                                + " está preso. Quédanlle "
                                + (xogador.getEstadoPreso() - 1)
                                + " turnos no Cárcere.\nTes dúas opcións: \n"
                                + "\t- Pagar 60 GM e sair do carcere (sair carcere)"
                                + "\n\t- Tentar sacar dobres (lanzar dados)\n");
                        break;
                    case 2:
                        consola.imprimir("O xogador " + xogador.getNome()
                                + " está preso. Este é o último turno"
                                + " no Cárcere.\nTes dúas opcións: \n"
                                + "\t- Pagar 60 GM e sair do carcere (sair carcere)"
                                + "\n\t- Tentar sacar dobres (lanzar dados)\n");
                        break;
                    case 1:
                        if (xogador.getFortuna() > Constantes.SAIR_CARCERE) {
                            xogador.modificarFortuna(-Constantes.SAIR_CARCERE);
                            xogador.setEstadoPreso(0);
                            consola.imprimir("O xogador " + xogador.getNome()
                                    + " pagou " + Constantes.SAIR_CARCERE + " "
                                    + "GM para saír do Cárcere. Podes lanzar os"
                                    + " dados.\n");
                        } else {
                            consola.imprimir("A hipotecar");
                        }
                        break;
                }
            }

            //Turno do xogador
            //Facemos as declaracións e imos lendo do caso que sexa
            String orde = consola.lerLinha("Turno de " + xogador.getNome() + "\n$> ");

            //Xestion de comando
            String[] partes = orde.split(" ");
            comando0 = partes[0];

            //INICIALIZACION COMANDOS
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
            if (partes.length > 3) {
                comando3 = partes[3];
            } else {
                comando3 = "";
            }

            switch (comando0) {
                case "cambiar":
                    if (comando1.equals("modo")) {
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
                    if (partes.length > 3) {
                        describir(comando1, comando2 + " " + comando3);
                    }
                    describir(comando1, comando2);
                    break;

                case "deshipotecar":
                    deshipotecar(comando1, xogador, hipotecar);
                    break;

                case "edificar":

                    switch (comando1) {
                        case "casa":
                        case "hotel":
                        case "piscinas":
                        case "pistas":
                            edificar(avatar, comando1, 1);
                            break;
                        case "casas":
                            edificar(avatar, comando1,
                                    Integer.parseInt(comando2));
                            break;
                    }

                    break;

                case "hipotecar":
                    hipotecar(comando1, xogador, hipotecar);
                    break;
                case "lanzar":
                    if (comando1.equals("dados")) {
                        if (poderLanzar()) {
                            lanzarDados();

                            if (xogador.getEstadoPreso() == 0) {

                                if ((sonDobres(getDadosLanzados())) && (getTirada() == 3)) {

                                    consola.imprimir("Tirar dobres por terceira vez consecutiva.\n");
                                    moverAoCarcere(avatar);
                                    consola.imprimir("O avatar " + avatar.getId() + " foi enviado "
                                            + "ao Cárcere.\n");

                                } else if (!sonDobres(getDadosLanzados())) {

                                    avanzar(avatar);
                                    comprobarCasilla(avatar.getPosicion(), xogador);
                                }
                                if (sonDobres(getDadosLanzados())) {

                                    consola.imprimir("Conseguiches tirar dobres. "
                                            + "Debes volver lanzar.\n");

                                    avanzar(avatar);
                                    comprobarCasilla(avatar.getPosicion(), xogador);
                                }

                            } else {

                                if (!sonDobres(getDadosLanzados())) {
                                    xogador.restarEstadoPreso();
                                    consola.imprimir("Non coneguiches lanzar dobres. Quédanche "
                                            + (xogador.getEstadoPreso() - 1)
                                            + " turnos no Cárcere.\n");

                                } else {
                                    consola.imprimir("Tiraches dobres, polo que saes do Cárcere.");
                                    xogador.setEstadoPreso(0);

                                    avanzar(avatar);
                                    comprobarCasilla(avatar.getPosicion(), xogador);
                                }
                            }
                        } else {
                            consola.imprimir("Non podes lanzar.\n");
                            //Excepcion
                        }
                    }
                case "listar":
                    listar(comando1, comando2);
                    break;
                case "rematar":
                    switch (comando1) {
                        case "partida":
                            sair = rematarPartida();
                            break;
                        case "turno":
                            rematarTurno();
                            break;
                    }
                    break;

                case "sair":
                    if (comando1.equals("carcere")) {
                        sairCarcere();
                    }
                    break;

                case "ver":
                    if (comando1.equals("taboleiro")) {
                        verTaboleiro();
                    }
                    break;

                case "xogador":
                    xogador();
                    break;
                case "tele":
                case "teletransport":
                case "Teletransport":
                    Casilla destino = null;
                    if (existeCasilla(comando1)) {
                        destino = taboleiro.getCasilla(comando1);
                    } else if (existeCasilla(comando1 + " " + comando2)) {
                        destino = taboleiro.getCasilla(comando1 + " " + comando2);
                    }
                    if (destino != null) {
                        Casilla procedencia = avatar.getPosicion();
                        procedencia.eliminarAvatar(avatar);

                        avatar.setPosicion(destino);
                        consola.imprimir("O avatar " + avatar.getId()
                                + " teletranspórtase dende a casilla "
                                + procedencia.getNome() + " ata a casilla "
                                + destino.getNome() + ".\n");

                        resetDados();
                        tiradas.get(1).get("d1").setValor(3);
                        tiradas.get(1).get("d2").setValor(2);

                        avatar.getPosicion().sumarFrecuenciaVisita(turno);

                        comprobarCasilla(avatar.getPosicion(), xogador);
                    }
                    break;
                default: //Excepcion
                    consola.imprimir("Comando incorrecto.\n");
                    break;

            }
        }
    }

    private void imprimirDados() {
        consola.imprimir("Dados: \n");
        for (int i = 1; i <= 3; i++) {
            consola.imprimir(tiradas.get(i).get("d1") + " ");
            consola.imprimir(tiradas.get(i).get("d2") + "  ");
        }
        consola.imprimir("\n");
    }

    //Getters e Setters
    public ArrayList<Xogador> getXogadores() {
        return xogadores;
    }

    public void setXogadores(ArrayList<Xogador> xogadores) {
        this.xogadores = xogadores;
    }

    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }

    public void setAvatares(ArrayList<Avatar> avatares) {
        this.avatares = avatares;
    }

    public Taboleiro getTaboleiro() {
        return taboleiro;
    }

    public void setTaboleiro(Taboleiro taboleiro) {
        this.taboleiro = taboleiro;
    }

    public HashMap<String, Dado> getDadosLanzables() {
        if (!dadosLanzados(tiradas.get(1))) {
            return tiradas.get(1);
        } else if ((!dadosLanzados(tiradas.get(2))) && (sonDobres(tiradas.get(1)))) {
            return tiradas.get(2);
        } else if ((!dadosLanzados(tiradas.get(3))) && (sonDobres(tiradas.get(2)))) {
            return tiradas.get(3);
        } else {
            //Excepcion
        }
        System.out.println("Non se deberia chegar aqui. Faltan as excepcions");
        return tiradas.get(3);
    }

    private HashMap<String, Dado> getDadosLanzados() {
        if (dadosLanzados(tiradas.get(3))) {
            return tiradas.get(3);
        } else if (dadosLanzados(tiradas.get(2))) {
            return tiradas.get(2);
        } else if (dadosLanzados(tiradas.get(1))) {
            return tiradas.get(1);
        } else {
            //Excepcion
        }
        System.out.println("Non se deberia chegar aqui. Faltan as excepcions");
        return tiradas.get(1);
    }

    private int getTirada() {
        if (dadosLanzados(tiradas.get(3))) {
            return 3;
        } else if (dadosLanzados(tiradas.get(2))) {
            return 2;
        } else if (dadosLanzados(tiradas.get(1))) {
            return 1;
        } else {
            //Excepcion
        }
        System.out.println("Non se deberia chegar aqui. Faltan as excepcions");
        return 0;
    }

    private void resetDados() {
        for (int i = 1; i <= 3; i++) {
            tiradas.get(i).get("d1").resetDado();
            tiradas.get(i).get("d2").resetDado();
        }
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Xogador getBanca() {
        return banca;
    }

    public void setBanca(Xogador banca) {
        this.banca = banca;
    }

    public int getTurnoAvatar(Avatar avatar) {
        if (avatar != null) {
            for (int i = 0; i < avatares.size(); i++) {
                if (avatar.equals(avatares.get(i))) {
                    return i;
                }
            }
        } else {
            //Excepcion
        }
        return 0;
    }

    //Métodos auxiliares para a funcionalidade
    private void avanzar(Avatar avatar) {
        if (!avatar.getModoAvanzado()) {
            avatar.moverEnBasico(sumarDados(getDadosLanzados()), taboleiro);
            avatar.getPosicion().sumarFrecuenciaVisita(turno);
        } else {
            avatar.moverEnAvanzado(sumarDados(getDadosLanzados()), taboleiro, banca);
            avatar.getPosicion().sumarFrecuenciaVisita(turno);
        }
    }

    @Override
    public final void cambiarModo(Avatar avatar) {
        if (avatar != null) {
            if (!avatar.getModoAvanzado()) {
                avatar.setModoAvanzado(true);
                consola.imprimir("A partir de agora o avatar " + avatar.getId()
                        + " moverase en modo avanzado.\n");
            } else {
                avatar.setModoAvanzado(false);
                consola.imprimir("A partir de agora o avatar " + avatar.getId()
                        + " moverase en modo básico.\n");
            }
        } else {
            //Excepcion
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

                if (xogador.getAvatar() instanceof Esfinxe) {
                    ((Esfinxe) xogador.getAvatar()).sumarHistorial("compra/"
                            + propiedade.getNome() + "/" + propiedade.getValor());
                }
            }
        }
    }

    private void comprobarCasilla(Casilla casilla, Xogador xogador) {
        if ((casilla != null) && (xogador != null)) {
            Avatar avatar = xogador.getAvatar();

            if (casilla instanceof Especial) {
                switch (casilla.getNome()) {

                    case "Parking":
                        float bote = ((Especial) casilla).getValor();
                        if (bote > 0) {
                            consola.imprimir("Conseguiches o bote do Parking."
                                    + " O xogador " + xogador.getNome()
                                    + " recibe " + bote + " GM.\n");
                            xogador.modificarFortuna(bote);
                            ((Especial) casilla).setValor(0);

                            if (avatar instanceof Esfinxe) {
                                ((Esfinxe) avatar).sumarHistorial("cobro/"
                                        + bote + "/Parking");
                            }
                        }
                        break;
                    case "IrCarcere":
                        moverAoCarcere(avatar);
                        consola.imprimir("O avatar " + avatar.getId()
                                + " foi enviado ao Carcere.\n");
                        break;

                    case "Sorte 1":
                    case "Sorte 2":
                    case "Sorte 3":
                    case "Caixa 1":
                    case "Caixa 2":
                    case "Caixa 3":
                        consola.imprimir("Sorte non implementada");
                        break;

                }
            } else if (casilla instanceof Imposto) {
                switch (casilla.getNome()) {
                    case "IRPF":
                        xogador.modificarFortuna(-Constantes.IMPOSTO1);
                        consola.imprimir(" O xogador " + xogador.getNome()
                                + " paga " + Constantes.IMPOSTO1 + " GM"
                                + " á banca.\n");
                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).sumarHistorial("pago/"
                                    + Constantes.IMPOSTO1);
                        }
                        break;
                    case "Subida Pension":
                        xogador.modificarFortuna(-Constantes.IMPOSTO2);
                        consola.imprimir(" O xogador " + xogador.getNome()
                                + " paga " + Constantes.IMPOSTO2 + " GM"
                                + " á banca.\n");

                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).sumarHistorial("pago/"
                                    + Constantes.IMPOSTO2);
                        }
                        break;
                }
            } else if (casilla instanceof Solar) {
                Solar solar = (Solar) casilla;
                if (solar.getDono().equals(banca)) {
                    consola.imprimir("O solar " + solar.getNome() + " non ten "
                            + "dono, pódese comprar.\n");
                } else {
                    float alquiler = solar.calculoAlquiler();
                    if (xogador.getFortuna() > alquiler) {
                        xogador.modificarFortuna(-alquiler);
                        solar.getDono().modificarFortuna(alquiler);
                        consola.imprimir("O xogador " + xogador.getNome()
                                + " pagulle ao xogador " + solar.getDono().getNome()
                                + " " + alquiler + " GM. A súa fortuna actual é "
                                + "de " + xogador.getFortuna() + "\n");

                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).sumarHistorial("alquiler/"
                                    + alquiler + "/" + solar.getDono().getNome()
                                    + "/" + solar.getNome());
                        }
                    } else {
                        consola.imprimir("Implementar bancarrota co numero da xogadores.");
                    }
                }
            } else if (casilla instanceof Transporte) {
                Transporte transporte = (Transporte) casilla;
                if (transporte.getDono().equals(banca)) {
                    consola.imprimir("A casilla de transporte "
                            + transporte.getNome() + " non ten dono, pódese"
                            + " comprar.\n");
                } else {
                    float alquiler = transporte.calculoAlquiler();
                    if (xogador.getFortuna() > alquiler) {
                        xogador.modificarFortuna(-alquiler);
                        transporte.getDono().modificarFortuna(alquiler);
                        consola.imprimir("O xogador " + xogador.getNome()
                                + " pagulle ao xogador " + transporte.getDono().getNome()
                                + " " + alquiler + " GM. A súa fortuna actual é "
                                + "de " + xogador.getFortuna() + "\n");

                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).sumarHistorial("alquiler/"
                                    + alquiler + "/" + transporte.getDono().getNome()
                                    + "/" + transporte.getNome());
                        }
                    } else {
                        consola.imprimir("Implementar bancarrota co numero da xogadores.");
                    }
                }
            } else if (casilla instanceof Servizo) {
                Servizo servizo = (Servizo) casilla;
                if (servizo.getDono().equals(banca)) {
                    consola.imprimir("A casilla de servizo "
                            + servizo.getNome() + " non ten dono, pódese"
                            + " comprar.\n");
                } else {
                    float alquiler = servizo.calculoAlquiler();
                    if (xogador.getFortuna() > alquiler) {
                        xogador.modificarFortuna(-alquiler);
                        servizo.getDono().modificarFortuna(alquiler);
                        consola.imprimir("O xogador " + xogador.getNome()
                                + " pagulle ao xogador " + servizo.getDono().getNome()
                                + " " + alquiler + " GM. A súa fortuna actual é "
                                + "de " + xogador.getFortuna() + "\n");

                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).sumarHistorial("alquiler/"
                                    + alquiler + "/" + servizo.getDono().getNome()
                                    + "/" + servizo.getNome());
                        }
                    } else {
                        consola.imprimir("Implementar bancarrota co número da xogadores.");
                    }
                }
            }
        } else {
            //Excepcion
        }
    }

    public boolean dadosLanzados(HashMap<String, Dado> dados) {
        if ((dados.get("d1").getValor() == 0) && (dados.get("d2").getValor() == 0)) {
            return false;
        }
        return true;
    }

    @Override
    public final void describir(String tipo, String nome) {
        switch (tipo) {
            case "xogador":
                describirXogador(nome);
                break;
            case "avatar":
                describirAvatar(nome.charAt(0));
                break;
            case "casilla":
                describirCasilla(nome);
                break;
            case "Carcere":
            case "Parking":
                describirCasilla(tipo);
                break;
            default:
                //Excepcion
                break;

        }
    }

    private void describirAvatar(char idAvatar) {
        if (existeAvatar(idAvatar)) {
            for (int i = 0; i < avatares.size(); i++) {
                if (avatares.get(i).getId() == idAvatar) {
                    consola.imprimir(avatares.get(i).toString());
                }
            }
        } else {
            //Excepcion
        }

    }

    public void describirCasilla(String nomeCasilla) {
        if (existeCasilla(nomeCasilla)) {
            consola.imprimir(taboleiro.getCasilla(nomeCasilla).imprimirCasilla());
        } else {
            //Excepcion
        }
    }

    private void describirXogador(String nomeXogador) {
        if (existeXogador(nomeXogador)) {
            for (int i = 0; i < xogadores.size(); i++) {
                if (xogadores.get(i).getNome().equals(nomeXogador)) {
                    consola.imprimir(xogadores.get(i).toString());
                }
            }
        } else {
            //Excepcion
        }
    }

    private void deshipotecar(String nom, Xogador xogador, Xogador hipo) {
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {
                if (taboleiro.getCasillas().get(i).get(j).getNome().equals(nom)) {
                    if (taboleiro.getCasillas().get(i).get(j) instanceof Propiedade) {
                        Propiedade prop = (Propiedade) taboleiro.getCasillas().get(i).get(j);
                        hipo.deshipotecar(xogador, prop);
                    }
                }

            }
        }
    }

    @Override
    public final void edificar(Avatar avatar, String tipoEdificacion, int nEdificios) {
        if (avatar.getPosicion() instanceof Solar) {
            Solar solar = (Solar) avatar.getPosicion();
            Xogador xogador = avatar.getXogador();
            //Comprobamos que sexa o dono da casilla
            if (solar.getDono().equals(xogador)) {

                if ((solar.getGrupo().existeMonopolio())
                        || (solar.frecuenciaVisita(getTurnoAvatar(avatar)) >= 2)) {
                    for (int i = 0; i < nEdificios; ++i) {
                        solar.edificar(tipoEdificacion);
                    }
                    if (nEdificios == 1) {
                        consola.imprimir("Construíuse 1 " + tipoEdificacion
                                + " no solar " + solar.getNome() + ". A fortuna "
                                + "de " + xogador.getNome() + " redúcese a "
                                + xogador.getFortuna() + " GM.\n");

                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).sumarHistorial("edificar/"
                                    + ((Solar) avatar.getPosicion()).
                                            calculoPrezoEdificio(tipoEdificacion)
                                    + "/" + avatar.getPosicion().getNome() + "/"
                                    + tipoEdificacion);
                        }
                    } else {
                        consola.imprimir("Construíronse " + nEdificios
                                + "casas no solar " + solar.getNome()
                                + ". A fortuna de " + xogador.getNome()
                                + " redúcese a " + xogador.getFortuna()
                                + " GM.\n");

                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).sumarHistorial("edificar/"
                                    + ((Solar) avatar.getPosicion()).
                                            calculoPrezoEdificio(tipoEdificacion) * nEdificios
                                    + "/" + avatar.getPosicion().getNome() + "/"
                                    + tipoEdificacion + "-" + nEdificios);
                        }
                    }
                } else {
                    //Excepcion
                }
            } else {
                //Excepcion
            }
        } else {
            //Exception
        }
    }

    public boolean existeAvatar(char idAvatar) {
        boolean existe = false;

        for (int i = 0; i < avatares.size(); i++) {
            if (avatares.get(i).getId() == idAvatar) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean existeCasilla(String nomeCasilla) {
        return taboleiro.getCasilla(nomeCasilla) != null;
    }

    public boolean existeXogador(String nomeXogador) {
        boolean existe = false;

        for (int i = 0; i < xogadores.size(); i++) {
            if (xogadores.get(i).getNome().equals(nomeXogador)) {
                existe = true;
            }
        }
        return existe;
    }

    private void hipotecar(String nom, Xogador xogador, Xogador hipo) {
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {
                if (taboleiro.getCasillas().get(i).get(j).getNome().equals(nom)) {
                    if (taboleiro.getCasillas().get(i).get(j) instanceof Propiedade) {
                        Propiedade prop = (Propiedade) taboleiro.getCasillas().get(i).get(j);
                        xogador.hipotecar(prop, hipo);
                    }
                }

            }
        }
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

    @Override
    public final void lanzarDados() {

        if (poderLanzar()) {
            HashMap<String, Dado> dados = getDadosLanzables();
            dados.get("d1").tirardado();
            dados.get("d2").tirardado();

            consola.imprimir("Dado1: " + dados.get("d1"));
            consola.imprimir("Dado2: " + dados.get("d2"));
            consola.imprimir("Suma dos dados: " + sumarDados(dados));
        } else {
            //Excepcion
        }

    }

    @Override
    public final void listar(String comando1, String grupo) {
        switch (comando1) {
            case "xogadores":
                listarXogador();
                break;
            case "avatares":
                listarAvatar();
                break;
            case "casillas":
                listarCasilla();
                break;
            case "edificios":
                listarEdificios(grupo);
                break;
            case "enventa":
                listarEnVenta();
                break;
            default:
                //Excepcion
                break;

        }
    }

    private void listarAvatar() {
        for (int i = 0; i < avatares.size(); i++) {
            consola.imprimir(avatares.get(i).toString());
        }
    }

    private void listarEdificios(String grupo) {
        boolean atopado = false;
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {
                if (taboleiro.getCasillas().get(i).get(j) instanceof Solar) {
                    if (((Solar) taboleiro.getCasillas().get(i).get(j)).getGrupo().getNome().equals(grupo)) {
                        atopado = true;
                        for (int k = 0; k < ((Solar) taboleiro.getCasillas().get(i).get(j)).getEdificios().size(); k++) {
                            consola.imprimir(((Solar) taboleiro.getCasillas().get(i).get(j)).getEdificios().get(i).toString());
                        }
                    }
                }
            }
        }
        if (!atopado) {
            for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
                for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {
                    if (taboleiro.getCasillas().get(i).get(j) instanceof Solar) {
                        for (int k = 0; k < ((Solar) taboleiro.getCasillas().get(i).get(j)).getEdificios().size(); k++) {
                            consola.imprimir(((Solar) taboleiro.getCasillas().get(i).get(j)).getEdificios().get(i).toString());
                        }
                    }
                }
            }
        }
    }

    private void listarEnVenta() {
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {
                if (taboleiro.getCasillas().get(i).get(j) instanceof Propiedade) {
                    if (((Propiedade) taboleiro.getCasillas().get(i).get(j)).getDono().getNome().equals("Banca")) {
                        consola.imprimir(taboleiro.getCasillas().get(i).get(j).imprimirCasilla());
                    }
                }
            }
        }
    }

    private void listarCasilla() {
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            for (int j = 0; j < taboleiro.getCasillas().get(i).size(); j++) {
                consola.imprimir(taboleiro.getCasillas().get(i).get(j).imprimirCasilla());
            }
        }
    }

    private void listarXogador() {
        for (int i = 0; i < xogadores.size(); i++) {
            consola.imprimir(xogadores.get(i).toString());
        }
    }

    private boolean nomeIgualXogador(String nome) {
        if (!xogadores.isEmpty()) {
            for (Xogador xogador : xogadores) {
                if (xogador.getNome().equals(nome)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void moverAoCarcere(Avatar avatar) {
        if (avatar != null) {
            Casilla procedencia = avatar.getPosicion();
            Casilla destino = taboleiro.getCasilla("Carcere");

            procedencia.eliminarAvatar(avatar);
            destino.engadirAvatar(avatar);
            avatar.setPosicion(destino);
            avatar.getXogador().setEstadoPreso(4);

        } else {
            //Excepcion
        }
    }

    private boolean propiedadeComprable(Propiedade propiedade) {
        return propiedade.getDono().equals(banca);
    }

    private boolean poderLanzar() {
        if (!dadosLanzados(tiradas.get(1))) {
            return true;
        } else if ((!dadosLanzados(tiradas.get(2))) && (sonDobres(tiradas.get(1)))) {
            return true;
        } else if ((!dadosLanzados(tiradas.get(3))) && (sonDobres(tiradas.get(2)))) {
            return true;
        }
        return false;
    }

    @Override
    public final void rematarTurno() {
        if (!poderLanzar()) {
            this.resetDados();
            if (turno == (xogadores.size() - 1)) {
                turno = 0;
            } else {
                turno++;
            }
            consola.imprimir("Turno de " + xogadores.get(turno).getNome() + ".\n");
        } else {
            //Excepcion
            consola.imprimir("O xogador non pode rematar turno.");
        }
    }

    @Override
    public final boolean rematarPartida() {
        return true;
    }

    @Override
    public final void sairCarcere() {
        if (xogadores.get(turno).getEstadoPreso() > 0) {
            if (poderLanzar()) {
                xogadores.get(turno).modificarFortuna(-Constantes.SAIR_CARCERE);
                xogadores.get(turno).setEstadoPreso(0);
                consola.imprimir("O xogador " + xogadores.get(turno).getNome()
                        + " pagou " + Constantes.SAIR_CARCERE + " GM para saír do"
                        + " Cárcere. A súa fortuna actual é de "
                        + xogadores.get(turno).getFortuna() + ".\n");
            } else {
                //Excepcion
                consola.imprimir("Xa lanzaches. Agora non podes pagar.\n");
            }
        } else {

            //Excepcion
        }
    }

    private boolean sonDobres(HashMap<String, Dado> dados) {
        if (dados != null) {
            if (dados.get("d1").equals(dados.get("d2"))) {
                if (dados.get("d1").getValor() > 0) {
                    return true;
                }
            }
        } else {
            //Excepcion
        }
        return false;
    }

    public int sumarDados(HashMap<String, Dado> dados) {
        if (dados != null) {
            return (dados.get("d1").getValor() + dados.get("d2").getValor());
        } else {
            //Excepcion
        }
        return 0;
    }

    @Override
    public final void verTaboleiro() {
        taboleiro.imprimirTaboleiro();
    }

    @Override
    public final void xogador() {
        xogadores.get(turno);
        consola.imprimir("\n");
    }

    //Fin clase
}
