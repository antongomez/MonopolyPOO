package carta;

import estrutura.*;
import estrutura.Edificio;
import xogadores.*;

public class Sorte extends Carta {


    public Sorte(String nome, String accion)
    {
        super(nome, accion);
    }

    public void accion(Xogador xogador)
    {
        String accion = "";
        String partes[] = super.getNome().split("-");
        switch (partes[1])
        {
            case "1": this.Carta1(xogador);
                break;
            case "2": this.Carta2(xogador);
                break;
            case "3": this.Carta3(xogador);
                break;
        }
    }

    @Override
    public void Carta1(Xogador xogador)
    {
        String accion ="Carta de Sorte 1";
        accion += "O aumento do imposto sobre bens inmobles afecta a todas as túas propiedades. "
                + "Paga 50 GM por  casa, 145 GM por hotel, 25 GM por piscina e 90 GM por pista de deporte.\n";
        System.out.println(accion);
        this.pagarImpostoInmoble(xogador);
    }
    public void Carta2(Xogador xogador)
    {
        String accion ="Carta de Sorte 2";
        accion += "Vendes a túa Lancha. Cobra 60 GM.\n";
        System.out.println(accion);
        xogador.modificarFortuna(60);
        System.out.println("O xogador " + xogador.getNome() + " ganhou 60 GM.\n");
    }
    public void Carta3(Xogador xogador)
    {
        String accion ="Carta de Sorte 3";
        accion += "Ganhaches o bote da lotaría! Recibe 125 GM.\n";
        System.out.println(accion);
        xogador.modificarFortuna(125);
        System.out.println("O xogador " + xogador.getNome() + " ganhou 125 GM.\n");
    }
    public void Carta4(Xogador xogador)
    {
        String accion ="Carta de Sorte 4";
        accion += "Paga 185 GM pola matrícula do colexio privado.\n";
        System.out.println(accion);
        xogador.modificarFortuna(-185);
        System.out.println("O xogador " + xogador.getNome() + " pagou 185 GM.\n");

    }


    private void pagarImpostoInmoble(Xogador xogador) {
        if (xogador != null) {
            int casas = 0, hoteis = 0, pistas = 0, piscinas = 0;
            float total;
            for (Casilla casilla : xogador.getPropiedades()) {
                for (Edificio edificio : casilla.getEdificios()) {
                    if (edificio instanceof Casa) {
                        casas++;
                    } else if (edificio instanceof Hotel) {
                        hoteis++;
                    } else if (edificio instanceof Piscina) {
                        piscinas++;
                    } else if (edificio instanceof Pista) {
                        pistas++;
                    }
                }
            }
            total = 50 * casas + 145 * hoteis + 25 * piscinas + 90 * pistas;
            xogador.modificarFortuna(-total);
            xogador.setPagoTasasEImportos(total);
            System.out.println("\nO xogador " + xogador.getNome() + " pagou " + total
                    + " GM, xa que ten " + casas + " casas, " + hoteis
                    + " hoteis, " + piscinas + " piscinas e " + pistas + " pistas de deporte.\n");
        } else {
            System.out.println("\nErro en pagarImpostoInmoble.\n");
        }
    }
}
