package controller;

import dao.FinanceiroDAO;
import model.Financeiro;
import view.FinanceiroView;

public class FinanceiroController {
    private Financeiro financeiro;
    FinanceiroView financeiroView;
    private FinanceiroDAO fDAO;

    public FinanceiroController(FinanceiroView financeiroView) {
        this.financeiro = new Financeiro();
        this.financeiroView = financeiroView;
        this.fDAO = new FinanceiroDAO();
        this.fDAO.relatorio(this.financeiro);
    }

    public void gerenciarFinancas() {
        financeiro.setRenda(financeiroView.obterRenda());
        String descricao = financeiroView.obterDescricaoDespesa();
        double valor = financeiroView.obterValorDespesa();
        financeiro.adicionarDespesa(descricao, valor);
        financeiroView.exibirSaldo(financeiro.calcularSaldo());
    }
}