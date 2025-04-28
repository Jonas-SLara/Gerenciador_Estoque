package com.atacadao.model;

public class Gerente {

    private int id_gerente;
    private int id_usuario;
    private int id_setor;
    private double salario;

    public Gerente(){}
    
    public int getIdGerente(){return this.id_gerente;}
    public int getIdUsuario(){return this.id_usuario;}
    public int getIdSetor(){return this.id_setor;}
    public double getSalario(){return this.salario;}
    
    public void setIdGerente(int id){
        this.id_gerente = id;
    }
    public void setIdUsuario(int id){
        this.id_usuario = id;
    }
    public void setIdSetor(int id){
        this.id_setor = id;
    }
    public void setSalario(double salario){
        this.salario = salario;
    }
}


