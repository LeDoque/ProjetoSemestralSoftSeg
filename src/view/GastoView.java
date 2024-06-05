
package view;

import controller.GastoController;
import model.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GastoView {

    private GastoController gastoController;
    private Scanner scanner;
    private Usuario usuarioAtual;

    public GastoView(Usuario usuarioAtual) {
        this.gastoController = new GastoController();
        this.scanner = new Scanner(System.in);
        this.usuarioAtual = usuarioAtual;
    }

    public void adicionarGasto() {
        System.out.println("Adicionar Gasto:");
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
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
}
