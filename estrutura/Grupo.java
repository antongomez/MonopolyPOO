package estrutura;

import java.util.ArrayList;

//import java.util.ArrayList;
public class Grupo extends Casilla {

    private int id;
    private ArrayList<Propiedade> propiedades;
    private String colorear;
    private String cor;
    private int maxconstrucions;
    private int casas;
    private int hoteis;
    private int piscinas;
    private int pistas;

    public Grupo() {
    }

    public Grupo(String nome, float valor, int id, String cor) {
        super(nome, valor);
        this.id = id;
        switch (id) {
            case 1:
                this.id = id;
                this.colorear = Constantes.LARANXA;
                this.cor = "Laranxa";

                maxconstrucions = Constantes.G1_MAX_CONS;
                this.casas = 0;
                this.hoteis = 0;
                this.piscinas = 0;

                this.pistas = 0;
                break;
            case 2:
                this.id = id;
                this.colorear = Constantes.CIAN;
                this.cor = "Cian";

                maxconstrucions = Constantes.G2_MAX_CONS;
                this.casas = 0;
                this.hoteis = 0;
                this.piscinas = 0;
                this.pistas = 0;

                break;
            case 3:
                this.id = id;
                this.colorear = Constantes.ROSA;
                this.cor = "Rosa";

                maxconstrucions = Constantes.G3_MAX_CONS;
                this.casas = 0;
                this.hoteis = 0;
                this.piscinas = 0;
                this.pistas = 0;

                break;
            case 4:
                this.id = id;
                this.colorear = Constantes.GRIS;
                this.cor = "Gris";

                maxconstrucions = Constantes.G4_MAX_CONS;
                this.casas = 0;
                this.hoteis = 0;
                this.piscinas = 0;
                this.pistas = 0;

                break;
            case 5:
                this.id = id;
                this.colorear = Constantes.VERMELLO;
                this.cor = "Vermello";

                maxconstrucions = Constantes.G5_MAX_CONS;
                this.casas = 0;
                this.hoteis = 0;
                this.piscinas = 0;
                this.pistas = 0;

                break;
            case 6:
                this.id = id;
                this.colorear = Constantes.AMARELO;
                this.cor = "Amarelo";

                maxconstrucions = Constantes.G6_MAX_CONS;
                this.casas = 0;
                this.hoteis = 0;
                this.piscinas = 0;
                this.pistas = 0;

                break;
            case 7:
                this.id = id;
                this.colorear = Constantes.VERDE;
                this.cor = "Verde";

                maxconstrucions = Constantes.G7_MAX_CONS;
                this.casas = 0;
                this.hoteis = 0;
                this.piscinas = 0;
                this.pistas = 0;

                break;
            case 8:
                this.id = id;
                this.colorear = Constantes.AZUL;
                this.cor = "Azul";

                maxconstrucions = Constantes.G8_MAX_CONS;
                this.casas = 0;
                this.hoteis = 0;
                this.piscinas = 0;
                this.pistas = 0;

                break;
            default:
                this.id = id;
                this.colorear = Constantes.NEGRO;

                break;
        }
    }

    public Grupo(int id) {
        this.id = id;
        this.colorear = Constantes.NEGRO;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String Cor) {
        this.cor = Cor;
    }

    public String getColorear() {
        return this.colorear;
    }

    public void setColorear(String colorear) {
        if (colorear != null) {
            this.colorear = colorear;
        } else {
            System.out.println("Error en setColor.");
        }
    }

    public int getMaxconstrucions() {
        return maxconstrucions;
    }

    public void setMaxconstrucions(int cons) {
        if ((cons == 2) || (cons == 3)) {
            this.maxconstrucions = cons;
        } else {
            System.out.println("\nErro en setMaxCons.\n");
        }
    }

    public int getCasas() {
        return casas;
    }

    public void setCasas(int cons) {
        int MaxEdificios = 0;
        if ((id == 1) || (id == 8)) {
            MaxEdificios = Constantes.MAXCONS_2;
        } else if ((id == 2) || (id == 3) || (id == 4) || (id == 5)
                || (id == 6) || (id == 7)) {
            MaxEdificios = Constantes.MAXCONS_3;
        }
        if ((cons + hoteis * 4 < MaxEdificios) && (cons < 12)) {
            casas = cons;
        }
    }

    public void sumarCasa() {
        int MaxEdificios = 0;
        if ((id == 1) || (id == 8)) {
            MaxEdificios = Constantes.MAXCONS_2;
        } else if ((id == 2) || (id == 3) || (id == 4) || (id == 5)
                || (id == 6) || (id == 7)) {
            MaxEdificios = Constantes.MAXCONS_3;
        }
        if ((casas + hoteis * 4 < MaxEdificios) && (casas < 12)) {
            casas++;
        }
    }

    public int getHoteis() {
        return hoteis;
    }

    public void setHoteis(int cons) { //Limitar
        this.hoteis = cons;
    }

    public int getPiscinas() {
        return piscinas;
    }

    public void setPiscinas(int cons) {
        this.piscinas = cons;
    }

    public int getPistas() {
        return pistas;
    }

    public void setPistas(int cons) {
        this.pistas = cons;
    }
}
