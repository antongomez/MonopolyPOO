package InterfazGrafica;

import estrutura.Casilla;

import javax.swing.*;
import java.awt.*;

public class DescripVenta extends JDialog {
    private JPanel panel;
    private JTextArea info;

    public DescripVenta(Frame owner, Casilla casilla)
    {
        super(owner);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setLocation(width/4, height/4);

        this.setSize(width/3, height/2);
        this.setResizable(false);
        this.setTitle(" ");

        this.setVisible(true);

        this.panel = new JPanel();
        this.panel.setVisible(true);
        this.info = new JTextArea();
        this.inciarDescripVenta(casilla);
        this.add(panel);

        this.info.setSize(width/3, height/2);
        this.info.setPreferredSize(new Dimension(width/3, height/2));
        this.info.setEditable(false);
        this.info.setFont(new Font("tahoma",0,12));
    }

    public void inciarDescripVenta(Casilla casilla)
    {
        this.info.setText(casilla.imprimirCasilla());
        this.info.setVisible(true);
        this.panel.add(info);

    }

}
