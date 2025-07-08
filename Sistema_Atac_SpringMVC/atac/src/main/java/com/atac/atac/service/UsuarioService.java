package com.atac.atac.service;

import com.atac.atac.dao.UsuarioDAO;
import com.atac.atac.model.Usuario;

public class UsuarioService {
    private UsuarioDAO udao = new UsuarioDAO();

    public Usuario buscarUsuario(String cpf){
        //deixar apenas numeros no cpf
        String cpf_formatado = cpf.replaceAll("[^0-9]+", "");
        System.out.println(cpf_formatado + " cpf formatodo; pesquisa...");
        return udao.buscar_por_cpf(cpf_formatado);
    }

    public boolean editar(Usuario u){
        System.out.println("editando usuario " + u.getCpf());
        return udao.alterarUsuario(u);
    }
}
