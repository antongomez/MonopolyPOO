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
            Grupo grupo0 = new Grupo(0); //Especiais
            Grupo grupo9 = new Grupo(9); //Caixa e Sorte
            Grupo grupo10 = new Grupo(10); //Impostos
            Grupo grupo11 = new Grupo(11); //Servizos
            Grupo grupo12 = new Grupo(12); //Transportes

            //Creamos o lado Sur
            casillas.add(ladoSur);
            ladoSur.add(new Casilla("Saida", "Especial", 0, grupo0));
            ladoSur.add(new Casilla("Santa-Cruz", "Solar", 1, Constantes.CASILLA_GRUPO1, grupo1, banca, Constantes.ACASILLA_GRUPO1));
            ladoSur.add(new Casilla("Caixa-1", "Caixa e Sorte", 2, grupo9));
            ladoSur.add(new Casilla("Arteixo", "Solar", 3, Constantes.CASILLA_GRUPO1, grupo1, banca, Constantes.ACASILLA_GRUPO1));
            ladoSur.add(new Casilla("IRPF", "Impostos", 4, grupo10));
            ladoSur.add(new Casilla("Autobus", "Transporte", 5, 0, grupo12, banca));
            ladoSur.add(new Casilla("Meanho", "Solar", 6, Constantes.CASILLA_GRUPO2, grupo2, banca, Constantes.ACASILLA_GRUPO2));
            ladoSur.add(new Casilla("Sorte-1", "Caixa e Sorte", 7, grupo9));
            ladoSur.add(new Casilla("Corrubedo", "Solar", 8, Constantes.CASILLA_GRUPO2, grupo2, banca, Constantes.ACASILLA_GRUPO2));
            ladoSur.add(new Casilla("Lugo", "Solar", 9, Constantes.CASILLA_GRUPO2, grupo2, banca, Constantes.ACASILLA_GRUPO2));

            //Creamos o lado Oeste
            casillas.add(ladoOeste);
            ladoOeste.add(new Casilla("Carcere", "Especial", 10, grupo0));
            ladoOeste.add(new Casilla("O-Carballinho", "Solar", 11, Constantes.CASILLA_GRUPO3, grupo3, banca, Constantes.ACASILLA_GRUPO3));
            ladoOeste.add(new Casilla("SERGAS", "Servizo", 12, 0, grupo11, banca));
            ladoOeste.add(new Casilla("Sada", "Solar", 13, Constantes.CASILLA_GRUPO3, grupo3, banca, Constantes.ACASILLA_GRUPO3));
            ladoOeste.add(new Casilla("Mera", "Solar", 14, Constantes.CASILLA_GRUPO3, grupo3, banca, Constantes.ACASILLA_GRUPO3));
            ladoOeste.add(new Casilla("Lancha Motora", "Transporte", 15, 0, grupo12, banca));
            ladoOeste.add(new Casilla("Cedofeita", "Solar", 16, Constantes.CASILLA_GRUPO4, grupo4, banca, Constantes.ACASILLA_GRUPO4));
            ladoOeste.add(new Casilla("Caixa-2", "Caixa e Sorte", 17, grupo9));
            ladoOeste.add(new Casilla("Viveiro", "Solar", 18, Constantes.CASILLA_GRUPO4, grupo4, banca, Constantes.ACASILLA_GRUPO4));
            ladoOeste.add(new Casilla("Ribadeo", "Solar", 19, Constantes.CASILLA_GRUPO4, grupo4, banca, Constantes.ACASILLA_GRUPO4));

            //Creamos o lado Norte
            casillas.add(ladoNorte);
            ladoNorte.add(new Casilla("Parking", "Especial", 20, grupo0));
            ladoNorte.add(new Casilla("Verin", "Solar", 21, Constantes.CASILLA_GRUPO5, grupo5, banca, Constantes.ACASILLA_GRUPO5));
            ladoNorte.add(new Casilla("Sorte-2", "Caixa e Sorte", 22, grupo9));
            ladoNorte.add(new Casilla("Cambados", "Solar", 23, Constantes.CASILLA_GRUPO5, grupo5, banca, Constantes.ACASILLA_GRUPO5));
            ladoNorte.add(new Casilla("Tui", "Solar", 24, Constantes.CASILLA_GRUPO5, grupo5, banca, Constantes.ACASILLA_GRUPO5));
            ladoNorte.add(new Casilla("Iate", "Transporte", 25, 0, grupo12, banca));
            ladoNorte.add(new Casilla("O-Caurel", "Solar", 26, Constantes.CASILLA_GRUPO6, grupo6, banca, Constantes.ACASILLA_GRUPO6));
            ladoNorte.add(new Casilla("A-Guarda", "Solar", 27, Constantes.CASILLA_GRUPO6, grupo6, banca, Constantes.ACASILLA_GRUPO6));
            ladoNorte.add(new Casilla("EnsinoPublico", "Servizo", 28, 0, grupo11, banca));
            ladoNorte.add(new Casilla("Ourense", "Solar", 29, Constantes.CASILLA_GRUPO6, grupo6, banca, Constantes.ACASILLA_GRUPO6));

            //Creamos o lado Este
            casillas.add(ladoEste);
            ladoEste.add(new Casilla("IrCarcere", "Especial", 30, grupo0));
            ladoEste.add(new Casilla("Santiago", "Solar", 31, Constantes.CASILLA_GRUPO7, grupo7, banca, Constantes.ACASILLA_GRUPO7));
            ladoEste.add(new Casilla("Sanxenxo", "Solar", 32, Constantes.CASILLA_GRUPO7, grupo7, banca, Constantes.ACASILLA_GRUPO7));
            ladoEste.add(new Casilla("Caixa-3", "Caixa e Sorte", 33, grupo9));
            ladoEste.add(new Casilla("Pontevedra", "Solar", 34, Constantes.CASILLA_GRUPO7, grupo7, banca, Constantes.ACASILLA_GRUPO7));
            ladoEste.add(new Casilla("Jet Privado", "Transporte", 35, 0, grupo12, banca));
            ladoEste.add(new Casilla("Sorte-3", "Caixa e Sorte", 36, grupo9));
            ladoEste.add(new Casilla("Vigo", "Solar", 37, Constantes.CASILLA_GRUPO8, grupo8, banca, Constantes.ACASILLA_GRUPO8));
            ladoEste.add(new Casilla("SubidaPension", "Impostos", 38, grupo10));
            ladoEste.add(new Casilla("A Corunha", "Solar", 39, Constantes.CASILLA_GRUPO8, grupo8, banca, Constantes.ACASILLA_GRUPO8));

            //Calculamos o valor das casillas de servizos e transportes.
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
            }

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

    public void imprimirTaboeiro(int nJugadores) {
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

    public boolean casillaComprable(Casilla casilla) {
        return (casilla.getTipo().equals("Solar")) || (casilla.getTipo().equals("Transporte")) || (casilla.getTipo().equals("Servizo"));
    }
}
