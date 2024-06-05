package view;

import controller.RendaController;
import model.Usuario;

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
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        rendaController.adicionarRenda(usuarioAtual.getId(), valor);
        System.out.println("Renda adicionada com sucesso.");
    }
}
