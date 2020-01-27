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
    private JSplitPane split;
    private JTabbedPane tabs;
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

        this.tabs = new JTabbedPane();
        this.panelComandos = new PanelComandos(this);
        this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        this.menuBar = new JMenuBar();
        this.menuAplicacion = new JMenu("Aplicacion");
    }

    /**
     *
     */
    private void setupComponents() {
        //
        this.tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        this.panelComandos.setPreferredSize(new Dimension(200, 200));
        this.split.setRightComponent(tabs);
        this.split.setLeftComponent(panelComandos);

        this.menuBar.add(menuAplicacion);
    }

    /**
     *
     */
    private void layoutComponents() {
        //
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.split);

        this.setJMenuBar(menuBar);
    }

    /**
     *
     */
    private void addEventHandlers() {
        //this.salir.addActionListener(new XestionMenus(this));
    }

    /**
     *
     * @return
     */
    public JMenu getMenuSeleccion() {
        return menuAplicacion;
    }

    /**
     *
     * @return
     */
    public PanelComandos getPanelComandos() {
        return panelComandos;
    }

    /**
     *
     * @return
     */
    public JSplitPane getSplit() {
        return split;
    }

    /**
     *
     * @return
     */
    public JTabbedPane getTabs() {
        return tabs;
    }
}
