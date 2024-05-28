package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import model.Usuario;
import view.CadastroView;

public class CadastroController {

    CadastroView cv;
    private Usuario usuario;
    private UsuarioDAO uDAO;
    public CadastroController(){
        this.cv = new CadastroView();
        this.usuario = this.cv.cadastro();
        this.uDAO = new UsuarioDAO();
        this.uDAO.inserir(this.usuario);
    }


}
