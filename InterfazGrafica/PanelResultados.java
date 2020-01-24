package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Manuel Lama Pen�n
 *
 */
public class PanelResultados extends JPanel {
    //

    private JPanel panelComponentes;
    private JScrollPane scroll;
    private JEditorPane areaResultados;
    private JButton botonCerrar;
    private TitledBorder bordeAreaResultados;

    private InterfazGrafica ventanaPrincipal;

    /**
     *
     */
    public PanelResultados(InterfazGrafica interfaz) {
        this.ventanaPrincipal = interfaz;
        initComponents();
        setupComponents();
        layoutComponents();
        //addEventHandlers();
    }

    /**
     *
     */
    public void initComponents() {
        //
        this.bordeAreaResultados = BorderFactory.createTitledBorder("Resultados");
        this.panelComponentes = new JPanel();
        this.areaResultados = new JEditorPane();
        this.scroll = new JScrollPane(this.areaResultados);
        this.botonCerrar = new JButton("Cerrar");
    }

    /**
     *
     */
    private void setupComponents() {
        //
        this.scroll.setBorder(bordeAreaResultados);
        this.scroll.setPreferredSize(new Dimension(400, 480));
        this.scroll.setMinimumSize(new Dimension(400, 500));
        this.areaResultados.setContentType("text/html");
    }

    /**
     *
     */
    private void layoutComponents() {
        //
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints restricciones = new GridBagConstraints();
        this.panelComponentes.setLayout(layout);

        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(this.scroll, restricciones);
        this.panelComponentes.add(this.scroll);

        restricciones.fill = GridBagConstraints.NONE;
        restricciones.weightx = 0.0;
        layout.setConstraints(this.botonCerrar, restricciones);
        this.panelComponentes.add(this.botonCerrar);

        this.setLayout(new BorderLayout());
        this.add(this.panelComponentes, BorderLayout.NORTH);
    }

    /**
     *
     */
    /*private void addEventHandlers() {
        this.botonCerrar.addActionListener(new GestionBotones(this.ventanaPrincipal));
    }*/
    /**
     *
     * @return
     */
    public JEditorPane getAreaResultados() {
        return areaResultados;
    }

    /**
     *
     * @return
     */
    public TitledBorder getBordeAreaResultados() {
        return bordeAreaResultados;
    }

    /**
     *
     * @return
     */
    public JButton getBotonCerrar() {
        return botonCerrar;
    }

    /**
     *
     * @return
     */
    public JPanel getPanelComponentes() {
        return panelComponentes;
    }

    /**
     *
     * @return
     */
    public JScrollPane getScroll() {
        return scroll;
    }

    /**
     *
     * @return
     */
    public InterfazGrafica getVentanaPrincipal() {
        return ventanaPrincipal;
    }
}
