package com.atacadao.service;

import java.util.ArrayList;

import com.atacadao.dao.ProdutoDAO;
import com.atacadao.model.Produto;

public class ProdutoService {
    public static ProdutoDAO pdao = new ProdutoDAO();

    public ArrayList<Produto> list(){
        return pdao.listar_produtos();
    }

    public boolean cadastrarProduto(Produto p){
        return pdao.inserir_produto(p);
    }

    public boolean excluirProduto(int id){
        return pdao.remover_produto(id);
    }

    public Produto buscarProduto(int id){
        return pdao.buscar_por_id(id);
    }
}
