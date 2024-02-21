package InterfazGrafica;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelDescripcion extends JPanel {

    private ImageIcon imaxe = new ImageIcon("assets/Fondo Descripcion.png");

    @Override
    public void paint(Graphics g) {
        g.drawImage(imaxe.getImage(), 0, 0, getWidth(), getHeight(), this);
        //g.setOpaque(false);
        super.paint(g);
    }
}
