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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class PanelComandos extends JPanel {

    private InterfazGrafica ventaPrincipal;

    private JPanel panelTexto;
    private TitledBorder bordePanelSeleccion;
    private JLabel etiquetaComando;
    private JTextField campoComandos;
    private JLabel etiquetaHistorial;
    private JTextArea historialComandos;

    private JTextField textoPe;

    private XestionCampoTexto introducionComando;

    public PanelComandos(InterfazGrafica interfaz) {
        this.ventaPrincipal = interfaz;
        initComponents();
        setupComponents();
        layoutComponents();
        addEventHandlers();
    }

    private void initComponents() {

        this.introducionComando = new XestionCampoTexto(ventaPrincipal);
        this.panelTexto = new JPanel();
        this.bordePanelSeleccion = BorderFactory.createTitledBorder("Comandos");
        this.campoComandos = new JTextField(25);
        this.etiquetaComando = new JLabel("Introduce aquí os comandos: ");
        this.historialComandos = new JTextArea(15, 25);
        this.etiquetaHistorial = new JLabel("Historial: ");
        this.textoPe = new JTextField("Ningún tipo seleccionado");
    }

    private void setupComponents() {

        //this.panelComandos.setBorder(bordePanelSeleccion);
        this.panelTexto.setPreferredSize(new Dimension(300, 600));
        this.historialComandos.setEditable(false);
        this.textoPe.setEnabled(false);
        this.textoPe.setDisabledTextColor(Color.BLUE);
        this.textoPe.setBackground(this.getBackground());
        this.textoPe.setBorder(null);
    }

    private void layoutComponents() {

        FlowLayout layout = new FlowLayout();
        layout.setVgap(7);

        this.panelTexto.setLayout(layout);
        this.panelTexto.add(etiquetaComando);
        this.panelTexto.add(campoComandos);
        this.panelTexto.add(etiquetaHistorial);
        this.panelTexto.add(historialComandos);
        JScrollPane scroll = new JScrollPane(historialComandos,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setSize(historialComandos.getSize());
        this.add(scroll);

        this.setLayout(new BorderLayout());
        this.add(panelTexto, BorderLayout.NORTH);
        this.add(this.textoPe, BorderLayout.SOUTH);
    }

    private void addEventHandlers() {
        this.campoComandos.addKeyListener(introducionComando);
        /*
        this.formatoCorto.addItemListener(new XestionCampoTexto(this.ventaPrincipal));
        this.formatoLargo.addItemListener(new XestionCampoTexto(this.ventaPrincipal));
        this.combo.addItemListener(new XestionCampoTexto(this.ventaPrincipal));*/
    }

    //Getters e Setters
    public TitledBorder getBordePanelSeleccion() {
        return bordePanelSeleccion;
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

    public JPanel getPanelTexto() {
        return panelTexto;
    }

    public void setPanelTexto(JPanel campoTexto) {
        this.panelTexto = campoTexto;
    }

    public XestionCampoTexto getIntroducionComando() {
        return introducionComando;
    }

    public void setIntroducionComando(XestionCampoTexto introducionComando) {
        this.introducionComando = introducionComando;
    }

}
