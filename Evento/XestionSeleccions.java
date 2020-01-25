package Evento;

import InterfazGrafica.InterfazGrafica;
import InterfazGrafica.PanelSeleccion;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author Manuel Lama Pen�n
 *
 */
public class XestionSeleccions implements ItemListener {
    //

    private InterfazGrafica ventanaPrincipal;
    private static int nseleccion;

    /**
     *
     * @param interfaz
     */
    public XestionSeleccions(InterfazGrafica interfaz) {
        this.ventanaPrincipal = interfaz;
    }

    /**
     *
     */
    public void itemStateChanged(ItemEvent evento) {
    }

    /**
     *
     * @return
     */
    public InterfazGrafica getVentanaPrincipal() {
        return ventanaPrincipal;
    }
}
