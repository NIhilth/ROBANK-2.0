public class ContaInexistenteException extends RuntimeException {
    public ContaInexistenteException() {
        super("Conta n�o encontrada!");
    }
}
