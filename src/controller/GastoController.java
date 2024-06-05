package controller;

import model.Gasto;
import dao.GastoDAO;
import dao.RendaDAO;
import model.Renda;
import java.util.Date;
import java.util.List;

public class GastoController {
    private GastoDAO gastoDAO;
    private RendaDAO rendaDAO;

    public GastoController() {
        this.gastoDAO = new GastoDAO();
        this.rendaDAO = new RendaDAO();
    }

    public void adicionarGasto(int usuarioId, double valor, String descricao, Date dataGasto) {
        Gasto gasto = new Gasto();
        gasto.setUsuarioId(usuarioId);
        gasto.setValor(valor);
        gasto.setDescricao(descricao);
        gasto.setDataGasto(dataGasto);
        gastoDAO.inserir(gasto);
    }

    public List<Gasto> listarGastos(int usuarioId) {
        return gastoDAO.listarPorUsuario(usuarioId);
    }

    public double calcularResumoGastos(int usuarioId) {
        List<Gasto> gastos = listarGastos(usuarioId);
        double totalGastos = 0;
        System.out.println("Lista de Gastos:");
        for (Gasto gasto : gastos) {
            totalGastos += gasto.getValor();
            System.out.println("Descrição: " + gasto.getDescricao() + ", Valor: " + gasto.getValor() + ", Data: " + gasto.getDataGasto());
        }
        System.out.println("Total de Gastos: " + totalGastos);
        return totalGastos;
    }

    public double calcularTotalRenda(int usuarioId) {
        List<Renda> rendas = rendaDAO.listarPorUsuario(usuarioId);
        double totalRenda = 0;
        for (Renda renda : rendas) {
            totalRenda += renda.getValor();
        }
        return totalRenda;
    }
}
