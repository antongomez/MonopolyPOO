package InterfazGrafica;

import Evento.XestionCampoTexto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Botons extends JPanel {
    private JButton lanzardados;
    private JButton comprar;
    private String comando;

    public Botons(PanelEsquerdo panex)
    {
        this.setVisible(true);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(panex.getWidth(),panex.getHeight()/4));
        this.setSize(this.getPreferredSize());

        this.setLayout(new BorderLayout());

        lanzardados = new JButton("Lanzar");
        lanzardados.setVisible(true);
        lanzardados.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()/2));

        MouseListener lanzar = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                panex.getPanelComandos().getIntroducionComando().setComando("lanzar dados");
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
        };

        lanzardados.addMouseListener(lanzar);
        this.add(lanzardados, BorderLayout.CENTER);
    }
}
