package errosExternos.errosExistencia;

public class AvatarNonExiste extends NonExiste {

    public AvatarNonExiste(char Id) {
        super("O avatar " + Id + " non existe.");
    }
}
