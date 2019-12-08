package Excepcions;

import consola.Consola;

public class ComprarErr extends MonoExcep{
    public ComprarErr(String mensaxe) {
        super(mensaxe);
        System.out.println("\n Comprar:");
    }
}
