package errosExternos;

public class TratoNonPropostoA extends ErroExterno {

    public TratoNonPropostoA(String nomeTrato, String nomeXogador) {
        super("O trato " + nomeTrato + " non foi proposto ao xogador "
                + nomeXogador + ".\n");
    }
}