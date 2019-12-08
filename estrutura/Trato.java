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

        this.nome = "TRATO" + nome;
        this.xogadorPropon = xogProp;
        this.xogadorRecibe = xogRec;
        this.propPropon = propProp;
        this.propRecibe = propRec;
        this.propAlRecibe = propAl;
        this.cartosProp = cartosProp;
        this.cartosRec = cartosRec;
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
            consola.imprimir("Non tes suficientes cartos para aceptar o trato.");
            return;
        }

        if (xogadorPropon.getFortuna() < cartosProp) {
            return;
        }
        //excepcion
        //Paga 1 a 2
        xogadorRecibe.setFortuna(xogadorRecibe.getFortuna() + cartosProp);
        xogadorPropon.setFortuna(xogadorPropon.getFortuna() - cartosProp);
        //Paga 2 a 1
        xogadorRecibe.setFortuna(xogadorRecibe.getFortuna() - cartosRec);
        xogadorPropon.setFortuna(xogadorPropon.getFortuna() + cartosRec);

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
            trato = "\n--------" + nome + "--------"
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
