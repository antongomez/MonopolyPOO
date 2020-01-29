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
            Grupo grupo1 = new Grupo("Marron", 1, Constantes.GRIS);
            Grupo grupo2 = new Grupo("Cian", 2, Constantes.CIAN);
            Grupo grupo3 = new Grupo("Rosa", 3, Constantes.ROSA);
            Grupo grupo4 = new Grupo("Laranxa", 4, Constantes.LARANXA);
            Grupo grupo5 = new Grupo("Vermello", 5, Constantes.VERMELLO);
            Grupo grupo6 = new Grupo("Amarelo", 6, Constantes.AMARELO);
            Grupo grupo7 = new Grupo("Verde", 7, Constantes.VERDE);
            Grupo grupo8 = new Grupo("Azul", 8, Constantes.AZUL);

            //Creacion do resto dos grupos
            Grupo grupoServ = new Grupo("Servizos", 9);
            Grupo grupoTrans = new Grupo("Transportes", 10);

            //Creamos o lado Sur
            casillas.add(ladoSur);
            ladoSur.add(new Especial("Saida", 0));
            ladoSur.add(new Solar("Santa Cruz", 1, Constantes.CASILLA_G1, grupo1, banca));
            ladoSur.add(new Especial("Caixa 1", 2));
            ladoSur.add(new Solar("Arteixo", 3, Constantes.CASILLA_G1, grupo1, banca));
            ladoSur.add(new Imposto("IRPF", 4, Constantes.IMPOSTO1));
            ladoSur.add(new Transporte("Autobus", 5, grupoTrans, banca));
            ladoSur.add(new Solar("Meanho", 6, Constantes.CASILLA_G2, grupo2, banca));
            ladoSur.add(new Especial("Sorte 1", 7));
            ladoSur.add(new Solar("Corrubedo", 8, Constantes.CASILLA_G2, grupo2, banca));
            ladoSur.add(new Solar("Lugo", 9, Constantes.CASILLA_G2, grupo2, banca));

            //Creamos o lado Oeste
            casillas.add(ladoOeste);
            ladoOeste.add(new Especial("Carcere", 10));
            ladoOeste.add(new Solar("O Carballinho", 11, Constantes.CASILLA_G3, grupo3, banca));
            ladoOeste.add(new Servizo("SERGAS", 12, grupoServ, banca));
            ladoOeste.add(new Solar("Sada", 13, Constantes.CASILLA_G3, grupo3, banca));
            ladoOeste.add(new Solar("Mera", 14, Constantes.CASILLA_G3, grupo3, banca));
            ladoOeste.add(new Transporte("Lancha Motora", 15, grupoTrans, banca));
            ladoOeste.add(new Solar("Negreira", 16, Constantes.CASILLA_G4, grupo4, banca));
            ladoOeste.add(new Especial("Caixa 2", 17));
            ladoOeste.add(new Solar("Viveiro", 18, Constantes.CASILLA_G4, grupo4, banca));
            ladoOeste.add(new Solar("Ribadeo", 19, Constantes.CASILLA_G4, grupo4, banca));

            //Creamos o lado Norte
            casillas.add(ladoNorte);
            ladoNorte.add(new Especial("Parking", 20));
            ladoNorte.add(new Solar("Verin", 21, Constantes.CASILLA_G5, grupo5, banca));
            ladoNorte.add(new Especial("Sorte 2", 22));
            ladoNorte.add(new Solar("Cambados", 23, Constantes.CASILLA_G5, grupo5, banca));
            ladoNorte.add(new Solar("Tui", 24, Constantes.CASILLA_G5, grupo5, banca));
            ladoNorte.add(new Transporte("Iate", 25, grupoTrans, banca));
            ladoNorte.add(new Solar("O Caurel", 26, Constantes.CASILLA_G6, grupo6, banca));
            ladoNorte.add(new Solar("A Guarda", 27, Constantes.CASILLA_G6, grupo6, banca));
            ladoNorte.add(new Servizo("Ensino Publico", 28, grupoServ, banca));
            ladoNorte.add(new Solar("Ourense", 29, Constantes.CASILLA_G6, grupo6, banca));

            //Creamos o lado Este
            casillas.add(ladoEste);
            ladoEste.add(new Especial("IrCarcere", 30));
            ladoEste.add(new Solar("Santiago", 31, Constantes.CASILLA_G7, grupo7, banca));
            ladoEste.add(new Solar("Sanxenxo", 32, Constantes.CASILLA_G7, grupo7, banca));
            ladoEste.add(new Especial("Caixa 3", 33));
            ladoEste.add(new Solar("Pontevedra", 34, Constantes.CASILLA_G7, grupo7, banca));
            ladoEste.add(new Transporte("Jet Privado", 35, grupoTrans, banca));
            ladoEste.add(new Especial("Sorte 3", 36));
            ladoEste.add(new Solar("Vigo", 37, Constantes.CASILLA_G8, grupo8, banca));
            ladoEste.add(new Imposto("Subida Pension", 38, Constantes.IMPOSTO2));
            ladoEste.add(new Solar("A Corunha", 39, Constantes.CASILLA_G8, grupo8, banca));

            grupo1.engadirPropiedade((Propiedade) getCasilla("Santa Cruz"));
            grupo1.engadirPropiedade((Propiedade) getCasilla("Arteixo"));

            grupo2.engadirPropiedade((Propiedade) getCasilla("Meanho"));
            grupo2.engadirPropiedade((Propiedade) getCasilla("Corrubedo"));
            grupo2.engadirPropiedade((Propiedade) getCasilla("Lugo"));

            grupo3.engadirPropiedade((Propiedade) getCasilla("O Carballinho"));
            grupo3.engadirPropiedade((Propiedade) getCasilla("Sada"));
            grupo3.engadirPropiedade((Propiedade) getCasilla("Mera"));

            grupo4.engadirPropiedade((Propiedade) getCasilla("Negreira"));
            grupo4.engadirPropiedade((Propiedade) getCasilla("Viveiro"));
            grupo4.engadirPropiedade((Propiedade) getCasilla("Ribadeo"));

            grupo5.engadirPropiedade((Propiedade) getCasilla("Verin"));
            grupo5.engadirPropiedade((Propiedade) getCasilla("Cambados"));
            grupo5.engadirPropiedade((Propiedade) getCasilla("Tui"));

            grupo6.engadirPropiedade((Propiedade) getCasilla("O Caurel"));
            grupo6.engadirPropiedade((Propiedade) getCasilla("A Guarda"));
            grupo6.engadirPropiedade((Propiedade) getCasilla("Ourense"));

            grupo7.engadirPropiedade((Propiedade) getCasilla("Santiago"));
            grupo7.engadirPropiedade((Propiedade) getCasilla("Sanxenxo"));
            grupo7.engadirPropiedade((Propiedade) getCasilla("Pontevedra"));

            grupo8.engadirPropiedade((Propiedade) getCasilla("Vigo"));
            grupo8.engadirPropiedade((Propiedade) getCasilla("A Corunha"));

            grupoServ.engadirPropiedade((Propiedade) getCasilla("SERGAS"));
            grupoServ.engadirPropiedade((Propiedade) getCasilla("Ensino Publico"));

            grupoTrans.engadirPropiedade((Propiedade) getCasilla("Autobus"));
            grupoTrans.engadirPropiedade((Propiedade) getCasilla("Lancha Motora"));
            grupoTrans.engadirPropiedade((Propiedade) getCasilla("Iate"));
            grupoTrans.engadirPropiedade((Propiedade) getCasilla("Jet Privado"));

        } else {
            System.out.println("\nErro no constructor de taboeiro {Banca}.\n");
        }
    }

    //Getters e Setters
    public ArrayList<Casilla> getCasillas() {
        ArrayList<Casilla> ArrayCasillas;
        ArrayCasillas = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ArrayCasillas.add(getCasilla(i));
        }

        return ArrayCasillas;
    }

    public void imprimirTaboleiro() {
        String texto = "";
        // Espazos en branco que se usann para separar o lado oeste do este.
        String espazos = "                                                                                                                                                                                                                                 ";

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
    //Fin clase
}
