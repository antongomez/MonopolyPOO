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

public class InterfazGrafica extends JFrame {

    //
    private PanelComandos panelComandos;
    private JSplitPane split1;
    private PanelDereito tab;
    private JMenuBar menuBar;
    private JMenu menuAplicacion;

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
        //this.setLocationRelativeTo(null);
        this.setTitle("MONOPOLY GALEGO");
        this.setMinimumSize(new Dimension(200, 200));
        this.setSize(new Dimension(800, 600));
    }

    private void iniciarComponhentes() {

        this.tab = new PanelDereito(this);
        this.panelComandos = new PanelComandos(this);
        this.split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        this.menuBar = new JMenuBar();
        this.menuAplicacion = new JMenu("Aplicacion");
    }

    private void setupComponents() {

        //this.panelComandos.setPreferredSize(new Dimension(300, 300));
        this.split1.setRightComponent(tab);
        this.split1.setLeftComponent(panelComandos);

        this.menuBar.add(menuAplicacion);
    }

    private void layoutComponents() {

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.split1);

        this.setJMenuBar(menuBar);
    }

    private void addEventHandlers() {

    }

    public JMenu getMenuSeleccion() {
        return menuAplicacion;
    }

    public PanelComandos getPanelComandos() {
        return panelComandos;
    }

    public JSplitPane getSplit1() {
        return split1;
    }

    public PanelDereito getTab() {
        return tab;
    }
}
