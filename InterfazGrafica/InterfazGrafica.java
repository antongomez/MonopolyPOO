package InterfazGrafica;

import estrutura.Taboleiro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.*;

public class InterfazGrafica extends JFrame {

    private PanelEsquerdo panelEsquerdo;
    private PanelDereito panelDereito;
    private Taboleiro taboleiro;

    public InterfazGrafica(Taboleiro taboleiro) {
        this.taboleiro = taboleiro;
        iniciarVenta();
        iniciarComponhentes();

        setupComponents();
        layoutComponents();
        addEventHandlers();
    }

    private void iniciarVenta() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //A execucion finalizase cando se pecha a venta
        this.setLayout(new BorderLayout());
        this.setTitle("MONOPOLY GALEGO");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        this.setMinimumSize(new Dimension(1000, height - 33));
        this.setSize(new Dimension(width, height - 33));
    }

    private void iniciarComponhentes() {

        this.panelDereito = new PanelDereito(this, taboleiro);
        this.panelEsquerdo = new PanelEsquerdo(this);
    }

    private void setupComponents() {

        this.add(panelDereito, BorderLayout.CENTER);
        this.add(panelEsquerdo, BorderLayout.WEST);
    }

    private void layoutComponents() {

    }

    private void addEventHandlers() {

    }

    //Getters e Settersç
    public PanelEsquerdo getPanelEsquerdo() {
        return panelEsquerdo;
    }

    public void setPanelEsquerdo(PanelEsquerdo panelEsquerdo) {
        this.panelEsquerdo = panelEsquerdo;
    }

    public PanelDereito getPanelDereito() {
        return panelDereito;
    }

    public void setPanelDereito(PanelDereito panelDereito) {
        this.panelDereito = panelDereito;
    }

    public Taboleiro getTaboleiro() {
        return taboleiro;
    }

    public void setTaboleiro(Taboleiro taboleiro) {
        this.taboleiro = taboleiro;
    }

}
