package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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

    private void setUpComp() {
        GridLayout layoutSur = new GridLayout(1, 11, 0, 0);
        this.panelSur.setLayout(layoutSur);

        for (int j = 10; j >= 0; j--) {
            setUpCasilla(casillas.get(0).get(j));
            panelSur.add(casillas.get(0).get(j));
        }

        GridLayout layoutOeste = new GridLayout(9, 1, 0, 0);
        this.panelOeste.setLayout(layoutOeste);

        for (int j = 8; j >= 0; j--) {
            setUpCasilla(casillas.get(1).get(j));
            panelOeste.add(casillas.get(1).get(j));
        }

        GridLayout layoutNorte = new GridLayout(1, 11, 0, 0);
        this.panelNorte.setLayout(layoutNorte);

        for (int j = 0; j <= 10; j++) {
            setUpCasilla(casillas.get(2).get(j));
            panelNorte.add(casillas.get(2).get(j));
        }

        GridLayout layoutEste = new GridLayout(9, 1, 0, 0);
        this.panelEste.setLayout(layoutEste);

        for (int j = 0; j <= 8; j++) {
            setUpCasilla(casillas.get(3).get(j));
            panelEste.add(casillas.get(3).get(j));
        }

    }

    private void setUpCasilla(JButton casilla) {
        casilla.setPreferredSize(new Dimension(60, 60));
        casilla.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));
    }

    private void layoutComp() {
        this.setLayout(disposicion);
        this.add(panelSur, BorderLayout.SOUTH);
        this.add(panelOeste, BorderLayout.WEST);
        this.add(panelNorte, BorderLayout.NORTH);
        this.add(panelEste, BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.CENTER);
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
