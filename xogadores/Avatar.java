package xogadores;

import estrutura.Casilla;
import estrutura.Taboleiro;

import java.util.ArrayList;
import java.util.Random;
/*
public class Avatar {

    //Atributos
    private char id;
    private String tipo;
    private Xogador xogador;
    private Casilla posicion;
    private String modo;
    private int bloqueado;
    private boolean arrancado;

    //Constructor
    public Avatar() {
    }

    public Avatar(String tipo, Xogador xogador, Taboleiro taboleiro) {
        if ((tipo != null) && (xogador != null) && (taboleiro != null)) {
            tipo = tipo.toLowerCase();
            if (tipo.equals("sombreiro") || tipo.equals("esfinxe")
                    || tipo.equals("coche") || tipo.equals("pelota")) {
                this.tipo = tipo;
            } else {
                this.tipo = "coche";
                System.out.println("\nTipo de avatar incorrecto. Introduciuse por defecto coche.\n");
            }

            this.xogador = xogador;
            this.xerarId();
            this.posicion = taboleiro.getCasilla(0);
            this.modo = "normal";
            this.bloqueado = 0;
            this.arrancado = false;
            taboleiro.getCasilla(0).engadirAvatar(this);

        } else {
            System.out.println("Produciuse un erro no constructor de avatar.\n");
        }
    }

    //Método que xera un id aleatorio para o avatar.
    public void xerarId() {

        Random ale = new Random(System.currentTimeMillis());
        int numero = ale.nextInt(20) + 65;
        char nombre = (char) numero;
        id = nombre;

    }

    //Getters y Setters
    public char getId() {
        return id;
    }

    public void setId(char letra) {
        this.id = letra;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        if (tipo != null) {
            this.tipo = tipo;
        } else {
            System.out.println("Erro en setTipo.\n");
        }
    }

    public boolean getArrancado() {
        return arrancado;
    }

    public void setArrancado(boolean arrancado) {
        this.arrancado = arrancado;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int turnos) {
        if (turnos >= 0) {
            this.bloqueado = turnos;
        }
    }

    public void restarTurnoBloqueo() {
        if (bloqueado > 0) {
            bloqueado--;
        }
    }

    public Xogador getXogador() {
        return xogador;
    }

    public void setXogador(Xogador xogador) {
        if (xogador != null) {
            this.xogador = xogador;
        } else {
            System.out.println("Erro en setXogador.");
        }
    }

    public Casilla getPosicion() {
        return posicion;
    }

    public void setPosicion(Casilla posicion) {
        if (posicion != null) {
            posicion.setAvatares();
            this.posicion = posicion;
        } else {
            System.out.println("ERRO en setPosicion.\n");
        }
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        if (modo != null) {
            if ((modo.toLowerCase().equals("normal")) || (modo.equals("avanzado"))) {
                this.modo = modo.toLowerCase();
            } else {
                System.out.println("Modo de movemento incorrecto. O avatar "
                        + id + " moverase en modo Normal.\n");
                this.modo = "Normal";
            }
        } else {
            System.out.println("ERRO en setModo.\n");
        }

    }

    //Métodos
    @Override
    public String toString() {
        String texto = "{ \n" + "\tid: " + this.id
                + "\n" + "\ttipo: " + this.tipo
                + "\n" + "\tXogador: " + this.xogador.getNome()
                + "\n" + "}";
        return texto;
    }
}*/


public abstract class Avatar{
    private char id;
    String tipo;
    private Xogador xogador;
    private Casilla posicion;
    private String modo;
    private int bloqueado;
    private boolean arrancado;

    public Avatar(ArrayList<Avatar> avatares, Xogador xogador, Taboleiro taboleiro)
    {
        this.id=xerarID(avatares);
        this.xogador=xogador;
        this.posicion = taboleiro.getCasilla(0);
        this.modo = "normal";
        this.bloqueado = 0;
        this.arrancado = false;
        taboleiro.getCasilla(0).engadirAvatar(this);
    }

    //Getters y Setters
    public char getId() {
        return id;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int turnos) {
        if (turnos >= 0) {
            this.bloqueado = turnos;
        }
    }

    public void restarTurnoBloqueo() {
        if (bloqueado > 0) {
            bloqueado--;
        }
    }

    public Xogador getXogador() {
        return xogador;
    }

    public void setXogador(Xogador xogador) {
        if (xogador != null) {
            this.xogador = xogador;
        } else {
            System.out.println("Erro en setXogador.");
        }
    }

    public Casilla getPosicion() {
        return posicion;
    }

    public void setPosicion(Casilla posicion) {
        if (posicion != null) {
            posicion.engadirAvatar(this);
            this.posicion = posicion;
        } else {
            System.out.println("ERRO en setPosicion.\n");
        }
    }


    public abstract void moverEnBasico();
    public abstract void moverEnAvanzado();

    public char xerarID(ArrayList<Avatar> avatares)
    {
        char id;
        boolean distinto;
        int numero;
        do {
            Random ale = new Random(System.currentTimeMillis());
            numero = ale.nextInt(20) + 65;
            id = (char) numero;
            distinto=true;

            for (int i=0; i < avatares.size(); i++)
            {
                if (id == avatares.get(i).id)
                {
                    distinto=false;
                }
            }

        }while (!distinto);
        return id;
    }






    @Override
    public String toString() {
        String texto = "{ \n" + "\tid: " + this.id
                + "\n" + "\ttipo: " + this.tipo
                + "\n" + "\tXogador: " + this.xogador.getNome()
                + "\n" + "}";
        return texto;
    }
}
