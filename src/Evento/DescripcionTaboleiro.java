package Evento;

import InterfazGrafica.DescripVenta;
import estrutura.Casilla;
import estrutura.Taboleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DescripcionTaboleiro {
    private JButton boton;
    private Casilla casilla;


    public DescripcionTaboleiro(Frame pai, JButton boton, Casilla casilla)
    {
        this.boton = boton;
        this.casilla = casilla;

        MouseListener ointeDeAccion =  new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                new DescripVenta(pai,casilla);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };
        boton.addMouseListener(ointeDeAccion);
    }
}
