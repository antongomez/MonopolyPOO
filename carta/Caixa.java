package carta;

import xogadores.Xogador;

public class Caixa extends Carta {

    public Caixa(String nome)
    {
        super(nome);
    }

    public void accion(Xogador xogador)
    {
        String partes[] = super.getNome().split("-");
        switch (partes[1])
        {
            case "1": this.Carta1(xogador);
                break;
            case "2": this.Carta2(xogador);
                break;
            case "3": this.Carta3(xogador);
                break;
            case "4": this.Carta2(xogador);
                break;
        }
    }

    @Override
    public void Carta1(Xogador xogador)
    {
        String accion ="Carta de Caixa 1\n";
        accion += "Paga 60 GM por un fin de semana nun balneario de 5 estrelas en Corrubedo.\n";
        System.out.println(accion);
        xogador.modificarFortuna(-60);
        System.out.println("O xogador " + xogador.getNome() + " pagou 60 GM.\n");

    }
    public void Carta2(Xogador xogador)
    {
        String accion ="Carta de Caixa 2\n";
        accion += "Devolución de Facenda. Cobra 60 GM.\n";
        System.out.println(accion);
        xogador.modificarFortuna(60);
        System.out.println("O xogador " + xogador.getNome() + " ganhou 60 GM.\n");
    }
    public void Carta3(Xogador xogador)
    {
        String accion ="Carta de Caixa 2\n";
        accion += "Recibe 125 GM de beneficios por alquilar os servicios do teu Jet Privado.\n";
        System.out.println(accion);
        xogador.modificarFortuna(125);
        System.out.println("O xogador " + xogador.getNome() + " ganhou 125 GM.\n");
    }

}
