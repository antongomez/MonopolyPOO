package xogadores;

import estrutura.Casilla;
import estrutura.Constantes;
import estrutura.Propiedade;
import estrutura.Taboleiro;
import xogadores.Avatar;
import java.util.ArrayList;
/*
public class Xogador {

    private Avatar avatar;
    private String nome;
    private float fortuna;
    private float cartosGastados;
    private ArrayList<Casilla> propiedades; //A Clave e o nome da casilla
    private int estarPreso;
    private int nVoltas;
    private float cartosInvertidos;//EN todos los seters so se suma o introducido
    private float pagoTasasEImportos;
    private float PagoDeAlquileres;
    private float CobroDeAlquileres;
    private float pasarPorSalida;
    private float premiosInversionsOBote;
    private int vecesNoCarcere;
    private int vecesDados;

    //Constructor banca
    public Xogador() {
        this.nome = "Banca";
        this.fortuna = 100000000;
        this.cartosGastados = 0; //Cartos invertidos en propiedades ou edificios. Non se contan impostos nin pagos de alquiler.
        this.avatar = null;
        this.propiedades = new ArrayList<>();
        this.estarPreso = 0;
        this.nVoltas = 0;
        cartosInvertidos = 0;
        pagoTasasEImportos = 0;
        PagoDeAlquileres = 0;
        CobroDeAlquileres = 0;
        pasarPorSalida = 0;
        premiosInversionsOBote = 0;
        vecesNoCarcere = 0;
        vecesDados=0;
    }

    //Constructor Hipotecar
    public Xogador(String hipotecar) {
        this.nome = hipotecar;
        this.fortuna = 100000000;
        this.cartosGastados = 0; //Cartos invertidos en propiedades ou edificios. Non se contan impostos nin pagos de alquiler.
        this.avatar = null;
        this.propiedades = new ArrayList<>();
        this.estarPreso = 0;
        this.nVoltas = 0;
        cartosInvertidos = 0;
        pagoTasasEImportos = 0;
        PagoDeAlquileres = 0;
        CobroDeAlquileres = 0;
        pasarPorSalida = 0;
        premiosInversionsOBote = 0;
        vecesNoCarcere = 0;
        vecesDados=0;
    }

    //Constructor Xogadores normais
    public Xogador(String nome, String tipo_avatar, Taboleiro taboleiro) {
        if ((nome != null) && (tipo_avatar != null) && (taboleiro != null)) {
            this.nome = nome;
            avatar = new Avatar(tipo_avatar, this, taboleiro);
            this.fortuna = Constantes.FORTUNA_INICIAL;
            this.cartosGastados = 0;
            this.propiedades = new ArrayList<>();
            this.estarPreso = 0;
            this.nVoltas = 0;
            cartosInvertidos = 0;
            pagoTasasEImportos = 0;
            PagoDeAlquileres = 0;
            CobroDeAlquileres = 0;
            pasarPorSalida = 0;
            premiosInversionsOBote = 0;
            vecesNoCarcere = 0;
            vecesDados=0;
        } else {
            System.out.println("Erro en cosntrutor de xogador.\n");
        }
    }

    //Getters y setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        if (nome != null) {
            this.nome = nome;
        } else {
            System.out.println("Erro en setNome.\n");
        }
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {

        if (avatar != null) {
            this.avatar = avatar;
        } else {
            System.out.println("Erro en setAvatar.\n");
        }
    }

    public int getVecesDados()
    {
        return vecesDados;
    }
    public void setVecesDados(int num)
    {
        this.vecesDados=this.vecesDados+num;
    }

    public double getFortuna() {
        return fortuna;
    }

    public void setFortuna(float fortuna) {
        this.fortuna = fortuna;
    }

    //Metodo que substitue a setFortuna()
    public void modificarFortuna(float modificacion) {
        this.fortuna = this.fortuna + modificacion;
    }

    public int getnVoltas() {
        return nVoltas;
    }

    //Non se usa
    public void setnVoltas(int valor) {
        this.nVoltas = valor;
    }

    public void sumarnVoltas() {
        nVoltas++;
    }

    public float getCartosGastados() {
        return cartosGastados;
    }

    public void setCartosGastados(float cartosGastados) {
        this.cartosGastados = cartosGastados;
    }

    public void modificarCartosGastados(float cartosGastados) {
        this.cartosGastados += cartosGastados;
    }

    public ArrayList<Casilla> getPropiedades() {
        return (propiedades);
    }

    public void setPropiedades(ArrayList<Casilla> propiedades) {
        if (propiedades == null) {
            System.err.println("Propiedades non inicializadas.\n");
        }
    }

    public int getEstarPreso() {
        return this.estarPreso;
    }

    public void setEstarPreso(int estado) {
        if ((estado >= 0) && (estado <= 4)) {
            this.estarPreso = estado;
        } else {
            this.estarPreso = 0;
            System.out.println("\nError en estado carcere\n.");
        }
    }

    public float getCartosInvertidos() {
        return cartosInvertidos;
    }

    public void setCartosInvertidos(float cartosInvertidos) {
        this.cartosInvertidos = this.cartosInvertidos + cartosInvertidos;
    }

    public float getPagoDeAlquileres() {
        return PagoDeAlquileres;
    }

    public void setPagoDeAlquileres(float pagoDeAlquileres) {
        this.PagoDeAlquileres = this.PagoDeAlquileres + pagoDeAlquileres;
    }

    public float getCobroDeAlquileres() {
        return CobroDeAlquileres;
    }

    public void setCobroDeAlquileres(float cobroDeAlquileres) {
        this.CobroDeAlquileres = this.CobroDeAlquileres + cobroDeAlquileres;
    }

    public float getPagoTasasEImportos() {
        return pagoTasasEImportos;
    }

    public void setPagoTasasEImportos(float pagoTasasEImportos) {
        this.pagoTasasEImportos = this.pagoTasasEImportos + pagoTasasEImportos;
    }

    public float getPasarPorSalida() {
        return pasarPorSalida;
    }

    public void setPasarPorSalida(float pasarPorSalida) {
        this.pasarPorSalida = this.pasarPorSalida + pasarPorSalida;
    }

    public float getPremiosInversionsOBote() {
        return premiosInversionsOBote;
    }

    public void setPremiosInversionsOBote(float premiosInversionsOBote) {
        this.premiosInversionsOBote = this.premiosInversionsOBote + premiosInversionsOBote;
    }

    public int getVecesNoCarcere() {
        return vecesNoCarcere;
    }

    public void setVecesNoCarcere(int vecesNoCarcere) {
        this.vecesNoCarcere = this.vecesNoCarcere + vecesNoCarcere;
    }

    //Métodos
    public void introducirPropiedade(Casilla propiedade) {
        this.propiedades.add(propiedade);
    }

    public void eliminarPropiedade(Casilla casilla) {
        if (propiedades != null) {
            propiedades.remove(casilla);
        }
    }

    public String imprimirPropiedades() {
        String texto = "";

        for (Casilla casilla : propiedades) {
            texto += casilla.imprimirCasilla() + "\n";
        }

        return texto;
    }

    //Metodo que comproba cantas propiedades do mesmo grupo posee un xogador
    public int nPropiedadesGrupo(int grupo) {
        int conta = 0;

        for (Casilla casilla : propiedades) {
            if (casilla.getGrupo().getId() == grupo) {
                conta++;
            }
        }
        return conta;
    }

    public String estatisticasImprimir() {
        String texto = "########## Estatisticas ##########\n\n"
                + "Nome: " + this.getNome() + "\n"
                + "Dinero invertido: " + cartosInvertidos + " GM\n"
                + "Pago tasxas e impostos: " + pagoTasasEImportos + " GM\n"
                + "Pago de alugueres: " + getPagoDeAlquileres() + " GM\n"
                + "Cobro de alquileres: " + CobroDeAlquileres + " GM\n"
                + "Pasar por saida: " + pasarPorSalida + " GM\n"
                + "Inversións ou bote: " + premiosInversionsOBote + " GM\n"
                + "Veces no cárcere: " + vecesNoCarcere + "\n\n"
                + "##################################\n";
        return texto;

    }

    public void imprimirXogador() {
        System.out.println("Nombre: " + this.getNome());
        System.out.println("Avatar: " + this.getAvatar().getId());
    }

    @Override
    public String toString() {
        if (avatar == null) {
            String texto = "\nXogador:\n"
                    + "\n" + "\tNome: " + this.nome
                    + "\n" + "\tFortuna: " + this.fortuna
                    + "\n" + "\tCartos gastados: " + this.cartosGastados
                    + "\n" + this.imprimirPropiedades()
                    + "\n";
            return texto;
        } else {
            String texto = "\nXogador:\n"
                    + "\tAvatar: " + this.avatar.getId()
                    + "\n" + "\tNome: " + this.nome
                    + "\n" + "\tFortuna: " + this.fortuna
                    + "\n" + "\tCartos gastados: " + this.cartosGastados
                    + "\n" + this.imprimirPropiedades()
                    + "\n";
            return texto;
        }

    }
}
*/

public class Xogador{
    private Avatar avatar;
    private String nome;
    private float fortuna;
    private ArrayList<Propiedade> propiedades;
    private int nVoltas;
    private float cartosInvertidos;
    private float pagoTasasEImportos;
    private float pagoDeAlquileres;
    private float cobroDeAlquileres;
    private float pasarPorSalida;
    private float premiosInversionsOBote;

    public float cartosGastados()
    {
       return  cartosInvertidos + pagoTasasEImportos + pagoDeAlquileres;
    }

}