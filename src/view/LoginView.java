package view;

import controller.UsuarioController;
import controller.GastoController;
import model.Usuario;
import java.util.Scanner;

public class LoginView {
    private UsuarioController usuarioController;
    private GastoController gastoController;
    private Scanner scanner;
    private Usuario usuarioAtual;

    public LoginView() {
        this.usuarioController = new UsuarioController();
        this.gastoController = new GastoController();
        this.scanner = new Scanner(System.in);
    }

    public void logar() {
        System.out.println("Login:");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        usuarioAtual = usuarioController.autenticarUsuario(email, senha);
        if (usuarioAtual != null) {
            System.out.println("Login bem-sucedido");
            mostrarMenuUsuarioLogado();
        } else {
            System.err.println("Falha no login");
        }
    }

    private void mostrarMenuUsuarioLogado() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Renda");
            System.out.println("2. Adicionar Gastos");
            System.out.println("3. Resumo dos Gastos");
            System.out.println("4. Logout");
            System.out.println("0. Sair da aplicação");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    new RendaView(usuarioAtual).adicionarRenda();
                    break;
                case 2:
                    new GastoView(usuarioAtual).adicionarGasto();
                    break;
                case 3:
                    mostrarResumoGastos();
                    break;
                case 4:
                    usuarioAtual = null;
                    return;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void mostrarResumoGastos() {
        double totalGastos = gastoController.calcularResumoGastos(usuarioAtual.getId());
        double totalRenda = gastoController.calcularTotalRenda(usuarioAtual.getId());
        double saldo = totalRenda - totalGastos;

        System.out.println("Resumo dos Gastos:");
        System.out.println("Total de Gastos: " + totalGastos);
        System.out.println("Saldo: " + saldo);
    }
}
