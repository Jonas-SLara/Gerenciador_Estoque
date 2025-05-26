package com.atacadao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.atacadao.model.Gerente;

public class GerenteDAO {
    public ArrayList<Gerente> listar_gerentes(){
        ArrayList<Gerente> gerentes = new ArrayList<Gerente>();
        String sql = "SELECT * FROM gerente";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Gerente g = new Gerente();
                g.setCpfUsuario(rs.getString("cpf_usuario"));
                g.setBonificacao(rs.getDouble("bonificacao"));
                g.setId(rs.getInt("id"));
                gerentes.add(g);
            }
        } catch (SQLException e) {
            System.out.println("erro ao listar gerentes: " + e.getMessage());
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
        }
        return gerentes;
    }

    public Gerente buscar_por_id(int id){
        Gerente g = null;
        String sql = "SELECT * FROM gerente WHERE id = ?";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                g = new Gerente();
                g.setId(id);
                g.setBonificacao(rs.getDouble("bonificacao"));
                g.setCpfUsuario(rs.getString("cpf_usuario"));
            }else{
                System.out.println("gerente não encontrado");
            }
        } catch (SQLException e) {
            System.out.println("erro ao buscar gerente: " + e.getMessage());
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
        }
        System.out.println("gerente buscado com sucesso");
        return g;
    }

    public boolean inserir_gerente(Gerente g){
        String sql = "INSERT INTO gerente (cpf_usuario, bonificacao) VALUES(?, ?)";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, g.getCpfUsuario());
            stmt.setDouble(2, g.getBonificacao());
            stmt.execute();
            System.out.println("gerente cadastrado com sucesso");
        } catch (SQLException e) {
            System.out.println("erro ao cadastrar gerente");
            return false;
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean remover_gerente(int id){
        String sql = "DELETE FROM gerente WHERE id = ?";
        try(Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
            if(stmt.getUpdateCount() <= 0){
                System.out.println("nenhum gerente removido");
                return false;
            }
            System.out.println("gerente removido com sucesso");
        } catch(SQLException e){
            System.out.println("erro ao remover gerente: " + e.getMessage());
            return false;
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean alterar_gerente(Gerente g){
        String sql = "UPDATE gerente SET bonificacao = ? WHERE id = ?";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, g.getBonificacao());
            stmt.setInt(2, g.getId());
            stmt.execute();
            System.out.println("dados do gerente alterado");
        } catch (SQLException e) {
            System.out.println("erro ao alterar dados do gerente "+ e.getMessage());
            return false;
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
            return false;
        }
        return true;
    }
}
