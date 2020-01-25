package Evento;

import InterfazGrafica.InterfazGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class XestionMenus implements ActionListener {
    //

    private InterfazGrafica ventanaPrincipal;

    /**
     *
     * @param interfaz
     */
    public XestionMenus(InterfazGrafica interfaz) {
        this.ventanaPrincipal = interfaz;
    }

    /**
     *
     */
    public void actionPerformed(ActionEvent evento) {
    }

    /**
     *
     * @return
     */
    public InterfazGrafica getVentanaPrincipal() {
        return ventanaPrincipal;
    }
}
