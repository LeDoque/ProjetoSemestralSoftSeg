package dao;

import config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String host;
    private String usuario;
    private String senha;
    private Connection connection;
    private static Conexao conexao;

    private Conexao() {
        Config config = new Config();
        this.host = config.getProperty("DB_HOST");
        this.usuario = config.getProperty("DB_USER");
        this.senha = config.getProperty("DB_PASSWORD");

        conectar();
    }

    public static Conexao getInstance() {
        if (conexao == null) {
            conexao = new Conexao();
        }
        return conexao;
    }

    private void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(host, usuario, senha);
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver JDBC não encontrado: " + ex.getMessage());
            ex.printStackTrace(System.err);
        } catch (SQLException ex) {
            System.err.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                conectar();
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter conexão: " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
        return this.connection;
    }

    public void fecharConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao fechar a conexão: " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }
}