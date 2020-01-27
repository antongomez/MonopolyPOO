package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class PanelDereito extends JPanel {

    private JPanel panelComponentes;
    private PanelTaboleiro panelTab;
    private InterfazGrafica ventaPrincipal;

    public PanelDereito(InterfazGrafica interfaz) {
        this.ventaPrincipal = interfaz;
        initComponents();
        setupComponents();
        layoutComponents();
        addEventHandlers();
    }

    public void initComponents() {

        this.panelComponentes = ventaPrincipal.getPanelComandos();
        this.panelTab = new PanelTaboleiro(ventaPrincipal);

    }

    private void setupComponents() {

    }

    private void layoutComponents() {
        this.add(panelTab);
    }

    private void addEventHandlers() {

    }

    //Getters e Setters
    public JPanel getPanelComponentes() {
        return panelComponentes;
    }

    public void setPanelComponentes(JPanel panelComponentes) {
        this.panelComponentes = panelComponentes;
    }

    public PanelTaboleiro getPanelTab() {
        return panelTab;
    }

    public void setPanelTab(PanelTaboleiro panelTab) {
        this.panelTab = panelTab;
    }

    public InterfazGrafica getVentaPrincipal() {
        return ventaPrincipal;
    }

    public void setVentaPrincipal(InterfazGrafica ventaPrincipal) {
        this.ventaPrincipal = ventaPrincipal;
    }
}
