package carta;

import xogadores.Xogador;

public class Caixa extends Carta {

    public Caixa(String nome)
    {
        super(nome);
    }

    public void accion(Xogador xogador)
    {
        String accion = "";
        String partes[] = super.getNome().split("-");
        switch (partes[0])
        {

        }
    }

    @Override
    public void Carta1(Xogador xogador) {

    }
}
