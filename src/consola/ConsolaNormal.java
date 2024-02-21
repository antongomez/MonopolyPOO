package consola;

import InterfazGrafica.PanelSaida;

import javax.swing.*;
import java.util.Scanner;

public class ConsolaNormal implements Consola {
    private PanelSaida saida;

    public ConsolaNormal() {
    }


    @Override
    public void imprimir(String mensaxe) {
        if (mensaxe != null) {
            System.out.println(mensaxe);
            saida.terminal(mensaxe);
        }
    }

    @Override
    public String ler(String descricion, JTextField campotexto) {

        String mensaxe = null;
        if (descricion != null) {

            System.out.println(descricion);
            saida.terminal(descricion);
            synchronized (campotexto) {
                try {
                    campotexto.wait();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            mensaxe = campotexto.getText();
        } else {
            //Excepcion
        }
        return mensaxe;
    }

    @Override
    public String ler(String descricion) {
        String mensaxe = null;
        if (descricion != null) {

            System.out.println(descricion);
            saida.terminal(mensaxe);
            Scanner sc = new Scanner(System.in);
            mensaxe = sc.next();
        } else {
            //Excepcion
        }
        return mensaxe;
    }

    @Override
    public String lerLinha(String descricion) {
        String mensaxe = null;
        if (descricion != null) {

            System.out.println(descricion);
            saida.terminal(mensaxe);
            Scanner sc = new Scanner(System.in);
            mensaxe = sc.nextLine();
        } else {
            //Excepcion
        }
        return mensaxe;
    }

    public String lerLinha(String descricion, JTextField campotexto) {
        String mensaxe = null;
        if (descricion != null) {

            System.out.println(descricion);
            saida.terminal(mensaxe);
            //Scanner sc = new Scanner(System.in);
            //mensaxe = sc.nextLine();
            mensaxe = campotexto.getText();
        } else {
            //Excepcion
        }
        return mensaxe;
    }

    @Override
    public String ler() {
        String mensaxe;

        Scanner sc = new Scanner(System.in);
        mensaxe = sc.next();

        return mensaxe;
    }

    @Override
    public String lerLinha() {
        String mensaxe;

        Scanner sc = new Scanner(System.in);
        mensaxe = sc.nextLine();

        return mensaxe;
    }

    public PanelSaida getSaida() {
        return saida;
    }

    public void setSaida(PanelSaida saida) {
        this.saida = saida;
    }
}
