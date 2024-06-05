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
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                conectar();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
        }
    }
}
