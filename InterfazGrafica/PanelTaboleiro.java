package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Manuel Lama Pen�n
 *
 */
public class PanelTaboleiro extends JPanel {
    //

    private JPanel panelComponentes;
    private JSplitPane split;
    private JPanel panelTab;
    private ArrayList<ArrayList<JButton>> casillas;
    private InterfazGrafica ventaPrincipal;

    public PanelTaboleiro(InterfazGrafica interfaz) {
        this.ventaPrincipal = interfaz;
        initComponents();
        creacionCasillas();
        setupComponents();
        layoutComponents();
        addEventHandlers();
    }

    public void initComponents() {

        this.panelComponentes = new JPanel();
        this.split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        this.panelTab = new JPanel();
        this.casillas = new ArrayList<>();

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

        casillas.add(ladoSur);

        ArrayList<JButton> ladoOeste = new ArrayList<>();
        ArrayList<JButton> ladoNorte = new ArrayList<>();
        ArrayList<JButton> ladoEste = new ArrayList<>();
    }

    private void setupComponents() {

        BorderLayout layout = new BorderLayout();
        this.panelTab.setLayout(layout);
        split.setLeftComponent(panelTab);
    }

    private void layoutComponents() {

    }

    private void addEventHandlers() {
        //this.botonCerrar.addActionListener(new XestionBotons(this.ventaPrincipal));
    }

    public JPanel getPanelComponentes() {
        return panelComponentes;
    }

    public InterfazGrafica getVentaPrincipal() {
        return ventaPrincipal;
    }
}
