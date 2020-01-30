package InterfazGrafica;

import xogadores.Xogador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelXogadores extends JPanel {

    private JTabbedPane panel;
    private ArrayList<JPanel> paneisxog;

    public PanelXogadores() {
        //Iniciamos panel
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        this.setOpaque(false);

        panel = new JTabbedPane();
        panel.setPreferredSize(getPreferredSize());
        this.add(panel);
        panel.setVisible(true);
        this.panel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        paneisxog = new ArrayList<>();

    }

    public void centrado(JPanel pan) {
        //Establecemos o Layout
        pan.setLayout(new BorderLayout());

        //Obtemos tamaño panel
        int height = this.getHeight();
        int width = this.getWidth();

        //Creamos os paneis
        JPanel superior = new JPanel();
        superior.setPreferredSize(new Dimension(width, height / 10));
        JPanel inferior = new JPanel();
        inferior.setPreferredSize(new Dimension(width, height / 10));
        JPanel este = new JPanel();
        este.setPreferredSize(new Dimension(width / 10, height));
        JPanel leste = new JPanel();
        leste.setPreferredSize(new Dimension(width / 10, height));

        //ENgadimolos
        pan.add(superior, BorderLayout.NORTH);
        pan.add(inferior, BorderLayout.SOUTH);
        pan.add(este, BorderLayout.EAST);
        pan.add(leste, BorderLayout.WEST);

    }

    public void actualizar(ArrayList<Xogador> xogadores) {
        for (int i = 0; i < this.paneisxog.size(); i++) {
            this.paneisxog.remove(this.paneisxog.size() - 1);
        }

        JPanel panaux;
        JTextArea textaux;

        for (int i = 0; i < xogadores.size(); i++) {
            panaux = new JPanel();
            textaux = new JTextArea();

            //Fijamos tamaños
            panaux.setPreferredSize(new Dimension(this.getWidth() - 10, this.getHeight() - 10));
            textaux.setPreferredSize(new Dimension(this.getWidth() - 10, this.getHeight() - 10));

            //Introducimos la informacion
            textaux.setText(xogadores.get(i).toString());

            //Visibilidad
            textaux.setVisible(true);
            panaux.setVisible(true);
            panaux.setOpaque(false);

            //Axustamollo
            centrado(panaux);

            //Establecemos fonte
            textaux.setFont(new Font("arial", 0, 14));

            panaux.add(textaux);
            this.paneisxog.add(panaux);
            this.panel.add(xogadores.get(i).getNome(), panaux);
        }
    }

}
