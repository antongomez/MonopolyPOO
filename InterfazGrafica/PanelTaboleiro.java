package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelTaboleiro extends JPanel {
    
    private InterfazGrafica ventaPrincipal;
    private BorderLayout disposicion;
    private ArrayList<ArrayList<JButton>> casillas;
    
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
        this.setPreferredSize(new Dimension(400, 400));
    }
    
    private void creacionCasillas() {
        ArrayList<JButton> ladoSur = new ArrayList<>();
        
        JButton Saida = new JButton("S");
        ladoSur.add(Saida);
        JButton SantaCruz = new JButton("s");
        ladoSur.add(SantaCruz);
        JButton Caixa1 = new JButton("C1");
        ladoSur.add(Caixa1);
        JButton Arteixo = new JButton("Art");
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
        
        casillas.add(ladoSur);
        
        ArrayList<JButton> ladoOeste = new ArrayList<>();
        ArrayList<JButton> ladoNorte = new ArrayList<>();
        ArrayList<JButton> ladoEste = new ArrayList<>();
    }
    
    private void setUpComp() {
        for (int j = 0; j < 10; j++) {
            establecerTamCasilla(casillas.get(0).get(j));
        }
    }
    
    private void establecerTamCasilla(JButton casilla) {
        casilla.setSize(new Dimension(30, 30));
    }
    
    private void layoutComp() {
        this.setLayout(disposicion);
        this.add(casillas.get(0).get(0), BorderLayout.SOUTH);
        this.add(casillas.get(0).get(1), BorderLayout.EAST);
        this.add(casillas.get(0).get(2), BorderLayout.WEST);
        this.add(casillas.get(0).get(3), BorderLayout.NORTH);
        /*for (int j = 9; j >= 0; j--) {
            this.add(casillas.get(0).get(j), BorderLayout.SOUTH);
        }*/
    }
    
}
