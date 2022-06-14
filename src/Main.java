import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Banco banco = new Banco();

    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal(){
        System.out.println("""
                --- Bem Vindo ao Banco ---
                1 - Criar conta;
                2 - Selecionar conta;
                3 - Remover conta;
                4 - Gerar relatório;
                5 - Encerrar.""");
        try {
            switch (sc.nextInt()) {
                case 1 -> createAccount();
                case 2 -> selectAccount();
                case 3 -> removeAccount();
                case 4 -> generateReport();
                case 5 -> System.exit(0);
                default -> throw new OpcaoInvalidaException();
            }
        } catch (RuntimeException idiota){
            System.out.println(idiota.getClass().getSimpleName() + ": " + idiota.getMessage() + "\n");
            menuPrincipal();
        } finally {
            menuPrincipal();
        }
    }

    public static  void createAccount(){
        System.out.println("""
                --- Account Creation ---
                Account Type:
                1 - Checking Account;
                2 - Savings Account.""");
        int accountOption = sc.nextInt();
        System.out.print("Account number: ");
        int accountNumber = sc.nextInt();

        if(null != banco.procurarConta(accountNumber)){
            throw new ContaExistenteException();
        };

        System.out.print("Current balance: ");
        double balance = sc.nextDouble();
        if(accountOption == 1){
            banco.inserir(new ContaCorrente(accountNumber, balance));
        } else {
            banco.inserir(new ContaPoupanca(accountNumber, balance));
        }

        System.out.println("Your account has been successfully created!");
    }

    private static ContaBancaria getAccount(){
        System.out.print("Insert the account's number: ");
        return banco.procurarConta(sc.nextInt());
    }

    private static void selectAccount(){
        ContaBancaria logAccount = getAccount();
        if(logAccount != null){
            loggedMenu(logAccount);
        } else {
            throw new ContaInvalidaException();
        }
    }

    private static void loggedMenu(ContaBancaria logAccount){
        System.out.print("""
                --- Your Account Menu ---
                1 - Deposit;
                2 - Withdraw;
                3 - Transference;
                4 - Generate Report;
                5 - Return to the main menu;""");
        try {
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.print("Deposit amount U$: ");
                    logAccount.depositar(sc.nextDouble());
                }
                case 2 -> withdraw(logAccount);

                case 3 -> {
                    System.out.print("Benefited account number");
                    ContaBancaria benefitedAcc = getAccount();
                    System.out.print("Transference's amount U$: ");
                    double amount = sc.nextDouble();

                    logAccount.setSaldo(logAccount.getSaldo() - amount);
                    benefitedAcc.setSaldo(benefitedAcc.getSaldo() + amount);
                    System.out.print("Transference has been made!");
                }
                case 4 -> System.out.println(logAccount.toString());
                case 5 -> menuPrincipal();
                default -> throw new OpcaoInvalidaException();
            }
        }  catch(RuntimeException idiota){
            System.out.println(idiota.getClass().getSimpleName() + ": " + idiota.getMessage() + "\n");
        }
    }

    private static void withdraw(ContaBancaria logAccount){
        System.out.print("Withdraw amount U$: ");
        double withdraw = sc.nextDouble();
        if(logAccount instanceof  ContaCorrente){
            if(withdraw < logAccount.getSaldo() + (((ContaCorrente) logAccount).getLimite())){
                if(logAccount.getSaldo() >= 0 - (((ContaCorrente) logAccount).getLimite())){
                    logAccount.sacar(withdraw);
                } else {
                    throw new SaldoInsuficienteException();
                }
            } else {
                System.out.println("You cannot withdraw more than U$" + (logAccount.getSaldo() + (((ContaCorrente) logAccount).getLimite())));
                throw new SemLimiteException();
            }
        } else {
            if(withdraw > logAccount.getSaldo() && withdraw > 0){
                logAccount.sacar(withdraw);
            } else {
                System.out.println("You cannot withdraw more than U$" + (logAccount.getSaldo()) + "\n" + "And the withdraw has to be more than 0");
            }
        }
    }

    private static void removeAccount(){
        ContaBancaria logAccount = getAccount();
        if(logAccount != null){
            banco.remover(logAccount);
            System.out.println("Account have been removed!");
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void generateReport(){
        System.out.println("--- Account's Report ---");
        System.out.println(banco.mostrarContas());
    }


}
