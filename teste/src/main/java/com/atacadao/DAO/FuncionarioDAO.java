package com.atacadao.DAO;

import com.atacadao.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAO {
    //metodo para listar funcionarios cadastrados
    public ArrayList<Funcionario> listarFuncionarios(){
        String sql = "SELECT * FROM FUNCIONARIO";
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("id_funcionario"));
                f.setIdGerente(rs.getInt("id_gerente"));
                f.setNome(rs.getString("nome"));
                f.setSalario(rs.getDouble("salario"));
                funcionarios.add(f);
            }
        } catch (SQLException e) {
            System.err.println("erro ao listar os funcionários " + e.getMessage());
            return null;
        }
        return funcionarios;
    }

    public boolean removerFuncionario(int id_funcionario){
        String sql = "DELETE FROM FUNCIONARIO WHERE id_funcionario = ?";
        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id_funcionario);
            stmt.execute();
            if(stmt.getUpdateCount()<=0){
                System.out.println("Nenhum funcionario com id " + id_funcionario + " encontrado");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao exlcuir funcionario " + e.getMessage());
            return false;
        }
        System.out.println("funcionario id: " + id_funcionario + " excluido com sucesso");
        return true;
    }

    //método para alterar um funcionario
    public boolean alterarFuncionario(Funcionario f){
        String sql = "UPDATE FUNCIONARIO SET id_gerente = ?, nome = ?, salario = ? WHERE id_funcionario = ?";
        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, f.getIdGerente());
            stmt.setString(2, f.getNome());
            stmt.setDouble(3, f.getSalario());
            stmt.setInt(4, f.getIdFuncionario());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("erro ao alterar funcionario " + e.getMessage());
            return false;
        }
        System.out.println("funcionario id: " + f.getIdFuncionario() + " alterado");
        return true;
    }

    //método para inserir um novo funcionario
    public boolean inserirFuncionario(Funcionario f){
        String sql = "INSERT INTO FUNCIONARIO (id_gerente, nome, salario) VALUES (?, ?, ?)";
        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, f.getIdGerente());
            stmt.setString(2, f.getNome());
            stmt.setDouble(3, f.getSalario());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("erro ao inserir funcionario " + e.getMessage());
            return false;
        }
        System.out.println("novo funcionario cadastrado " + f.getNome());
        return true;
    }
}
