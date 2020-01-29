package InterfazGrafica;

import estrutura.Casilla;

import javax.swing.*;
import java.awt.*;

public class DescripVenta extends JDialog {
    private JPanel panel;
    private JTextPane info;
    private JButton imaxe;

    public DescripVenta(Frame owner, Casilla casilla)
    {
        super(owner);

        //Obtemos as dimensións da pantalla para a xestión da ubicación
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;

        //Configuramos a benta
        setLocation(width/4, height/4);
        this.setSize(width/2, (int) Math.ceil(height/1.5));
        this.setResizable(false); //Non redimnsionable
        this.setTitle(" ");
        this.setVisible(true);


        //Configuramos o panel
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.panel.setVisible(true);
        this.add(panel);

        this.info = new JTextPane();
        this.info.setSize(width/3, height/2);
        this.info.setPreferredSize(new Dimension(width/4, (int) Math.ceil(height/1.5)));
        this.info.setEditable(false);
        this.info.setFont(new Font("arial",0,14));
        this.info.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        this.panel.add(info, BorderLayout.EAST);


        //Imaxe
        this.imaxe = new JButton();
        this.panel.add(this.imaxe, BorderLayout.WEST);
        this.imaxe.setPreferredSize(new Dimension(width/4, (int) Math.ceil(height/1.5)));
        this.imaxe.setEnabled(false);



        //FUnción para engadir informacion
        this.inciarDescripVenta(casilla);
    }

    public void inciarDescripVenta(Casilla casilla)
    {
        this.info.setText(casilla.imprimirCasilla());
        this.info.setVisible(true);

        //this.imaxe.setIcon( new ImageIcon(casilla.getImaxedescrip().getImage().getScaledInstance(imaxe.getWidth(), imaxe.getHeight(), Image.SCALE_SMOOTH)));


    }

}
