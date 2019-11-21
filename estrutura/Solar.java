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

    public ArrayList<Hotel> getHoteis() {
        ArrayList<Hotel> hoteis = new ArrayList<>();
        for (Edificio hotel : edificios) {
            if (hotel instanceof Hotel) {
                hoteis.add((Hotel) hotel);
            }
        }
        return hoteis;
    }

    public int getNHoteis() {
        return this.getHoteis().size();
    }

    public ArrayList<Piscina> getPiscinas() {
        ArrayList<Piscina> piscinas = new ArrayList<>();
        for (Edificio piscina : edificios) {
            if (piscina instanceof Piscina) {
                piscinas.add((Piscina) piscina);
            }
        }
        return piscinas;
    }

    public int getNPiscinas() {
        return this.getPiscinas().size();
    }

    public ArrayList<PistaDeDeporte> getPistas() {
        ArrayList<PistaDeDeporte> pistas = new ArrayList<>();
        for (Edificio pista : edificios) {
            if (pista instanceof PistaDeDeporte) {
                pistas.add((PistaDeDeporte) pista);
            }
        }
        return pistas;
    }

    public int getNPistas() {
        return this.getPistas().size();
    }

    public float alquilerInicialGrupo() {
        float valor = 0;

        switch (this.getGrupo().getId()) {
            case 1:
                valor = Constantes.ACASILLA_GRUPO1;
                break;
            case 2:
                valor = Constantes.ACASILLA_GRUPO2;
                break;
            case 3:
                valor = Constantes.ACASILLA_GRUPO3;
                break;
            case 4:
                valor = Constantes.ACASILLA_GRUPO4;
                break;
            case 5:
                valor = Constantes.ACASILLA_GRUPO5;
                break;
            case 6:
                valor = Constantes.ACASILLA_GRUPO6;
                break;
            case 7:
                valor = Constantes.ACASILLA_GRUPO7;
                break;
            case 8:
                valor = Constantes.ACASILLA_GRUPO8;
                break;
        }

        return valor;
    }

    public float calculoAlquilerMonopolio() {
        if (this.getGrupo().existeMonopolio()) {
            return this.alquilerInicialGrupo();
        }
        return 0;
    }

    public float calculoAlquilerEdificios() {
        float valor = 0;

        switch (this.getNCasas()) {
            case 1:
                valor += 5 * alquilerInicialGrupo();
                break;
            case 2:
                valor += 15 * alquilerInicialGrupo();
                break;
            case 3:
                valor += 35 * alquilerInicialGrupo();
                break;
            case 4:
                valor += 50 * alquilerInicialGrupo();
                break;
        }

        valor += 70 * alquilerInicialGrupo() * this.getNHoteis();
        valor += 25 * alquilerInicialGrupo() * this.getNPiscinas();
        valor += 25 * alquilerInicialGrupo() * this.getNPistas();

        return valor;
    }

    @Override
    public void calculoAlquiler() {
        //Establecese o valor inicial do alquiler do solar
        float valor = alquilerInicialGrupo();
        //Sumase o valor engadido se hai monopolio
        valor += calculoAlquilerMonopolio();
        //Sumase o valor engadido por todas as edificacions
        valor += calculoAlquilerEdificios();
        //Modificase o valor do alquiler?
        this.setAlquiler(valor);

        //return valor;
    }
}
