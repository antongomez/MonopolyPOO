package Excepcions;

import consola.Consola;

public class Comprar  extends MonoExcep{
    public Comprar(String mensaxe) {
        super(mensaxe);
        System.out.println("\n Comprar:");
    }
}
