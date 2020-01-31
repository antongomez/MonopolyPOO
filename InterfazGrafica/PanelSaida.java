package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.security.Principal;

public class PanelSaida extends JPanel {

    private JPanel principal;
    private JTextArea terminal;

    public PanelSaida(Dimension dimension) {
        this.setPreferredSize(dimension);
        this.setOpaque(false);
        this.setVisible(true);
        this.setBackground(new Color(199, 255, 185));

        principal = new JPanel();
        principal.setPreferredSize(dimension);
        principal.setOpaque(false);
        principal.setVisible(true);
        this.add(principal);

        terminal = new JTextArea(15, 25);
        terminal.setForeground(Color.GREEN);
        terminal.setBackground(Color.BLACK);
        terminal.setVisible(true);
        terminal.setPreferredSize(dimension);
        terminal.setSize(terminal.getPreferredSize());
        this.add(terminal);

        /*JScrollPane scroll = new JScrollPane(terminal,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setSize(terminal.getSize());
        this.principal.add(scroll);*/
    }

    public void terminal(String saida) {
        terminal.append(saida + "\n");
    }

}
