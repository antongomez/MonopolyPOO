package errosExternos.errosExistencia;

public class TratoNonExiste extends NonExiste {

    public TratoNonExiste(String nomeTrato) {
        super("O trato " + nomeTrato + " non existe.");
    }
    public TratoNonExiste(){
        super("Débese introducir o nome dun trato.");
    }
}
