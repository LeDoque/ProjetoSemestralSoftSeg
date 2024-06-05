package dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;

public class UsuarioDAO {
    private Conexao conexao;

    public UsuarioDAO() {
        this.conexao = Conexao.getInstance();
    }

    public void inserir(Usuario usuario) {
        String query = "INSERT INTO Usuarios (email) VALUES (?)";
        try (Connection conn = this.conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, usuario.getEmail());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao inserir usuário: " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }

    public Usuario validar(String email) {
        email = Normalizer.normalize(email, Normalizer.Form.NFKC);

        String query = "SELECT * FROM Usuarios WHERE email = ?";
        try (Connection conn = this.conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    return usuario;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao validar usuário: " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
        return null;
    }
}
