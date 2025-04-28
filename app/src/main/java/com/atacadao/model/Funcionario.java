package com.atacadao.model;

public class Funcionario {
    private int id_funcionario;
    private int id_gerente;
    private double salario;
    private String nome;

    public int getIdFuncionario() {
        return id_funcionario;
    }

    public void setIdFuncionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getIdGerente() {
        return id_gerente;
    }

    public void setIdGerente(int id_gerente) {
        this.id_gerente = id_gerente;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Funcionario(){}
    
}
