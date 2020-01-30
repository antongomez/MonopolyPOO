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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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
    private PanelSaida panelSaida;
    private PanelXogadores panelXogadores;

    private JTextField textoPe;

    public PanelEsquerdo(InterfazGrafica ventaPrincipal) {
        this.ventaPrincipal = ventaPrincipal;
        this.taboleiro = taboleiro;
        this.panelComandos = new PanelComandos(ventaPrincipal);
        this.panelSaida = new PanelSaida();
        JPanel panelIntermedio = new JPanel(new BorderLayout(0, 0));

        this.textoPe = new JTextField("Ningún tipo seleccionado");

        this.textoPe.setEnabled(false);
        this.textoPe.setDisabledTextColor(Color.BLUE);
        this.textoPe.setBackground(this.getBackground());
        this.textoPe.setBorder(null);

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        panelIntermedio.add(panelComandos, BorderLayout.WEST);
        panelIntermedio.add(panelSaida, BorderLayout.EAST);
        this.add(panelIntermedio, BorderLayout.NORTH);
        this.add(this.textoPe, BorderLayout.SOUTH);

        XestionXogadores();
        setOpaque(false);

        setTamanhos();
    }

    private void setTamanhos() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;

        //Establecemos o tamaño do panel esquerdo
        this.setPreferredSize(new Dimension(380, height));
        this.setSize(getPreferredSize());

        //Establecemos as dimensions do panel de comandos
        int metadeAncho = (int) Math.round(getPreferredSize().getWidth() / 4);
        int metadeAlto = (int) Math.round(getPreferredSize().getHeight() / 2);
        this.panelComandos.setPreferredSize(new Dimension(metadeAncho, metadeAlto));

        //Establecemos as dimensions do panel de saida
        this.panelComandos.setPreferredSize(panelComandos.getPreferredSize());

        //Establecemos as dimensions do panel de xogadores
        this.panelXogadores.setPreferredSize(new Dimension(getWidth(), metadeAlto));
        this.panelXogadores.setSize(getPreferredSize());
    }

    private void XestionXogadores() {
        panelXogadores = new PanelXogadores(new Dimension( this.getWidth(),this.getHeight()));
        this.add(panelXogadores, BorderLayout.CENTER);
    }

    public PanelXogadores getPanelXogadores() {
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
