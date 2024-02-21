package errosExternos.errosExistencia;

public class GrupoNonExiste extends NonExiste {

    public GrupoNonExiste(String nomeGrupo) {
        super("O grupo " + nomeGrupo + " non existe.");
    }
}
