package InterfazGrafica;

import estrutura.Taboleiro;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDereito extends JPanel {

    private InterfazGrafica ventaPrincipal;
    private Taboleiro taboleiro;
    private JPanel panelIntermedio;
    private PanelTaboleiro tab;

    public PanelDereito(InterfazGrafica ventaPrincipal, Taboleiro taboleiro) {
        this.ventaPrincipal = ventaPrincipal;
        this.taboleiro = taboleiro;
        this.tab = new PanelTaboleiro(ventaPrincipal, taboleiro);
        this.panelIntermedio = new JPanel();

        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        this.panelIntermedio.setLayout(layout);
        this.panelIntermedio.add(tab);

        BorderLayout layoutColocacion = new BorderLayout();
        this.setLayout(layoutColocacion);
        this.add(panelIntermedio, BorderLayout.CENTER);

        JPanel panelEspazo1 = new JPanel();
        panelEspazo1.setPreferredSize(new Dimension(panelIntermedio.getWidth(), 30));
        this.add(panelEspazo1, BorderLayout.NORTH);
        
        JPanel panelEspazo2 = new JPanel();
        panelEspazo2.setPreferredSize(new Dimension(20, panelIntermedio.getHeight()));
        this.add(panelEspazo2, BorderLayout.EAST);

        setOpaque(false);
    }

//Getters e Setters
    public InterfazGrafica getVentaPrincipal() {
        return ventaPrincipal;
    }

    public void setVentaPrincipal(InterfazGrafica ventaPrincipal) {
        this.ventaPrincipal = ventaPrincipal;
    }

    public Taboleiro getTaboleiro() {
        return taboleiro;
    }

    public void setTaboleiro(Taboleiro taboleiro) {
        this.taboleiro = taboleiro;
    }

    public PanelTaboleiro getTab() {
        return tab;
    }

    public void setTab(PanelTaboleiro tab) {
        this.tab = tab;
    }
}
