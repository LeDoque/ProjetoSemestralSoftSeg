package controller;

import model.Financeiro;
import view.FinanceiroView;

public class FinanceiroController {
    private Financeiro financeiro;
    private FinanceiroView financeiroView;

    public FinanceiroController(FinanceiroView financeiroView) {
        this.financeiro = new Financeiro();
        this.financeiroView = financeiroView;
    }

    public void gerenciarFinancas() {
        financeiro.setRenda(financeiroView.obterRenda());
        String descricao = financeiroView.obterDescricaoDespesa();
        double valor = financeiroView.obterValorDespesa();
        financeiro.adicionarDespesa(descricao, valor);
        financeiroView.exibirSaldo(financeiro.calcularSaldo());
    }
}
