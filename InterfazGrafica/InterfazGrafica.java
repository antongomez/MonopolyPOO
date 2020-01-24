package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class InterfazGrafica extends JFrame {

    //
    private PanelSeleccion seleccion;
    private JSplitPane split;
    private JTabbedPane tabs;
    private JMenuBar menuBar;
    private JMenu menuAplicacion;

    /**
     *
     */
    public InterfazGrafica() {
        initComponents();
        setupComponents();
        layoutComponents();
        addEventHandlers();
    }

    /**
     *
     */
    public void initComponents() {
        //
        this.tabs = new JTabbedPane();
        this.seleccion = new PanelSeleccion(this);
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
        this.setSize(new Dimension(800, 600));
        this.seleccion.setPreferredSize(new Dimension(200, 200));
        this.split.setRightComponent(tabs);
        this.split.setLeftComponent(seleccion);

        menuBar.add(menuAplicacion);
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
        //this.salir.addActionListener(new GestionMenus(this));
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
    public PanelSeleccion getSeleccion() {
        return seleccion;
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
