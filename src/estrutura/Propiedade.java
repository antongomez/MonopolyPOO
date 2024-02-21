package estrutura;

import java.util.HashMap;
import xogadores.*;

public abstract class Propiedade extends Casilla {

    private float valor;
    private HashMap<String, Integer> exentos;
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
        this.exentos = new HashMap<>();
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public HashMap<String, Integer> getExentos() {
        return exentos;
    }

    public boolean estaExento(String nomeXogador) {
        return exentos.get(nomeXogador) != null;
    }

    public void engadirExento(String nomeXogador, Integer nTurnos) {
        this.exentos.put(nomeXogador, nTurnos);
    }

    public void eliminarExento(String nomeXogador) {
        this.exentos.remove(nomeXogador);
    }

    public void restarTurnoExento(String nomeXogador) {
        Integer oldValue = exentos.get(nomeXogador);
        if (oldValue > 1) {
            this.exentos.replace(nomeXogador, oldValue - 1);
        } else if (oldValue == 1) {
            this.exentos.remove(nomeXogador);
        }
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
        novoDono.engadirPropiedade(this);
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
        String nomeaux = String.format("%-15s", this.getNome());
        String avatares = String.format("%6s", imprimirAvatares());
        String texto = this.grupo.getColorear() + "| " + nomeaux + " " + avatares + "|";
        return texto;
    }
}
