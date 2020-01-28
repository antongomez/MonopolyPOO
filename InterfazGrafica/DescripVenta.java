package InterfazGrafica;

import estrutura.Casilla;

import javax.swing.*;
import java.awt.*;

public class DescripVenta extends JDialog {
    private JLabel info;

    public DescripVenta(Frame owner, Casilla casilla)
    {
        super(owner);
        this.inciarDescripVenta(casilla);

    }

    public void inciarDescripVenta(Casilla casilla)
    {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setLocation(width/4, height/4);

        this.setSize(width/3, height/3);
        this.setResizable(false);
        this.setTitle(" ");

        this.setVisible(true);

        this.info.setText(casilla.imprimirCasilla());

    }

}
