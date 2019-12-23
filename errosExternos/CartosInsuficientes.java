package errosExternos;

public class CartosInsuficientes extends ErroExterno {

    public CartosInsuficientes(String nomeXogador) {
        super("O xogador " + nomeXogador + " non ten GreloMoedas suficientes"
                + " para realizar a acción.\n");
    }
}
