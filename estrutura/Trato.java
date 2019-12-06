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
    private float dineroPropon;
    private float dineroRecibe;
    private int nTurnos;

    public Trato(Xogador xogcrea)
    {
        xogadorPropon = xogcrea;
        xogadorRecibe = null;
        propPropon = null;
        propRecibe = null;
        propAlRecibe = null;
        dineroPropon = 0;
        dineroRecibe = 0;
        nTurnos = 0;
    }
    public Trato(Xogador xogProp, Xogador xogRec, Propiedade propProp, Propiedade propRec, Propiedade propAl, float dineroProp, float dineroRec, int numturn)
    {
        this.xogadorPropon = xogProp;
        this.xogadorRecibe = xogRec;
        this.propPropon = propProp;
        this.propRecibe = propRec;
        this.propAlRecibe = propAl;
        this.dineroPropon = dineroProp;
        this.dineroRecibe = dineroRec;
        this.nTurnos = numturn;
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

        dineroPropon=Float.parseFloat(buffer); //Diéiro a pagar
        if (xogadorPropon.getFortuna() < dineroPropon)
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
        if (dineroPropon == 0)
        {
            buffer = consola.ler("Te gustaría pedir dinero a cambio? 0 par non");
            if (Float.parseFloat(buffer)>=0)
            {
                dineroRecibe = Float.parseFloat(buffer);
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

    public void aceptarTrato()
    {
        final ConsolaNormal consola = new ConsolaNormal();
        if (xogadorRecibe.getFortuna() < dineroRecibe)
            {
                consola.imprimir("Non tes suficientes cartos para aceptar o trato.");
                return;
            }

        if (xogadorPropon.getFortuna() < dineroPropon)
            return;
            //excepcion
        //Paga 1 a 2
        xogadorRecibe.setFortuna(xogadorRecibe.getFortuna() + dineroPropon);
        xogadorPropon.setFortuna(xogadorPropon.getFortuna() - dineroPropon);
        //Paga 2 a 1
        xogadorRecibe.setFortuna(xogadorRecibe.getFortuna() - dineroRecibe);
        xogadorPropon.setFortuna(xogadorPropon.getFortuna() + dineroRecibe);

        if (propPropon != null)
        {
            //introducimos novo dono
            propPropon.setDono(xogadorRecibe);
            xogadorRecibe.engadirPropiedade(propPropon);

            //Eliminamos do dono previo
            xogadorPropon.eliminarPropiedade(propPropon);
        }

        if (propRecibe != null)
        {
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
        String trato="";
        if (xogadorRecibe != null)
        {
             trato = "\n--------TRATO--------"+
                     "\nPropon: " + xogadorPropon.getNome()+
                     "\nA: " + xogadorRecibe.getNome() +
                     "\nCambiar:";
             if (propPropon != null)
                 trato += "\nPropiedad: " + propPropon.getNome();

             if (dineroPropon > 0)
             {
                 trato += "\n Diñeiro: " + dineroPropon + "GM";
             }

             trato += "\n Por:";
            if (propRecibe != null)
                trato += "\nPropiedad: " + propRecibe.getNome();

            if (dineroRecibe > 0)
            {
                trato += "\n Diñeiro: " + dineroRecibe + "GM";
            }

            if (nTurnos > 0)
            {
                trato += "\n No pagar el alquiler de " + propAlRecibe.getNome() +
                        "\n durante " + nTurnos + " turnos.\n";
            }
            return trato;
        }
        else
            return trato;
    }

}
