package consola;

import java.util.Scanner;

public class ConsolaNormal implements Consola {

    public ConsolaNormal() {
    }

    @Override
    public void imprimir(String mensaxe) {
        if (mensaxe != null) {
            System.out.println(mensaxe);
        }
    }

    @Override
    public String ler(String descricion) {
        String mensaxe = null;
        if (descricion != null) {

            System.out.println(descricion);
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
            Scanner sc = new Scanner(System.in);
            mensaxe = sc.next();
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
}
