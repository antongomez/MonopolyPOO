package estrutura;

import consola.ConsolaNormal;
import xogadores.Xogador;

import java.util.ArrayList;

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


    /*
    public void InciarTrato(ArrayList<Xogador> xogadores)
    {
        final ConsolaNormal consola = new ConsolaNormal();
        String buffer;
        boolean xogadoratopado = false;
        boolean pedirProp = false;
        boolean pediralquiler = false;

        do {
            //Xogador a tratar
            buffer = consola.ler("Con quen quere facer o trato?");
            for (int i = 0; i < xogadores.size(); i++)
            {
                if (xogadores.get(i).getNome().equals(buffer))
                {
                    xogadorRecibe = xogadores.get(i);
                    xogadoratopado=true;
                }
            }
        }while(!xogadoratopado);

        //Dñeiro propon
        buffer = consola.ler("Canto diñeiro lle gustaría ofertar? 0 para indicar nada.");
        if (Float.parseFloat(buffer)>=0)
        {
            //Excepcion
            return;
        }

        cartosProp=Float.parseFloat(buffer); //Diéiro a pagar
        if (xogadorPropon.getFortuna() < cartosProp)
        {
            //excepcion
        }

        //Porpiedade
        buffer = consola.ler("Desexa ofertar unha propiedade?");//propieade a cambiar
        for (int i = 0; i < xogadorPropon.getPropiedades().size(); i++)
        {
            if (xogadorPropon.getPropiedades().get(i).getNome().equals(buffer))
            {
                propPropon = xogadorPropon.getPropiedades().get(i);
            }
        }

        //Pedir dinero a cambio
        if (cartosProp == 0)
        {
            buffer = consola.ler("Te gustaría pedir dinero a cambio? 0 par non");
            if (Float.parseFloat(buffer)>=0)
            {
                cartosRec = Float.parseFloat(buffer);
            }
            //else
                //excepcion
        }

        //Pedir propiedade a cambio
        do {

            buffer = consola.ler("Te gustaría obtener una propieade del acuerdo? Non para non.");
            if (!buffer.equals("Nom ")) {
                for (int i = 0; i < xogadorRecibe.getPropiedades().size(); i++) {
                    if (xogadorRecibe.getPropiedades().get(i).getNome().equals(buffer)) {
                        propRecibe = xogadorRecibe.getPropiedades().get(i);
                        pedirProp = true;
                    }
                }

                //ALquiler por turnos
                do {
                    buffer = consola.ler("Desexa pedir alquiler coma parte de pago? Non para non.");
                    for (int i = 0; i < xogadorRecibe.getPropiedades().size(); i++)
                    {
                        if (xogadorRecibe.getPropiedades().get(i).getNome().equals(buffer))
                        {
                            if (xogadorRecibe.getPropiedades().get(i).getNome().equals(propRecibe.getNome()))
                                consola.imprimir("Esta propidade xa foi seleccionada.");
                            else {
                                propAlRecibe = xogadorRecibe.getPropiedades().get(i);
                                buffer = consola.ler("Durante cantos turnos?");
                                if (Integer.parseInt(buffer)>0){
                                    nTurnos = Integer.parseInt(buffer);
                                    pedirProp = true;
                                    pediralquiler = true;
                                }
                                //else
                                    //Excpción
                            }
                        }
                    }
                    if (buffer.equals("Non"))
                    {
                        pedirProp = true;
                        pediralquiler = true;
                    }
                    else
                        consola.imprimir("O xogador co que trata non posee esa propiedade.");
                }while (!pediralquiler);


                if (!pedirProp) {
                    pedirProp = false;
                    consola.imprimir("O xogador co que trata non posee esa propiedade.");
                }
            } else
                pedirProp = true;
        }while(!pedirProp);
    }*/
    public void aceptarTrato() {
        final ConsolaNormal consola = new ConsolaNormal();
        if (xogadorRecibe.getFortuna() < cartosRec) {
            consola.imprimir("Neste momento, non tes suficientes cartos para "
                    + "aceptar o trato. Debes conseguir "
                    + (cartosRec - xogadorRecibe.getFortuna()) + " GM.");
            return;
        }

        if (xogadorPropon.getFortuna() < cartosProp) {
            consola.imprimir("O trato non pode ser aceptado ata que o xogador "
                    + "que propuxo o trato consiga " + cartosProp + " GM.");
            return;
        }
        //excepcion
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

        //XestionAlquilares
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
