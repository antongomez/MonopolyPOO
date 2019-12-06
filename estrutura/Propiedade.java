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
        this.cartosProducidos = 0;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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

    //Metodo que compra unha propiedade. Actualiza a fortuna do xogador.
    public void comprar(Xogador novoDono) {
        this.dono = novoDono;
        novoDono.modificarFortuna(-this.valor);
    }

    public void actualizarValor() {
        //Comprobamos que o dono da propiedade sexa a banca.
        if (this.dono.getNome().equals("Banca")) {
            this.valor *= 1.05;
        }
    }

    public abstract float calculoAlquiler();

    @Override
    public String toString() {
        String nomeaux = String.format("%-13s", this.getNome());
        String avatares = String.format("%6s", imprimirAvatares());
        String texto = this.grupo.getColorear() + "| " + nomeaux + " " + avatares + "|";
        return texto;
    }
}
