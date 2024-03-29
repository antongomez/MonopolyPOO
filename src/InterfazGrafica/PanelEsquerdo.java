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
    private PanelComandos panelComandos;
    private PanelSaida panelSaida;
    private PanelXogadores panelXogadores;
    private JPanel panelIntermedio;
    private JPanel panelbotons;
    private JTextField textoPe;

    public PanelEsquerdo(InterfazGrafica ventaPrincipal) {
        this.ventaPrincipal = ventaPrincipal;

        //Color
        this.setOpaque(false);

        //Maqueamos panel esquerdo
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        this.setPreferredSize(new Dimension(630, ventaPrincipal.getHeight()));
        this.setSize(getPreferredSize());

        int metadeAncho = (int) Math.round(getPreferredSize().getWidth() / 2);
        int metadeAlto = (int) Math.round(getPreferredSize().getHeight() / 3);

        //Medidas de panel intermedio
        panelIntermedio = new JPanel(new BorderLayout(0, 0));
        panelIntermedio.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));
        panelIntermedio.setSize(new Dimension(2 * metadeAncho, getHeight() / 3));

        //Panel comandos
        this.panelComandos = new PanelComandos(ventaPrincipal);
        this.panelComandos.setPreferredSize(new Dimension(metadeAncho, metadeAlto));

        //Panelsaida
        this.panelSaida = new PanelSaida(new Dimension(300, 300));
        panelIntermedio.add(panelComandos, BorderLayout.WEST);
        panelIntermedio.add(panelSaida, BorderLayout.CENTER);

        //Merdas de panel botons
        panelbotons = new JPanel();
        //panelbotons.setPreferredSize(panelIntermedio.getPreferredSize());
        panelbotons.setOpaque(true);
        panelbotons.setBackground(new Color(199, 255, 185));
        panelbotons.setVisible(true);
        panelbotons.add(new Botons(this));

        //Merdas de textpe
        this.textoPe = new JTextField("Ningún tipo seleccionado");
        this.textoPe.setEnabled(false);
        this.textoPe.setDisabledTextColor(Color.BLUE);
        this.textoPe.setBackground(this.getBackground());
        this.textoPe.setBorder(null);

        //Mierdas de Panel xogadores
        panelXogadores = new PanelXogadores(new Dimension(this.getWidth(), this.getHeight()));

        //Metemos as cousas no seu sitio
        //this.add(this.textoPe, BorderLayout.SOUTH);
        this.add(panelIntermedio, BorderLayout.NORTH);
        panelIntermedio.setOpaque(true);
        panelIntermedio.setBackground(new Color(199, 255, 185));
        this.add(panelbotons, BorderLayout.CENTER);
        this.add(panelXogadores, BorderLayout.SOUTH);

        //Establecemos as dimensions do panel de xogadores
        this.panelXogadores.setPreferredSize(new Dimension(getWidth(), metadeAlto));
        this.panelXogadores.setSize(getPreferredSize());

        setOpaque(false);
    }

    public PanelSaida getPanelSaida() {
        return panelSaida;
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
