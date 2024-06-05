package dao;

import model.Gasto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GastoDAO {

    private Conexao conexao;

    public GastoDAO() {
        this.conexao = Conexao.getInstance();
    }

    public void inserir(Gasto gasto) {
        String query = "INSERT INTO Gastos (usuario_id, valor, descricao, data_gasto) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, gasto.getUsuarioId());
            ps.setDouble(2, gasto.getValor());
            ps.setString(3, gasto.getDescricao());
            ps.setDate(4, new java.sql.Date(gasto.getDataGasto().getTime()));
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();  // Melhorar para usar logging
        }
    }

    public List<Gasto> listarPorUsuario(int usuarioId) {
        List<Gasto> gastos = new ArrayList<>();
        String query = "SELECT * FROM Gastos WHERE usuario_id = ?";
        try (Connection conn = this.conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, usuarioId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Gasto gasto = new Gasto();
                    gasto.setId(rs.getInt("id"));
                    gasto.setUsuarioId(rs.getInt("usuario_id"));
                    gasto.setValor(rs.getDouble("valor"));
                    gasto.setDescricao(rs.getString("descricao"));
                    gasto.setDataGasto(rs.getDate("data_gasto"));
                    gastos.add(gasto);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();  // Melhorar para usar logging
        }
        return gastos;
    }
}
