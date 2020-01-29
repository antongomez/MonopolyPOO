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
        this.disposicion = new BorderLayout();
        this.panelSur = new JPanel();
        this.panelOeste = new JPanel();
        this.panelNorte = new JPanel();
        this.panelEste = new JPanel();
    }

    private void creacionCasillas() {
        ArrayList<JButton> ladoSur = new ArrayList<>();

        JButton Saida = new JButton();
        ladoSur.add(Saida);
        JButton SantaCruz = new JButton("Santa");
        ladoSur.add(SantaCruz);
        JButton Caixa1 = new JButton("Caixa1");
        ladoSur.add(Caixa1);
        JButton Arteixo = new JButton("Arteixo");
        ladoSur.add(Arteixo);
        JButton IRPF = new JButton("IRPF");
        ladoSur.add(IRPF);
        JButton Autobus = new JButton("Auto");
        ladoSur.add(Autobus);
        JButton Meanho = new JButton("Meanho");
        ladoSur.add(Meanho);
        JButton Sorte1 = new JButton("Sorte1");
        ladoSur.add(Sorte1);
        JButton Corrubedo = new JButton("Corrubedo");
        ladoSur.add(Corrubedo);
        JButton Lugo = new JButton("Lugo");
        ladoSur.add(Lugo);
        JButton Carcere = new JButton("Carcere");
        ladoSur.add(Carcere);

        casillas.add(ladoSur);

        ArrayList<JButton> ladoOeste = new ArrayList<>();

        JButton Carballinho = new JButton("Carballinho");
        ladoOeste.add(Carballinho);
        JButton Sergas = new JButton("Sergas");
        ladoOeste.add(Sergas);
        JButton Sada = new JButton("Sada");
        ladoOeste.add(Sada);
        JButton Mera = new JButton("Mera");
        ladoOeste.add(Mera);
        JButton Lancha = new JButton("Lancha");
        ladoOeste.add(Lancha);
        JButton Cedofeita = new JButton("Cedofeita");
        ladoOeste.add(Cedofeita);
        JButton Caixa2 = new JButton("Caixa2");
        ladoOeste.add(Caixa2);
        JButton Viveiro = new JButton("Viveiro");
        ladoOeste.add(Viveiro);
        JButton Ribadeo = new JButton("Ribadeo");
        ladoOeste.add(Ribadeo);

        casillas.add(ladoOeste);

        ArrayList<JButton> ladoNorte = new ArrayList<>();

        JButton Parking = new JButton("Parking");
        ladoNorte.add(Parking);
        JButton Verin = new JButton("Verin");
        ladoNorte.add(Verin);
        JButton Sorte2 = new JButton("Sorte2");
        ladoNorte.add(Sorte2);
        JButton Cambados = new JButton("Cambados");
        ladoNorte.add(Cambados);
        JButton Tui = new JButton("Tui");
        ladoNorte.add(Tui);
        JButton Iate = new JButton("Iate");
        ladoNorte.add(Iate);
        JButton Caurel = new JButton("Caurel");
        ladoNorte.add(Caurel);
        JButton Guarda = new JButton("Guarda");
        ladoNorte.add(Guarda);
        JButton Ensino = new JButton("Ensino");
        ladoNorte.add(Ensino);
        JButton Ourense = new JButton("Ourense");
        ladoNorte.add(Ourense);
        JButton IrCarcere = new JButton("IrCarcere");
        ladoNorte.add(IrCarcere);

        casillas.add(ladoNorte);

        ArrayList<JButton> ladoEste = new ArrayList<>();

        JButton Santiago = new JButton("Santiago");
        ladoEste.add(Santiago);
        JButton Sanxenxo = new JButton("Sanxenxo");
        ladoEste.add(Sanxenxo);
        JButton Caixa3 = new JButton("Caixa3");
        ladoEste.add(Caixa3);
        JButton Pontevedra = new JButton("Pontevedra");
        ladoEste.add(Pontevedra);
        JButton Jet = new JButton("Jet");
        ladoEste.add(Jet);
        JButton Sorte3 = new JButton("Sorte3");
        ladoEste.add(Sorte3);
        JButton Vigo = new JButton("Vigo");
        ladoEste.add(Vigo);
        JButton Subida = new JButton("Subida");
        ladoEste.add(Subida);
        JButton Corunha = new JButton("Corunha");
        ladoEste.add(Corunha);

        casillas.add(ladoEste);
    }

    public void activarDescripcion(InterfazGrafica ventaPrincipal, Taboleiro taboleiro) {
        for (int i = 0; i < taboleiro.getCasillas().size(); i++) {
            new DescripcionTaboleiro(ventaPrincipal, getCasilla(taboleiro.getCasillas().get(i).getPosicion()), taboleiro.getCasillas().get(i));
        }
    }

    private void setUpComp() {

        for (int i = 0; i < casillas.size(); i++) {
            if ((i == 0) || (i == 1)) {
                for (int j = casillas.get(i).size() - 1; j >= 0; j--) {
                    setUpCasilla(casillas.get(i).get(j));
                }
            } else if ((i == 2) || (i == 3)) {
                for (int j = 0; j < casillas.get(i).size(); j++) {
                    setUpCasilla(casillas.get(i).get(j));
                }
            }
        }

        //Colocanse as imaxes enriba dos botons
        JButton casilla;

        casilla = casillas.get(0).get(0);
        ImageIcon imaxeSaida = new ImageIcon("FotosMonopoly/SantaCruz.png");
        casilla.setIcon(new ImageIcon(imaxeSaida.getImage().getScaledInstance(50, 62, Image.SCALE_SMOOTH)));
        //colocarIconos();

        GridLayout layoutSur = new GridLayout(1, 11, 0, 0);

        this.panelSur.setLayout(layoutSur);
        for (int j = 10;
                j >= 0; j--) {
            panelSur.add(casillas.get(0).get(j));
        }

        GridLayout layoutOeste = new GridLayout(9, 1, 0, 0);

        this.panelOeste.setLayout(layoutOeste);

        for (int j = 8;
                j >= 0; j--) {
            setUpCasilla(casillas.get(1).get(j));
            panelOeste.add(casillas.get(1).get(j));
        }

        GridLayout layoutNorte = new GridLayout(1, 11, 0, 0);

        this.panelNorte.setLayout(layoutNorte);

        for (int j = 0;
                j <= 10; j++) {
            setUpCasilla(casillas.get(2).get(j));
            panelNorte.add(casillas.get(2).get(j));
        }

        GridLayout layoutEste = new GridLayout(9, 1, 0, 0);

        this.panelEste.setLayout(layoutEste);

        for (int j = 0;
                j <= 8; j++) {
            setUpCasilla(casillas.get(3).get(j));
            panelEste.add(casillas.get(3).get(j));
        }

    }

    private void setUpCasilla(JButton casilla) {
        casilla.setPreferredSize(new Dimension(50, 62));
        casilla.setSize(new Dimension(50, 62));
        casilla.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));
    }

    private void colocarIconos() {
        JButton casilla;

        casilla = casillas.get(0).get(1);
        ImageIcon imaxeSaida = new ImageIcon("FotosMonopoly/Santa Cruz.png");
        casilla.setIcon(new ImageIcon(imaxeSaida.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));
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
