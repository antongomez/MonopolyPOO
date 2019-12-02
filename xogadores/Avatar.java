package xogadores;
import estrutura.Casilla;
import estrutura.Taboleiro;
import java.util.ArrayList;
import java.util.Random;


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
