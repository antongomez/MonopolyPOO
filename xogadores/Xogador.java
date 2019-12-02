package xogadores;

import estrutura.Casilla;
import estrutura.Constantes;
import estrutura.Propiedade;
import estrutura.Taboleiro;
import xogadores.Avatar;
import xogo.Partida;

import java.util.ArrayList;

public class Xogador {

    private Avatar avatar;
    private String nome;
    private float fortuna;
    private ArrayList<Propiedade> propiedades;
    private int nVoltas;
    private float cartosInvertidos;
    private float pagoTasasEImportos;
    private float pagoDeAlquileres;
    private float cobroDeAlquileres;
    private float pasarPorSaida;
    private float premiosInversionsOBote;

    //Constructor da Banca
    public Xogador(){
        this.nome = "Banca";
        this.fortuna = 100000000;
        this.nVoltas = 0;
        this.propiedades = new ArrayList<>();
        this.cartosInvertidos = 0;
        this.pagoTasasEImportos = 0;
        this.pagoDeAlquileres = 0;
        this.cobroDeAlquileres = 0;
        this.pasarPorSaida = 0;
        this.premiosInversionsOBote = 0;
    }

    public Xogador(String hipotecar){
        this.nome = "Hipotecar";
        this.fortuna = 100000000;
        this.propiedades = new ArrayList<>();
        this.nVoltas = 0;
        this.cartosInvertidos = 0;
        this.pagoTasasEImportos = 0;
        this.pagoDeAlquileres = 0;
        this.cobroDeAlquileres = 0;
        this.pasarPorSaida = 0;
        this.premiosInversionsOBote = 0;
    }

    public Xogador(String nome, String tipo_avatar, Taboleiro taboleiro, Partida partida) {
        if ((nome != null) && (tipo_avatar != null) && (taboleiro != null)) {
            this.nome = nome;
            this.fortuna = Constantes.FORTUNA_INICIAL;
            this.propiedades = new ArrayList<>();
            this.nVoltas = 0;
            cartosInvertidos = 0;
            pagoTasasEImportos = 0;
            premiosInversionsOBote = 0;
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

    public void CreateAvatarXogador(String tipo_avatar, Taboleiro taboleiro, Partida partida)
    {
        switch(tipo_avatar)
        {
            case "Sombrero":
            case "sombrero":
            case "Sombreiro":
            case "sombreiro":
            case"Chapeu":
            case "chapeu": this.setAvatar(new Chapeu(partida.getAvatares(), this, taboleiro));
                break;
            case "Coche":
            case "coche": this.setAvatar(new Coche(partida.getAvatares(), this, taboleiro));
                break;
            case "Esfinge":
            case "esfinge":
            case "esfinxe":
            case "Esfinxe": this.setAvatar(new Esfinxe(partida.getAvatares(), this, taboleiro));
                break;
            case "pelota":
            case "Pelota": this.setAvatar(new Pelota(partida.getAvatares(), this, taboleiro));
                break;
            default: this.setAvatar(new Pelota(partida.getAvatares(), this, taboleiro));
                break;
        }

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

        for (Casilla casilla : propiedades) {
            texto += casilla.imprmirPropieade() + "\n";
        }

        return texto;
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
//Fin Clase
}
