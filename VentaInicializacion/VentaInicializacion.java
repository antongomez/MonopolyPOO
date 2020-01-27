package VentaInicializacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class VentaInicializacion extends JFrame {

    private JPanel panel;
    private HashMap<String, JLabel> etiquetas;
    private HashMap<String, JTextField> camposTexto;
    private JButton botonAceptar;

    public VentaInicializacion(int nXogadores) {
        iniciarVenta(); //Inicializase a Venta
        initComp(); //Inicializanse os atributos
        setUpPanel(nXogadores); //Inicialízanse e colócanse os elementos sobre o panel
        colocarPanel(); //Colocase o panel
    }

    private void iniciarVenta() {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //A venta non se pode pechar
        //this.setLocationRelativeTo(null);

        //Victor: Centrado que me funciona
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setLocation(width/4, height/4);
        //Víctor

        this.setTitle("Introdución de Xogadores");
        this.setSize(new Dimension(500, 600));
        this.setPreferredSize(new Dimension(500, 600));
    }

    private void initComp() {
        GridLayout layout = new GridLayout(13, 2);
        layout.setHgap(10);
        layout.setVgap(10);
        panel = new JPanel(layout);
        etiquetas = new HashMap<>();
        camposTexto = new HashMap<>();
        botonAceptar = new JButton("Aceptar");
    }

    private void setUpPanel(int nXogadores) {
        initCompPanel(nXogadores);

        colocarElementos(nXogadores);

        darFormaPanel();
    }

    private void initCompPanel(int nXogadores) {
        //Inicializanse as etiquetas e as JTextField
        for (int i = 0; i < nXogadores; i++) {
            /*ID DAS ETIQUETAS, EXEMPLO:
             * etiqueta que ide o nome do xogador 2: 2.1
             * etiquetas que pide o tipo de avatar do xogador 1: 1.2
             */
            String idEtiquetaNome = (i + 1) + ".1"; //Id das etiquetas
            String idEtiquetaAvatar = (i + 1) + ".2";

            etiquetas.put(idEtiquetaNome, new JLabel("Nome do "
                    + "xogador " + (i + 1) + ": "));
            etiquetas.put(idEtiquetaAvatar, new JLabel("Tipo de "
                    + "avatar do xogador " + (i + 1) + ": "));

            String idTextFieldNome = (i + 1) + ".1"; //Id das JTextField
            String idTextFieldAvatar = (i + 1) + ".2";

            camposTexto.put(idEtiquetaNome, new JTextField(20));
            camposTexto.put(idEtiquetaAvatar, new JTextField(20));
        }

    }

    private void colocarElementos(int nXogadores) {
        for (int i = 0; i < nXogadores; i++) {
            panel.add(etiquetas.get((i + 1) + ".1"));
            panel.add(camposTexto.get((i + 1) + ".1"));
            panel.add(etiquetas.get((i + 1) + ".2"));
            panel.add(camposTexto.get((i + 1) + ".2"));
        }
        panel.add(botonAceptar);
    }

    private void darFormaPanel() {
        Border border = BorderFactory.createTitledBorder("Inicialización");
        panel.setBorder(border);
        panel.setPreferredSize(new Dimension(450, 550));
    }

    private void colocarPanel() {
        this.add(panel);
    }

    public void engadirAccionBoton(ActionListener al) {
        //Colocaselle a accion ao boton
        botonAceptar.addActionListener(al);
    }

    //Getters e Setters
    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public HashMap<String, JLabel> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(HashMap<String, JLabel> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public HashMap<String, JTextField> getCamposTexto() {
        return camposTexto;
    }

    public void setCamposTexto(HashMap<String, JTextField> camposTexto) {
        this.camposTexto = camposTexto;
    }

    public JButton getBotonAceptar() {
        return botonAceptar;
    }

    public void setBotonAceptar(JButton botonAceptar) {
        this.botonAceptar = botonAceptar;
    }

}
