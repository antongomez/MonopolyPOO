package Evento;

import InterfazGrafica.InterfazGrafica;
import InterfazGrafica.PanelComandos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;

public class XestionCampoTexto implements KeyListener {
    private String comando;
    private boolean valido;

    private InterfazGrafica ventaPrincipal;

    public XestionCampoTexto(InterfazGrafica interfaz) {

        this.ventaPrincipal = interfaz;
        this.comando = " ";
        this.valido = false;
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

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyChar() == '\n') {
            comando = ventaPrincipal.getPanelComandos().getCampoComandos().getText();
            String[] partes = comando.split(" ");
            String comando2 = partes[0];

            switch (comando2)
            {
                case "aceptar":
                case "eliminar":
                case "listar":
                case "describir":
                case "cambiar":
                case "lanzar":
                case "rematar":
                case "comprar":
                case "sair":
                case "xogador":
                case "estatisticas":
                    synchronized (ventaPrincipal.getPanelComandos().getCampoComandos()) {
                        ventaPrincipal.getPanelComandos().getCampoComandos().notifyAll();
                    }

                    ventaPrincipal.getPanelComandos().getHistorialComandos().append(comando + "\n");
                    ventaPrincipal.getPanelComandos().getCampoComandos().setText("");
                    break;
            }
        }


    }
}
