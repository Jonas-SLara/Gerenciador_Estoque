package com.atac.atac.service;

import java.util.ArrayList;

import com.atac.atac.dao.GerenteDAO;
import com.atac.atac.model.*;

public class GerenteService {
    private GerenteDAO gdao = new GerenteDAO();

    public Gerente buscarGerente(Usuario u){
        if(u==null){
            return null;
        }
        else{
            return gdao.buscar_gerente(u.getCpf());
        }
    }

    public ArrayList<Produto> lista_Produtos(int id){
        return gdao.listar_seus_produtos(id);
    }

    public ArrayList<Funcionario> lista_Funcionarios(int id){
        return gdao.listar_seus_funcionarios(id);
    }
}
