/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

import xogo.*;

public class Especial extends Casilla {

    private float valor;

    public Especial(String nome, int posicion) {
        super(nome, posicion);
        switch (nome) {
            case "Saida":
                this.valor = Constantes.VALOR_VOLTA;
                break;
            case "Carcere":
                this.valor = Constantes.SAIR_CARCERE;
                break;
            default:
                this.valor = 0;
                break;
        }
    }

    //Getters e Setters
    public float getValor() {
        return valor;
    }
    
    public void setValor(float valor){
        this.valor = valor;
    }

    public void modificarBoteParking(float valor) {
        if (this.getNome().equals("Parking")) {
            this.valor += valor;
        } else {
            //Excepcion
        }
    }

    @Override
    public String imprimirCasilla() {
        String texto = "{\n"
                + "\tnome:" + this.getNome() + ",\n";
        switch (this.getNome()) {
            case "Parking":
                texto += "\tbote: " + valor + ",\n";
                break;
            case "Saida":
                texto += "\tcobro por saída: " + valor + ",\n";
                break;
            case "Carcere":
                texto += "\tcobro por sair do cárcere: " + valor + ",\n";
                break;

        }
        texto += "\txogadores: [" + "]\n"
                + "}\n";

        return texto;
    }
//Fin clase
}
