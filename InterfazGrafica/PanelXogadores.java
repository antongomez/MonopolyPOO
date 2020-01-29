package InterfazGrafica;

import javax.swing.*;
import java.awt.*;

public class PanelXogadores extends JPanel {
    private JTabbedPane panel;
    private JPanel paneisxogadores;


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
        this.panel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public void PestañaXOgador()
    {
        JPanel panel = new JPanel();
    }

}