/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepcions;

/**
 *
 * @author Anton
 */
public class ErroSintaxe extends MonoExcep{
    public ErroSintaxe(String mensaxe) {
        super(mensaxe);
    }

    public ErroSintaxe() {
        super("A casilla non é da túa propiedade");
    }
}
