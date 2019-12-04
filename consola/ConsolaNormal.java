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
        String mensaxe;
        System.out.println(descricion);
        Scanner sc = new Scanner(System.in);
        mensaxe = sc.next();

        return mensaxe;
    }
}
