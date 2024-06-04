package view;

import java.util.Scanner;

public class FinanceiroView {
    private Scanner scanner;

    public FinanceiroView(Scanner scanner) {
        this.scanner = scanner;
    }

    public double obterRenda() {
        System.out.println("Digite sua renda:");
        return scanner.nextDouble();
    }

    public String obterDescricaoDespesa() {
        System.out.println("Digite a descrição da despesa:");
        return scanner.next();
    }

    public double obterValorDespesa() {
        System.out.println("Digite o valor da despesa:");
        return scanner.nextDouble();
    }

    public void exibirSaldo(double saldo) {
        System.out.println("Seu saldo é: " + saldo);
    }
}
