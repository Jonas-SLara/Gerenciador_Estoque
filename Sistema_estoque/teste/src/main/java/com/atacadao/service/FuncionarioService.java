package com.atacadao.service;

import java.util.ArrayList;

import com.atacadao.dao.FuncionarioDAO;
import com.atacadao.model.Funcionario;

public class FuncionarioService {

    private static FuncionarioDAO fdao = new FuncionarioDAO();

    public Funcionario getFuncionario(String cpf){
        cpf=UsuarioService.formatarCPF(cpf);
        Funcionario f = fdao.buscar_funcionario(cpf);
        return f;
    }
    
    public ArrayList<Funcionario> list(){
        return fdao.listar_funcionarios();
    }
}
