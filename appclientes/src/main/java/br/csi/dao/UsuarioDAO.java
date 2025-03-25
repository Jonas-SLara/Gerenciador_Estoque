package br.csi.dao;

import br.csi.model.ConectarBancoDados;
import br.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {
    public ArrayList<Usuario> getUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Connection con = ConectarBancoDados.conectarBancoPostgres();

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from usuario");

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                boolean ativo = rs.getBoolean("ativo");

                Usuario u = new Usuario(email, senha, ativo);
                u.setId(id);
                usuarios.add(u);
            }

        }catch(ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver!");
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println("Erro ao conectar ao Banco de Dados");
            e.printStackTrace();
        }
        return usuarios;
    }

    public String inserirUsuario(Usuario usuario) {
        try {
            Connection con = ConectarBancoDados.conectarBancoPostgres();

            PreparedStatement stmt = con.prepareStatement("insert into usuario (email, senha, ativo) values (?,?,?)");
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setBoolean(3, usuario.isAtivo());

            stmt.execute();
        }catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir usuario!");
        }
        return "Inserido com sucesso";
    }

    
}
