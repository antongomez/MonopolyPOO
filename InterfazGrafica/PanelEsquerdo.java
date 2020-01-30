/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import estrutura.Taboleiro;
import xogadores.Xogador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Anton
 */
public class PanelEsquerdo extends JPanel {

    private InterfazGrafica ventaPrincipal;
    private Taboleiro taboleiro;
    private PanelComandos panelComandos;
    private PanelXogadores panelXogadores;

    private JTextField textoPe;

    public PanelEsquerdo(InterfazGrafica ventaPrincipal) {
        this.ventaPrincipal = ventaPrincipal;
        this.taboleiro = taboleiro;
        this.panelComandos = new PanelComandos(ventaPrincipal);

        this.textoPe = new JTextField("Ningún tipo seleccionado");

        this.textoPe.setEnabled(false);
        this.textoPe.setDisabledTextColor(Color.BLUE);
        this.textoPe.setBackground(this.getBackground());
        this.textoPe.setBorder(null);

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(panelComandos, BorderLayout.NORTH);
        this.add(this.textoPe, BorderLayout.SOUTH);

       XestionXogadores();
       setOpaque(false);
    }

    public void XestionXogadores()
    {
        panelXogadores = new PanelXogadores();
        this.add(panelXogadores, BorderLayout.CENTER);
    }

    public PanelXogadores getPanelXogadores()
    {
        return this.panelXogadores;
    }

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

    public PanelComandos getPanelComandos() {
        return panelComandos;
    }

    public void setPanelComandos(PanelComandos panelComandos) {
        this.panelComandos = panelComandos;
    }

    public JTextField getTextoPe() {
        return textoPe;
    }
}
