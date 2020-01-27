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
    private JSplitPane split;
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

        this.panelComponentes = new JPanel();
        this.split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        this.panelTab = new PanelTaboleiro(ventaPrincipal);
        this.setPreferredSize(new Dimension(400, 400));

    }

    private void setupComponents() {
        //split.add(panelTab);
    }

    private void layoutComponents() {
        //this.add(split);
        this.add(panelTab);
    }

    private void addEventHandlers() {
        //this.botonCerrar.addActionListener(new XestionBotons(this.ventaPrincipal));
    }

    //Getters e Setters
    public JPanel getPanelComponentes() {
        return panelComponentes;
    }

    public InterfazGrafica getVentaPrincipal() {
        return ventaPrincipal;
    }

}
