
package errosExternos.errosExistencia;

public class XogadorNonExiste extends NonExiste {

    public XogadorNonExiste(String nomeXogador) {
        super("O xogador " + nomeXogador + " non existe.");
    }
}