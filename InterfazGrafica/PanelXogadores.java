package InterfazGrafica;

import xogadores.Xogador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelXogadores extends JPanel {
    private JTabbedPane panel;
    private JPanel aux;
    private ArrayList<JPanel> paneisxog;


    public PanelXogadores()
    {
        //Iniciamos panel
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;

        this.setPreferredSize(new Dimension(width/2, height/2));
        this.setBackground(Color.CYAN);

        panel = new JTabbedPane();
        panel.setPreferredSize(new Dimension(width/2, height/2));
        this.add(panel);
        panel.setVisible(true);
        this.panel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        PestañaXogador();

        this.panel.add("ejemplo",aux);

        paneisxog = new ArrayList<>();
    }

    public void PestañaXogador()
    {
        //Creamos paneis e text area
        aux = new JPanel();
        aux.setVisible(true);
        JTextArea info = new JTextArea();
        info.setVisible(true);
        info.setText("proba");
        aux.setPreferredSize(new Dimension(200,100));
        info.setPreferredSize(new Dimension(200,100));


        //Introducimos
        aux.add(info);
    }

    public void actualizar(ArrayList<Xogador> xogadores)
    {
        for (int i = 0;  i < this.paneisxog.size(); i++)
        {
            this.paneisxog.remove(this.paneisxog.size() -1);
        }

        JPanel panaux;
        JTextArea textaux;

        for (int i = 0; i < xogadores.size(); i++)
        {
            panaux = new JPanel();
            textaux = new JTextArea();


            //Fijamos tamaños
            panaux.setPreferredSize(new Dimension(this.getWidth()-10,this.getHeight()-10));
            textaux.setPreferredSize(new Dimension(this.getWidth()-10,this.getHeight()-10));

            //Introducimos la informacion
            textaux.setText(xogadores.get(i).toString());


            //Visibilidad
            textaux.setVisible(true);
            panaux.setVisible(true);

            panaux.add(textaux);
            this.paneisxog.add(panaux);
            this.panel.add(xogadores.get(i).getNome(), panaux);
        }
    }

}