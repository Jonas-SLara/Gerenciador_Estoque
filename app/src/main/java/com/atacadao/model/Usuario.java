package com.atacadao.model;

public class Usuario{
    private String nome;
    private String email;
    private String senha;
    private int id;

    public Usuario(){}

    public String getNome(){
        return this.nome;
    }
    public void setNome(String novoNome){
        this.nome=novoNome;
    }

    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getSenha(){
        return this.senha;
    }
    public void setSenha(String novaSenha){
        this.senha = novaSenha;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String novoEmail){
        this.email = novoEmail;
    }
}
