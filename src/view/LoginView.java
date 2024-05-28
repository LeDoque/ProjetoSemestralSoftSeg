package view;

import model.Usuario;
import controller.LoginController;

import java.util.Scanner;

public class LoginView {
    private Scanner scanner;
    private LoginController lc;
    public LoginView(LoginController lc, Scanner scanner){
        this.scanner = new Scanner(System.in);
        this.lc = lc;
    }
    public void login(){
        System.out.println("---- LOGIN ----");

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = lc.validate(email, senha);

        if (usuario != null) {
            System.out.println("Login realizado");
        } else {
            System.out.println("Erro no login, informações incorretas.");
        }
    }
}
