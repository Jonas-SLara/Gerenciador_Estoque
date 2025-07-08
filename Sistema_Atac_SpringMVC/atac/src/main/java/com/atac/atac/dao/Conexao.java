package com.atac.atac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static String URL = "jdbc:postgresql://localhost:5432/db-atac";
    private static String USUARIO= "postgres";
    private static String SENHA = "1234";

    public static Connection obterConexao() throws SQLException{
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
