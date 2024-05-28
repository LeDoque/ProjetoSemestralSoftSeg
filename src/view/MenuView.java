package view;

import java.util.Scanner;

public class MenuView {

    private Scanner scanner;

    public MenuView(){
        this.scanner = new Scanner(System.in);
    }

    public int menu(){
        System.out.println("---- BEM-VINDO ----");
        System.out.println("Por favor, escolha uma das opções abaixo");
        System.out.println("(1) Realizar Login");
        System.out.println("(2) Cadastrar");
        System.out.println("(9) Sair");

        return scanner.nextInt();
    }

    public void escolhaInvalida(){

        System.out.println("Escolha Inválida!");
    }
}
