package com.atacadao.util;

import java.util.ArrayList;
import java.util.Iterator;

import com.atacadao.dao.FuncionarioDAO;
import com.atacadao.dao.GerenteDAO;
import com.atacadao.dao.ProdutoDAO;
import com.atacadao.dao.UsuarioDAO;

import com.atacadao.model.Usuario;
import com.atacadao.model.Funcionario;
import com.atacadao.model.Gerente;
import com.atacadao.model.Produto;

class Teste {
    public static UsuarioDAO udao = new UsuarioDAO();
    public static FuncionarioDAO fdao = new FuncionarioDAO();
    public static GerenteDAO gdao = new GerenteDAO();
    public static ProdutoDAO pdao = new ProdutoDAO();
    

    public static void main(String[] args) {
        mostrarUsers(udao.listarUsuarios());
        mostrarFuncionarios(fdao.listar_funcionarios());
        mostrarGerentes(gdao.listar_gerentes());
        mostrarProdutos(pdao.listar_produtos());

        mostrarFuncionarios(gdao.listar_seus_funcionarios(1));
        mostrarProdutos(gdao.listar_seus_produtos(1));
        mostrarFuncionarios(gdao.listar_seus_funcionarios(2));
        mostrarProdutos(gdao.listar_seus_produtos(2));
    }

    public static void mostrarUsers(ArrayList<Usuario> users){
        for (Usuario u : users) {
            System.out.println(u.getNome() + " " + u.getCelular() + " " + u.getEmail() +
               " " + u.getCpf() + " " + u.getSenha() + " " + u.getSalario());
        }
        System.out.println("_______________________________________________________________");
        System.out.println("\n");
    }
    
    public static void mostrarFuncionarios(ArrayList<Funcionario> funcionarios){
        for (Funcionario f : funcionarios) {
            System.out.println(f.getId() + " " + f.getUsuario().getNome() + " " + f.getUsuario().getEmail() + " "
             + f.getUsuario().getCelular() + " " + f.getCargo() + " " + f.getCpfUsuario() + " " + f.getIdGerente()
             + " " + f.getUsuario().getSalario());
        }
        System.out.println("_______________________________________________________________");
        System.out.println("\n");
    }

    public static void mostrarProdutos(ArrayList<Produto> produtos){
        Iterator<Produto> it = produtos.iterator();
        Produto p = null;
        while (it.hasNext()) {
            p = it.next();
            System.out.println(p.getNome() + " " + p.getId() + " " + p.getValor() +
               " " + p.getQuantidade() + " " + p.getIdGerente());
        }
        System.out.println("_______________________________________________________________");
        System.out.println("\n");
    }

    public static void mostrarFuncionario(Funcionario f){
        System.out.println(f.getId() + " " + f.getUsuario().getNome() + " " + f.getUsuario().getEmail() + " "
        + f.getUsuario().getCelular() + " " + f.getCargo() + " " + f.getCpfUsuario() + " " + f.getIdGerente()
        + " " + f.getUsuario().getSalario());
        System.out.println("_______________________________________________________________");
        System.out.println("\n");
    }

    public static void mostrarGerentes(ArrayList<Gerente> gerentes){
        for (Gerente g : gerentes) {
            System.out.println(g.getId() + " " + g.getUsuario().getNome() + " " + g.getUsuario().getEmail() + " " +
            g.getCpfUsuario() + " " + g.getBonificacao() + " " + g.getUsuario().getSalario());
        }    
        System.out.println("_______________________________________________________________");
        System.out.println("\n");      
    }

    public static void mostrarGerente(Gerente g){
        System.out.println(g.getId() + " " + g.getUsuario().getNome() + " " + g.getUsuario().getEmail() + " " +
            g.getCpfUsuario() + " " + g.getBonificacao() + " " + g.getUsuario().getSalario());
        System.out.println("_______________________________________________________________");
        System.out.println("\n"); 
    }
}