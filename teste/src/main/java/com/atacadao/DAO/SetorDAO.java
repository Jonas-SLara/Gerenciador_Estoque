package com.atacadao.DAO;

import com.atacadao.model.Setor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SetorDAO {
    //listar todas os setores cadastrados
    public ArrayList<Setor> listarSetores(){
        String sql = "SELECT * FROM SETOR";
        ArrayList<Setor> setores = new ArrayList<Setor>();
        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while(rs.next()){
                Setor s = new Setor();
                s.setId(rs.getInt("id"));
                s.setNome(rs.getString("nome"));
                setores.add(s);
            }
            return setores;

        } catch (SQLException e) {
            System.err.println("erro ao listar setores " + e.getMessage());
            return null;
        }
    }

    public boolean removerSetor(int id){
        String sql = "DELETE FROM SETOR WHERE id = ?";
        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            if(stmt.getUpdateCount()<=0){
                System.out.println("não foi possivel excluir, setor nao encontrado");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("erro ao excluir " + e.getMessage());
            return false;
        }
        System.out.println("Setor id: " + id + " foi excluido com sucesso");
        return true;
    }

    public boolean alterarSetor(Setor s){
        String sql = "UPDATE SETOR SET nome = ? WHERE id = ?";
        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, s.getNome());
            stmt.setInt(2, s.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("erro ao alterar tabela " + e.getMessage());
            return false;
        }
        System.out.println("Setor " + s.getId() + " foi alterado");
        return true;
    }

    public boolean inserirSetor(Setor s){
        String sql = "INSERT INTO SETOR (nome) VALUES (?)";
        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, s.getNome());
            stmt.execute();
            System.out.println("novo setor inserido");
            return true;
        } catch(SQLException e){
            System.out.println("Erro ao inserir setor "+e.getMessage());
            return false;
        }
    }
}