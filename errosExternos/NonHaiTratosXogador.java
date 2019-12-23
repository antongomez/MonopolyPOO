
package errosExternos;

public class NonHaiTratosXogador extends ErroExterno {

    public NonHaiTratosXogador(String nomeXogador) {
        super("O xogador " + nomeXogador + " non ten tratos pendentes.");
    }
}