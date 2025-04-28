package com.atacadao.model;

public class Setor {
    private int id;
    private String nome;
    public Setor(){}
    
    public void setId(int id){
        this.id=id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public int getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
}
