package estrutura;

import java.util.ArrayList;

public class Solar extends Propiedade {

    private ArrayList<Edificio> edificios;

    public Solar(String nome, int posicion, float valor) {
        super(nome, posicion, valor);
        edificios = new ArrayList<>();
    }

    //Getters e Setters
    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    public void engadirEdificio(Edificio edificio) {
        edificios.add(edificio);
    }

    public void eliminarEdificio(Edificio edificio) {
        edificios.remove(edificio);
    }

    public ArrayList<Casa> getCasas() {
        ArrayList<Casa> casas = new ArrayList<>();
        for (Edificio casa : edificios) {
            if (casa instanceof Casa) {
                casas.add((Casa) casa);
            }
        }
        return casas;
    }

    public int getNCasas() {
        return this.getCasas().size();
    }

    @Override
    public void calculoAlquiler() {
        //Falta ter en conta o numero de edificios
        this.setAlquiler(this.getValor() * 0.1f);
    }
}
