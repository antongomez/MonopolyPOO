package estrutura;

import xogadores.Xogador;
import java.util.ArrayList;

public class Taboleiro {

    private ArrayList<ArrayList<Casilla>> casillas;

    public Taboleiro() {
    }

    public Taboleiro(Xogador banca) {
        casillas = new ArrayList<>();
        if (banca != null) {
            ArrayList<Casilla> ladoSur = new ArrayList();
            ArrayList<Casilla> ladoOeste = new ArrayList();
            ArrayList<Casilla> ladoNorte = new ArrayList();
            ArrayList<Casilla> ladoEste = new ArrayList();

            //Creacion de grupos Solares
            Grupo grupo1 = new Grupo(1, "Laranxa");
            Grupo grupo2 = new Grupo(2, "Cian");
            Grupo grupo3 = new Grupo(3, "Rosa");
            Grupo grupo4 = new Grupo(4, "Gris");
            Grupo grupo5 = new Grupo(5, "Vermello");
            Grupo grupo6 = new Grupo(6, "Amarelo");
            Grupo grupo7 = new Grupo(7, "Azul");
            Grupo grupo8 = new Grupo(8, "Negro");

            //Creacion do resto dos grupos
            Grupo grupoErv = new Grupo(11); //Servizos
            Grupo grupoTrans = new Grupo(12); //Transportes

            //Creamos o lado Sur
            casillas.add(ladoSur);
            ladoSur.add(new Especial("Saida", 0));
            ladoSur.add(new Solar("Santa-Cruz", 1, Constantes.CASILLA_G1, grupo1, banca));
            ladoSur.add(new Especial("Caixa-1", 2));
            ladoSur.add(new Solar("Arteixo", 3, Constantes.CASILLA_G1, grupo1, banca));
            ladoSur.add(new Imposto("IRPF", 4));
            ladoSur.add(new Transporte("Autobus", 5, 0, grupoTrans, banca));
            ladoSur.add(new Solar("Meanho", 6, Constantes.CASILLA_G2, grupo2, banca));
            ladoSur.add(new Especial("Sorte-1", 7));
            ladoSur.add(new Solar("Corrubedo", 8, Constantes.CASILLA_G2, grupo2, banca));
            ladoSur.add(new Solar("Lugo", 9, Constantes.CASILLA_G2, grupo2, banca));

            //Creamos o lado Oeste
            casillas.add(ladoOeste);
            ladoOeste.add(new Especial("Carcere", 10));
            ladoOeste.add(new Solar("O-Carballinho", 11, Constantes.CASILLA_G3, grupo3, banca));
            ladoOeste.add(new Servizo("SERGAS", 12, 0, grupoErv, banca));
            ladoOeste.add(new Solar("Sada", 13, Constantes.CASILLA_G3, grupo3, banca));
            ladoOeste.add(new Solar("Mera", 14, Constantes.CASILLA_G3, grupo3, banca));
            ladoOeste.add(new Transporte("Lancha Motora", 15, 0, grupoTrans, banca));
            ladoOeste.add(new Solar("Cedofeita", 16, Constantes.CASILLA_G4, grupo4, banca));
            ladoOeste.add(new Especial("Caixa-2", 17));
            ladoOeste.add(new Solar("Viveiro", 18, Constantes.CASILLA_G4, grupo4, banca));
            ladoOeste.add(new Solar("Ribadeo", 19, Constantes.CASILLA_G4, grupo4, banca));

            //Creamos o lado Norte
            casillas.add(ladoNorte);
            ladoNorte.add(new Especial("Parking", 20));
            ladoNorte.add(new Solar("Verin", 21, Constantes.CASILLA_G5, grupo5, banca));
            ladoNorte.add(new Especial("Sorte-2", 22));
            ladoNorte.add(new Solar("Cambados", 23, Constantes.CASILLA_G5, grupo5, banca));
            ladoNorte.add(new Solar("Tui", 24, Constantes.CASILLA_G5, grupo5, banca));
            ladoNorte.add(new Transporte("Iate", 25, 0, grupoTrans, banca));
            ladoNorte.add(new Solar("O-Caurel", 26, Constantes.CASILLA_G6, grupo6, banca));
            ladoNorte.add(new Solar("A-Guarda", 27, Constantes.CASILLA_G6, grupo6, banca));
            ladoNorte.add(new Servizo("EnsinoPublico", 28, 0, grupoErv, banca));
            ladoNorte.add(new Solar("Ourense", 29, Constantes.CASILLA_G6, grupo6, banca));

            //Creamos o lado Este
            casillas.add(ladoEste);
            ladoEste.add(new Especial("IrCarcere", 30));
            ladoEste.add(new Solar("Santiago", 31, Constantes.CASILLA_G7, grupo7, banca));
            ladoEste.add(new Solar("Sanxenxo", 32, Constantes.CASILLA_G7, grupo7, banca));
            ladoEste.add(new Especial("Caixa-3", 33));
            ladoEste.add(new Solar("Pontevedra", 34, Constantes.CASILLA_G7, grupo7, banca));
            ladoEste.add(new Transporte("Jet Privado", 35, 0, grupoTrans, banca));
            ladoEste.add(new Especial("Sorte-3", 36));
            ladoEste.add(new Solar("Vigo", 37, Constantes.CASILLA_G8, grupo8, banca));
            ladoEste.add(new Imposto("SubidaPension", 38));
            ladoEste.add(new Solar("A Corunha", 39, Constantes.CASILLA_G8, grupo8, banca));

            /*//Calculamos o valor das casillas de servizos e transportes.
            for (int i = 0; i < 4; i++) {
                obterCasilla(i, 5).setValor(Constantes.CASILLA_TRANSPORTES);
            }
            obterCasilla(1, 2).setValor(Constantes.CASILLA_SERVIZOS);
            obterCasilla(2, 8).setValor(Constantes.CASILLA_SERVIZOS);

            //Introducimos as propiedades no xogador banca
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    if (!(casillas.get(i).get(j).getTipo().equals("Caixa e Sorte") || casillas.get(i).get(j).getTipo().equals("Especial") || casillas.get(i).get(j).getTipo().equals("Impostos"))) {
                        banca.introducirPropiedade(casillas.get(i).get(j));
                    }
                }
            }*/

        } else {
            System.out.println("\nErro no constructor de taboeiro {Banca}.\n");
        }
    }

    //Getters e Setters
    public ArrayList<ArrayList<Casilla>> getCasillas() {
        return casillas;
    }

    public void setCasillas() {
    } //Carece de sentido nesta implementación

    public void imprimirTaboeiro() {
        String texto = "";
        // Espazos en branco que se usann para separar o lado oeste do este.
        String espazos = "                                                                                                                                                                                                               ";

        //Imprimimos o lado Norte mais a primeira casilla do lado este.
        for (int i = 0; i < casillas.get(2).size(); i++) {
            texto = texto + casillas.get(2).get(i).toString();
        }
        //Imprimimos a primeira casilla do lado este.
        texto = texto + casillas.get(3).get(0).toString();
        System.out.println(texto);

        //Imprimos o lado oeste + espazos + lado este.
        int j = casillas.get(1).size() - 1;
        for (int i = 1; i < casillas.get(3).size(); i++) {
            System.out.println(casillas.get(1).get(j).toString() + espazos + casillas.get(3).get(i).toString());
            j--;
        }
        //Imprimimos o lado sur mais a primeira casilla do lado oeste.
        texto = casillas.get(1).get(0).toString();
        for (int i = casillas.get(0).size(); i > 0; i--) {
            texto = texto + casillas.get(0).get(i - 1).toString();
        }
        System.out.println(texto);
    }

    //Metodos para obter casillas.
    public Casilla getCasilla(String nombre) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (casillas.get(i).get(j).getNome().equals(nombre)) {
                    return casillas.get(i).get(j);
                }
            }
        }
        return null;
    }

    public Casilla getCasilla(int posicion) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (casillas.get(i).get(j).getPosicion() == posicion) {
                    return casillas.get(i).get(j);
                }
            }
        }
        return null;
    }

    public boolean existeCasilla(String nomeCasilla) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (casillas.get(i).get(j).getNome().equals(nomeCasilla)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Casilla obterCasilla(int lado, int posicion) {
        return this.casillas.get(lado).get(posicion);
    }

    /*public boolean casillaComprable(Casilla casilla) {
        return (casilla.getTipo().equals("Solar")) || (casilla.getTipo().equals("Transporte")) || (casilla.getTipo().equals("Servizo"));
    }*/
}
