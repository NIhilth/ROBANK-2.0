public class OperacaoZeroException extends RuntimeException {
    public OperacaoZeroException(String operacao) {
        super("N�o � poss�vel " + operacao + " zero reais.");
    }
}
