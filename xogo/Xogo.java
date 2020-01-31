package xogo;

import errosInternos.*;
import errosExternos.*;
import errosExternos.errosExistencia.*;
import Excepcions.*;
import carta.*;
import estrutura.*;
import xogadores.*;
import consola.*;
import VentaInicializacion.*;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import InterfazGrafica.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Xogo implements Comando {

    public static final ConsolaNormal consola = new ConsolaNormal();
    //public ConsolaNormal consola;
    private ArrayList<Xogador> xogadores;
    private ArrayList<Avatar> avatares;
    private HashMap<String, ArrayList<Trato>> tratos;
    private Taboleiro taboleiro;
    private HashMap<Integer, HashMap<String, Dado>> tiradas;
    private HashMap<String, ArrayList<Carta>> cartas;
    private int turno;
    private Xogador banca;
    private Xogador hipotecar;
    private int nXogadores;
    private InterfazGrafica interfaz;
    private VentaInicializacion ventaInicial;

    public Xogo() {
        //Créanse as cartas de sorte e caixa
        cartas = new HashMap<>();
        crearCartas();
        //Créanse os arrays de xogadores, avatares e tratos baleiros
        xogadores = new ArrayList<>();
        avatares = new ArrayList<>();
        tratos = new HashMap<>();
        //Créanse os dados e inicialízanse a 0 todos
        tiradas = new HashMap<>();
        crearDados();
        banca = new Xogador();
        //Xogador ao que se lle hipoteca
        hipotecar = new Xogador("Hipotecar");
        taboleiro = new Taboleiro(banca);
        turno = 0;
        nXogadores = 0;
        interfaz = new InterfazGrafica(taboleiro); //Arrancase a interfaz
        interfaz.setVisible(true); //Ponse visible

        consola.setSaida(interfaz.getPanelEsquerdo().getPanelSaida());

        Boolean sair = false;

        do {

            nXogadores = Integer.parseInt(JOptionPane.showInputDialog(interfaz,
                    "Cantos xogadores sodes?"));
            if ((nXogadores < 2) || (nXogadores > 7)) {
                JOptionPane.showMessageDialog(interfaz, "O número de xogadores non é válido.\n");
            }
        } while ((nXogadores < 2) || (nXogadores > 6));

        //Inicializase a venta para pedir o nome dos xogadores
        ventaInicial = new VentaInicializacion(nXogadores);
        ventaInicial.setVisible(true);

        //Implementacion da accion do boton aceptar.
        ActionListener ointe = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                boolean camposBaleiros = false;
                String nomeXogador;
                String tipoAvatar;

                for (int i = 0; i < nXogadores; i++) {
                    nomeXogador = ventaInicial.getCamposTexto().get((i + 1) + ".1").getText();
                    if (nomeXogador.isBlank()) {
                        camposBaleiros = true;
                    }
                }

                if (camposBaleiros) {
                    JOptionPane.showInternalMessageDialog(null,
                            "Non se poden deixar nomes de xogadores en branco.",
                            "Erro", JOptionPane.WARNING_MESSAGE);
                } else if (nomesIguaisXogadores()) {
                    JOptionPane.showInternalMessageDialog(null,
                            "Non se poden repetir os nomes dos xogadores.",
                            "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (int i = 0; i < nXogadores; i++) {

                        nomeXogador = ventaInicial.getCamposTexto().get((i + 1) + ".1").getText();
                        if (!ventaInicial.getCamposTexto().get((i + 1) + ".2").getText().isEmpty()) {
                            tipoAvatar = ventaInicial.getCamposTexto().get((i + 1) + ".2").getText();
                        } else {
                            tipoAvatar = "valorPorDefecto";
                        }

                        crearXogador(nomeXogador, tipoAvatar);

                    }

                    synchronized (ventaInicial.getBotonAceptar()) {
                        ventaInicial.getBotonAceptar().notifyAll();
                    }
                    ventaInicial.setVisible(false);
                }

            }
        };

        //Engadese a accion ao boton aceptar
        ventaInicial.engadirAccionBoton(ointe);

        //Fio que espera a que se introduzan os datos na venta emerxente inicial
        if (xogadores.size() != nXogadores) {
            synchronized (ventaInicial.getBotonAceptar()) {
                try {
                    ventaInicial.getBotonAceptar().wait();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        interfaz.getPanelEsquerdo().getPanelXogadores().actualizar(xogadores);

        taboleiro.imprimirTaboleiro();

        //Incio xogo
        String menuimpreso = "=========== MENÚ ==========\n"
                + "1. listar xogadores\n" + "2. listar avatares\n"
                + "3. listar en venta\n" + "4. listar edificios <corGrupo>\n"
                + "5. describir casilla <nomeCasilla>\n"
                + "6. describir xogador<nomeXogador>\n"
                + "7. describir avatar<idAvatar>\n"
                + "8. xogador (indica o turno)\n" + "9. estatísticas\n"
                + "10. cambiar modo\n" + "11. sair carcere\n"
                + "12. lanzar dados\n" + "13. comprar\n"
                + "14. edificar <tipoedificio>\n"
                + "15. edificar casas <númeroCasas>\n" + "16. rematar turno\n"
                + "17. ver taboleiro\n" + "18. rematar partida\n"
                + "===========================\n";

        while (!sair) {
            consola.imprimir(menuimpreso);

            Xogador xogador = xogadores.get(turno);
            Avatar avatar = xogador.getAvatar();
            String comando0, comando1, comando2, comando3, comando4;
            try {
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
            } catch (VariableNull ex) {
                consola.imprimir("Fin da partida.");
                sair = true;
            }

            if (!tratos.get(xogador.getNome()).isEmpty()) {
                try {
                    consola.imprimir("\nTratos pendentes: ");
                    imprimirTratos(xogador);
                    consola.imprimir("\n");
                } catch (NonHaiTratos ex) {
                    consola.imprimir(ex.toString());
                }
            }

            //Turno do xogador
            //Facemos as declaracións e imos lendo do caso que sexa
            consola.imprimir("Turno de " + xogador.getNome() + "\n$> ");

            synchronized (interfaz.getPanelEsquerdo().getPanelComandos().getCampoComandos()) {
                try {
                    interfaz.getPanelEsquerdo().getPanelComandos().getCampoComandos().wait();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            String orde = interfaz.getPanelEsquerdo().getPanelComandos().getIntroducionComando().getComando();

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
            if (partes.length > 4) {
                comando4 = partes[4];
            } else {
                comando4 = "";
            }

            switch (comando0) {
                case "aceptar":
                    try {
                        aceptarTrato(comando1);
                    } catch (MonoExcep ex) {
                        consola.imprimir(ex.toString());
                    }
                    break;
                case "cambiar":
                    if (comando1.equals("modo")) {
                        cambiarModo(avatar);
                    }
                    break;
                case "comprar":
                    try {
                        if ((!poderLanzar()) || (sonDobres(getDadosLanzados()))) {
                            if (avatar.getPosicion() instanceof Propiedade) {
                                try {
                                    comprar((Propiedade) avatar.getPosicion(), xogador);
                                } catch (MonoExcep ex) {
                                    consola.imprimir(ex.toString());
                                    if (ex instanceof CartosInsuficientes) {
                                        consola.imprimir("Debes conseguir "
                                                + (((Propiedade) avatar.getPosicion())
                                                        .getValor() - xogador.getFortuna())
                                                + " GM para poder comprar"
                                                + "esta propiedade.");
                                    }
                                }
                            } else {
                                consola.imprimir("Só se poden comprar propiedades");
                            }
                        } else {
                            consola.imprimir("Debes lanzar os dados antes.");
                        }
                    } catch (VariableNull ex) {
                        consola.imprimir("Fin da partida");
                        sair = true;
                    } catch (NonLanzou ex) {
                        consola.imprimir(ex.getMessage());
                    }
                    break;

                case "crear":
                    if (comando1.equals("xogador")) {
                        crearXogador(comando2, comando3);
                    } else {
                        consola.imprimir("Sintaxe errónea:"
                                + " crear xogador <nome>");
                    }
                    break;

                case "describir":
                    try {
                        if (partes.length > 3) {
                            describir(comando1, comando2 + " " + comando3);
                        }
                        describir(comando1, comando2);
                    } catch (MonoExcep ex) {
                        consola.imprimir(ex.getMessage());
                    }
                    break;

                case "deshipotecar":
                    try {

                        if (existeCasilla(comando1)) {
                            deshipotecar(comando1);

                        } else if (existeCasilla(comando1 + " " + comando2)) {
                            deshipotecar(comando1 + " " + comando2);
                        } else {
                            consola.imprimir("Erro de sintaxe: "
                                    + "deshipotecar <nomePropiedade>");
                        }
                    } catch (CartosInsuficientes ex) {
                        consola.imprimir("Non tes cartos suficientes para "
                                + "deshipotecar a propiedade");
                    } catch (ErroExterno ex) {
                        consola.imprimir(ex.toString());
                    }
                    break;

                case "edificar":

                    switch (comando1) {
                        case "casa":
                        case "hotel":
                        case "piscina":
                        case "pista":
                            try {
                                edificar(avatar, comando1, 1);
                            } catch (ErroInterno ex) {
                                consola.imprimir(ex.toString());
                                consola.imprimir("Fin da partida.");
                                sair = true;
                            } catch (ErroExterno ex) {
                                consola.imprimir(ex.toString());
                            }
                            break;
                        case "casas":
                            try {
                                edificar(avatar, comando1, Integer.parseInt(comando2));
                            } catch (ErroInterno ex) {
                                consola.imprimir(ex.toString());
                                consola.imprimir("Fin da partida.");
                                sair = true;
                            } catch (ErroExterno ex) {
                                consola.imprimir(ex.toString());
                            }
                            break;
                    }

                    break;

                case "eliminar":
                    try {
                        eliminarTrato(comando1);
                    } catch (NonExiste ex) {
                        consola.imprimir(ex.toString());
                    }
                    break;

                case "hipotecar":
                    try {
                        if (existeCasilla(comando1)) {
                            hipotecar(comando1);

                        } else if (existeCasilla(comando1 + " " + comando2)) {
                            hipotecar(comando1 + " " + comando2);

                        } else {
                            consola.imprimir("Erro de sintaxe: "
                                    + "hipotecar <nomePropiedade>");
                        }
                    } catch (ErroExterno ex) {
                        consola.imprimir(ex.toString());
                    }
                    break;
                case "lanzar":
                    if (comando1.equals("dados")) {
                        try {
                            if (poderLanzar()) {

                                //Paso para trucar os dados
                                if (partes.length == 4) {
                                    lanzarDados(Integer.parseInt(comando2),
                                            Integer.parseInt(comando3));
                                } else if (comando2.equals("dobres")) {
                                    lanzarDadosDobres();
                                } else if (partes.length == 3) {
                                    lanzarDados(Integer.parseInt(comando2));
                                } else {
                                    lanzarDados();
                                }

                                if (xogador.getEstadoPreso() == 0) {

                                    if ((sonDobres(getDadosLanzados())) && (getTirada() == 3)) {

                                        consola.imprimir("Tirar dobres por"
                                                + " terceira vez consecutiva.\n");
                                        moverAoCarcere(avatar);
                                        consola.imprimir("O avatar "
                                                + avatar.getId() + " foi enviado "
                                                + "ao Cárcere.\n");

                                    } else if (!sonDobres(getDadosLanzados())) {

                                        avanzar(avatar);
                                        if ((avatar instanceof Esfinxe) || (avatar instanceof Chapeu)) {
                                            if (!(avatar.getModoAvanzado() && (sumarDados(getDadosLanzados()) < 4))) {
                                                comprobarCasilla(avatar.getPosicion(), xogador);
                                            }
                                        } else {
                                            comprobarCasilla(avatar.getPosicion(), xogador);
                                        }
                                    }
                                    if (sonDobres(getDadosLanzados())) {

                                        consola.imprimir("Conseguiches tirar dobres. "
                                                + "Debes volver lanzar.\n");

                                        avanzar(avatar);
                                        if ((avatar instanceof Esfinxe) || (avatar instanceof Chapeu)) {
                                            if (!(avatar.getModoAvanzado() && (sumarDados(getDadosLanzados()) < 4))) {
                                                comprobarCasilla(avatar.getPosicion(), xogador);
                                            }
                                        } else {
                                            comprobarCasilla(avatar.getPosicion(), xogador);
                                        }
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
                            }
                        } catch (VariableNull ex) {
                            consola.imprimir("Fin da partida.");
                            sair = true;
                        } catch (CartosInsuficientes ex) {
                            consola.imprimir(ex.toString());
                            consola.imprimir("Debes hipotecar algunha "
                                    + "propiedade ou vender algunha casa");
                            consola.imprimir("Implementar Bancarrota.");
                        } catch (NonLanzou | NonPodeLanzar ex) {
                            consola.imprimir("Resetéase o turno."
                                    + " Volves empezar o turno");
                            resetDados();
                        } catch (XogadorNonPreso ex) {
                            consola.imprimir(ex.toString());

                        } catch (ErroInicializacion | ErroExterno ex) {
                            consola.imprimir("Rematouse a partida.");
                            sair = true;
                        }
                    }
                    break;

                case "listar":
                    try {
                        listar(comando1, comando2);
                    } catch (ErroExterno ex) {
                        consola.imprimir(ex.toString());
                    }
                    break;
                case "rematar":
                    switch (comando1) {
                        case "partida":
                            sair = rematarPartida();
                            consola.imprimir("Fin da partida");
                            break;
                        case "turno":
                            try {
                                rematarTurno();
                            } catch (VariableNull ex) {
                                consola.imprimir("Fin da partida");
                                sair = true;
                            }
                            break;
                    }
                    break;

                case "sair":
                    if (comando1.equals("carcere")) {
                        try {
                            sairCarcere();
                        } catch (XogadorNonPreso ex) {
                            consola.imprimir(ex.toString());
                        } catch (VariableNull ex) {
                            consola.imprimir("Fin da partida");
                            sair = true;
                        }
                    }
                    break;

                //Exemplo: proponherTrato Anton: cambiar Santa Cruz por nonalquiler(A Guarda 3)
                //Exemplo: proponherTrato Anton: cambiar 500 por Lugo
                case "trato":
                    if (existeXogador(comando1.replace(":", ""))) {
                        if (comando2.equals("cambiar")) {
                            try {
                                proponherTrato(partes);
                            } catch (ErroExterno ex) {
                                consola.imprimir(ex.toString());
                            } catch (VariableNull ex) {
                                consola.imprimir("Fin da partida");
                                sair = true;
                            }
                        } else {
                            consola.imprimir("comando incorrecto. Falta cambiar.");
                        }
                    } else {
                        consola.imprimir("O xogador " + comando1.replace(":", "")
                                + " non existe.");
                    }
                    break;

                case "vender":
                    try {
                        if (existeCasilla(comando2)) {
                            vender(comando1, comando2, comando3);
                        } else if (existeCasilla(comando2 + " " + comando3)) {
                            vender(comando1, comando2 + " " + comando3, comando4);
                        }
                    } catch (PropiedadesNonEdificios ex) {
                        consola.imprimir(ex.toString());
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
                case "Tele":
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
                        tiradas.get(1).get("d2").setValor(1);
                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).resetHistorial();
                        } else if (avatar instanceof Chapeu) {
                            ((Chapeu) avatar).resetHistorial();
                        }
                        avatar.getPosicion().sumarFrecuenciaVisita(turno);
                        try {
                            comprobarCasilla(avatar.getPosicion(), xogador);
                        } catch (CartosInsuficientes ex) {
                            consola.imprimir(ex.toString());
                            consola.imprimir("Debes hipotecar para poder pagar.");
                        } catch (VariableNull ex) {
                            consola.imprimir("Fin da partida");
                            sair = true;
                        }
                    }
                    break;
                case "historial":
                    if (xogadores.get(turno).equals(xogador)) {
                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).imprimirHistorial();
                        } else if (avatar instanceof Chapeu) {
                            ((Chapeu) avatar).imprimirHistorial();
                        }
                    }
                    break;
                case "sumar":
                    if (existeXogador(comando1)) {

                        try {
                            getXogador(comando1).modificarFortuna(Float.
                                    parseFloat(comando2));

                            consola.imprimir("O xogador " + comando1
                                    + " recibiu " + comando2 + " GM. A súa "
                                    + "fortuna actual é de "
                                    + getXogador(comando1).getFortuna()
                                    + " GM.\n");

                        } catch (VariableNull ex) {
                            consola.imprimir("Se queres facer trampas aprende "
                                    + "a programar.");
                        }
                    } else {
                        consola.imprimir("O xogador introducido non existe.");
                    }
                    break;

                case "restar":
                    if (existeXogador(comando1)) {

                        try {
                            getXogador(comando1).modificarFortuna(-Float.
                                    parseFloat(comando2));

                            consola.imprimir("O xogador " + comando1
                                    + " perdeu " + comando2 + " GM. A súa "
                                    + "fortuna actual é de "
                                    + getXogador(comando1).getFortuna()
                                    + " GM.\n");

                        } catch (VariableNull ex) {
                            consola.imprimir("Se queres facer trampas aprende "
                                    + "a programar.");
                        }
                    } else {
                        consola.imprimir("O xogador introducido non existe.");
                    }
                    break;

                default:
                    consola.imprimir("Comando incorrecto.\n");
                    break;

            }
        }
    }

    /*Función que se usa para comprobar que os dados funcionan. Imprime todas
    * as tiradas do xogador actual no turno actual*/
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

    public final Xogador getXogador(String nome) throws VariableNull {
        if (nome != null) {
            for (Xogador xogador : xogadores) {
                if (xogador.getNome().equals(nome)) {
                    return xogador;
                }

            }
        }
        throw new VariableNull();
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

    public Grupo getGrupo(String grupo) throws GrupoNonExiste {
        for (Casilla casilla : taboleiro.getCasillas()) {
            if (casilla instanceof Propiedade) {
                if (((Propiedade) casilla).getGrupo().getNome().equals(grupo)) {
                    return ((Propiedade) casilla).getGrupo();
                }
            }
        }
        throw new GrupoNonExiste(grupo);
    }

    public Taboleiro getTaboleiro() {
        return taboleiro;
    }

    public void setTaboleiro(Taboleiro taboleiro) {
        this.taboleiro = taboleiro;
    }

    /*Función que devolve a parella de dados que se poden lanzar.*/
    public HashMap<String, Dado> getDadosLanzables()
            throws NonPodeLanzar, VariableNull {

        if (!dadosLanzados(tiradas.get(1))) {
            return tiradas.get(1);
        } else if ((!dadosLanzados(tiradas.get(2))) && (sonDobres(tiradas.get(1)))) {
            return tiradas.get(2);
        } else if ((!dadosLanzados(tiradas.get(3))) && (sonDobres(tiradas.get(2)))) {
            return tiradas.get(3);
        }
        throw new NonPodeLanzar();
    }

    /*Función que devolve a parella de dados que se lanzou por última vez.*/
    private HashMap<String, Dado> getDadosLanzados() throws NonLanzou {
        if (dadosLanzados(tiradas.get(3))) {
            return tiradas.get(3);
        } else if (dadosLanzados(tiradas.get(2))) {
            return tiradas.get(2);
        } else if (dadosLanzados(tiradas.get(1))) {
            return tiradas.get(1);
        }
        throw new NonLanzou();
    }

    /*Función que devolve o número da última tirada.*/
    private int getTirada() throws NonLanzou {
        if (dadosLanzados(tiradas.get(3))) {
            return 3;
        } else if (dadosLanzados(tiradas.get(2))) {
            return 2;
        } else if (dadosLanzados(tiradas.get(1))) {
            return 1;
        }
        throw new NonLanzou();
    }

    /*Función que resetea os dados para que outro xogador poida lanzar.*/
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

    public int getTurnoAvatar(Avatar avatar) throws ErroInterno {
        if (avatar != null) {
            for (int i = 0; i < avatares.size(); i++) {
                if (avatar.equals(avatares.get(i))) {
                    return i;
                }
            }
        }
        throw new ErroInterno("O avatar do que se quere obter o turno non"
                + " existe.");
    }

    //Métodos auxiliares para a funcionalidade
    @Override
    public final void aceptarTrato(String nomeTrato)
            throws TratoNonExiste, TratoNonPropostoA, VariableNull, TratoNonAceptado {

        if (nomeTrato != null) {
            if (existeTratoXogador(xogadores.get(turno), nomeTrato)) {

                getTrato(xogadores.get(turno), nomeTrato).aceptarTrato();
                tratos.get(xogadores.get(turno).getNome()).remove(getTrato(xogadores.get(turno), nomeTrato));
                consola.imprimir("Trato aceptado.");

            }
            throw new TratoNonPropostoA(nomeTrato, xogadores.get(turno).getNome());

        }
        throw new TratoNonExiste(nomeTrato);

    }

    private void avanzar(Avatar avatar) throws VariableNull, ErroInicializacion,
            ErroExterno {
        try {
            if (!avatar.getModoAvanzado()) {
                
                avatar.moverEnBasico(sumarDados(getDadosLanzados()), taboleiro);
                avatar.getPosicion().sumarFrecuenciaVisita(turno);
            } else {

                avatar.moverEnAvanzado(sumarDados(getDadosLanzados()),
                        taboleiro, banca, hipotecar);
            }
            avatar.getPosicion().sumarFrecuenciaVisita(turno);

            if (getTirada() == 1) {
                if (avatar instanceof Esfinxe) {
                    ((Esfinxe) avatar).resetHistorial();
                } else if (avatar instanceof Chapeu) {
                    ((Chapeu) avatar).resetHistorial();
                }
            }

        } catch (NonLanzou nonlanzou) {
            consola.imprimir(nonlanzou.getMessage());
            //Solución provisional
            resetDados();
            consola.imprimir("Reseteouse o turno. Volves empezar o turno.");
        } catch (CartosInsuficientes ex) {
            consola.imprimir("Implemenetar bancaRota");
        } catch (NonPodeEdificar ex) {
            consola.imprimir("Erro na orde de inserción do historial");
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
        }
    }

    @Override
    public final void comprar(Propiedade propiedade, Xogador xogador)
            throws PropNonComprable, CartosInsuficientes {

        if (propiedadeComprable(propiedade)) {

            if (xogadorPodePagar(xogador, propiedade.getValor())) {

                propiedade.comprar(xogador);
                consola.imprimir("O xogador " + xogador.getNome() + " compra a "
                        + "casilla " + propiedade.getNome() + " por "
                        + propiedade.getValor() + ". A súa fortuna actual é "
                        + xogador.getFortuna() + " GM.\n");

                if (xogador.getAvatar() instanceof Esfinxe) {
                    ((Esfinxe) xogador.getAvatar()).sumarHistorial("compra/"
                            + propiedade.getValor() + "/" + propiedade.getNome());
                } else if (xogador.getAvatar() instanceof Chapeu) {
                    ((Chapeu) xogador.getAvatar()).sumarHistorial("compra/"
                            + propiedade.getValor() + "/" + propiedade.getNome());
                }
                if (propiedade instanceof Solar) {
                    if ((((Solar) propiedade)).getGrupo().existeMonopolio()) {
                        consola.imprimir("O xogador " + xogador.getNome()
                                + " adquiriu todos os solares do grupo "
                                + propiedade.getGrupo().getNome() + ". A partir"
                                + "de agora pode edificar.");
                    }
                }
            } else {
                throw new CartosInsuficientes(propiedade.getNome());
            }

        } else {
            throw new PropNonComprable(propiedade.getNome(), "comprar");
        }
    }

    private void comprobarCasilla(Casilla casilla, Xogador xogador)
            throws CartosInsuficientes, VariableNull {

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
                            } else if (xogador.getAvatar() instanceof Chapeu) {
                                ((Chapeu) avatar).sumarHistorial("cobro/"
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
                    case "Sorte 3": {
                        //Collections.shuffle(cartas.get("Sorte"));
                        int numCarta;
                        do {
                            numCarta = Integer.parseInt(consola.ler("Escolle "
                                    + "unha carta (1-14):", interfaz.getPanelEsquerdo().getPanelComandos().getCampoComandos()));
                            if ((numCarta < 1) || (numCarta > 14)) {
                                System.out.print("\nEscolla non válida.\n");
                            }
                        } while ((numCarta < 1) || (numCarta > 14));

                        cartas.get("Sorte").get(numCarta - 1).accion(xogador,
                                taboleiro, xogadores);
                    }
                    break;

                    case "Caixa 1":
                    case "Caixa 2":
                    case "Caixa 3": {
                        //Collections.shuffle(cartas.get("Caixa"));
                        int numCarta;
                        do {
                            numCarta = Integer.parseInt(consola.ler("Escolle "
                                    + "unha carta (1-10):", interfaz.getPanelEsquerdo().getPanelComandos().getCampoComandos()));
                            if ((numCarta < 1) || (numCarta > 10)) {
                                System.out.print("\nEscolla non válida.\n");
                            }
                        } while ((numCarta < 1) || (numCarta > 10));

                        cartas.get("Caixa").get(numCarta - 1).accion(xogador,
                                taboleiro, xogadores);
                    }
                    break;

                }
            } else if (casilla instanceof Imposto) {
                switch (casilla.getNome()) {
                    case "IRPF":
                        if (xogadorPodePagar(xogador, Constantes.IMPOSTO1)) {
                            xogador.modificarFortuna(-Constantes.IMPOSTO1);
                            consola.imprimir(" O xogador " + xogador.getNome()
                                    + " paga " + Constantes.IMPOSTO1 + " GM"
                                    + " á banca.\n");
                            if (avatar instanceof Esfinxe) {
                                ((Esfinxe) avatar).sumarHistorial("pago/"
                                        + Constantes.IMPOSTO1);
                            } else if (xogador.getAvatar() instanceof Chapeu) {
                                ((Chapeu) avatar).sumarHistorial("pago/"
                                        + Constantes.IMPOSTO1);
                            }
                        } else {
                            throw new CartosInsuficientes(xogador.getNome());
                        }

                        break;

                    case "Subida Pension":
                        if (xogadorPodePagar(xogador, Constantes.IMPOSTO2)) {
                            xogador.modificarFortuna(-Constantes.IMPOSTO2);
                            consola.imprimir(" O xogador " + xogador.getNome()
                                    + " paga " + Constantes.IMPOSTO2 + " GM"
                                    + " á banca.\n");

                            if (avatar instanceof Esfinxe) {
                                ((Esfinxe) avatar).sumarHistorial("pago/"
                                        + Constantes.IMPOSTO2);
                            } else if (xogador.getAvatar() instanceof Chapeu) {
                                ((Chapeu) avatar).sumarHistorial("pago/"
                                        + Constantes.IMPOSTO2);
                            }
                        } else {
                            throw new CartosInsuficientes(xogador.getNome());
                        }
                        break;
                }
            } else if (casilla instanceof Solar) {
                Solar solar = (Solar) casilla;
                if (solar.getDono().equals(banca)) {
                    consola.imprimir("O solar " + solar.getNome() + " non ten "
                            + "dono, pódese comprar.\n");
                } else if (!solar.getExentos().isEmpty()) {
                    if (solar.getExentos().get(xogador.getNome()) != null) {
                        consola.imprimir("O xogador " + xogador.getNome() + " está"
                                + " exento de pagar o aluguer.\n");
                    }
                } else {
                    if (!solar.getDono().equals(xogador)) {
                        float alquiler = solar.calculoAlquiler();
                        if (xogadorPodePagar(xogador, alquiler)) {
                            xogador.modificarFortuna(-alquiler);
                            solar.getDono().modificarFortuna(alquiler);
                            consola.imprimir("O xogador " + xogador.getNome()
                                    + " pagulle ao xogador "
                                    + solar.getDono().getNome() + " " + alquiler
                                    + " GM. A súa fortuna actual é de "
                                    + xogador.getFortuna() + "\n");

                            if (avatar instanceof Esfinxe) {
                                ((Esfinxe) avatar).sumarHistorial("alquiler/"
                                        + alquiler + "/" + solar.getDono().getNome()
                                        + "/" + solar.getNome());
                            } else if (xogador.getAvatar() instanceof Chapeu) {
                                ((Chapeu) avatar).sumarHistorial("alquiler/"
                                        + alquiler + "/" + solar.getDono().getNome()
                                        + "/" + solar.getNome());
                            }
                        } else {
                            throw new CartosInsuficientes(xogador.getNome());
                        }
                    } else {
                        if (solar.frecuenciaVisita(turno) == 2) {
                            consola.imprimir("O xogador " + xogador.getNome()
                                    + " caeu dúas veces no solar "
                                    + solar.getNome() + ". A partir de agora "
                                    + "pode edificar.");
                        }
                    }
                }
            } else if (casilla instanceof Transporte) {
                Transporte transporte = (Transporte) casilla;
                if (transporte.getDono().equals(banca)) {
                    consola.imprimir("A casilla de transporte "
                            + transporte.getNome() + " non ten dono, pódese"
                            + " comprar.\n");
                } else {
                    if (!transporte.getDono().equals(xogador)) {
                        float alquiler = transporte.calculoAlquiler();
                        if (xogadorPodePagar(xogador, alquiler)) {
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
                            } else if (xogador.getAvatar() instanceof Chapeu) {
                                ((Chapeu) avatar).sumarHistorial("alquiler/"
                                        + alquiler + "/" + transporte.getDono().getNome()
                                        + "/" + transporte.getNome());
                            }
                        } else {
                            throw new CartosInsuficientes(xogador.getNome());
                        }
                    }
                }
            } else if (casilla instanceof Servizo) {
                Servizo servizo = (Servizo) casilla;
                if (servizo.getDono().equals(banca)) {
                    consola.imprimir("A casilla de servizo "
                            + servizo.getNome() + " non ten dono, pódese"
                            + " comprar.\n");
                } else {
                    if (!servizo.getDono().equals(xogador)) {
                        float alquiler;
                        try {
                            alquiler = servizo.calculoAlquiler() * sumarDados(getDadosLanzados());
                        } catch (NonLanzou ex) {
                            int sumDados = Integer.parseInt(consola.ler("Intro"
                                    + "duce o valor da suma dos dados da última"
                                    + " tirada.", interfaz.getPanelEsquerdo().getPanelComandos().getCampoComandos()));
                            alquiler = servizo.calculoAlquiler() * sumDados;
                        }
                        if (xogadorPodePagar(xogador, alquiler)) {
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
                            } else if (xogador.getAvatar() instanceof Chapeu) {
                                ((Chapeu) avatar).sumarHistorial("alquiler/"
                                        + alquiler + "/" + servizo.getDono().getNome()
                                        + "/" + servizo.getNome());
                            }
                        } else {
                            throw new CartosInsuficientes(xogador.getNome());
                        }
                    }
                }
            }
        }

    }

    private void crearCartas() {
        cartas.put("Sorte", new ArrayList<Carta>());
        cartas.get("Sorte").add(new Sorte("Sorte-1"));
        cartas.get("Sorte").add(new Sorte("Sorte-2"));
        cartas.get("Sorte").add(new Sorte("Sorte-3"));
        cartas.get("Sorte").add(new Sorte("Sorte-4"));
        cartas.get("Sorte").add(new Sorte("Sorte-5"));
        cartas.get("Sorte").add(new Sorte("Sorte-6"));
        cartas.get("Sorte").add(new Sorte("Sorte-7"));
        cartas.get("Sorte").add(new Sorte("Sorte-8"));
        cartas.get("Sorte").add(new Sorte("Sorte-9"));
        cartas.get("Sorte").add(new Sorte("Sorte-10"));
        cartas.get("Sorte").add(new Sorte("Sorte-11"));
        cartas.get("Sorte").add(new Sorte("Sorte-12"));
        cartas.get("Sorte").add(new Sorte("Sorte-13"));
        cartas.get("Sorte").add(new Sorte("Sorte-14"));

        cartas.put("Caixa", new ArrayList<Carta>());
        cartas.get("Caixa").add(new Caixa("Caixa-1"));
        cartas.get("Caixa").add(new Caixa("Caixa-2"));
        cartas.get("Caixa").add(new Caixa("Caixa-3"));
        cartas.get("Caixa").add(new Caixa("Caixa-4"));
        cartas.get("Caixa").add(new Caixa("Caixa-5"));
        cartas.get("Caixa").add(new Caixa("Caixa-6"));
        cartas.get("Caixa").add(new Caixa("Caixa-7"));
        cartas.get("Caixa").add(new Caixa("Caixa-8"));
        cartas.get("Caixa").add(new Caixa("Caixa-9"));
        cartas.get("Caixa").add(new Caixa("Caixa-10"));
    }

    private void crearDados() {
        for (int i = 1; i <= 3; i++) {
            HashMap<String, Dado> dados = new HashMap<>();
            dados.put("d1", new Dado());
            dados.put("d2", new Dado());
            tiradas.put(i, dados);
        }
    }

    @Override
    public final void crearXogador(String nomeXogador, String av) {
        if (nXogadores < 6) {
            if (!nomeIgualXogador(nomeXogador)) {

                Xogador xogador = new Xogador(nomeXogador, taboleiro);
                xogadores.add(xogador);
                tratos.put(nomeXogador, new ArrayList());

                String tipoAvatar;
                char IdAvatar;

                //Creamos o avatar
                tipoAvatar = av;
                IdAvatar = Avatar.xerarId();

                while (IdIgualAvatar(IdAvatar)) {
                    IdAvatar = Avatar.xerarId();
                }

                Avatar avatar;
                //Engadimos o avatar
                switch (tipoAvatar.toLowerCase()) {
                    case "sombreiro":
                    case "chapeu":
                        avatar = new Chapeu(IdAvatar, xogador, taboleiro, interfaz);
                        avatares.add(avatar);
                        break;
                    case "coche":
                        avatar = new Coche(IdAvatar, xogador, taboleiro, interfaz);
                        avatares.add(avatar);
                        break;
                    case "esfinxe":
                        avatar = new Esfinxe(IdAvatar, xogador, taboleiro, interfaz);
                        avatares.add(avatar);
                        break;
                    case "pelota":
                        avatar = new Pelota(IdAvatar, xogador, taboleiro, interfaz);
                        avatares.add(avatar);
                        break;
                    default:
                        avatar = new Pelota(IdAvatar, xogador, taboleiro, interfaz);
                        avatares.add(avatar);
                        consola.imprimir("Introduciuse pelota por defecto.\n");
                        break;
                }

                xogador.setAvatar(avatar);
                interfaz.getPanelDereito().getTab().getAvatares().get(0).addAvater(avatar);

                System.out.println("Xogador " + xogador.getNome() + " creado.\n");
                System.out.println("Avatar: " + avatar.getId() + ".\n");

            } else {
                consola.imprimir("Xa existe un xogador co nome "
                        + nomeXogador + ".");
            }
        } else {
            consola.imprimir("Non se poden engadir máis xogadores.");
        }
    }

    public boolean dadosLanzados(HashMap<String, Dado> dados) {
        if ((dados.get("d1").getValor() == 0) && (dados.get("d2").getValor() == 0)) {
            return false;
        }
        return true;
    }

    @Override
    public final void describir(String tipo, String nome) throws ErroSintaxe, NonExiste {
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
                throw new ErroSintaxe("Comando describir incorrecto.\n"
                        + "\tSintaxe correcta: describir <xogador/avatar/casilla>"
                        + " <nome>");

        }
    }

    private void describirAvatar(char idAvatar) throws AvatarNonExiste {
        if (existeAvatar(idAvatar)) {
            for (int i = 0; i < avatares.size(); i++) {
                if (avatares.get(i).getId() == idAvatar) {
                    consola.imprimir(avatares.get(i).toString());
                }
            }
        } else {
            throw new AvatarNonExiste(idAvatar);
        }

    }

    public void describirCasilla(String nomeCasilla) throws CasillaNonExiste {
        if (existeCasilla(nomeCasilla)) {
            consola.imprimir(taboleiro.getCasilla(nomeCasilla).imprimirCasilla());
        } else {
            throw new CasillaNonExiste(nomeCasilla);
        }
    }

    private void describirXogador(String nomeXogador) throws XogadorNonExiste {
        if (existeXogador(nomeXogador)) {
            for (int i = 0; i < xogadores.size(); i++) {
                if (xogadores.get(i).getNome().equals(nomeXogador)) {
                    consola.imprimir(xogadores.get(i).toString());
                }
            }
        } else {
            throw new XogadorNonExiste(nomeXogador);
        }
    }

    @Override
    public final void deshipotecar(String nomeCasilla)
            throws CartosInsuficientes, PropiedadeNonPertenceA,
            PropiedadeNonHipotecada, PropNonComprable {

        if (taboleiro.getCasilla(nomeCasilla) instanceof Propiedade) {
            Propiedade prop = (Propiedade) taboleiro.getCasilla(nomeCasilla);
            xogadores.get(turno).deshipotecar(hipotecar, prop);

            Avatar avatar = xogadores.get(turno).getAvatar();
            if (avatar instanceof Esfinxe) {
                ((Esfinxe) avatar).sumarHistorial("deshipoteca/"
                        + (prop.getValor() * 0.5) + "/" + prop.getNome());
            } else if (avatar instanceof Chapeu) {
                ((Chapeu) avatar).sumarHistorial("deshipoteca/"
                        + (prop.getValor() * 0.5) + "/" + prop.getNome());
            }

        } else {
            throw new PropNonComprable(nomeCasilla, "hipotecar");
        }

    }

    @Override
    public final void edificar(Avatar avatar, String tipoEdificacion, int nEdificios)
            throws CasillaNonEdificable, ErroInterno,
            NonPropiedade, CartosInsuficientes, NonPodeEdificar {

        if (avatar.getPosicion() instanceof Solar) {
            Solar solar = (Solar) avatar.getPosicion();
            Xogador xogador = avatar.getXogador();
            //Comprobamos que sexa o dono da casilla
            if (solar.getDono().equals(xogador)) {

                if ((solar.getGrupo().existeMonopolio())
                        || (solar.frecuenciaVisita(getTurnoAvatar(avatar)) >= 2)) {
                    for (int i = 0; i < nEdificios; i++) {
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
                        } else if (xogador.getAvatar() instanceof Chapeu) {
                            ((Chapeu) avatar).sumarHistorial("edificar/"
                                    + ((Solar) avatar.getPosicion()).
                                            calculoPrezoEdificio(tipoEdificacion)
                                    + "/" + avatar.getPosicion().getNome() + "/"
                                    + tipoEdificacion);
                        }
                    } else {
                        consola.imprimir("Construíronse " + nEdificios
                                + " casas no solar " + solar.getNome()
                                + ". A fortuna de " + xogador.getNome()
                                + " redúcese a " + xogador.getFortuna()
                                + " GM.\n");

                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).sumarHistorial("edificar/"
                                    + ((Solar) avatar.getPosicion()).
                                            calculoPrezoEdificio("casa") * nEdificios
                                    + "/" + avatar.getPosicion().getNome() + "/"
                                    + tipoEdificacion + "-" + nEdificios);
                        } else if (xogador.getAvatar() instanceof Chapeu) {
                            ((Chapeu) avatar).sumarHistorial("edificar/"
                                    + ((Solar) avatar.getPosicion()).
                                            calculoPrezoEdificio("casa") * nEdificios
                                    + "/" + avatar.getPosicion().getNome() + "/"
                                    + tipoEdificacion + "-" + nEdificios);
                        }
                    }
                } else {
                    throw new CasillaNonEdificable(avatar.getXogador().getNome(), solar.getNome());
                }
            } else {
                throw new NonPropiedade(solar.getNome());
            }
        } else {
            throw new CasillaNonEdificable(avatar.getPosicion().getNome());
        }
    }

    @Override
    public final void eliminarTrato(String nomeTrato) throws TratoNonExiste {
        try {
            if (existeTrato(nomeTrato)) {
                for (int i = 0; i < xogadores.size(); i++) {
                    if (i != turno) {
                        for (Trato trato : tratosXogador(xogadores.get(i))) {
                            //Miramos que coincida o xogador que o propon e o nome do trato.
                            if (trato.getXogadorPropon().equals(xogadores.get(turno))) {
                                if (trato.getNome().equals(nomeTrato)) {
                                    consola.imprimir(trato.getNome() + " eliminado.");
                                    tratosXogador(xogadores.get(i)).remove(trato);
                                }

                            }
                        }
                    }
                }

            } else {
                throw new TratoNonExiste(nomeTrato);
            }
        } catch (VariableNull ex) {
            consola.imprimir("Para eliminar un trato débese introducir o nome.");
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

    private boolean existeCasilla(String nomeCasilla) {
        return taboleiro.getCasilla(nomeCasilla) != null;
    }

    private boolean existeTrato(String nomeTrato) throws VariableNull {
        if (nomeTrato != null) {
            if (!tratos.isEmpty()) {
                for (int i = 0; i < xogadores.size(); i++) {
                    for (Trato trato : tratosXogador(xogadores.get(i))) {
                        if (trato.getNome().equals(nomeTrato)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        throw new VariableNull();
    }

    private boolean existeTratoXogador(Xogador xogador, String nomeTrato)
            throws VariableNull {

        if ((xogador != null) && (nomeTrato != null)) {
            if (!tratos.isEmpty()) {
                if (!tratos.get(xogador.getNome()).isEmpty()) {
                    for (Trato trato : tratosXogador(xogador)) {
                        if (trato.getNome().equals(nomeTrato)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        throw new VariableNull();
    }

    public final boolean existeXogador(String nomeXogador) {
        boolean existe = false;

        for (int i = 0; i < xogadores.size(); i++) {
            if (xogadores.get(i).getNome().equals(nomeXogador)) {
                existe = true;
            }
        }
        return existe;
    }

    private Trato getTrato(Xogador xogador, String nomeTrato) throws TratoNonExiste {
        try {
            if (existeTrato(nomeTrato)) {
                if (existeTratoXogador(xogador, nomeTrato)) {
                    for (Trato trato : tratosXogador(xogador)) {
                        if (trato.getNome().equals(nomeTrato)) {
                            return trato;
                        }
                    }
                }

            }
            throw new TratoNonExiste(nomeTrato);
        } catch (VariableNull ex) {
            throw new TratoNonExiste(nomeTrato);
        }

    }

    @Override
    public final void hipotecar(String nomeCasilla)
            throws PropiedadeNonPertenceA, PropNonComprable {

        if (taboleiro.getCasilla(nomeCasilla) instanceof Propiedade) {
            Propiedade prop = (Propiedade) taboleiro.getCasilla(nomeCasilla);

            xogadores.get(turno).hipotecar(prop, hipotecar);

            Avatar avatar = xogadores.get(turno).getAvatar();
            if (avatar instanceof Esfinxe) {
                ((Esfinxe) avatar).sumarHistorial("hipoteca/"
                        + (prop.getValor() * 0.5) + "/" + prop.getNome());
            } else if (avatar instanceof Chapeu) {
                ((Chapeu) avatar).sumarHistorial("hipoteca/"
                        + (prop.getValor() * 0.5) + "/" + prop.getNome());
            }

        } else {
            throw new PropNonComprable(nomeCasilla, "hipotecar");
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

    /*Función que percorre os tratos e os imprime*/
    private void imprimirTratos(Xogador xogador) throws NonHaiTratos {
        if (!tratos.isEmpty()) {
            if (!tratos.get(xogador.getNome()).isEmpty()) {
                for (Trato trato : tratos.get(xogador.getNome())) {
                    consola.imprimir(trato.toString());
                }
            }
        } else {
            throw new NonHaiTratos();
        }
    }

    @Override
    public final void lanzarDados() throws NonPodeLanzar, VariableNull {

        if (poderLanzar()) {
            HashMap<String, Dado> dados = getDadosLanzables();
            dados.get("d1").tirardado();
            dados.get("d2").tirardado();

            consola.imprimir("Dado1: " + dados.get("d1"));
            consola.imprimir("Dado2: " + dados.get("d2"));
            consola.imprimir("Suma dos dados: " + sumarDados(dados));
        } else {
            throw new NonPodeLanzar();
        }

    }

    public final void lanzarDados(int dado1, int dado2)
            throws NonPodeLanzar, VariableNull {

        if (poderLanzar()) {
            HashMap<String, Dado> dados = getDadosLanzables();
            dados.get("d1").setValor(dado1);
            dados.get("d2").setValor(dado2);

            consola.imprimir("Dado1: " + dados.get("d1"));
            consola.imprimir("Dado2: " + dados.get("d2"));
            consola.imprimir("Suma dos dados: " + sumarDados(dados));
        } else {
            throw new NonPodeLanzar();
        }

    }

    public final void lanzarDados(int dado1)
            throws NonPodeLanzar, VariableNull {

        if (poderLanzar()) {
            HashMap<String, Dado> dados = getDadosLanzables();
            dados.get("d1").setValor(dado1);
            dados.get("d2").setValor(dado1);

            consola.imprimir("Dado1: " + dados.get("d1"));
            consola.imprimir("Dado2: " + dados.get("d2"));
            consola.imprimir("Suma dos dados: " + sumarDados(dados));
        } else {
            throw new NonPodeLanzar();
        }

    }

    public final void lanzarDadosDobres()
            throws NonPodeLanzar, VariableNull {

        if (poderLanzar()) {
            HashMap<String, Dado> dados = getDadosLanzables();
            dados.get("d1").tirardado();
            dados.get("d2").setValor(dados.get("d1").getValor());

            consola.imprimir("Dado1: " + dados.get("d1"));
            consola.imprimir("Dado2: " + dados.get("d2"));
            consola.imprimir("Suma dos dados: " + sumarDados(dados));
        } else {
            throw new NonPodeLanzar();
        }

    }

    public void listarTratos() throws NonHaiTratos {
        for (int i = 0; i < xogadores.size(); i++) {
            imprimirTratos(xogadores.get(i));
        }
    }

    @Override
    public final void listar(String comando1, String grupo)
            throws ErroSintaxe, NonHaiTratos, GrupoNonExiste, PropiedadesNonEdificios {

        switch (comando1) {
            case "xogadores":
                listarXogadores();
                break;
            case "avatares":
                listarAvatares();
                break;
            case "casillas":
                listarCasillas();
                break;
            case "edificios":
                listarEdificios(grupo);
                break;
            case "enventa":
            case "en":
                listarEnVenta();
                break;
            case "tratos":
                listarTratos();
                break;
            default:
                throw new ErroSintaxe("Sintaxe incorrecta: \n"
                        + " listar <xogadores/avatares/casillas/edificios/enventa/tratos>\n");

        }
    }

    private void listarAvatares() {
        for (int i = 0; i < avatares.size(); i++) {
            consola.imprimir(avatares.get(i).toString());
        }
    }

    private void listarEdificios(String corGrupo)
            throws GrupoNonExiste, PropiedadesNonEdificios {

        if (corGrupo != null) {
            Grupo grupo = getGrupo(corGrupo);

            if (grupo.grupoSolares()) {
                for (Edificio edificio : grupo.getEdificios()) {
                    consola.imprimir(edificio.toString());
                }

            } else {
                throw new PropiedadesNonEdificios(corGrupo);
            }
        }
    }

    private void listarEnVenta() {
        for (Casilla casilla : taboleiro.getCasillas()) {
            if (casilla instanceof Propiedade) {
                if (((Propiedade) casilla).getDono().getNome().equals("Banca")) {
                    consola.imprimir(casilla.imprimirCasilla());
                }
            }
        }
        //Imprimir non hai propiedades en venta cun arraylist de prpiedades en banca
    }

    private void listarCasillas() {
        for (Casilla casilla : taboleiro.getCasillas()) {
            consola.imprimir(casilla.imprimirCasilla());
        }
    }

    private void listarXogadores() {
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

        return (nome.isBlank()) || (nome.toLowerCase().equals("hipoteca"))
                || (nome.toLowerCase().equals("banca"));
    }

    private boolean nomesIguaisXogadores() {
        String nomeXogador, nomeXogador2;
        for (int i = 0; i < nXogadores; i++) {
            nomeXogador = ventaInicial.getCamposTexto().get((i + 1) + ".1").getText();
            if ((nomeXogador.toLowerCase().equals("hipoteca"))
                    || (nomeXogador.toLowerCase().equals("banca"))) {
                return true;
            }
            for (int j = (i + 1); j < nXogadores; j++) {
                nomeXogador2 = ventaInicial.getCamposTexto().get((j + 1) + ".1").getText();
                if (nomeXogador.toLowerCase().equals(nomeXogador2.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void moverAoCarcere(Avatar avatar) {
        if (avatar != null) {
            Casilla procedencia = avatar.getPosicion();
            Casilla destino = taboleiro.getCasilla("Carcere");

            procedencia.eliminarAvatar(avatar);
            avatar.setPosicion(destino);
            avatar.getXogador().setEstadoPreso(4);
        }
    }

    public int nTratos(Xogador xogador) {
        if (xogador != null) {
            if (!tratos.isEmpty()) {
                int nTrat;
                if (!tratos.get(xogador.getNome()).isEmpty()) {
                    nTrat = tratos.get(xogador.getNome()).size();
                    return nTrat;
                }
            }
        }
        return 0;
    }

    public int nTratos() {
        if (!tratos.isEmpty()) {
            int nTrat = 0;

            for (Xogador xogador : xogadores) {
                nTrat += nTratos(xogador);
            }

            return nTrat;
        }
        return 0;
    }

    private boolean propiedadeComprable(Propiedade propiedade) {
        return propiedade.getDono().equals(banca);
    }

    /*Exemplo:

    trato Anton: cambiar Lugo por Santiago
    trato Anton: cambiar Lugo por 500
    trato Anton: cambiar 500 por Santiago
    trato Anton: cambiar Lugo e 500 por Santiago
    trato Anton: cambiar Lugo por Santiago e 500
    trato Anton: cambiar Lugo por Santiago e nonalquiler(Meanho,3)

     */
    @Override
    public final void proponherTrato(String[] partes)
            throws NonPropiedade, ErroSintaxe, VariableNull {
        if (partes != null) {

            boolean comandoViable = false;
            boolean rematar = false;

            for (int i = 0; i < partes.length; i++) {
                if (partes[i].equals("por")) {
                    comandoViable = true;
                }
            }

            if (!comandoViable) {
                throw new ErroSintaxe("Houbo un erro na sintaxe do comando");
            }

            if ((comandoViable) && (partes.length >= 5)) { //Lemos as casillasPropon que se proponhen
                Xogador xogProp = xogadores.get(turno);
                Xogador xogRec = getXogador(partes[1].replace(":", ""));

                String nomeCasilla = "";
                Propiedade propProp = null;
                float cartos1 = 0;
                Propiedade propRec = null;
                float cartos2 = 0;
                Propiedade propAl = null;
                int nTurnos = 0;

                nomeCasilla = partes[3].replace("-", " ");

                if (existeCasilla(nomeCasilla)) {
                    if (taboleiro.getCasilla(nomeCasilla) instanceof Propiedade) {
                        if (((Propiedade) taboleiro.getCasilla(nomeCasilla)).getDono().equals(xogProp)) {

                            propProp = (Propiedade) taboleiro.getCasilla(nomeCasilla);

                            //Trato 5
                            if (partes[4].equals("e")) { //Lemos a cantidade de cartos
                                cartos1 = Float.parseFloat(partes[5]);

                                nomeCasilla = partes[7].replace("-", " ");

                                if (existeCasilla(nomeCasilla)) {
                                    if (taboleiro.getCasilla(nomeCasilla) instanceof Propiedade) {
                                        if (((Propiedade) taboleiro.getCasilla(nomeCasilla)).getDono().equals(xogRec)) {

                                            propRec = (Propiedade) taboleiro.getCasilla(nomeCasilla);
                                            rematar = true;

                                        } else {
                                            throw new NonPropiedade("Non podes cambiar a propiedade "
                                                    + nomeCasilla
                                                    + " co xogador " + xogRec.getNome()
                                                    + " porque non é del.");
                                        }
                                    } else {
                                        throw new NonPropiedade("Non podes cambiar a casilla "
                                                + nomeCasilla
                                                + " co xogador " + xogRec.getNome()
                                                + " porque non é del.");
                                    }
                                } else {
                                    throw new NonPropiedade("A casilla "
                                            + nomeCasilla
                                            + " non existe.");

                                }

                            }

                        } else {
                            throw new NonPropiedade("Non podes cambiar a propiedade "
                                    + nomeCasilla
                                    + " porque non é túa.");
                        }
                    } else {
                        throw new NonPropiedade("Non podes cambiar a propiedade "
                                + nomeCasilla
                                + " porque non é túa.");
                    }
                } else {
                    cartos1 = Float.parseFloat(partes[3]);
                }

                if (!rematar) {

                    if (partes[4].equals("por")) { //Lemos o que se quere

                        nomeCasilla = partes[5].replace("-", " ");

                        if (existeCasilla(nomeCasilla)) {
                            if (taboleiro.getCasilla(nomeCasilla) instanceof Propiedade) {
                                if (((Propiedade) taboleiro.getCasilla(nomeCasilla)).getDono().equals(xogRec)) {
                                    propRec = (Propiedade) taboleiro.getCasilla(nomeCasilla);

                                    if (partes.length > 6) {

                                        if (partes[7].contains("non")) {
                                            String[] comandos = partes[7].split("->");
                                            String[] alquileres = comandos[1].split(",");
                                            nomeCasilla = alquileres[0].replace("-", " ");
                                            if (existeCasilla(nomeCasilla)) {
                                                if (taboleiro.getCasilla(nomeCasilla) instanceof Propiedade) {
                                                    if (((Propiedade) taboleiro.getCasilla(nomeCasilla)).getDono().equals(xogRec)) {
                                                        propAl = (Propiedade) taboleiro.getCasilla(nomeCasilla);
                                                        nTurnos = Integer.parseInt(alquileres[1]);
                                                    }
                                                } else {
                                                    throw new NonPropiedade("A casilla "
                                                            + nomeCasilla
                                                            + " non é do xogador "
                                                            + xogRec.getNome() + ".");
                                                }
                                            } else {
                                                throw new NonPropiedade("A casilla "
                                                        + nomeCasilla
                                                        + " non existe.");
                                            }
                                        } else {
                                            cartos2 = Float.parseFloat(partes[7]);
                                        }
                                    }

                                } else {
                                    throw new NonPropiedade("A propiedade "
                                            + nomeCasilla
                                            + " non pertence ao xogador "
                                            + xogRec.getNome() + ".");
                                }
                            } else {
                                throw new NonPropiedade("A casilla "
                                        + nomeCasilla
                                        + " non existe.");
                            }
                        } else {
                            cartos2 = Float.parseFloat(partes[5]);
                        }

                    } else {
                        consola.imprimir("comando non viable");
                    }
                }
                consola.imprimir("NTratos + 1: " + (nTratos() + 1));
                tratos.get(xogRec.getNome()).add(
                        new Trato(nTratos() + 1, xogProp, xogRec,
                                propProp, propRec, propAl, cartos1,
                                cartos2, nTurnos));
                consola.imprimir("\nComprobacion: \n");
                consola.imprimir("Trato: ");
                if (propProp != null) {
                    consola.imprimir("CasillaPropon: " + propProp.getNome());
                }
                if (cartos1 != 0) {
                    consola.imprimir("cartos1: " + cartos1);
                }
                if (propRec != null) {
                    consola.imprimir("CasillaQuere: " + propRec.getNome());
                }
                if (propAl != null) {
                    consola.imprimir("CasillaAlquiler: " + propAl.getNome());
                    consola.imprimir("Turnos: " + nTurnos);
                }
                if (cartos2 != 0) {
                    consola.imprimir("cartos2: " + cartos2);
                }

            } else {
                throw new ErroSintaxe("comando non viable");
            }
        } else {
            throw new ErroSintaxe("partes null");
        }
    }

    private boolean poderLanzar() throws VariableNull {
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
    public final void rematarTurno() throws VariableNull {
        if (!poderLanzar()) {
            this.resetDados();

            for (int i = 0; i < 40; i++) {
                if (taboleiro.getCasilla(i) instanceof Propiedade) {
                    if (!((Propiedade) taboleiro.getCasilla(i)).getExentos().isEmpty()) {
                        Propiedade prop = (Propiedade) taboleiro.getCasilla(i);
                        if (prop.estaExento(xogadores.get(turno).getNome())) {
                            prop.restarTurnoExento(xogadores.get(turno).getNome());
                            consola.imprimir("Ao xogador " + xogadores.get(turno).getNome()
                                    + " quédanlle " + prop.getExentos().get(xogadores.get(turno).getNome())
                                    + " turnos exento de pagar o aluguer de " + prop.getNome());
                        }
                    }
                }
            }

            if (turno == (xogadores.size() - 1)) {
                turno = 0;
            } else {
                turno++;
            }
            consola.imprimir("Turno de " + xogadores.get(turno).getNome() + ".\n");
        } else {
            consola.imprimir("O xogador non pode rematar turno.");
        }
    }

    @Override
    public final boolean rematarPartida() {
        return true;
    }

    @Override
    public final void sairCarcere() throws XogadorNonPreso, VariableNull {
        if (xogadores.get(turno).getEstadoPreso() > 0) {
            if (poderLanzar()) {
                xogadores.get(turno).modificarFortuna(-Constantes.SAIR_CARCERE);
                xogadores.get(turno).setEstadoPreso(0);
                consola.imprimir("O xogador " + xogadores.get(turno).getNome()
                        + " pagou " + Constantes.SAIR_CARCERE + " GM para saír do"
                        + " Cárcere. A súa fortuna actual é de "
                        + xogadores.get(turno).getFortuna() + ".\n");

                if (avatares.get(turno) instanceof Esfinxe) {
                    ((Esfinxe) avatares.get(turno)).sumarHistorial("pago/"
                            + Constantes.SAIR_CARCERE);
                } else if (avatares.get(turno) instanceof Chapeu) {
                    ((Chapeu) avatares.get(turno)).sumarHistorial("pago/"
                            + Constantes.SAIR_CARCERE);
                }
            } else {
                //Excepcion
                consola.imprimir("Xa lanzaches. Agora non podes pagar.\n");
            }
        } else {
            throw new XogadorNonPreso(xogadores.get(turno).getNome());
        }
    }

    private boolean sonDobres(HashMap<String, Dado> dados) throws VariableNull {
        if (dados != null) {
            if (dados.get("d1").equals(dados.get("d2"))) {
                if (dados.get("d1").getValor() > 0) {
                    return true;
                }
            }
            return false;
        }
        throw new VariableNull();
    }

    public final int sumarDados(HashMap<String, Dado> dados) throws VariableNull {
        if (dados != null) {
            return (dados.get("d1").getValor() + dados.get("d2").getValor());
        }
        throw new VariableNull();
    }

    public ArrayList<Trato> tratosXogador(Xogador xogador) throws VariableNull {
        if (xogador != null) {
            return tratos.get(xogador.getNome());
        }
        throw new VariableNull();
    }

    @Override
    public final void vender(String tipoEdificio, String casilla,
            String nEdificios) throws PropiedadesNonEdificios {

        int n = Integer.parseInt(nEdificios);
        if (taboleiro.getCasilla(casilla) instanceof Solar) {
            Solar solar = (Solar) taboleiro.getCasilla(casilla);

            float valor = 0;
            if ((n > 0) && (n <= 4)) {
                for (int i = 0; i < n; i++) {
                    valor += solar.venderEdificio(tipoEdificio);
                }
                consola.imprimir("O xogador " + solar.getDono().getNome()
                        + " vendeu " + nEdificios + " " + tipoEdificio
                        + "pola cantidade de " + valor
                        + " GM.\n");
                if (avatares.get(turno) instanceof Esfinxe) {
                    ((Esfinxe) avatares.get(turno)).sumarHistorial("venta/"
                            + valor + "/" + casilla + "/" + tipoEdificio);

                } else if (avatares.get(turno) instanceof Chapeu) {
                    ((Chapeu) avatares.get(turno)).sumarHistorial("venta/"
                            + valor + "/" + casilla + "/" + tipoEdificio);
                }
            } else {
                valor = solar.venderEdificio(tipoEdificio);
                consola.imprimir("O xogador " + solar.getDono().getNome()
                        + " vendeu 1 " + tipoEdificio
                        + "pola cantidade de " + valor
                        + " GM.\n");
                if (avatares.get(turno) instanceof Esfinxe) {
                    ((Esfinxe) avatares.get(turno)).sumarHistorial("venta/"
                            + valor + "/" + casilla + "/" + tipoEdificio);

                } else if (avatares.get(turno) instanceof Chapeu) {
                    ((Chapeu) avatares.get(turno)).sumarHistorial("venta/"
                            + valor + "/" + casilla + "/" + tipoEdificio);
                }
            }
        } else {
            throw new PropiedadesNonEdificios(taboleiro.getCasilla(casilla));
        }
    }

    @Override
    public final void verTaboleiro() {
        taboleiro.imprimirTaboleiro();
    }

    @Override
    public final void xogador() {
        consola.imprimir(xogadores.get(turno).toString());
        consola.imprimir("\n");
    }

    public boolean xogadorPodePagar(Xogador xogador, float pago) {
        return (xogador.getFortuna() >= pago);
    }

    //Fin clase
}
