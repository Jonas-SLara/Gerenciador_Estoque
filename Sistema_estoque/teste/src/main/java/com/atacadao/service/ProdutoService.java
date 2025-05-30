package com.atacadao.service;

import java.util.ArrayList;

import com.atacadao.dao.ProdutoDAO;
import com.atacadao.model.Produto;

public class ProdutoService {
    public static ProdutoDAO pdao = new ProdutoDAO();

    public ArrayList<Produto> list(){
        return pdao.listar_produtos();
    }
}
