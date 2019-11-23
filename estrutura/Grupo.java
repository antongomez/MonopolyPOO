package estrutura;

import java.util.ArrayList;
import java.util.HashMap;

//import java.util.ArrayList;
public class Grupo extends Casilla {

    private int id;
    private HashMap<String, Propiedade> propiedades;
    private String colorear;
    private int maxConstructions;

    public Grupo() {
    }

    public Grupo(String nome, int id, String cor) {
        super(nome);
        this.id = id;
        switch (id) {
            case 1:
                this.id = id;
                this.colorear = Constantes.LARANXA;
                this.propiedades = new HashMap<>();
                this.maxConstructions=2;
                break;

            case 2:
                this.id = id;
                this.colorear = Constantes.CIAN;
                this.maxConstructions=3;
                break;

            case 3:
                this.id = id;
                this.colorear = Constantes.ROSA;
                this.maxConstructions=3;
                break;

            case 4:
                this.id = id;
                this.colorear = Constantes.GRIS;
                this.maxConstructions=3;
                break;

            case 5:
                this.id = id;
                this.colorear = Constantes.VERMELLO;
                this.maxConstructions=3;
                break;

            case 6:
                this.id = id;
                this.colorear = Constantes.AMARELO;
                this.maxConstructions=3;
                break;

            case 7:
                this.id = id;
                this.colorear = Constantes.VERDE;
                this.maxConstructions=3;
                break;

            case 8:
                this.id = id;
                this.colorear = Constantes.AZUL;
                this.maxConstructions=2;
                this.maxConstructions=3;
                break;

            default:
                this.id = id;
                this.colorear = Constantes.NEGRO;
                this.maxConstructions=0;
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

    public int getMaxConstructions() {return this.maxConstructions;}
    //Setter non funcional
    public void setMaxConstructions() {return;}

    public HashMap<String, Propiedade> getPropiedades() {
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

    public boolean existeMonopolio() {
        switch (id) {
            case 1:
            case 8:
                if (propiedades.size() == 2) {
                    return true;
                }
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                if (propiedades.size() == 3) {
                    return true;
                }
                break;

        }
        return false;
    }
    //Fin Clase
}
