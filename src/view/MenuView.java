package view;

import java.util.Scanner;

public class MenuView {
    private Scanner scanner;

    public MenuView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("---- BEM-VINDO ----");
            System.out.println("Por favor, escolha uma das opções abaixo");
            System.out.println("1. Cadastrar");
            System.out.println("2. Logar");
            System.out.println("0. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    new CadastroView().registrar();
                    break;
                case 2:
                    new LoginView().logar();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
