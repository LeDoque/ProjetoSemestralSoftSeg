package dao;

import model.Renda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RendaDAO {

    private Conexao conexao;

    public RendaDAO() {
        this.conexao = Conexao.getInstance();
    }

    public void inserir(Renda renda) {
        String query = "INSERT INTO Rendas (usuario_id, valor) VALUES (?, ?)";
        try (Connection conn = this.conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, renda.getUsuarioId());
            ps.setDouble(2, renda.getValor());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Renda> listarPorUsuario(int usuarioId) {
        List<Renda> rendas = new ArrayList<>();
        String query = "SELECT * FROM Rendas WHERE usuario_id = ?";
        try (Connection conn = this.conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, usuarioId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Renda renda = new Renda();
                    renda.setId(rs.getInt("id"));
                    renda.setUsuarioId(rs.getInt("usuario_id"));
                    renda.setValor(rs.getDouble("valor"));
                    rendas.add(renda);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rendas;
    }
}
