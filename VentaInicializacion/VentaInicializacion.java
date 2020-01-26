package VentaInicializacion;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //A execucion finalizase cando se pecha a venta
        this.setLocationRelativeTo(null);
        this.setTitle("Introdución de XOgadores");
        this.setMinimumSize(new Dimension(200, 200));
        this.setSize(new Dimension(400, 400));
    }

    private void initComp() {
        panel = new JPanel();
        etiquetas = new HashMap<>();
        camposTexto = new HashMap<>();
        botonAceptar = new JButton("Aceptar");
    }

    private void setUpPanel(int nXogadores) {
        initCompPanel(nXogadores);

        colocarElementos(nXogadores);
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

            etiquetas.put(idEtiquetaNome, new JLabel("Introduce o nome do "
                    + "xogador " + (i + 1) + ": "));
            etiquetas.put(idEtiquetaAvatar, new JLabel("Introduce o tipo de "
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

    private void colocarPanel() {
        this.add(panel);
    }
}
