package com.atac.atac.service;

import com.atac.atac.model.Usuario;

public class LoginService {

    public Usuario autenticar(String cpf, String senha){
        Usuario u = new UsuarioService().buscarUsuario(cpf);
        if(u == null) return null;

        System.out.println(u.getNome());
        String cpf_formatado = cpf.replaceAll("[^0-9]+", "");

        if(u.getSenha().equals(senha) && u.getCpf().equals(cpf_formatado)){
            return u;
        }else{
            return null;
        }
    }
}
