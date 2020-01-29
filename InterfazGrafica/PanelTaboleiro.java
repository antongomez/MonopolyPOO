package InterfazGrafica;

import Evento.DescripcionTaboleiro;
import estrutura.Taboleiro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelTaboleiro extends JPanel {

    private InterfazGrafica ventaPrincipal;
    private BorderLayout disposicion;
    private ArrayList<ArrayList<JButton>> casillas;

    private JPanel panelSur;
    private JPanel panelOeste;
    private JPanel panelNorte;
    private JPanel panelEste;

    public PanelTaboleiro(InterfazGrafica ventaPrincipal, Taboleiro taboleiro) {
        this.ventaPrincipal = ventaPrincipal;
        initComp();
        creacionCasillas();
        setUpComp();
        layoutComp();
        activarDescripcion(ventaPrincipal, taboleiro);

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
    }

    public void activarDescripcion(InterfazGrafica ventaPrincipal, Taboleiro taboleiro) {
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            new DescripcionTaboleiro(ventaPrincipal, getCasilla(taboleiro.getCasillas().get(i).getPosicion()), taboleiro.getCasillas().get(i));
        }
    }

    private void setUpComp() {
        //this.setPreferredSize(new Dimension(600, 800));

        for (int i = 0; i < casillas.size(); i++) {
            if ((i == 0) || (i == 1)) {
                for (int j = casillas.get(i).size() - 1; j >= 0; j--) {
                    setUpCasillaVertical(casillas.get(i).get(j));
                }
            } else if ((i == 2) || (i == 3)) {
                for (int j = 0; j < casillas.get(i).size(); j++) {
                    setUpCasillaVertical(casillas.get(i).get(j));
                }
            }
        }

        //Colocanse as imaxes enriba dos botons
        colocarIconos();

        GridLayout layoutSur = new GridLayout(1, 11, 0, 0);
        this.panelSur.setLayout(layoutSur);
        for (int j = 10; j >= 0; j--) {
            setUpCasillaVertical(casillas.get(0).get(j));
            panelSur.add(casillas.get(0).get(j));
        }

        GridLayout layoutOeste = new GridLayout(9, 1, 0, 0);
        this.panelOeste.setLayout(layoutOeste);

        for (int j = 8; j >= 0; j--) {
            setUpCasillaHorizontal(casillas.get(1).get(j));
            panelOeste.add(casillas.get(1).get(j));
        }

        GridLayout layoutNorte = new GridLayout(1, 11, 0, 0);
        this.panelNorte.setLayout(layoutNorte);

        for (int j = 0; j <= 10; j++) {
            setUpCasillaVertical(casillas.get(2).get(j));
            panelNorte.add(casillas.get(2).get(j));
        }

        GridLayout layoutEste = new GridLayout(9, 1, 0, 0);
        this.panelEste.setLayout(layoutEste);

        for (int j = 0; j <= 8; j++) {
            setUpCasillaHorizontal(casillas.get(3).get(j));
            panelEste.add(casillas.get(3).get(j));
        }

    }

    private void setUpCasillaVertical(JButton casilla) {
        casilla.setPreferredSize(new Dimension(56, 65));
        casilla.setSize(new Dimension(56, 65));
        casilla.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));
    }

    private void setUpCasillaHorizontal(JButton casilla) {
        casilla.setPreferredSize(new Dimension(65, 56));
        casilla.setSize(new Dimension(65, 56));
        casilla.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));
    }

    private void colocarIconos() {
        JButton casilla;
        ImageIcon imaxe;

        casilla = casillas.get(0).get(1);
        imaxe = new ImageIcon("FotosMonopoly/Santa Cruz Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(3);
        imaxe = new ImageIcon("FotosMonopoly/Arteixo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(6);
        imaxe = new ImageIcon("FotosMonopoly/Meaño Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(8);
        imaxe = new ImageIcon("FotosMonopoly/Corrubedo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(0).get(9);
        imaxe = new ImageIcon("FotosMonopoly/Lugo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(0);
        imaxe = new ImageIcon("FotosMonopoly/O Carballiño Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(2);
        imaxe = new ImageIcon("FotosMonopoly/Sada Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(3);
        imaxe = new ImageIcon("FotosMonopoly/Mera Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(5);
        imaxe = new ImageIcon("FotosMonopoly/Negreira Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(6);
        imaxe = new ImageIcon("FotosMonopoly/Viveiro Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(1).get(8);
        imaxe = new ImageIcon("FotosMonopoly/Ribadeo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(1);
        imaxe = new ImageIcon("FotosMonopoly/Verín Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(3);
        imaxe = new ImageIcon("FotosMonopoly/Cambados Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(4);
        imaxe = new ImageIcon("FotosMonopoly/Tui Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(6);
        imaxe = new ImageIcon("FotosMonopoly/O Caurel Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(7);
        imaxe = new ImageIcon("FotosMonopoly/A Guarda Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(2).get(9);
        imaxe = new ImageIcon("FotosMonopoly/Ourense Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(0);
        imaxe = new ImageIcon("FotosMonopoly/Santiago Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(1);
        imaxe = new ImageIcon("FotosMonopoly/Sanxenxo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(3);
        imaxe = new ImageIcon("FotosMonopoly/Pontevedra Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(6);
        imaxe = new ImageIcon("FotosMonopoly/Vigo Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));

        casilla = casillas.get(3).get(8);
        imaxe = new ImageIcon("FotosMonopoly/A Coruña Taboleiro.png");
        casilla.setIcon(new ImageIcon(imaxe.getImage().getScaledInstance(65, 56, Image.SCALE_SMOOTH)));
    }

    private void layoutComp() {
        this.setLayout(disposicion);
        this.add(panelSur, BorderLayout.SOUTH);
        this.add(panelOeste, BorderLayout.WEST);
        this.add(panelNorte, BorderLayout.NORTH);
        this.add(panelEste, BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.CENTER);
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
                "Victor pasache mal o número da casilla",
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
