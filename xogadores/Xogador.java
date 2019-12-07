package xogadores;

import consola.ConsolaNormal;
import estrutura.*;

import java.util.ArrayList;

public class Xogador {

    private Avatar avatar;
    private String nome;
    private float fortuna;
    private ArrayList<Propiedade> propiedades;
    private int nVoltas;
    private int estadoPreso;
    private float cartosInvertidos;
    private float pagoTasasEImportos;
    private float pagoDeAlquileres;
    private float cobroDeAlquileres;
    private float pasarPorSaida;
    private float premiosInversionsOBote;

    //Constructor da Banca
    public Xogador() {
        this.nome = "Banca";
        this.fortuna = 100000000;
        this.nVoltas = 0;
        this.estadoPreso = 0;
        this.propiedades = new ArrayList<>();
        this.cartosInvertidos = 0;
        this.pagoTasasEImportos = 0;
        this.pagoDeAlquileres = 0;
        this.cobroDeAlquileres = 0;
        this.pasarPorSaida = 0;
        this.premiosInversionsOBote = 0;
    }

    public Xogador(String hipotecar) {
        this.nome = "Hipotecar";
        this.fortuna = 100000000;
        this.propiedades = new ArrayList<>();
        this.nVoltas = 0;
        this.estadoPreso = 0;
        this.cartosInvertidos = 0;
        this.pagoTasasEImportos = 0;
        this.pagoDeAlquileres = 0;
        this.cobroDeAlquileres = 0;
        this.pasarPorSaida = 0;
        this.premiosInversionsOBote = 0;
    }

    public Xogador(String nome, Taboleiro taboleiro) {
        if ((nome != null) && (taboleiro != null)) {
            this.nome = nome;
            this.fortuna = Constantes.FORTUNA_INICIAL;
            this.propiedades = new ArrayList<>();
            this.nVoltas = 0;
            this.estadoPreso = 0;
            this.cartosInvertidos = 0;
            this.pagoTasasEImportos = 0;
            this.premiosInversionsOBote = 0;
        } else {
            System.out.println("Erro en cosntrutor de xogador.\n");
        }
    }

    //Getters e Setters
    public Avatar getAvatar() {
        return this.avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getFortuna() {
        return this.fortuna;
    }

    public void setFortuna(float fortuna) {
        this.fortuna = fortuna;
    }

    public void modificarFortuna(float valor) {
        this.fortuna += valor;
    }

    public ArrayList<Propiedade> getPropiedades() {
        return this.propiedades;
    }

    public void setPropiedades(ArrayList<Propiedade> prop) {
        this.propiedades = prop;
    }

    public void engadirPropiedade(Propiedade propiedade) {
        this.propiedades.add(propiedade);
    }

    public void eliminarPropiedade(Propiedade propiedade) {
        this.propiedades.remove(propiedade);
    }

    public int getNVoltas() {
        return this.nVoltas;
    }

    public void setNVoltas(int nVoltas) {
        this.nVoltas = nVoltas;
    }

    public int getEstadoPreso() {
        return this.estadoPreso;
    }

    public void setEstadoPreso(int estado) {
        if ((estado <= 4) && (estado >= 0)) {
            this.estadoPreso = estado;
        }
    }

    public void restarEstadoPreso() {
        if (this.estadoPreso > 0) {
            this.estadoPreso--;
        } else {
            //Excepcion
        }
    }

    public float getCartosInvertidos() {
        return cartosInvertidos;
    }

    public void setCartosInvertidos(float cartosInvertidos) {
        this.cartosInvertidos = cartosInvertidos;
    }

    public float getPagoTasasEImportos() {
        return pagoTasasEImportos;
    }

    public void setPagoTasasEImportos(float pagoTasasEImportos) {
        this.pagoTasasEImportos = pagoTasasEImportos;
    }

    public float getPagoDeAlquileres() {
        return pagoDeAlquileres;
    }

    public void setPagoDeAlquileres(float pagoDeAlquileres) {
        this.pagoDeAlquileres = pagoDeAlquileres;
    }

    public float getCobroDeAlquileres() {
        return cobroDeAlquileres;
    }

    public void setCobroDeAlquileres(float cobroDeAlquileres) {
        this.cobroDeAlquileres = cobroDeAlquileres;
    }

    public float getPasarPorSaida() {
        return pasarPorSaida;
    }

    public void setPasarPorSaida(float pasarPorSaida) {
        this.pasarPorSaida = pasarPorSaida;
    }

    public float getPremiosInversionsOBote() {
        return premiosInversionsOBote;
    }

    public void setPremiosInversionsOBote(float premiosInversionsOBote) {
        this.premiosInversionsOBote = premiosInversionsOBote;
    }

    //Metodos
    public float cartosGastados() {
        return cartosInvertidos + pagoTasasEImportos + pagoDeAlquileres;
    }

    //Metodo para sumar voltas
    public void sumarVolta() {
        this.nVoltas++;
    }

    public String imprimirPropiedades() {
        String texto = "";

        for (Propiedade prop : propiedades) {
            texto += prop.imprimirCasilla();
        }

        return texto;
    }

    public void hipotecar(Propiedade prop, Xogador hipotecar) {
        boolean atopada = false;
        final ConsolaNormal consola = new ConsolaNormal();
        for (Propiedade propiedade : this.propiedades) {
            if (propiedade.getNome().equals(prop.getNome())) {
                atopada = true;
                break;
            }
        }

        if (!atopada) {
            consola.imprimir("Esta propiedade non te pertence.");
            //excepcion
            return;
        }

        if (prop instanceof Solar) {
            for (int i = ((Solar) prop).getEdificios().size() - 1; i >= 0; i--) {
                String id = ((Solar) prop).getEdificios().get(i).getId();
                this.fortuna = this.fortuna + ((Solar) prop).venderEdificios(id);
            }
        }

        prop.setDono(hipotecar);
        hipotecar.engadirPropiedade(prop);
        consola.imprimir("Hipoteca realizada correctamente.");
        this.fortuna = (float) (fortuna + prop.getValor() * 0.5);
    }

    public void deshipotecar(Xogador xog, Propiedade prop) {
        final ConsolaNormal consola = new ConsolaNormal();
        boolean edel = false;

        if (!prop.getDono().getNome().equals("Hipotecar")) {
            consola.imprimir("Esta propiedade non esta hipotecada.");
            return;
        }

        for (int i = 0; i < xog.getPropiedades().size(); i++) {
            if (xog.getPropiedades().get(i).getNome().equals(prop.getNome())) {
                edel = true;
                break;
            }
        }

        if (edel) {
            xog.modificarFortuna((float) (-0.5 * prop.getValor()));
            prop.setDono(xog);
            xog.engadirPropiedade(prop);
            consola.imprimir("Deshipotecado correctamente.");

            xog.eliminarPropiedade(prop);
        } else {
            consola.imprimir("Esta propiedade non é túa pilloín!");
        }
    }

    @Override
    public String toString() {
        if (avatar == null) {
            String texto = "\nXogador:\n"
                    + "\n" + "\tNome: " + this.nome
                    + "\n" + "\tFortuna: " + this.fortuna
                    + "\n" + "\tCartos gastados: " + this.cartosGastados()
                    + "\n" + this.imprimirPropiedades()
                    + "\n";
            return texto;
        } else {
            String texto = "\nXogador:\n"
                    + "\tAvatar: " + this.avatar.getId()
                    + "\n" + "\tNome: " + this.nome
                    + "\n" + "\tFortuna: " + this.fortuna
                    + "\n" + "\tCartos gastados: " + this.cartosGastados()
                    + "\n" + this.imprimirPropiedades()
                    + "\n";
            return texto;
        }
    }

    @Override
    public boolean equals(Object xogador) {
        if (!(xogador instanceof Xogador)) {
            return false;
        }
        return (this.nome.equals(((Xogador) xogador).getNome()));
    }
//Fin Clase
}
