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

        //Maqueamos panel esquerdo
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
<<<<<<< HEAD
=======

        panelIntermedio.add(panelComandos, BorderLayout.WEST);
        panelIntermedio.add(panelSaida, BorderLayout.CENTER);
        this.add(panelIntermedio, BorderLayout.NORTH);
        this.add(this.textoPe, BorderLayout.SOUTH);

        setTamanhos();
        
        XestionXogadores();
        setOpaque(false);

       
    }

    private void setTamanhos() {
>>>>>>> Cambiso
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        this.setPreferredSize(new Dimension(width/2, height));
        this.setSize(getPreferredSize());

        //Trangallamos o panel comandos
        this.panelComandos = new PanelComandos(ventaPrincipal);
        int metadeAncho = (int) Math.round(getPreferredSize().getWidth() / 2);
        int metadeAlto = (int) Math.round(getPreferredSize().getHeight() / 3);
        this.panelComandos.setPreferredSize(new Dimension(metadeAncho, metadeAlto));

<<<<<<< HEAD
        //Trangallamos panelsaida
        this.panelSaida = new PanelSaida(new Dimension(metadeAncho, metadeAlto));

        //Merdas de panel intermedio
        panelIntermedio = new JPanel(new BorderLayout(0, 0));
        panelIntermedio.setPreferredSize(new Dimension(2*metadeAncho, height/3));
        panelIntermedio.setSize(new Dimension(2*metadeAncho, height/3));
        panelIntermedio.add(panelComandos, BorderLayout.WEST);
        panelIntermedio.add(panelSaida, BorderLayout.EAST);

        //Merdas de panel botons
        panelbotons = new JPanel();
        panelbotons.setPreferredSize(panelIntermedio.getPreferredSize());
        panelbotons.setOpaque(false);
        panelbotons.setVisible(true);
        panelbotons.add(new Botons(this));

        //Merdas de textpe
        this.textoPe = new JTextField("Ningún tipo seleccionado");
        this.textoPe.setEnabled(false);
        this.textoPe.setDisabledTextColor(Color.BLUE);
        this.textoPe.setBackground(this.getBackground());
        this.textoPe.setBorder(null);


        //Mierdas de Panel xogadores
        panelXogadores = new PanelXogadores(new Dimension( this.getWidth(),this.getHeight()));

        //Metemos as cousas no seu sitio
        //this.add(this.textoPe, BorderLayout.SOUTH);
        this.add(panelIntermedio, BorderLayout.NORTH);
        this.add(panelbotons,BorderLayout.CENTER);
        this.add(panelXogadores, BorderLayout.SOUTH);
=======
        //Establecemos as dimensions do panel de saida
        this.panelSaida.setPreferredSize(panelComandos.getPreferredSize());
>>>>>>> Cambiso

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
