
package errosExternos.errosExistencia;

public class CasillaNonExiste extends NonExiste {

    public CasillaNonExiste(String nomeCasilla) {
        super("O xogador " + nomeCasilla + " non existe.");
    }
}