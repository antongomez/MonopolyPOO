package estrutura;

import java.util.ArrayList;

//import java.util.ArrayList;
public class Grupo extends Casilla {

    private int id;
    private ArrayList<Propiedade> propiedades;
    private String colorear;

    public Grupo() {
    }

    public Grupo(String nome, int id, String cor) {
        super(nome);
        this.id = id;
        switch (id) {
            case 1:
                this.id = id;
                this.colorear = Constantes.LARANXA;
                this.propiedades = new ArrayList<>();
                break;

            case 2:
                this.id = id;
                this.colorear = Constantes.CIAN;
                break;

            case 3:
                this.id = id;
                this.colorear = Constantes.ROSA;
                break;

            case 4:
                this.id = id;
                this.colorear = Constantes.GRIS;
                break;

            case 5:
                this.id = id;
                this.colorear = Constantes.VERMELLO;
                break;

            case 6:
                this.id = id;
                this.colorear = Constantes.AMARELO;
                break;

            case 7:
                this.id = id;
                this.colorear = Constantes.VERDE;
                break;

            case 8:
                this.id = id;
                this.colorear = Constantes.AZUL;
                break;

            default:
                this.id = id;
                this.colorear = Constantes.NEGRO;
                break;

        }
    }

    //Getters e Setters
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

    public ArrayList<Propiedade> getPropiedades() {
        return this.propiedades;
    }

    public String getColorear() {
        return this.colorear;
    }

    public void setColorear(String colorear) {
        if (colorear != null) {
            this.colorear = colorear;
        } else {
            System.out.println("Erro en setColor.\n");
        }
    }

    //Metodos
    public int getNCasas() {
        return 0;
    }
}
