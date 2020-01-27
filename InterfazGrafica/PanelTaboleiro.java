package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelTaboleiro extends JPanel {

    private InterfazGrafica ventaPrincipal;
    private BorderLayout disposicion;
    private ArrayList<ArrayList<JButton>> casillas;

    private JPanel panelSur;
    private JPanel panelOeste;
    private JPanel panelNorte;
    private JPanel panelEste;

    public PanelTaboleiro(InterfazGrafica ventaPrincipal) {
        this.ventaPrincipal = ventaPrincipal;
        initComp();
        creacionCasillas();
        setUpComp();
        layoutComp();
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

        JButton Saida = new JButton("Saida");
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
        ladoSur.add(Carballinho);
        JButton Sergas = new JButton("Sergas");
        ladoSur.add(Sergas);
        JButton Sada = new JButton("Sada");
        ladoSur.add(Sada);
        JButton Mera = new JButton("Mera");
        ladoSur.add(Mera);
        JButton Lancha = new JButton("Lancha");
        ladoSur.add(Lancha);
        JButton Cedofeita = new JButton("Cedofeita");
        ladoSur.add(Cedofeita);
        JButton Caixa2 = new JButton("Caixa2");
        ladoSur.add(Caixa2);
        JButton Viveiro = new JButton("Viveiro");
        ladoSur.add(Viveiro);
        JButton Ribadeo = new JButton("Ribadeo");
        ladoSur.add(Ribadeo);

        casillas.add(ladoOeste);

        ArrayList<JButton> ladoNorte = new ArrayList<>();

        JButton Parking = new JButton("Parking");
        ladoSur.add(Parking);
        JButton Verin = new JButton("Verin");
        ladoSur.add(Verin);
        JButton Sorte2 = new JButton("Sorte2");
        ladoSur.add(Sorte2);
        JButton Cambados = new JButton("Cambados");
        ladoSur.add(Cambados);
        JButton Tui = new JButton("Tui");
        ladoSur.add(Tui);
        JButton Iate = new JButton("Iate");
        ladoSur.add(Iate);
        JButton Caurel = new JButton("Caurel");
        ladoSur.add(Caurel);
        JButton Guarda = new JButton("Guarda");
        ladoSur.add(Guarda);
        JButton Ensino = new JButton("Ensino");
        ladoSur.add(Ensino);
        JButton Ourense = new JButton("Ourense");
        ladoSur.add(Ourense);
        JButton IrCarcere = new JButton("IrCarcere");
        ladoSur.add(IrCarcere);

        casillas.add(ladoNorte);

        ArrayList<JButton> ladoEste = new ArrayList<>();

        JButton Santiago = new JButton("Santiago");
        ladoSur.add(Santiago);
        JButton Sanxenxo = new JButton("Sanxenxo");
        ladoSur.add(Sanxenxo);
        JButton Caixa3 = new JButton("Caixa3");
        ladoSur.add(Sorte2);
        JButton Pontevedra = new JButton("Pontevedra");
        ladoSur.add(Pontevedra);
        JButton Jet = new JButton("Jet");
        ladoSur.add(Jet);
        JButton Sorte3 = new JButton("Sorte3");
        ladoSur.add(Sorte3);
        JButton Vigo = new JButton("Vigo");
        ladoSur.add(Vigo);
        JButton Subida = new JButton("Subida");
        ladoSur.add(Subida);
        JButton Corunha = new JButton("Corunha");
        ladoSur.add(Corunha);

        casillas.add(ladoEste);
    }

    private void setUpComp() {
        GridLayout layoutSur = new GridLayout(1, 11, 0, 0);
        this.panelSur.setLayout(layoutSur);

        for (int j = 10; j >= 0; j--) {
            establecerTamCasilla(casillas.get(0).get(j));
            panelSur.add(casillas.get(0).get(j));
        }

        GridLayout layoutOeste = new GridLayout(9, 1, 0, 0);
        this.panelOeste.setLayout(layoutOeste);

        for (int j = 19; j > 10; j--) {
            establecerTamCasilla(casillas.get(0).get(j));
            panelOeste.add(casillas.get(0).get(j));
        }

        GridLayout layoutNorte = new GridLayout(1, 11, 0, 0);
        this.panelNorte.setLayout(layoutNorte);

        for (int j = 30; j > 19; j--) {
            establecerTamCasilla(casillas.get(0).get(j));
            panelNorte.add(casillas.get(0).get(j));
        }

        GridLayout layoutEste = new GridLayout(9, 1, 0, 0);
        this.panelEste.setLayout(layoutEste);

        for (int j = 39; j > 30; j--) {
            establecerTamCasilla(casillas.get(0).get(j));
            panelEste.add(casillas.get(0).get(j));
        }

    }

    private void establecerTamCasilla(JButton casilla) {
        casilla.setSize(new Dimension(30, 30));
    }

    private void layoutComp() {
        this.setLayout(disposicion);
        this.add(panelSur, BorderLayout.SOUTH);
        this.add(panelOeste, BorderLayout.WEST);
        this.add(panelNorte, BorderLayout.NORTH);
        this.add(panelEste, BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.CENTER);
    }

}
