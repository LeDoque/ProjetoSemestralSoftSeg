package controller;

import dao.UsuarioDAO;
import model.Usuario;
import view.LoginView;

import java.util.Scanner;

public class LoginController {
    private LoginView lv;
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    public LoginController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void realizarLogin(Scanner scanner) {
        if (lv == null) {
            lv = new LoginView(this, scanner);
        }
        lv.login();
    }

    public Usuario validate(String email, String senha) {
        return usuarioDAO.validar(email, senha);
    }
}
