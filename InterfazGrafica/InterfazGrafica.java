package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import Evento.XestionMenus;
import java.awt.Color;

public class InterfazGrafica extends JFrame {

    private PanelComandos panelComandos;
    private PanelDereito tab;

    /**
     *
     */
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
        this.setMinimumSize(new Dimension(800, 600));
        this.setSize(new Dimension(1000, 700));
    }

    private void iniciarComponhentes() {

        this.tab = new PanelDereito(this);
        this.panelComandos = new PanelComandos(this);
    }

    private void setupComponents() {

        //this.panelComandos.setPreferredSize(new Dimension(300, 400));
        this.panelComandos.setBackground(Color.red);
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
