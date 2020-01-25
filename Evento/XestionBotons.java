package Evento;

import InterfazGrafica.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class XestionBotons implements ActionListener {
    //

    private InterfazGrafica ventanaPrincipal;
    private PanelResultados resultados;

    /**
     *
     * @param interfaz
     */
    public XestionBotons(InterfazGrafica interfaz) {
        this.ventanaPrincipal = interfaz;
    }

    /**
     *
     */
    public void actionPerformed(ActionEvent evento) {
        PanelSeleccion seleccion = this.ventanaPrincipal.getSeleccion();
        if (evento.getSource().equals(seleccion.getBotonOk())) {
            int posicion = seleccion.getCombo().getSelectedIndex();
            String textoResultado = "ID <b>" + seleccion.getId().getText() + "</b> <br>";
            if (seleccion.getFormatoCorto().isSelected()) {
                textoResultado = textoResultado + Noticias.noticiasCortas[posicion];
            } else if (seleccion.getFormatoLargo().isSelected()) {
                textoResultado = textoResultado + Noticias.noticiasLargas[posicion];
            }
            this.resultados = new PanelResultados(this.ventanaPrincipal);
            this.resultados.getAreaResultados().setText(textoResultado);
            this.ventanaPrincipal.getTabs().addTab(seleccion.getId().getText(), this.resultados);
        } //
        else {
            this.resultados = (PanelResultados) this.ventanaPrincipal.getTabs().getSelectedComponent();
            if (evento.getSource().equals(this.resultados.getBotonCerrar())) {
                this.ventanaPrincipal.getTabs().remove(this.resultados);
            }
        }
    }

    /**
     *
     * @return
     */
    public PanelResultados getResultados() {
        return resultados;
    }

    /**
     *
     * @return
     */
    public InterfazGrafica getVentanaPrincipal() {
        return ventanaPrincipal;
    }
}
