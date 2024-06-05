package controller;

import model.Renda;
import dao.RendaDAO;

import java.util.List;

public class RendaController {

    private RendaDAO rendaDAO;

    public RendaController() {
        this.rendaDAO = new RendaDAO();
    }

    public void adicionarRenda(int usuarioId, double valor) {
        Renda renda = new Renda();
        renda.setUsuarioId(usuarioId);
        renda.setValor(valor);
        rendaDAO.inserir(renda);
    }

    public List<Renda> listarRendas(int usuarioId) {
        return rendaDAO.listarPorUsuario(usuarioId);
    }
}
