package com.atacadao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.atacadao.model.Funcionario;
import com.atacadao.model.Usuario;

public class FuncionarioDAO {

    public ArrayList<Funcionario> listar_funcionarios(){

        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        String sql = "SELECT * FROM funcionario f INNER JOIN usuario u "+
        "ON f.cpf_usuario = u.cpf";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    //seta funcionario
                    Funcionario f = new Funcionario();
                    f.setCpfUsuario(rs.getString("cpf_usuario"));
                    f.setId(rs.getInt("id"));
                    f.setCargo(rs.getString("cargo"));
                    f.setIdGerente(rs.getInt("id_gerente"));
                    //seta usuario
                    Usuario u = new Usuario();
                    u.setCelular(rs.getString("celular"));
                    u.setEmail(rs.getString("email"));
                    u.setNome(rs.getString("nome"));
                    u.setSalario(rs.getDouble("salario"));
                    u.setCpf(rs.getString("cpf"));
                    f.setUsuario(u);
                    funcionarios.add(f);
                }
        } catch (SQLException e) {
            System.out.println("erro ao listar usuarios: " + e.getMessage());
        } catch (IllegalStateException e){
            System.out.println("Banco de dados não configurado: " + e.getMessage());
        }
        return funcionarios;
    }

    public Funcionario buscar_funcionario(String cpf_usuario){
        Funcionario f = null;
        String sql ="SELECT * FROM funcionario f INNER JOIN usuario u "+
        "ON u.cpf_usuario = f.cpf AND cpf_usuario = ?";

        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, cpf_usuario);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                f = new Funcionario();
                f.setCpfUsuario(rs.getString("cpf_usuario"));
                f.setIdGerente(rs.getInt("id_gerente"));
                f.setCargo(rs.getString("cargo"));
                f.setId(rs.getInt("id"));
                Usuario u = new Usuario();
                u.setCelular(rs.getString("celular"));
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setSalario(rs.getDouble("salario"));
                f.setUsuario(u);
                System.out.println("funcionario encontrado");
            }else{
                System.out.println("funcionario não encontrado");
            }
        } catch (SQLException e) {
            System.out.println("erro ao buscar funcionario " + e.getMessage());
        } catch (IllegalStateException e){
            System.out.println("Banco de dados não configurado: " + e.getMessage());
        }
        return f;
    }

    public boolean inserir_funcionario(Funcionario f){
        String sql = "INSERT INTO funcionario (cpf_usuario, id_gerente, cargo) VALUES (?, ?, ?)";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, f.getCpfUsuario());
            stmt.setInt(2, f.getIdGerente());
            stmt.setString(3, f.getCargo());
            stmt.execute();
            System.out.println("funcionario cadastrado");
        } catch (SQLException e) {
            System.out.println("erro inserir funcionario " + e.getMessage());
            return false;
        } catch (IllegalStateException e){
            System.out.println("Banco de dados não configurado: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean remover_funcionario(int id){
        String sql = "DELETE FROM funcionario WHERE id = ?";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            if(stmt.getUpdateCount() <= 0){
                System.out.println("nenhum funcionario excluido");
                return false;
            }
            System.out.println("funcionario excluido");
        } catch (SQLException e) {
           System.out.println("erro ao excluir funcionario: " + e.getMessage());
           return false;
        } catch (IllegalStateException e){
            System.out.println("Banco de dados não configurado: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean alterar_funcionario(Funcionario f){
        String sql = "UPDATE funcionario SET cargo = ?, id_gerente = ? WHERE id = ?";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, f.getCargo());
            stmt.setInt(2, f.getIdGerente());
            stmt.setInt(3, f.getId());
            stmt.execute();
            System.out.println("dados do funcionario alterados");
        } catch (SQLException e) {
            System.out.println("erro ao alterar funcionario " + e.getMessage());
            return false;
        } catch (IllegalStateException e){
            System.out.println("Banco de dados não configurado: " + e.getMessage());
            return false;
        }
        return true;
    }
        
}
