package InterfazGrafica;

import Evento.DescripcionTaboleiro;
import estrutura.Taboleiro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelTaboleiro extends JPanel {

    private InterfazGrafica ventaPrincipal;
    private BorderLayout disposicion;
    private ArrayList<ArrayList<JButton>> casillas;
    private ArrayList<AvataresTaboleiro> avatares;

    private JPanel panelSur;
    private JPanel panelOeste;
    private JPanel panelNorte;
    private JPanel panelEste;

    public PanelTaboleiro(InterfazGrafica ventaPrincipal, Taboleiro taboleiro) {
        this.ventaPrincipal = ventaPrincipal;
        this.avatares = new ArrayList<AvataresTaboleiro>();
        initComp();
        creacionCasillas();
        setUpComp();
        layoutComp();
        activarDescripcion(ventaPrincipal, taboleiro);

        setOpaque(true);
        setBackground(new Color(199, 255, 185));

        panelSur.setOpaque(false);
        panelOeste.setOpaque(false);
        panelNorte.setOpaque(false);
        panelEste.setOpaque(false);
    }

    private void initComp() {
        this.casillas = new ArrayList<>();
        this.disposicion = new BorderLayout(0, 0);
        this.panelSur = new JPanel();
        this.panelOeste = new JPanel();
        this.panelNorte = new JPanel();
        this.panelEste = new JPanel();
    }

    private void creacionCasillas() {
        ArrayList<JButton> ladoSur = new ArrayList<>();

        JButton Saida = new JButton();
        ladoSur.add(Saida);
        JButton SantaCruz = new JButton();
        ladoSur.add(SantaCruz);
        JButton Caixa1 = new JButton();
        ladoSur.add(Caixa1);
        JButton Arteixo = new JButton();
        ladoSur.add(Arteixo);
        JButton IRPF = new JButton();
        ladoSur.add(IRPF);
        JButton Autobus = new JButton();
        ladoSur.add(Autobus);
        JButton Meanho = new JButton();
        ladoSur.add(Meanho);
        JButton Sorte1 = new JButton();
        ladoSur.add(Sorte1);
        JButton Corrubedo = new JButton();
        ladoSur.add(Corrubedo);
        JButton Lugo = new JButton();
        ladoSur.add(Lugo);
        JButton Carcere = new JButton();
        ladoSur.add(Carcere);

        casillas.add(ladoSur);

        ArrayList<JButton> ladoOeste = new ArrayList<>();

        JButton Carballinho = new JButton();
        ladoOeste.add(Carballinho);
        JButton Sergas = new JButton();
        ladoOeste.add(Sergas);
        JButton Sada = new JButton();
        ladoOeste.add(Sada);
        JButton Mera = new JButton();
        ladoOeste.add(Mera);
        JButton Lancha = new JButton();
        ladoOeste.add(Lancha);
        JButton Cedofeita = new JButton();
        ladoOeste.add(Cedofeita);
        JButton Caixa2 = new JButton();
        ladoOeste.add(Caixa2);
        JButton Viveiro = new JButton();
        ladoOeste.add(Viveiro);
        JButton Ribadeo = new JButton();
        ladoOeste.add(Ribadeo);

        casillas.add(ladoOeste);

        ArrayList<JButton> ladoNorte = new ArrayList<>();

        JButton Parking = new JButton();
        ladoNorte.add(Parking);
        JButton Verin = new JButton();
        ladoNorte.add(Verin);
        JButton Sorte2 = new JButton();
        ladoNorte.add(Sorte2);
        JButton Cambados = new JButton();
        ladoNorte.add(Cambados);
        JButton Tui = new JButton();
        ladoNorte.add(Tui);
        JButton Iate = new JButton();
        ladoNorte.add(Iate);
        JButton Caurel = new JButton();
        ladoNorte.add(Caurel);
        JButton Guarda = new JButton();
        ladoNorte.add(Guarda);
        JButton Ensino = new JButton();
        ladoNorte.add(Ensino);
        JButton Ourense = new JButton();
        ladoNorte.add(Ourense);
        JButton IrCarcere = new JButton();
        ladoNorte.add(IrCarcere);

        casillas.add(ladoNorte);

        ArrayList<JButton> ladoEste = new ArrayList<>();

        JButton Santiago = new JButton();
        ladoEste.add(Santiago);
        JButton Sanxenxo = new JButton();
        ladoEste.add(Sanxenxo);
        JButton Caixa3 = new JButton();
        ladoEste.add(Caixa3);
        JButton Pontevedra = new JButton();
        ladoEste.add(Pontevedra);
        JButton Jet = new JButton();
        ladoEste.add(Jet);
        JButton Sorte3 = new JButton();
        ladoEste.add(Sorte3);
        JButton Vigo = new JButton();
        ladoEste.add(Vigo);
        JButton Subida = new JButton();
        ladoEste.add(Subida);
        JButton Corunha = new JButton();
        ladoEste.add(Corunha);

        casillas.add(ladoEste);

        AvataresTaboleiro panelBoton;
        for (int i = 0; i < ladoSur.size(); i++) {
            panelBoton = new AvataresTaboleiro(ladoSur.get(i));
            ladoSur.get(i).add(panelBoton);
            avatares.add(panelBoton);
        }

        for (int i = 0; i < ladoOeste.size(); i++) {
            panelBoton = new AvataresTaboleiro(ladoOeste.get(i));
            ladoOeste.get(i).add(panelBoton);
            avatares.add(panelBoton);
        }

        for (int i = 0; i < ladoNorte.size(); i++) {
            panelBoton = new AvataresTaboleiro(ladoNorte.get(i));
            ladoNorte.get(i).add(panelBoton);
            avatares.add(panelBoton);
        }

        for (int i = 0; i < ladoEste.size(); i++) {
            panelBoton = new AvataresTaboleiro(ladoEste.get(i));
            ladoEste.get(i).add(panelBoton);
            avatares.add(panelBoton);
        }
    }

    public void activarDescripcion(InterfazGrafica ventaPrincipal, Taboleiro taboleiro) {
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            new DescripcionTaboleiro(ventaPrincipal, getCasilla(taboleiro.getCasillas().get(i).getPosicion()), taboleiro.getCasillas().get(i));
        }
    }

    private void setUpComp() {
        FlowLayout layoutSur = new FlowLayout(FlowLayout.CENTER, 0, 0);
        this.panelSur.setLayout(layoutSur);
        //Establecemos as dimensions das casillas especiais
        setUpCasillaEspecial(casillas.get(0).get(10));
        panelSur.add(casillas.get(0).get(10));
        for (int j = 9; j > 0; j--) {
            setUpCasillaVertical(casillas.get(0).get(j));
            panelSur.add(casillas.get(0).get(j));
        }
        setUpCasillaEspecial(casillas.get(0).get(0));
        panelSur.add(casillas.get(0).get(0));

        GridLayout layoutOeste = new GridLayout(9, 1, 0, 0);
        this.panelOeste.setLayout(layoutOeste);

        for (int j = 8; j >= 0; j--) {
            setUpCasillaHorizontal(casillas.get(1).get(j));
            panelOeste.add(casillas.get(1).get(j));
        }

        FlowLayout layoutNorte = new FlowLayout(FlowLayout.CENTER, 0, 0);
        this.panelNorte.setLayout(layoutNorte);

        setUpCasillaEspecial(casillas.get(2).get(0));
        panelNorte.add(casillas.get(2).get(0));
        for (int j = 1; j < 10; j++) {
            setUpCasillaVertical(casillas.get(2).get(j));
            panelNorte.add(casillas.get(2).get(j));
        }
        setUpCasillaEspecial(casillas.get(2).get(10));
        panelNorte.add(casillas.get(2).get(10));

        GridLayout layoutEste = new GridLayout(9, 1, 0, 0);
        this.panelEste.setLayout(layoutEste);

        for (int j = 0; j <= 8; j++) {
            setUpCasillaHorizontal(casillas.get(3).get(j));
            panelEste.add(casillas.get(3).get(j));
        }

        //Colocanse as imaxes enriba dos botons
        colocarIconos();

    }

    private void setUpCasillaEspecial(JButton casilla) {
        casilla.setPreferredSize(new Dimension(65, 65));
        casilla.setSize(new Dimension(65, 65));
        casilla.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    private void setUpCasillaVertical(JButton casilla) {
        casilla.setPreferredSize(new Dimension(56, 65));
        casilla.setSize(new Dimension(56, 65));
        casilla.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    private void setUpCasillaHorizontal(JButton casilla) {
        casilla.setPreferredSize(new Dimension(65, 56));
        casilla.setSize(new Dimension(65, 56));
        casilla.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    private void colocarIconos() {
        JButton casilla;
        ImageIcon imaxe;

        casilla = casillas.get(0).get(0);
        imaxe = new ImageIcon("assets/Saida Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(1);
        imaxe = new ImageIcon("assets/Santa Cruz Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(2);
        imaxe = new ImageIcon("assets/Caixa1 Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(3);
        imaxe = new ImageIcon("assets/Arteixo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(4);
        imaxe = new ImageIcon("assets/IRPF Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(5);
        imaxe = new ImageIcon("assets/Autobus Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(6);
        imaxe = new ImageIcon("assets/Meanho Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(7);
        imaxe = new ImageIcon("assets/Sorte1 Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(8);
        imaxe = new ImageIcon("assets/Corrubedo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(9);
        imaxe = new ImageIcon("assets/Lugo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(10);
        imaxe = new ImageIcon("assets/Carcere Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(0);
        imaxe = new ImageIcon("assets/O Carballinho Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(1);
        imaxe = new ImageIcon("assets/SERGAS Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(2);
        imaxe = new ImageIcon("assets/Sada Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(3);
        imaxe = new ImageIcon("assets/Mera Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(4);
        imaxe = new ImageIcon("assets/Lancha Motora Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(5);
        imaxe = new ImageIcon("assets/Negreira Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(6);
        imaxe = new ImageIcon("assets/Caixa2 Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(7);
        imaxe = new ImageIcon("assets/Viveiro Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(8);
        imaxe = new ImageIcon("assets/Ribadeo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(0);
        imaxe = new ImageIcon("assets/Parking Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(1);
        imaxe = new ImageIcon("assets/Verin Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(2);
        imaxe = new ImageIcon("assets/Sorte2 Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(3);
        imaxe = new ImageIcon("assets/Cambados Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(4);
        imaxe = new ImageIcon("assets/Tui Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(5);
        imaxe = new ImageIcon("assets/Iate Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(6);
        imaxe = new ImageIcon("assets/O Caurel Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(7);
        imaxe = new ImageIcon("assets/A Guarda Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(8);
        imaxe = new ImageIcon("assets/Ensino Publico Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(9);
        imaxe = new ImageIcon("assets/Ourense Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(10);
        imaxe = new ImageIcon("assets/IrCarcere Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(0);
        imaxe = new ImageIcon("assets/Santiago Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(1);
        imaxe = new ImageIcon("assets/Sanxenxo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(2);
        imaxe = new ImageIcon("assets/Caixa3 Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(3);
        imaxe = new ImageIcon("assets/Pontevedra Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(4);
        imaxe = new ImageIcon("assets/Jet Privado Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(5);
        imaxe = new ImageIcon("assets/Sorte3 Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(6);
        imaxe = new ImageIcon("assets/Vigo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(7);
        imaxe = new ImageIcon("assets/Baixada Pension Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(8);
        imaxe = new ImageIcon("assets/A Corunha Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));
    }

    private void layoutComp() {
        this.setLayout(disposicion);
        this.add(panelSur, BorderLayout.SOUTH);
        this.add(panelOeste, BorderLayout.WEST);
        this.add(panelNorte, BorderLayout.NORTH);
        this.add(panelEste, BorderLayout.EAST);
        JPanel aux = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        aux.setOpaque(false);
        JLabel fotoMonopoly = new JLabel();
        fotoMonopoly.setOpaque(false);
        fotoMonopoly.setPreferredSize(new Dimension(500, 500));
        fotoMonopoly.setSize(fotoMonopoly.getPreferredSize());
        ImageIcon foto = new ImageIcon("assets/Monopoly.png");
        fotoMonopoly.setIcon(new ImageIcon(foto.getImage().getScaledInstance(
                fotoMonopoly.getWidth(),
                fotoMonopoly.getHeight(),
                Image.SCALE_SMOOTH)));
        aux.add(fotoMonopoly);
        this.add(aux, BorderLayout.CENTER);
    }

    public JButton getCasilla(int i) {
        if (i <= 10) {
            return casillas.get(0).get(i);
        } else if (i <= 19) {
            return casillas.get(1).get(i - 11);
        } else if (i <= 30) {
            return casillas.get(2).get(i - 20);
        } else if (i <= 39) {
            return casillas.get(3).get(i - 31);
        }
        //Erro
        JOptionPane.showInternalMessageDialog(null,
                "Erro",
                "Erro", JOptionPane.WARNING_MESSAGE);
        return casillas.get(0).get(0);
    }

    //Getters e Setters
    public InterfazGrafica getVentaPrincipal() {
        return ventaPrincipal;
    }

    public void setVentaPrincipal(InterfazGrafica ventaPrincipal) {
        this.ventaPrincipal = ventaPrincipal;
    }

    public BorderLayout getDisposicion() {
        return disposicion;
    }

    public void setDisposicion(BorderLayout disposicion) {
        this.disposicion = disposicion;
    }

    public ArrayList<AvataresTaboleiro> getAvatares() {
        return avatares;
    }

    public ArrayList<ArrayList<JButton>> getCasillas() {
        return casillas;
    }

    public void setCasillas(ArrayList<ArrayList<JButton>> casillas) {
        this.casillas = casillas;
    }

    public JPanel getPanelSur() {
        return panelSur;
    }

    public void setPanelSur(JPanel panelSur) {
        this.panelSur = panelSur;
    }

    public JPanel getPanelOeste() {
        return panelOeste;
    }

    public void setPanelOeste(JPanel panelOeste) {
        this.panelOeste = panelOeste;
    }

    public JPanel getPanelNorte() {
        return panelNorte;
    }

    public void setPanelNorte(JPanel panelNorte) {
        this.panelNorte = panelNorte;
    }

    public JPanel getPanelEste() {
        return panelEste;
    }

    public void setPanelEste(JPanel panelEste) {
        this.panelEste = panelEste;
    }

}
