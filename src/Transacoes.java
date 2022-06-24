/*
 * @since: 0.2
 */

public interface Transacoes {
    void sacar(double valor);
    void depositar(double valor);
    void transferir(ContaBancaria conta , double valor);
}
