package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.security.Principal;

public class PanelSaida extends JPanel {

    private JTextArea terminal;

    public PanelSaida(Dimension dimension) {
        this.setPreferredSize(dimension);
        this.setOpaque(false);
        this.setVisible(true);
        this.setBackground(new Color(199, 255, 185));
        this.setLayout(new BorderLayout(0, 0));

        terminal = new JTextArea(20, 25);
        terminal.setForeground(Color.GREEN);
        terminal.setBackground(Color.BLACK);
        terminal.setEditable(false);
        terminal.setVisible(true);
        this.add(terminal, BorderLayout.CENTER);
        JPanel aux = new JPanel();
        aux.setOpaque(false);
        aux.setPreferredSize(new Dimension((int) Math.round(dimension.getWidth()), 15));
        this.add(aux, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(terminal,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setSize(terminal.getSize());
        this.add(scroll);
    }

    public void terminal(String saida) {
        terminal.append(saida + "\n");
    }

}
