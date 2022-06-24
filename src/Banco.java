import java.util.ArrayList;

/*
* Modela um objeto Banco que pode possuir várias contas bancárias.
*
*/

public class Banco extends Object {
    private ArrayList<ContaBancaria> contas = new ArrayList<>();

    public void inserir(ContaBancaria conta) {
        contas.add(conta);
    }

    public void remover(ContaBancaria conta) {
        contas.remove(conta);
    }

    public ContaBancaria procurarConta(int numeroConta) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        throw new ContaInexistenteException();
    }

    public String mostrarContas() {
        String dadosContas = "";
        for (ContaBancaria conta : contas) {
            dadosContas += conta.mostrarDados() + "\n";
        }
        return dadosContas;
    }
}
