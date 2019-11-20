package xogo;

public class Dado {

    private int dado;

    public Dado() {
        dado = 0;
    }

    public int getDado() {
        if ((dado < 1) || (dado > 6)) {
            System.out.println("\nDado non inicilizado.\n");
        }
        return this.dado;
    }

    public void setDado(int n) {
        if ((n > 0) && (n < 7)) {
            dado = n;
        }
    }

    public void tirardado() {
        //dado=2; Probar dobres
        dado = (int) Math.floor(Math.random() * 6 + 1);
    }
}
