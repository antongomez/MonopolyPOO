
package estrutura;

import xogadores.*;

public abstract class Propiedade extends Casilla {

    //private int posicion;
    private float valor;
    private float alquiler;
    private Xogador dono;
    private Grupo grupo;
    private float cartosProducidos;

    public Propiedade() {
    }

    public Propiedade(String nome, Grupo grupo, int posicion, Xogador dono, float valor) {
        super(nome, posicion);
        this.grupo = grupo;
        this.valor = valor;
        this.dono = dono;
    }

    public int getPosicion() {
        return super.getPosicion();
    }

    public void setPosicion(int posicion) {
        super.setPosicion(posicion);
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

    public Xogador getDono() {
        return this.dono;
    }

    public void setDono(Xogador xogador) {
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

    public void comprar(Xogador novoDono) {
        this.dono = novoDono;
        novoDono.modificarFortuna(-this.valor);
    }

    public abstract void calculoAlquiler();


    public String toString() {
            String nomeaux = String.format("%-13s", this.getNome());
            String avatares = String.format("%6s", imprimirAvatares());
            String texto = this.grupo.getColorear() + "| " + nomeaux + " " + avatares + "|";
            return texto;
        }
}
