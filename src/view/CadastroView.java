package view;

import controller.UsuarioController;

import java.util.Scanner;

public class CadastroView {

    private UsuarioController usuarioController;
    private Scanner scanner;

    public CadastroView() {
        this.usuarioController = new UsuarioController();
        this.scanner = new Scanner(System.in);
    }

    public void registrar() {
        System.out.println("Cadastrar novo usuário:");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        usuarioController.registrarUsuario(email, senha);
    }
}
