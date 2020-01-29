package estrutura;

import xogadores.*;
import java.util.ArrayList;

public class Grupo extends Casilla {

    private int id;
    private ArrayList<Propiedade> propiedades;
    private String colorear;

    //Constructores
    public Grupo() {
    }

    public Grupo(String nome, int id, String colorear) {
        super(nome, 0);
        this.id = id;
        this.colorear = colorear;
        this.propiedades = new ArrayList<>();
    }

    public Grupo(String nome, int id) {
        super(nome, 0);
        this.id = id;
        this.colorear = Constantes.NEGRO;
        this.propiedades = new ArrayList<>();
    }

    //Getters e Setters
    public int getId() {
        return id;
    }

    public ArrayList<Edificio> getEdificios() {
        ArrayList<Edificio> edificios = new ArrayList<>();
        if (grupoSolares()) {
            for (Propiedade propiedade : propiedades) {
                for (Edificio edificio : ((Solar) propiedade).getEdificios()) {
                    edificios.add(edificio);
                }
            }
        }
        return edificios;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Propiedade> getPropiedades() {
        return this.propiedades;
    }

    public boolean grupoSolares() {
        return (propiedades.get(0) instanceof Solar);
    }

    public String getColorear() {
        return this.colorear;
    }

    public void setColorear(String colorear) {
        this.colorear = colorear;
    }

    //Metodos
    public void engadirPropiedade(Propiedade propiedade) {
        this.propiedades.add(propiedade);
    }

    public void eliminarPropiedade(Propiedade propiedade) {
        this.propiedades.remove(propiedade);
    }

    public int getNSolares() {
        return this.propiedades.size();
    }

    /*Metodo que calcula o numero de casas totais do grupo*/
    public int getNCasas() {
        int nCasas = 0;
        if (!this.propiedades.isEmpty()) {
            if (this.propiedades.get(0) instanceof Solar) {
                for (Propiedade propiedade : this.propiedades) {
                    nCasas += ((Solar) propiedade).getNCasas();
                }
                return nCasas;
            }
        }
        //Introducir excepcion
        return 0;
    }

    /*Metodo que calcula o numero de hoteis totais do grupo*/
    public int getNHoteis() {
        int nHoteis = 0;
        if (!this.propiedades.isEmpty()) {
            if (this.propiedades.get(0) instanceof Solar) {
                for (Propiedade propiedade : this.propiedades) {
                    nHoteis += ((Solar) propiedade).getNHoteis();
                }
                return nHoteis;
            }
        }
        //Introducir excepcion
        return 0;
    }

    /*Metodo que calcula o numero de piscinas totais do grupo*/
    public int getNPiscinas() {
        int nPiscinas = 0;
        if (!this.propiedades.isEmpty()) {
            if (this.propiedades.get(0) instanceof Solar) {
                for (Propiedade propiedade : this.propiedades) {
                    nPiscinas += ((Solar) propiedade).getNPiscinas();
                }
                return nPiscinas;
            }
        }
        //Introducir excepcion
        return 0;
    }

    /*Metodo que calcula o numero de pistas totais do grupo*/
    public int getNPistas() {
        int nPistas = 0;
        if (!this.propiedades.isEmpty()) {
            if (this.propiedades.get(0) instanceof Solar) {
                for (Propiedade propiedade : this.propiedades) {
                    nPistas += ((Solar) propiedade).getNPistas();
                }
                return nPistas;
            }
        }
        //Introducir excepcion
        return 0;
    }

    /*Funcion que determina se se pode construir unha casa coa restricion de
    grupo. O maximo de casas son 15 (12 + 3) ou 10 (8 + 2).
     */
    public boolean poderConstruirCasa() {
        if (this.getNCasas() + this.getNHoteis() * 4 == getNSolares() * 5) {
            return false;
        } else if (this.getNCasas() + this.getNHoteis() * 4 > getNSolares() * 5) {
            //Excepcion, algo vai mal.
        }
        return true;
    }

    /*Funcion que determina se se pode construir un hotel coa restricion de
    grupo.
     */
    public boolean poderConstruirHotel() {
        if (this.getNHoteis() == getNSolares()) { //Xa hai 3 hoteis no grupo
            return false;
        } else if (this.getNHoteis() > getNSolares()) { //hai mais de tres hoteis no grupo
            //Excepcion, algo vai mal.
        }
        return true; //Hai menos de tres hoteis no grupo
    }

    /*Funcion que determina se se pode construir unha piscina coa restricion de
    grupo.
     */
    public boolean poderConstruirPiscina() {
        if (this.getNPiscinas() == getNSolares()) {
            return false;
        } else if (this.getNPiscinas() > getNSolares()) {
            //Excepcion, algo vai mal.
        }
        return true;
    }

    /*Funcion que determina se se pode construir unha pista coa restricion de
    grupo.
     */
    public boolean poderConstruirPista() {
        if (this.getNPistas() == getNSolares()) {
            return false;
        } else if (this.getNPistas() > getNSolares()) {
            //Excepcion, algo vai mal.
        }
        return true;
    }

    /*Metodo que determina os edificios que se poden construir ainda nun grupo.*/
    public ArrayList<Edificio> faltaConstruir() {
        ArrayList<Edificio> edificios = new ArrayList<>();

        return edificios;
    }

    /*Funcion que devolve true se un mesmo xogador posee
    todas as propiedades do grupo*/
    public boolean existeMonopolio() {
        ArrayList<Xogador> donos = new ArrayList<>();
        if (!this.propiedades.isEmpty()) {
            if (this.propiedades.get(0) instanceof Solar) {
                for (int i = 0; i < this.propiedades.size(); i++) {
                    donos.add(propiedades.get(i).getDono());
                }
                for (int i = 1; i < donos.size(); i++) {
                    if (!(donos.get(i).equals(donos.get(i - 1)))) {
                        return false;
                    }
                }
                return true;
            }
        }
        //Introducir excepcion
        return false;
    }

    @Override
    public String imprimirCasilla() {
        String texto ="\tnome: " + this.getNome() + "\n"
                + "\tnumero de Grupo:" + id + "\n";

        return texto;
    }
    //Fin Clase
}
