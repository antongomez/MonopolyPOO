package estrutura;

import errosExternos.*;
import xogadores.*;
import xogo.*;

public class Trato {

    private String nome;
    private Xogador xogadorPropon;
    private Xogador xogadorRecibe;
    private Propiedade propPropon;
    private Propiedade propRecibe;
    private Propiedade propAlRecibe;
    private float cartosProp;
    private float cartosRec;
    private int nTurnos;

    public Trato(Xogador xogcrea) {
        xogadorPropon = xogcrea;
        xogadorRecibe = null;
        propPropon = null;
        propRecibe = null;
        propAlRecibe = null;
        cartosProp = 0;
        cartosRec = 0;
        nTurnos = 0;
    }

    public Trato(int nome, Xogador xogProp, Xogador xogRec, Propiedade propProp,
            Propiedade propRec, Propiedade propAl, float cartosProp,
            float cartosRec, int nTurnos) {

        this.nome = "trato" + nome;
        this.xogadorPropon = xogProp;
        this.xogadorRecibe = xogRec;
        this.propPropon = propProp;
        this.propRecibe = propRec;
        this.propAlRecibe = propAl;
        this.cartosProp = cartosProp;
        this.cartosRec = cartosRec;
        this.nTurnos = nTurnos;
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Xogador getXogadorPropon() {
        return xogadorPropon;
    }

    public void setXogadorPropon(Xogador xogadorPropon) {
        this.xogadorPropon = xogadorPropon;
    }

    public Xogador getXogadorRecibe() {
        return xogadorRecibe;
    }

    public void setXogadorRecibe(Xogador xogadorRecibe) {
        this.xogadorRecibe = xogadorRecibe;
    }

    public Propiedade getPropPropon() {
        return propPropon;
    }

    public void setPropPropon(Propiedade propPropon) {
        this.propPropon = propPropon;
    }

    public Propiedade getPropRecibe() {
        return propRecibe;
    }

    public void setPropRecibe(Propiedade propRecibe) {
        this.propRecibe = propRecibe;
    }

    public Propiedade getPropAlRecibe() {
        return propAlRecibe;
    }

    public void setPropAlRecibe(Propiedade propAlRecibe) {
        this.propAlRecibe = propAlRecibe;
    }

    public float getCartosProp() {
        return cartosProp;
    }

    public void setCartosProp(float cartosProp) {
        this.cartosProp = cartosProp;
    }

    public float getCartosRec() {
        return cartosRec;
    }

    public void setCartosRec(float cartosRec) {
        this.cartosRec = cartosRec;
    }

    public int getnTurnos() {
        return nTurnos;
    }

    public void setnTurnos(int nTurnos) {
        this.nTurnos = nTurnos;
    }

    public void aceptarTrato() throws TratoNonAceptado {

        if (xogadorRecibe.getFortuna() < cartosRec) {
            throw new TratoNonAceptado("Neste momento, non tes suficientes cartos para "
                    + "aceptar o trato. Debes conseguir "
                    + (cartosRec - xogadorRecibe.getFortuna()) + " GM.");
        }

        if (xogadorPropon.getFortuna() < cartosProp) {
            throw new TratoNonAceptado("O trato non pode ser aceptado ata que o xogador "
                    + "que propuxo o trato consiga " + cartosProp + " GM.");
        }

        //Paga 1 a 2
        xogadorRecibe.modificarFortuna(cartosProp);
        xogadorPropon.modificarFortuna(-cartosProp);
        //Paga 2 a 1
        xogadorRecibe.modificarFortuna(-cartosRec);
        xogadorPropon.modificarFortuna(cartosRec);

        if (propPropon != null) {
            //introducimos novo dono
            propPropon.setDono(xogadorRecibe);
            xogadorRecibe.engadirPropiedade(propPropon);

            //Eliminamos do dono previo
            xogadorPropon.eliminarPropiedade(propPropon);
        }

        if (propRecibe != null) {
            //introducimos novo dono
            propRecibe.setDono(xogadorPropon);
            xogadorPropon.engadirPropiedade(propRecibe);

            //Eliminamos do dono previo
            xogadorRecibe.eliminarPropiedade(propRecibe);
        }

        if (propAlRecibe != null) {
            propAlRecibe.engadirExento(xogadorPropon.getNome(), nTurnos);
            Xogo.consola.imprimir("O xogador " + xogadorPropon.getNome()
                    + " está exento de pagar o aluguer de " + propAlRecibe.getNome()
                    + " durante " + nTurnos + ".");
        }
    }

    @Override
    public String toString() {
        String trato = "";
        if (xogadorRecibe != null) {
            trato = "\n--------" + nome.toUpperCase() + "--------"
                    + "\nPropon: " + xogadorPropon.getNome()
                    + "\n A: " + xogadorRecibe.getNome()
                    + "\nCambiar:";
            if (propPropon != null) {
                trato += "\nPropiedade: " + propPropon.getNome();
            }

            if (cartosProp > 0) {
                trato += "\n Cartos: " + cartosProp + "GM";
            }

            trato += "\n Por:";
            if (propRecibe != null) {
                trato += "\nPropiedade: " + propRecibe.getNome();
            }

            if (cartosRec > 0) {
                trato += "\n Cartos: " + cartosRec + "GM";
            }

            if (nTurnos > 0) {
                trato += "\n Non pagar o aluguer de " + propAlRecibe.getNome()
                        + "\n durante " + nTurnos + " turnos.\n";
            }
            return trato;
        } else {
            return trato;
        }
    }

}
