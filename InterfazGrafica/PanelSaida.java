package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.security.Principal;

public class PanelSaida extends JPanel {
    private JPanel principal;
    private JTextArea terminal;

    public PanelSaida(Dimension dimension)
    {
        principal = new JPanel();
        principal.setPreferredSize(dimension);
        principal.setOpaque(false);
        principal.setVisible(true);


        terminal = new JTextArea();
        terminal.setForeground(Color.GREEN);
        terminal.setBackground(Color.BLACK);
        terminal.setVisible(true);
    }

}
