
package estrutura;

import xogadores.*;

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
            return this.alquilerInicialGrupo();
        }
        return 0;
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
//Fin clase
}