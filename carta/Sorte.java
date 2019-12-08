package carta;

public class Sorte extends Carta {


    public Sorte(String nome, String accion)
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
