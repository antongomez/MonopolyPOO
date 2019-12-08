package carta;

public class Caixa extends Carta {

    public Caixa(String nome, String accion)
    {
        super(nome, accion);
    }

    public void accion()
    {
        String accion = "";
        String partes[] = super.getNome().split("-");
        switch (partes[0])
        {

        }
    }
}
