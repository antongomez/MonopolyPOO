package InterfazGrafica;

import estrutura.Taboleiro;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDereito extends JPanel {

    private InterfazGrafica ventaPrincipal;
    private Taboleiro taboleiro;
    private PanelTaboleiro tab;

    public PanelDereito(InterfazGrafica ventaPrincipal, Taboleiro taboleiro) {
        this.ventaPrincipal = ventaPrincipal;
        this.taboleiro = taboleiro;
        this.tab = new PanelTaboleiro(ventaPrincipal, taboleiro);

        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);

        this.setLayout(layout);
        this.add(tab);

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
