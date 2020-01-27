package Evento;

import InterfazGrafica.InterfazGrafica;
import InterfazGrafica.PanelComandos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;

public class XestionCampoTexto implements KeyListener {
    //

    private InterfazGrafica ventaPrincipal;

    public XestionCampoTexto(InterfazGrafica interfaz) {
        this.ventaPrincipal = interfaz;
    }

    public InterfazGrafica getVentanaPrincipal() {
        return ventaPrincipal;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyChar() == '\n') {
            String comando = ventaPrincipal.getPanelComandos().getCampoComandos().getText();
            ventaPrincipal.getPanelComandos().getHistorialComandos().append(comando + "\n");
            ventaPrincipal.getPanelComandos().getCampoComandos().setText("");
        }
    }
}
