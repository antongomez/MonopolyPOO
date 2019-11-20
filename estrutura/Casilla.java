package estrutura;

import xogadores.*;
import java.util.ArrayList;

public abstract class Casilla {

    //Atributos
    private String nome;
    
    private ArrayList<Avatar> avatares;
    private int frecuentada;

    //Constructor
    public Casilla() {
    }

    public Casilla(String nome) {
        this.nome = nome;
        this.avatares = new ArrayList<>();
        this.frecuentada = 0;
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

    public int getFrecuentada() {
        return this.frecuentada;
    }

    public void setFrecuentada(int num) {
        this.frecuentada = this.frecuentada + num;
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

    /*@Override
    public String toString() {
        String nomeaux = String.format("%-13s", this.nome);
        String avataresCasilla = String.format("%6s", imprimirAvatares());
        String texto = "| " + nomeaux + " " + avataresCasilla + "|";
        return texto;
    }*/
}
