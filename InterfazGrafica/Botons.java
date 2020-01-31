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
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(panex.getWidth(),panex.getHeight()/3));
        this.setSize(this.getPreferredSize());

        this.setBackground(new Color(199,255,185));

        //this.setLayout(new BorderLayout());

        lanzardados = new JButton("Lanzar dados");
        lanzardados.setFont(new Font("cooper black",3, 20));
        lanzardados.setBackground(Color.red);
        lanzardados.setForeground(Color.white);
        lanzardados.setVisible(true);
        lanzardados.setPreferredSize(new Dimension(this.getWidth()/3, 40));
        lanzardados.setSize(lanzardados.getPreferredSize());
        lanzardados.setOpaque(true);
        ImageIcon imaxe = new ImageIcon("dados.png");
        lanzardados.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(lanzardados.getWidth(), lanzardados.getHeight(), Image.SCALE_SMOOTH)));



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
        this.add(lanzardados);
    }
}
