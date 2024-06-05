package dao;

import model.Financeiro;

import java.sql.*;

import java.sql.SQLException;

public class FinanceiroDAO {
    private Conexao conexao;
    private String query;
    private PreparedStatement ps;

    public FinanceiroDAO() {
        this.conexao = Conexao.getInstance();
    }
    public void relatorio(Financeiro financeiro) {

        try {
            this.query = "INSERT INTO usuario (renda, gastos) VALUES (?, ?)";
            this.ps = this.conexao.getConnection().prepareStatement(query);
            this.ps.setDouble(1, financeiro.getRenda());
            this.ps.setDouble(2, financeiro.getTotalDespesas());
            this.ps.executeUpdate();
            this.ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
