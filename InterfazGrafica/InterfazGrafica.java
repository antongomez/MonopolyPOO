package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class InterfazGrafica extends JFrame {

    private PanelComandos panelComandos;
    private PanelDereito tab;

    public InterfazGrafica() {
        iniciarVenta();
        iniciarComponhentes();
        setupComponents();
        layoutComponents();
        addEventHandlers();
    }

    private void iniciarVenta() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //A execucion finalizase cando se pecha a venta
        this.setLayout(new BorderLayout());
        this.setTitle("MONOPOLY GALEGO");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        this.setMinimumSize(new Dimension(1000, height - 33));
        this.setSize(new Dimension(width, height - 33));
    }

    private void iniciarComponhentes() {

        this.tab = new PanelDereito(this);
        this.panelComandos = new PanelComandos(this);
    }

    private void setupComponents() {

        this.add(tab, BorderLayout.CENTER);
        this.add(panelComandos, BorderLayout.WEST);
    }

    private void layoutComponents() {

    }

    private void addEventHandlers() {

    }

    public PanelComandos getPanelComandos() {
        return panelComandos;
    }

    public PanelDereito getTab() {
        return tab;
    }
}
