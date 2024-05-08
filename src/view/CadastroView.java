package view;

import model.Usuario;

import java.util.Scanner;

public class CadastroView {
    private Scanner scanner;

    public CadastroView(){
        this.scanner = new Scanner(System.in);
    }

    public Usuario Cadastro(){
        System.out.println("---- CADASTRO ----");
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        return new Usuario(email, senha);

    }

    public void escolhaInvalida(){
        System.out.println("Escolha Inválida!");
    }
}
