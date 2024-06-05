package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String host;
    private String usuario;
    private String senha;
    private Connection connection;
    private static Conexao conexao;
    private Conexao(){
        this.host = "jdbc:mysql://localhost:3306/projetoSSS?useSSL=false";
        this.usuario = "root";
        this.senha = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(host, usuario, senha);
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static Conexao getInstance(){
        if(conexao == null) {
            conexao = new Conexao();
        }
        return conexao;
    }

    public Connection getConnection(){
        return this.connection;
    }

}
