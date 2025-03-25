package br.csi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBancoDados {
    public static Connection conectarBancoPostgres() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver carregado com sucesso!");

        String url = "jdbc:postgresql://localhost:5432/poow1";
        String user = "postgres";
        String senha = "1234";

        Connection con = DriverManager.getConnection(url, user, senha);
        return con;
    }
}
