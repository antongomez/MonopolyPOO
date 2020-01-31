package InterfazGrafica;

import estrutura.Casilla;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;
import javax.swing.border.BevelBorder;

public class DescripVenta extends JDialog {

    private PanelDescripcion panel;
    private JPanel este;
    private JPanel leste;
    private JTextArea info;
    private JLabel imaxe;
    private JPanel panelTitulo;
    private Casilla casilla;
    private JLabel titulo;

    public DescripVenta(Frame owner, Casilla casilla) {
        super(owner);

        this.casilla = casilla;
        this.setBackground(new Color(199, 255, 185));

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
        this.setTitle(casilla.getNome());
        this.setVisible(true);

        //Configuramos o panel base
        this.panel = new PanelDescripcion();
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
        this.este.setLayout(new BorderLayout(0, 0));

        //Introducimos os espazos adicionais para o JButton
        JPanel panelNorte = new JPanel();
        panelNorte.setPreferredSize(new Dimension(getWidth() / 2, 15));
        this.leste.add(panelNorte, BorderLayout.NORTH);

        //Introuducimos Jbutton
        JPanel panelImaxe = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        int ancho = 360;
        int alto = 420;

        this.imaxe = new JLabel();
        this.imaxe.setPreferredSize(new Dimension(ancho, alto));
        this.imaxe.setSize(imaxe.getPreferredSize());
        this.imaxe.setEnabled(true);
        this.imaxe.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelImaxe.add(imaxe);
        this.leste.add(panelImaxe, BorderLayout.CENTER);

        //Texto
        this.info = new JTextArea();
        this.info.setPreferredSize(new Dimension(getWidth() / 2, getHeight() - 120));
        this.este.add(info, BorderLayout.SOUTH);
        this.info.setFont(new Font("arial", 0, 14));
        this.info.setOpaque(false);
        this.info.setEditable(false);

        //Situar titulo
        this.panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.titulo = new JLabel(casilla.getNome());
        this.titulo.setOpaque(false);
        this.titulo.setFont(new Font("Alegreya", Font.PLAIN, 30));
        this.titulo.setBorder(null);

        this.panelTitulo.add(titulo);
        this.este.add(panelTitulo, BorderLayout.CENTER);

        JPanel panelNorte2 = new JPanel();
        panelNorte2.setPreferredSize(new Dimension(getWidth() / 2, 30));
        this.este.add(panelNorte2, BorderLayout.NORTH);

        //Función para engadir informacion
        this.inciarDescripVenta(casilla);

        //Poñemos transparente o fondo
        panel.setOpaque(false);
        leste.setOpaque(false);
        este.setOpaque(false);
        panelNorte.setOpaque(false);
        panelNorte2.setOpaque(false);
        panelImaxe.setOpaque(false);
        panelTitulo.setOpaque(false);

        //Plastidecor
        Color cor = new Color(199, 255, 185);
        panel.setBackground(cor);
        este.setBackground(cor);
        leste.setBackground(cor);
        info.setBackground(cor);
        imaxe.setBackground(cor);
        panelTitulo.setBackground(cor);
        panel.setOpaque(true);
        este.setOpaque(true);
        leste.setOpaque(true);
        info.setOpaque(true);
        imaxe.setOpaque(true);
        panelTitulo.setOpaque(true);

    }

    public void inciarDescripVenta(Casilla casilla) {
        this.info.setText(casilla.imprimirCasilla());
        this.info.setVisible(true);
        this.imaxe.setIcon(new ImageIcon(casilla.getImaxedescrip().getImage().getScaledInstance(imaxe.getWidth(), imaxe.getHeight(), Image.SCALE_SMOOTH)));

    }

}
