package InterfazGrafica;

import estrutura.Casilla;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class DescripVenta extends JDialog {

    private JPanel panel;
    private JPanel este;
    private JPanel leste;
    private JTextArea info;
    private JButton imaxe;
    private JPanel aux;

    public DescripVenta(Frame owner, Casilla casilla) {
        super(owner);

        //Obtemos as dimensións da pantalla para a xestión da ubicación
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;

        //Configuramos a venta
        this.setSize(920, 480);
        this.setBackground(Color.BLUE);
        int localizacionAncho = (int) Math.round(width / 2) - (int) Math.round(getSize().getWidth() / 2);
        int localizacionAlto = (int) Math.round(height / 2) - (int) Math.round(getSize().getHeight() / 2);
        this.setLocation(localizacionAncho, localizacionAlto);
        this.setResizable(false); //Non redimensionable
        this.setTitle(" ");
        this.setVisible(true);

        //Configuramos o panel base
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.add(panel);

        //Construimos paneles auxiliares
        this.leste = new JPanel();
        this.leste.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
        this.leste.setSize(leste.getPreferredSize());
        this.panel.add(leste, BorderLayout.WEST);
        this.leste.setLayout(new BorderLayout());
        this.este = new JPanel();
        this.este.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
        this.panel.add(este, BorderLayout.EAST);
        this.este.setLayout(new BorderLayout());

        //Introducimos os espazos adicionais para o JButton
        JPanel panelNorte = new JPanel();
        panelNorte.setPreferredSize(new Dimension(getWidth() / 2, 30));
        this.leste.add(panelNorte, BorderLayout.NORTH);

        JPanel panelSur = new JPanel();
        panelSur.setPreferredSize(new Dimension(getWidth() / 2, 30));
        this.leste.add(panelSur, BorderLayout.SOUTH);

        JPanel panelOeste = new JPanel();
        panelOeste.setPreferredSize(new Dimension(50, leste.getHeight()));
        this.leste.add(panelOeste, BorderLayout.WEST);

        JPanel panelEste = new JPanel();
        panelEste.setPreferredSize(new Dimension(50, leste.getHeight()));
        this.leste.add(panelEste, BorderLayout.EAST);

        //Poñemos transparente o fondo
        /*panel.setOpaque(false);
        leste.setOpaque(false);
        este.setOpaque(false);
        panelNorte.setOpaque(false);
        panelOeste.setOpaque(false);
        panelEste.setOpaque(false);
        panelSur.setOpaque(false);*/
        leste.setBackground(Color.red);
        este.setBackground(Color.yellow);
        panelNorte.setBackground(Color.black);
        panelOeste.setBackground(Color.orange);
        panelEste.setBackground(Color.GREEN);
        panelSur.setBackground(Color.MAGENTA);
        panel.setOpaque(false);
        leste.setOpaque(false);
        este.setOpaque(false);
        panelNorte.setOpaque(false);
        panelOeste.setOpaque(false);
        panelEste.setOpaque(false);
        panelSur.setOpaque(false);
        

        //Introuducimos Jbutton
        JPanel panelImaxe = new JPanel();
        int ancho = 360;
        int alto = 420;
        panelImaxe.setPreferredSize(new Dimension(ancho, alto));
        panelImaxe.setSize(panelImaxe.getPreferredSize());
        //FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        this.imaxe = new JButton();
        this.imaxe.setPreferredSize(panelImaxe.getPreferredSize());
        this.imaxe.setSize(imaxe.getPreferredSize());
        this.imaxe.setEnabled(true);
        panelImaxe.add(imaxe);
        this.leste.add(panelImaxe, BorderLayout.CENTER);

        //Texto
        this.info = new JTextArea();
        this.info.setPreferredSize(new Dimension(width / 3, (int) Math.ceil(height / 1.5)));
        this.este.add(info, BorderLayout.CENTER);
        this.info.setFont(new Font("arial", 0, 14));
        this.info.setOpaque(false);
        this.info.setEditable(false);

        //Situar texto
        this.aux = new JPanel();
        this.aux.setPreferredSize(new Dimension(width / 4, (int) Math.ceil(height / 12)));
        this.este.add(aux, BorderLayout.NORTH);

        //Función para engadir informacion
        this.inciarDescripVenta(casilla);
    }

    public void inciarDescripVenta(Casilla casilla) {
        this.info.setText(casilla.imprimirCasilla());
        this.info.setVisible(true);

        if (casilla.getImaxedescrip() != null) {
            this.imaxe.setIcon(new ImageIcon(casilla.getImaxedescrip().getImage().getScaledInstance(imaxe.getWidth(), imaxe.getHeight(), Image.SCALE_SMOOTH)));
        }
    }

}
