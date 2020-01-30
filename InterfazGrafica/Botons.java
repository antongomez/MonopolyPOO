package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Botons extends JPanel {
    private JButton lanzardados;
    private JButton comprar;

    public Botons(PanelEsquerdo panex)
    {
        this.setVisible(true);
        this.setOpaque(false);

        lanzardados = new JButton("Lanzar");
        lanzardados.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
        lanzardados.addMouseListener();
        MouseListener lanzar = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                synchronized (panex.getPanelComandos().getCampoComandos()) {
                    panex.getPanelComandos().getCampoComandos().notifyAll();
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        }


    }
}
