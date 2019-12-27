/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carta;

import xogadores.*;
import estrutura.*;
import java.util.ArrayList;
import errosExternos.*;

public abstract class Carta {

    private String nome;

    public Carta(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract void accion(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes;

    public abstract void Carta1(Xogador xogador);
}
