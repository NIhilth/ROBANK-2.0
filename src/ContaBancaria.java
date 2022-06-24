public abstract class ContaBancaria implements Transacoes{

    private int numeroConta;
    private double saldo = 0;
    private static int qtdContas = 0;

    public ContaBancaria() {
        qtdContas++;
        this.numeroConta = qtdContas;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract String mostrarDados();

    /*
    * Transfere de uma conta bancária para outra
    * @since: 0.1
    * @param: conta
    * @param: valor
    *
    * */
    public void transferir(ContaBancaria conta, double valor) {
        this.sacar(valor);
        conta.depositar(valor);
    }

}
