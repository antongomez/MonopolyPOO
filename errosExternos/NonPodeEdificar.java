package errosExternos;

public class NonPodeEdificar extends ErroExterno {

    public NonPodeEdificar(String nomeXogador, String tipoEdificacion, String nomeSolar) {
        super("O xogador " + nomeXogador + " non pode construír "
                + tipoEdificacion + " no solar " + nomeSolar + ".");
    }
}
