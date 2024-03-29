package estrutura;

import xogadores.*;
import xogo.*;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Casilla {

    //Atributos
    private String nome;
    private ArrayList<Avatar> avatares;
    private int posicion;
    //ArrayList que conten as veces que o avatar i visitou a casilla
    private ArrayList<Integer> frecuentada;
    private ImageIcon imaxedescrip;

    //Constructor
    public Casilla() {
    }

    public Casilla(String nome, int posicion) {
        this.nome = nome;
        this.posicion = posicion;
        this.avatares = new ArrayList<>();
        this.frecuentada = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            frecuentada.add(0);
        }
    }

    //Getters y setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        } else {
            System.out.println("Erro en setNome.");
        }
    }

    public ArrayList<Integer> getFrecuentada() {
        return this.frecuentada;
    }

    public void setFrecuentada(ArrayList<Integer> frecuencias) {
        this.frecuentada = frecuencias;
    }

    public int getPosicion() {
        return this.posicion;
    }

    public void setPosicion(int posicion) {
        if ((posicion >= 0) && (posicion < 40)) {
            this.posicion = posicion;
        }
    }

    public ImageIcon getImaxedescrip() {
        return imaxedescrip;
    }

    public void setImaxedescrip(ImageIcon imaxe) {
        this.imaxedescrip = imaxe;
    }

    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }

    public void engadirAvatar(Avatar avatar) {

        if (avatar != null) {
            this.avatares.add(avatar);

        } else {
            System.out.println("Erro en engadirAvatar.\n");
        }
    }

    public void eliminarAvatar(Avatar avatar) {

        if (avatar != null) {
            this.avatares.remove(avatar);
        } else {
            System.out.println("Erro en eliminarAvatar.\n");
        }
    }

    //Metodos
    public boolean estarAvatar(Avatar avatar) {
        for (Avatar av : avatares) {
            if (av.equals(avatar)) {
                return true;
            }
        }
        return false;
    }

    public int frecuenciaVisita() {
        int sumaFrecuencia = 0;
        if (!frecuentada.isEmpty()) {
            for (int i = 0; i < frecuentada.size(); i++) {
                sumaFrecuencia += frecuentada.get(i);
            }
        }
        return sumaFrecuencia;
    }

    public int frecuenciaVisita(int posicion) {
        return frecuentada.get(posicion);
    }

    public void sumarFrecuenciaVisita(int posicion) {
        this.frecuentada.set(posicion, this.frecuentada.get(posicion) + 1);
    }

    public String imprimirAvatares() {
        String avatarescasilla = " ";
        if (!this.avatares.isEmpty()) {
            avatarescasilla = "&";
            for (int i = 0; i < this.avatares.size(); i++) {
                avatarescasilla = avatarescasilla + this.avatares.get(i).getId();
            }
        } else {
        }
        return avatarescasilla;
    }

    public abstract String imprimirCasilla();

    @Override
    public String toString() {
        String nomeaux = String.format("%-15s", this.getNome());
        String avatares = String.format("%6s", imprimirAvatares());
        String texto = "| " + nomeaux + " " + avatares + "|";
        return texto;
    }

    @Override
    public boolean equals(Object casilla) {
        if (!(casilla instanceof Casilla)) {
            return false;
        }
        return (this.nome.equals(((Casilla) casilla).getNome()));
    }

}
