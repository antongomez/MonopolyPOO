package consola;

import javax.swing.*;

public interface Consola {

    /*Metodo que imprime unha mensaxe usando System.out.println*/
    public void imprimir(String descricion);

    /*Metodo que le un dato usando a clase Scanner*/
    public String ler(String mensaxe, JTextField campoTexto);
    public String ler(String mensaxe);


    public String lerLinha(String mensaxe);

    /*Metodo que le un dato sen pasar unha dscricion*/
    public String ler();

    public String lerLinha();
}
