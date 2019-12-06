package estrutura;

import consola.ConsolaNormal;
import xogadores.Xogador;

import java.util.ArrayList;

public class Trato {

    private  Xogador xogadorPropon;
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

    public void InciarTrato(ArrayList<Xogador> xogadores)
    {
        final ConsolaNormal consola = new ConsolaNormal();
        String buffer;
        boolean xogadoratopado = false;
        boolean propiedadeatopadaPropon = false;
        boolean propiedadeatopadaRecibe = false;

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

        buffer = consola.ler("Canto diñeiro lle gustaría ofertar 0 para indicar nada.");
        if (Float.parseFloat(buffer)>=0)
        {
            dineroPropon=Float.parseFloat(buffer); //Diéiro a pagar
            if (xogadorPropon.getFortuna() < dineroPropon)
            {
                //excepcion
            }
            buffer = consola.ler("Desexa ofertar unha propiedade?");//propieade a cambiar
            for (int i = 0; i < xogadorPropon.getPropiedades().size(); i++)
            {
                if (xogadorPropon.getPropiedades().get(i).getNome().equals(buffer))
                {
                    propPropon = xogadorPropon.getPropiedades().get(i);
                    propiedadeatopadaPropon = true;
                }
            }

            if (dineroPropon == 0)
            {
                buffer = consola.ler("Desexa ofertar unha propiedade?");
                if (Float.parseFloat(buffer)>=0)
                {
                    dineroRecibe = Float.parseFloat(buffer);
                }
                else
                    //excepcion
            }

        }
        else
            //Excepcion


    }

    public void

}
