package view;

import controller.RendaController;
import model.Usuario;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RendaView {
    private RendaController rendaController;
    private Scanner scanner;
    private Usuario usuarioAtual;

    public RendaView(Usuario usuarioAtual) {
        this.rendaController = new RendaController();
        this.scanner = new Scanner(System.in);
        this.usuarioAtual = usuarioAtual;
    }

    public void adicionarRenda() {
        System.out.println("Adicionar Renda:");
        double valor = 0;
        boolean valorValido = false;

        while (!valorValido) {
            System.out.print("Valor: ");
            try {
                valor = scanner.nextDouble();
                scanner.nextLine();
                valorValido = true;
            } catch (InputMismatchException e) {
                System.err.println("Por favor, insira um valor numérico válido.");
                scanner.nextLine();
            }
        }

        rendaController.adicionarRenda(usuarioAtual.getId(), valor);
        System.out.println("Renda adicionada com sucesso.");
    }
}
