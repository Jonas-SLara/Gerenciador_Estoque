package com.atacadao.DAO;

import com.atacadao.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO{

    //listar os usuarios da tabela USUARIO do banco estoque
    public ArrayList<Usuario> listarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM USUARIO";

        //abre e fecha a conexao com o banco com try resources
        try(Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Usuario u = new Usuario();
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setId(rs.getInt("id"));
                usuarios.add(u);
            }
        } catch(SQLException e) {
            System.err.println("Erro ao listar usuarios" + e.getMessage());
        }

        return usuarios;
    }

    //remover um novo usuario na tabela de USUARIO do banco estoque
    public boolean removerUsuario(int id){
        String sql = "DELETE FROM USUARIO WHERE id = ?";

        try( Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
            if(stmt.getUpdateCount()<=0){
                System.out.println("Nenhum usuario excluido");
                return false;
            }

        } catch(SQLException e){
            System.err.println("Erro ao remover usuario" + e.getMessage());
            return false;
        }
        System.out.println("Usuario id " + id + " excluido com sucesso");
        return true;
    }

    //alterar um usuario da tabela de USUARIO
    public boolean alterarUsuario(Usuario u){
        String sql = "UPDATE USUARIO SET email = ?, senha = ?, nome = ? WHERE id = ? ";
        try(Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getNome());
            stmt.setInt(4, u.getId());
            stmt.execute();
        } catch (SQLException e){
            System.err.println("erro ao alterar usuario " + e.getMessage());
            return false;
        }
        System.out.println("usuario id: " + u.getId() + " alterado");
        return true;
    }

    //inserir um usuario novo
    public boolean inserirUsuario(Usuario u){
        String sql = "INSERT INTO USUARIO (email, senha, nome) VALUES(?, ?, ?)";
        try(Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getNome());
            stmt.execute();
            System.out.println("novo usuario inserido com sucesso");
        } catch (SQLException e) {
            System.err.println("erro ao inserir usuario " + e.getMessage());
            return false;
        }
        return true;
    }
}
