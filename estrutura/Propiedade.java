package estrutura;

import xogadores.*;

public abstract class Propiedade extends Casilla {

    private int posicion;
    private float valor;
    private float alquiler;
    private Xogador dono;
    private Grupo grupo;
    private float cartosProducidos;

    public Propiedade() {
    }

    public Propiedade(String nome, int posicion, float valor) {
        super(nome);
        this.posicion = posicion;
        this.valor = valor;
    }

    public int getPosicion() {
        return this.posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getAlquiler() {
        return this.alquiler;
    }

    public void setAlquiler(float alquiler) {
        this.alquiler = alquiler;
    }

    public Grupo getGrupo() {
        return this.grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Xogador getXogador() {
        return this.dono;
    }

    public void setXogador(Xogador xogador) {
        this.dono = xogador;
    }

    public float getCartosProducidos() {
        return this.cartosProducidos;
    }

    public void setCartosProducidos(float cartosProducidos) {
        this.cartosProducidos = cartosProducidos;
    }

    //Metodos    
    public boolean perteneceAXogador(Xogador xogador) {
        return this.dono.equals(xogador);
    }

    public abstract void calculoAlquiler();

}
