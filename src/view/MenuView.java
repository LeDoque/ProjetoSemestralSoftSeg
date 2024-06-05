package view;

import controller.UsuarioController;
import controller.RendaController;
import controller.GastoController;
import model.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuView {

    private UsuarioController usuarioController;
    private RendaController rendaController;
    private GastoController gastoController;
    private Scanner scanner;
    private Usuario usuarioAtual;

    public MenuView() {
        this.usuarioController = new UsuarioController();
        this.rendaController = new RendaController();
        this.gastoController = new GastoController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar");
            System.out.println("2. Logar");
            System.out.println("3. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    registrar();
                    break;
                case 2:
                    logar();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void registrar() {
        System.out.println("Cadastrar novo usuário:");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        usuarioController.registrarUsuario(email, senha);
    }

    private void logar() {
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
            System.out.println("Falha no login");
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
                    adicionarRenda();
                    break;
                case 2:
                    adicionarGasto();
                    break;
                case 3:
                    mostrarResumoGastos();
                    break;
                case 4:
                    usuarioAtual = null;
                    return;
                case 0:System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void adicionarRenda() {
        System.out.println("Adicionar Renda:");
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        rendaController.adicionarRenda(usuarioAtual.getId(), valor);
        System.out.println("Renda adicionada com sucesso.");
    }

    private void adicionarGasto() {
        System.out.println("Adicionar Gasto:");
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();

        try {
            Date dataGasto = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
            gastoController.adicionarGasto(usuarioAtual.getId(), valor, descricao, dataGasto);
            System.out.println("Gasto adicionado com sucesso.");
        } catch (Exception e) {
            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
        }
    }

    private void mostrarResumoGastos() {
        gastoController.calcularResumoGastos(usuarioAtual.getId());
    }
}
