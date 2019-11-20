package estrutura;

import xogadores.*;

public abstract class Propiedade extends Casilla {

    private int posicion;
    private float alquiler;
    private Xogador dono;
    private float cartosProducidos;


    public Propiedade() {
    }

    public Propiedade(String nome, int posicion, float valor) {
        super(nome, valor);
        this.posicion = posicion;
    }

    public float getAlquiler() {
        return this.alquiler;
    }

    public void setAlquiler(float alquiler) {
        this.alquiler = alquiler;
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
