package estrutura;

import java.util.ArrayList;

public class Grupo extends Casilla {

    private int id;
    private ArrayList<Propiedade> propiedades;
    private String colorear;

    public Grupo() {
    }

    public Grupo(String nome, int id, String colorear) {
        super(nome, 0);
        this.id = id;
        this.colorear = colorear;
        this.propiedades = new ArrayList<>();
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
        this.colorear = colorear;
    }

    //Metodos
    public void engadirPropiedade(Propiedade propiedade) {
        this.propiedades.add(propiedade);
    }

    public void eliminarPropiedade(Propiedade propiedade) {
        this.propiedades.remove(propiedade);
    }

    public int getNSolares() {
        return this.propiedades.size();
    }

    /*Quero ter unha serie de funcions que me calculen se podo construir nun grupo*/
 /*Quero ter unha funcion que me calcule o numero de casas no grupo*/
    public int getNCasas() {

        return 0;
    }

    /*Funcion que determina se se pode construir unha casa*/
    public void poderConstruirCasa() {

    }

    /*Funcion que devolve true se un mesmo xogador posee 
    todas as propiedades do grupo*/
    //Falta por implementar
    public boolean existeMonopolio() {

        return false;
    }
    //Fin Clase
}
