package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Evento.XestionCampoTexto;
import java.awt.FlowLayout;
import javax.swing.JTextArea;

public class PanelComandos extends JPanel {
    //

    private JPanel panelComandos;

    private JLabel etiquetaComando;
    private JTextField campoComandos;
    private JLabel etiquetaHistorial;
    private JTextArea historialComandos;
    private JTextField textoPe;
    private TitledBorder bordePanelSeleccion;
    private InterfazGrafica ventaPrincipal;

    /**
     *
     */
    public PanelComandos(InterfazGrafica interfaz) {
        this.ventaPrincipal = interfaz;
        initComponents();
        setupComponents();
        layoutComponents();
        addEventHandlers();
    }

    private void initComponents() {

        this.bordePanelSeleccion = BorderFactory.createTitledBorder("Comandos");
        this.panelComandos = new JPanel();
        this.campoComandos = new JTextField(30);
        this.etiquetaComando = new JLabel("Introduce aquí os comandos: ");
        this.historialComandos = new JTextArea(20, 30);
        this.etiquetaHistorial = new JLabel("Historial: ");
        this.textoPe = new JTextField("Ningún tipo seleccionado");
    }

    private void setupComponents() {

        this.panelComandos.setBorder(bordePanelSeleccion);
        this.panelComandos.setPreferredSize(new Dimension(50, 125));
        historialComandos.setEditable(false);
        this.textoPe.setEnabled(false);
        this.textoPe.setDisabledTextColor(Color.BLUE);
        this.textoPe.setBackground(this.getBackground());
        this.textoPe.setBorder(null);
    }

    private void layoutComponents() {

        FlowLayout layout = new FlowLayout();
        layout.setVgap(5);
        this.panelComandos.setLayout(layout);
        this.panelComandos.add(etiquetaComando);
        this.panelComandos.add(campoComandos);
        this.panelComandos.add(etiquetaHistorial);
        this.panelComandos.add(historialComandos);

        this.setLayout(new BorderLayout());
        this.add(this.panelComandos, BorderLayout.NORTH);
        this.add(this.textoPe, BorderLayout.SOUTH);
    }

    /**
     *
     */
    private void addEventHandlers() {
        this.campoComandos.addKeyListener(new XestionCampoTexto(ventaPrincipal));
        /*
        this.formatoCorto.addItemListener(new XestionCampoTexto(this.ventaPrincipal));
        this.formatoLargo.addItemListener(new XestionCampoTexto(this.ventaPrincipal));
        this.combo.addItemListener(new XestionCampoTexto(this.ventaPrincipal));*/
    }

    //Getters e Setters
    public TitledBorder getBordePanelSeleccion() {
        return bordePanelSeleccion;
    }

    public JPanel getPanelComandos() {
        return panelComandos;
    }

    public InterfazGrafica getVentaPrincipal() {
        return ventaPrincipal;
    }

    public JTextField getTextoPe() {
        return textoPe;
    }

    public JLabel getEtiquetaComando() {
        return etiquetaComando;
    }

    public void setEtiquetaComando(JLabel etiquetaComando) {
        this.etiquetaComando = etiquetaComando;
    }

    public JTextField getCampoComandos() {
        return campoComandos;
    }

    public void setCampoComandos(JTextField campoComandos) {
        this.campoComandos = campoComandos;
    }

    public JLabel getEtiquetaHistorial() {
        return etiquetaHistorial;
    }

    public void setEtiquetaHistorial(JLabel etiquetaHistorial) {
        this.etiquetaHistorial = etiquetaHistorial;
    }

    public JTextArea getHistorialComandos() {
        return historialComandos;
    }

    public void setHistorialComandos(JTextArea historialComandos) {
        this.historialComandos = historialComandos;
    }
}
