package com.atacadao.util;

import com.atacadao.DAO.*;
import com.atacadao.model.*;

import java.util.ArrayList;

public class Teste {

    public static void verUsuarios(UsuarioDAO udao){
        ArrayList<Usuario> usuarios = udao.listarUsuarios();
        if(usuarios.size()==0){
            System.out.println("usuarios vazio");
        }
        for (Usuario u : usuarios) {
            System.out.println("Nome: " + u.getNome() + " Email: " + u.getEmail() +
                    " Senha: " + u.getSenha() + " ID: " + u.getId());
        }
    }

    public static void verSetores(SetorDAO sdao){
        ArrayList<Setor> setores = sdao.listarSetores();
        if(setores.size()==0){
            System.out.println("setores vazio");
        }
        for(Setor s : setores){
            System.out.println("Nome: " + s.getNome() + " ID: " + s.getId());
        }
    }

    public static void verGerentes(GerenteDAO gdao){
        ArrayList<Gerente> gerentes = gdao.listarGerentes();
        if(gerentes.size()==0){
            System.out.println("gerentes vazio");
        }
        for(Gerente g : gerentes){
            System.out.println("ID_setor: " + g.getIdSetor() + " ID_usuario: " + g.getIdUsuario() +
                    " Salario: " + g.getSalario() + " ID: " + g.getIdGerente());
        }
    }

    public static void verFuncionarios(FuncionarioDAO fdao){
        ArrayList<Funcionario> funcionarios = fdao.listarFuncionarios();
        for(Funcionario f : funcionarios){
            System.out.println("nome: " + f.getNome() + " salario: " + f.getSalario() +
                    " ID_gerente: " + f.getIdGerente() + " ID: " + f.getIdFuncionario());
        }
    }

    public static void verProdutos(ProdutoDAO pdao){
        ArrayList<Produto> produtos = pdao.listarProdutos();
        for(Produto p : produtos){
            System.out.println("Nome: " + p.getNome() + " idSetor: " + p.getIdSetor() + " Preco: " +
                    p.getPreco() + " quantidade: " + p.getQuantidade() + " ID: " + p.getIdProduto());
        }
    }
    public static void main(String[] args){
        ProdutoDAO p = new ProdutoDAO();
        SetorDAO s = new SetorDAO();
        FuncionarioDAO f = new FuncionarioDAO();
        GerenteDAO g = new GerenteDAO();
        UsuarioDAO u = new UsuarioDAO();

        Teste.verUsuarios(u);
        Teste.verGerentes(g);
        Teste.verSetores(s);
        Teste.verFuncionarios(f);
        Teste.verProdutos(p);
    }
}