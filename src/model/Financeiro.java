package model;

import java.util.HashMap;
import java.util.Map;

public class Financeiro {
    private double renda;
    private Map<String, Double> despesas;

    public Financeiro() {
        this.despesas = new HashMap<>();
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public Map<String, Double> getDespesas() {
        return despesas;
    }

    public void adicionarDespesa(String descricao, double valor) {
        this.despesas.put(descricao, valor);
    }

    public double getTotalDespesas() {
        return this.despesas.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calcularSaldo() {
        return this.renda - getTotalDespesas();
    }
}
