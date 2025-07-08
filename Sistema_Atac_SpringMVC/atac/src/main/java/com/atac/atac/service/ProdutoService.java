package com.atac.atac.service;

import java.util.ArrayList;

import com.atac.atac.dao.ProdutoDAO;
import com.atac.atac.model.Produto;

public class ProdutoService {
    private ProdutoDAO pdao = new ProdutoDAO();

    public ArrayList<Produto> lista(){
        return pdao.listar_produtos();
    }

    public boolean inserirProduto(int idGerente, Produto p){
        p.setIdGerente(idGerente);
        return pdao.inserir_produto(p);
    }

    public boolean excluirProduto(int id){
        return pdao.remover_produto(id);
    }

    public boolean editar(Produto p){
        return pdao.alterar_produto(p);
    }
}
