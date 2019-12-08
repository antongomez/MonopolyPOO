/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carta;

import xogadores.Xogador;

public abstract class Carta {
    private String nome;
    private String accion;

    public Carta(String nome, String accion)
    {
        this.nome = nome;
        this.accion = accion;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public abstract void accion(Xogador xogador);

    public abstract void Carta1(Xogador xogador);
}
