package controller;

import model.Usuario;
import service.CognitoService;
import dao.UsuarioDAO;

public class UsuarioController {
    private CognitoService cognitoService;
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.cognitoService = new CognitoService();
        this.usuarioDAO = new UsuarioDAO();
    }

    public void registrarUsuario(String email, String senha) {
        try {
            cognitoService.registrarUsuario(email, senha);
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuarioDAO.inserir(usuario);
        } catch (Exception e) {
            System.err.println("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    public Usuario autenticarUsuario(String email, String senha) {
        try {
            cognitoService.autenticarUsuario(email, senha);
            return usuarioDAO.validar(email);
        } catch (Exception e) {
            System.err.println("Erro ao autenticar usuário: " + e.getMessage());
        }
        return null;
    }
}
