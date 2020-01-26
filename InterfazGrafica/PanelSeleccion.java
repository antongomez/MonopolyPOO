package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Evento.XestionBotons;
import Evento.XestionSeleccions;

public class PanelSeleccion extends JPanel {
    //

    private JPanel panelComponentes;
    private ButtonGroup grupoSeleccion;
    private JComboBox combo;
    private JLabel etiquetaCombo;
    private JLabel etiquetaId;
    private JTextField id;
    private JTextField textoPie;
    private JCheckBox formatoCorto;
    private JCheckBox formatoLargo;
    private JButton botonOk;
    private TitledBorder bordePanelSeleccion;
    private InterfazGrafica ventanaPrincipal;

    /**
     *
     */
    public PanelSeleccion(InterfazGrafica interfaz) {
        this.ventanaPrincipal = interfaz;
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
        this.bordePanelSeleccion = BorderFactory.createTitledBorder("Selección");
        this.panelComponentes = new JPanel();
        this.grupoSeleccion = new ButtonGroup();
        this.etiquetaId = new JLabel("Id");
        this.etiquetaCombo = new JLabel("Noticia");
        this.id = new JTextField();
        this.textoPie = new JTextField("Ningún tipo seleccionado");
        this.formatoLargo = new JCheckBox("Largo");
        this.formatoCorto = new JCheckBox("Corto");
        this.botonOk = new JButton("Ok");

        this.combo = new JComboBox(Noticias.encabezados);
    }

    /**
     *
     */
    private void setupComponents() {
        //
        this.panelComponentes.setBorder(bordePanelSeleccion);
        this.panelComponentes.setPreferredSize(new Dimension(50, 125));
        this.grupoSeleccion.add(formatoCorto);
        this.grupoSeleccion.add(formatoLargo);
        this.textoPie.setEnabled(false);
        this.textoPie.setDisabledTextColor(Color.BLUE);
        this.textoPie.setBackground(this.getBackground());
        this.textoPie.setBorder(null);
    }

    /**
     *
     */
    private void layoutComponents() {
        //
        GridLayout layout = new GridLayout(4, 2);
        layout.setVgap(5);
        this.panelComponentes.setLayout(layout);
        this.panelComponentes.add(this.etiquetaId);
        this.panelComponentes.add(this.id);
        this.panelComponentes.add(this.etiquetaCombo);
        this.panelComponentes.add(this.combo);
        this.panelComponentes.add(this.formatoCorto);
        this.panelComponentes.add(this.formatoLargo);
        this.panelComponentes.add(this.botonOk);

        this.setLayout(new BorderLayout());
        this.add(this.panelComponentes, BorderLayout.NORTH);
        this.add(this.textoPie, BorderLayout.SOUTH);
    }

    /**
     *
     */
    private void addEventHandlers() {
        // Añadir el gestor de eventos para el campo de texto
        this.botonOk.addActionListener(new XestionBotons(this.ventanaPrincipal));
        this.formatoCorto.addItemListener(new XestionSeleccions(this.ventanaPrincipal));
        this.formatoLargo.addItemListener(new XestionSeleccions(this.ventanaPrincipal));
        this.combo.addItemListener(new XestionSeleccions(this.ventanaPrincipal));
    }

    /**
     *
     * @return
     */
    public TitledBorder getBordePanelSeleccion() {
        return bordePanelSeleccion;
    }

    /**
     *
     * @return
     */
    public JButton getBotonOk() {
        return botonOk;
    }

    /**
     *
     * @return
     */
    public JComboBox getCombo() {
        return combo;
    }

    /**
     *
     * @return
     */
    public JLabel getEtiquetaCombo() {
        return etiquetaCombo;
    }

    /**
     *
     * @return
     */
    public JLabel getEtiquetaId() {
        return etiquetaId;
    }

    /**
     *
     * @return
     */
    public JCheckBox getFormatoCorto() {
        return formatoCorto;
    }

    /**
     *
     * @return
     */
    public JCheckBox getFormatoLargo() {
        return formatoLargo;
    }

    /**
     *
     * @return
     */
    public ButtonGroup getGrupoSeleccion() {
        return grupoSeleccion;
    }

    /**
     *
     * @return
     */
    public JTextField getId() {
        return id;
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
    public InterfazGrafica getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public JTextField getTextoPie() {
        return textoPie;
    }
}
