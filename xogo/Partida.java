package xogo;

import xogadores.Avatar;
import xogadores.Xogador;
import java.util.ArrayList;
import java.util.HashMap;

public class Partida {

    //Atributos
    private ArrayList<Xogador> xogadores;
    private ArrayList<Avatar> avatares;
    private Xogador banca;
    private int turno;
    private int nXogadores;

    //Constructores
    public Partida() {
        this.xogadores = new ArrayList<>();
        this.avatares = new ArrayList<>();
        this.turno = 1;
    }

    public Partida(ArrayList<Xogador> xogadores, ArrayList<Avatar> avatares) {
        if ((xogadores != null) && (avatares != null)) {
            this.xogadores = xogadores;
            this.avatares = avatares;
        } else {
            System.out.println("Erro no construtor de partida");
        }
    }

    public Partida(int nXogadores) {
        this.xogadores = new ArrayList<>(nXogadores);
        this.avatares = new ArrayList<>(nXogadores);
        this.turno = 1;
        this.nXogadores = nXogadores;//Facilitar xestión turno.
    }

    //Getters e Setters
    public ArrayList<Xogador> getXogadores() {
        return this.xogadores;
    }

    public void setXogadores() {
    } //Carece de sentido nesta implementación

    public ArrayList<Avatar> getAvatares() {
        return this.avatares;
    }

    public void setAvatares() {
    } //Carece de sentido nesta implementación

    public void setAvatar(Avatar avatar) {
        if (avatar != null) {
            avatares.add(avatar);
        } else {
            System.out.println("Erro en setAvatar");
        }
    }

    public Avatar getAvatar(int posicion) {
        return avatares.get(posicion);
    }

    public Xogador getXogador(int posicion) {
        return xogadores.get(posicion);
    }

    public void setXogador(Xogador xogador) {
        if (xogador != null) {
            xogadores.add(xogador);
        } else {
            System.out.println("Erro en setXogador.");
        }
    }

    public Xogador getBanca() {
        return banca;
    }

    public void setBanca(Xogador banca) {
        if (banca != null) {
            this.banca = banca;
        } else {
            System.out.println("Erro en setBanca");
        }
    }

    public int getnXogadores() {
        return this.nXogadores;
    }

    public void setnXogadores(int numero) {
        this.nXogadores = numero;
    }

    public int getTurno() {
        return turno;// % nXogadores;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void seguinteTurno() {
        turno++;
    }

    //Funcion que calcula o turno seguinte.
    public void calcularTurnoSeguinte() {
        if (turno == xogadores.size()) {
            turno = 1;
        } else {
            turno++;
        }
    }
}