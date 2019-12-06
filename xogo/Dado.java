package xogo;

public class Dado {

    private int valor;

    public Dado() {
        valor = 0;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int n) {
        if ((n >= 0) && (n < 7)) {
            valor = n;
        }
    }

    public void resetDado() {
        this.valor = 0;
    }

    public void tirardado() {
        valor = (int) Math.floor(Math.random() * 6 + 1);
    }

    @Override
    public String toString() {
        String texto = "";

        texto += this.getValor();

        return texto;
    }

    @Override
    public boolean equals(Object d2) {
        if (!(d2 instanceof Dado)) {
            return false;
        }
        return (this.valor == ((Dado) d2).getValor());
    }
}
