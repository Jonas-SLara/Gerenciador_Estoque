package com.atacadao.DAO;

import com.atacadao.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
    //metodo para listar os produtos
    public ArrayList<Produto> listarProdutos(){
        String sql = "SELECT * FROM PRODUTO";
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        try(Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                Produto p = new Produto();
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setIdProduto(rs.getInt("id_produto"));
                p.setIdSetor(rs.getInt("id_setor"));
                p.setQuantidade(rs.getInt("quantidade"));
                produtos.add(p);
            }
        }catch(SQLException e){
            System.err.println("Erro ao listar produtos " + e.getMessage());
            return null;
        }
        return produtos;
    }

    public boolean removerProduto(int id_produto){
        String sql = "DELETE FROM PRODUTO WHERE id_produto = ?";
        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id_produto);
            stmt.execute();
            if(stmt.getUpdateCount()<=0){
                System.out.println("nenhum produto excluido");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("erro ao excluir produto " + e.getMessage());
            return false;
        }
        System.out.println("Produto excluido com sucesso");
        return true;
    }

    public boolean inserirProduto(Produto p){
        String sql = "INSERT INTO PRODUTO (id_setor, nome, preco, quantidade) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdSetor());
            stmt.setString(2, p.getNome());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getQuantidade());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("erro ao inserir novo produto " + e.getMessage());
            return false;
        }
        System.out.println("novo produto cadastrado com sucesso");
        return true;
    }

    public boolean alterarProduto(Produto p){
        String sql = "UPDATE PRODUTO SET id_setor = ?, nome = ?, quantidade = ? preco = ? WHERE id_produto = ?";
        try (Connection con = Conexao.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdSetor());
            stmt.setString(2, p.getNome());
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getIdProduto());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("erro ao alterar produto " + e.getMessage());
            return false;
        }
        System.out.println("produto alterado com sucesso");
        return true;
    }
}
