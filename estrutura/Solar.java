package estrutura;

import xogadores.*;
import xogo.*;

import java.util.ArrayList;

public class Solar extends Propiedade {

    private ArrayList<Edificio> edificios;

    public Solar(String nome, int posicion, float valor, Grupo grupo, Xogador dono) {
        super(nome, grupo, posicion, dono, valor);
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

    //Metodo que devolve un ArrayList coas casas do solar
    public ArrayList<Casa> getCasas() {
        ArrayList<Casa> casas = new ArrayList<>();
        for (Edificio casa : edificios) {
            if (casa instanceof Casa) {
                casas.add((Casa) casa);
            }
        }
        return casas;
    }

    //Devolve o numero de casas do solar. Usa getCasas.
    public int getNCasas() {
        return this.getCasas().size();
    }

    //Devolve un ArrayList cos hoteis so solar
    public ArrayList<Hotel> getHoteis() {
        ArrayList<Hotel> hoteis = new ArrayList<>();
        for (Edificio hotel : edificios) {
            if (hotel instanceof Hotel) {
                hoteis.add((Hotel) hotel);
            }
        }
        return hoteis;
    }

    //Devolve o numero de hoteis do solar. Usa getHoteis.
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

    public ArrayList<Pista> getPistas() {
        ArrayList<Pista> pistas = new ArrayList<>();
        for (Edificio pista : edificios) {
            if (pista instanceof Pista) {
                pistas.add((Pista) pista);
            }
        }
        return pistas;
    }

    public int getNPistas() {
        return this.getPistas().size();
    }

    //Metodo que inicializa o valor do alquiler
    private float alquilerInicialGrupo() {
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

    //Metodo que calcula o alquiler en funcion de se hai ou non monopolio
    public float calculoAlquilerMonopolio() {
        if (this.getGrupo().existeMonopolio()) {
            return this.alquilerInicialGrupo() * 2;
        }
        return this.alquilerInicialGrupo();
    }

    //Calcula o alquiler en funcion do numero de casas
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

    //Metodo que calcula o alquiler;
    @Override
    public void calculoAlquiler() {

        float valor;
        //Establecese o valor inicial do alquiler do solar e multiplicase por 2 se hai monopolio
        valor = calculoAlquilerMonopolio();
        //Sumase o valor engadido por todas as edificacions
        if (!edificios.isEmpty()) {
            valor = calculoAlquilerEdificios();
        }
        //Modificase o valor do alquiler?
        this.setAlquiler(valor);

        //return valor;
    }

    /*Metodo que devolve true se se pode edificar unha casa en funcion da 
    restricion da casilla e do grupo.
     */
    public boolean poderEdificarCasa() {
        return ((this.getNCasas() < 4)
                && (this.getGrupo().poderConstruirCasa()));
    }

    /*Metodo que devolve true se se pode edificar unha casa en funcion da 
    restricion da casilla e do grupo.
     */
    public boolean poderEdificarHotel() {
        return ((this.getNCasas() == 4)
                && (this.getGrupo().poderConstruirHotel()));
    }

    /*Metodo que devolve true se se pode edificar unha piscina en funcion da 
    restricion da casilla e do grupo. Unha piscina so se pode contruir con
    duas casas e un hotel como minimo.
     */
    public boolean poderEdificarPiscina() {
        //Consideramos que un hotel son 5 casas
        int unidadesConstrucion = this.getNCasas() + this.getNHoteis() * 5;

        return ((unidadesConstrucion >= 7)
                && (this.getGrupo().poderConstruirPiscina()));
    }

    /*Metodo que devolve true se se pode edificar unha pista en funcion da 
    restricion da casilla e do grupo. Unha pista so se pode contruir se se
    construiron dous hoteis na casilla.
     */
    public boolean poderEdificarPista() {
        return ((this.getNHoteis() >= 2)
                && (this.getGrupo().poderConstruirPista()));
    }

    public void Edificar(String tipo) {
        switch (tipo) {
            case "Casa": {
                if (((Propiedade) this).getDono().getFortuna() //facer un metodo en xogador que comprobe isto
                        > this.getValor() * Constantes.CASA) {
                    if (this.poderEdificarCasa()) {

                        //Construimos
                        this.engadirEdificio(new Casa(this));

                        //Cobramos
                        this.getDono().modificarFortuna(
                                -this.getValor() * Constantes.CASA);
                    } //Excepcion
                } else {
                    System.out.println("O dono deste solar non ten"
                            + " suficientes GM.");
                }
            }
            break;
            case "Hotel": {
                if (((Propiedade) this).getDono().getFortuna()
                        > this.getValor() * 0.6) {
                    if (this.poderEdificarHotel()) {
                        //Construimos
                        this.engadirEdificio(new Hotel(this));

                        //Cobramos
                        this.getDono().modificarFortuna(
                                -this.getValor() * Constantes.HOTEL);
                    } //Excepcion
                } else {
                    System.out.println("O dono deste solar non ten"
                            + " suficientes GM.");
                }
            }
            break;
            case "Piscina": {
                if (((Propiedade) this).getDono().getFortuna()
                        > this.getValor() * 0.4) {
                    if (this.poderEdificarPiscina()) {
                        //Construimos
                        this.engadirEdificio(new Piscina(this));

                        //Cobramos
                        this.getDono().modificarFortuna(
                                -this.getValor() * Constantes.PISCINA);
                    }
                } else {
                    System.out.println("O dono deste solar non ten"
                            + " suficientes GM.");
                }
            }
            break;
            case "Pista": {
                if (((Propiedade) this).getDono().getFortuna()
                        > this.getValor() * 10.) {
                    if (this.poderEdificarPista()) {
                        //Construimos
                        this.engadirEdificio(new Pista(this));

                        //Cobramos
                        this.getDono().modificarFortuna(
                                -this.getValor() * Constantes.PISTA);
                    }
                } else {
                    System.out.println("O dono deste solar non ten"
                            + " suficientes GM.");
                }
            }
            break;
            default:
                //Excepcion
                System.out.println("Debe introducir un tipo edificio valido.");
                break;
        }
    }

    @Override
    public String imprimirCasilla() {
        String texto = "{\n"
                + "\tnome: " + this.getNome() + ",\n"
                + "\ttipo: Solar,\n"
                + "\tgrupo: " + this.getGrupo().getNome() + ",\n"
                + "\tdono: " + this.getDono().getNome() + ",\n"
                + "\tvalor: " + this.getValor() + " GM,\n"
                + "\talquiler: " + this.getAlquiler() + " GM,\n"
                + "\tvalor casa: " + (this.getValor() * Constantes.CASA) + " GM,\n"
                + "\tvalor hotel: " + (this.getValor() * Constantes.HOTEL) + " GM,\n"
                + "\tvalor piscina: " + (this.getValor() * Constantes.PISCINA) + " GM,\n"
                + "\tvalor pista de deporte: " + (this.getValor() * Constantes.PISTA) + " GM,\n"
                + "\talquiler unha casa: " + " GM,\n"
                + "\talquiler dúas casas: " + " GM,\n"
                + "\talquiler tres casas: " + " GM,\n"
                + "\talquiler catro casas: " + " GM,\n"
                + "\talquiler hotel: " + " GM,\n"
                + "\talquiler piscina: " + " GM,\n"
                + "\talquiler pista de deporte: " + " GM,\n"
                + "\txogadores: [" + "]\n"
                + "}\n";

        return texto;
    }

    /* tipo: solar,  grupo: Rosa,  propietario: Pedro,  valor: 2600000,  alquiler: 220000,  valor hotel: 1560000,  valor casa: 1560000,  valor piscina: 1040000,  valor pista de deporte: 3250000,  alquiler una casa: 1100000,  alquiler dos casas: 3300000,  alquiler tres casas: 7700000,  alquiler cuatro casas: 11000000,  alquiler hotel: 15400000,  alquiler piscina: 5500000,  alquiler pista de deporte: 5500000*/
//Fin clase
}
