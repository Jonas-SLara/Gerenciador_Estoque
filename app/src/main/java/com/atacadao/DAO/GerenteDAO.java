package com.atacadao.DAO;

import java.sql.*;
import java.util.ArrayList;
import com.atacadao.model.Gerente;

public class GerenteDAO {
    //lista todos os gerentes cadastrados
    public ArrayList<Gerente> listarGerentes(){
        String sql = "SELECT * FROM GERENTE";
        ArrayList<Gerente> gerentes = new ArrayList<Gerente>();

        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while(rs.next()){
                Gerente g = new Gerente();
                g.setIdSetor(rs.getInt("id_setor"));
                g.setIdGerente(rs.getInt("id_gerente"));
                g.setIdUsuario(rs.getInt("id_usuario"));
                g.setSalario(rs.getDouble("salario"));
                gerentes.add(g);
            }
            return gerentes;

        } catch (SQLException e) {
            System.err.println("erro ao listar gerentes " + e.getMessage());
            return null;
        }
    }
    
    //remover um gerente pelo id
    public boolean removerGerente(int id){
        String sql="DELETE FROM GERENTE WHERE id_gerente = ?";
        try(Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
            if(stmt.getUpdateCount()<=0){
                System.out.println("usuario nao encontrado para excluir");
                return false;
            }
            System.out.println("gerente id: " + id + " excluido com sucesso");
            return true;
        } catch(SQLException e){
            System.err.println("erro ao deletar gerente " + e.getMessage());
            return false;
        }
    } 
    
    //alterar gerente pelo id 
    public boolean alterarGerente(Gerente g){
        String sql = "UPDATE GERENTE SET id_setor = ?, id_usuario = ?, salario = ? WHERE id_gerente = ?";
        try (Connection con = Conexao.obterConexao();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, g.getIdSetor());
            stmt.setInt(2, g.getIdUsuario());
            stmt.setDouble(3, g.getSalario());
            stmt.setInt(4, g.getIdGerente());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao alterar gerente " + e.getMessage());
            return false;
        }
        System.out.println("Gerente id: " + g.getIdGerente() + " foi alterado");
        return true;
    }

    //inserir gerente 
    public boolean inserirgerente(Gerente g){
        String sql = "INSERT INTO GERENTE (id_usuario, id_setor, salario) VALUES(?, ?, ?)";
        try (Connection con = Conexao.obterConexao();
                PreparedStatement stmt = con.prepareStatement(sql)) {
           stmt.setInt(1, g.getIdUsuario());
           stmt.setInt(2, g.getIdSetor());
           stmt.setDouble(3, g.getSalario());
           stmt.execute();
        } catch (SQLException e) {
            System.err.println("erro ao inserir gerente " + e.getMessage());
            return false;
        }
        System.out.println("novo gerente cadastrado");
        return true;
    }
}
