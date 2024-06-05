package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UsuarioDAO {

    private Conexao conexao;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean check = false;

    public UsuarioDAO() {
        this.conexao = Conexao.getInstance();
    }

    public void inserir(Usuario usuario) {

        try {
            this.query = "INSERT INTO usuario (email, senha) VALUES (?, ?)";
            this.ps = this.conexao.getConnection().prepareStatement(query);
            this.ps.setString(1, usuario.getEmail());
            this.ps.setString(2, usuario.getSenha());
            this.ps.executeUpdate();
            this.ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Usuario validar(String email, String senha) {

        try {
            this.query = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
            this.ps = this.conexao.getConnection().prepareStatement(query);
            this.ps.setString(1, email);
            this.ps.setString(2, senha);
            rs = this.ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }
            rs.close();
            this.ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Usuario();
    }
}