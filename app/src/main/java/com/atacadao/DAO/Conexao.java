package com.atacadao.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/estoque";
    private static final String USUARIO = "admin";
    private static final String SENHA = "admin1234";
    
    public static Connection obterConexao(){
        try{
            //carregando o driver explicitamente
            Class.forName("org.postgresql.Driver");
            //retorna a Conexao
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        catch(ClassNotFoundException e){
            System.err.println("Driver JDBC do postgresql não foi encontrado");
            e.printStackTrace();
            return null;
        }
        catch (SQLException e){
            System.err.println("Erro ao conectar com o banco de dados");
            return null;
        }
    } 
}
