package com.atacadao.service;

import java.util.ArrayList;

import com.atacadao.dao.GerenteDAO;
import com.atacadao.model.Gerente;
import com.atacadao.model.Produto;
import com.atacadao.model.Funcionario;

public class GerenteService {

    private static GerenteDAO gdao = new GerenteDAO();

    public Gerente getGerente(String cpf){
        cpf = UsuarioService.formatarCPF(cpf);
        Gerente g = gdao.buscar_gerente(cpf);
        return g;
    }

    public ArrayList<Gerente> list(){
        return gdao.listar_gerentes();
    }

    public ArrayList<Funcionario> listFuncionarios(int id){
        return gdao.listar_seus_funcionarios(id);
    }

    public ArrayList<Produto> listProdutos(int id){
        return gdao.listar_seus_produtos(id);
    }
}
