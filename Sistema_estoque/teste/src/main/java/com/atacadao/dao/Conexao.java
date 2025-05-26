package com.atacadao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/atacadao";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1234";

    public static Connection obterConexao() throws SQLException{
        try{
            //carregando o driver explicitamente(opcional desde o JDBC 4.0)
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            throw new IllegalStateException("Driver JDBC do postgresql não foi encontrado " + e);
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
