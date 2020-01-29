package InterfazGrafica;

import estrutura.Casilla;

import javax.swing.*;
import java.awt.*;

public class DescripVenta extends JDialog {
    private JPanel panel;
    private JPanel este;
    private JPanel leste;
    private JTextArea info;
    private JButton imaxe;
    private JPanel aux;



    public DescripVenta(Frame owner, Casilla casilla)
    {
        super(owner);

        //Obtemos as dimensións da pantalla para a xestión da ubicación
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;

        //Configuramos a benta
        setLocation(width/4, height/4);
        this.setSize((int) Math.ceil(width/1.75), (int) Math.ceil(height/1.5));
        this.setResizable(false); //Non redimnsionable
        this.setTitle(" ");
        this.setVisible(true);


        //Configuramos o panel base
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.panel.setVisible(true);
        this.add(panel);

        //Construimos paneles auxiliares
        this.leste = new JPanel();
        this.leste.setPreferredSize(new Dimension(width/4, (int) Math.ceil(height/1.5)));
        this.panel.add(leste,BorderLayout.WEST);
        this.leste.setLayout(new BorderLayout());
        this.leste.setBackground(Color.BLUE);
        this.leste.setOpaque(true);
        this.este = new JPanel();
        this.este.setPreferredSize(new Dimension(width/3, (int) Math.ceil(height/1.5)));
        this.panel.add(este,BorderLayout.EAST);
        this.este.setLayout(new BorderLayout());


        //Introuducimos Jbutton
        this.imaxe = new JButton();
        this.imaxe.setPreferredSize(new Dimension(width/4, (int) Math.ceil(height/1.5)));
        this.imaxe.setEnabled(true);
        this.leste.add(imaxe, BorderLayout.CENTER);


        //Texto
        this.info = new JTextArea();
        this.info.setPreferredSize(new Dimension(width/3, (int) Math.ceil(height/1.5)));
        this.este.add(info, BorderLayout.CENTER);
        this.info.setFont(new Font("arial",0,14));
        this.info.setOpaque(false);
        this.info.setEditable(false);


        //Situar texto
        this.aux = new JPanel();
        this.aux.setPreferredSize(new Dimension(width/4, (int) Math.ceil(height/12)));
        this.este.add(aux, BorderLayout.NORTH);


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
