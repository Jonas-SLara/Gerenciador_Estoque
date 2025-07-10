package com.atac.atac.service;

import java.util.ArrayList;

import com.atac.atac.dao.FuncionarioDAO;
import com.atac.atac.model.Funcionario;
import com.atac.atac.model.Usuario;

public class FuncionarioService {
    private FuncionarioDAO fdao = new FuncionarioDAO();

    public Funcionario buscarFuncionario(Usuario u){
        if(u == null){
            return null;
        }
        else{
            return fdao.buscar_funcionario(u.getCpf());
        }
    }
    
    public ArrayList<Funcionario> lista(){
        return fdao.listar_funcionarios();
    }

    public boolean cadastrarFuncionario(Funcionario f){
        return fdao.inserir_funcionario(f);
    }

    public boolean editarFuncionario(Funcionario f){
        return fdao.alterar_funcionario(f);
    }
    public boolean excluirFuncionario(int id){
        return fdao.remover_funcionario(id);
    }
}
