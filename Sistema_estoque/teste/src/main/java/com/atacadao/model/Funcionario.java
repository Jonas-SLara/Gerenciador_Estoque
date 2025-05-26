package com.atacadao.model;

public class Funcionario {
    private int id;
    private int id_gerente;
    private String cpf_usuario;
    private String cargo;
    
    public Funcionario(){}
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpf_usuario() {
        return this.cpf_usuario;
    }

    public void setCpf_usuario(String cpf_usuario) {
        this.cpf_usuario = cpf_usuario;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGerente() {
        return this.id_gerente;
    }

    public void setIdGerente(int id_gerente) {
        this.id_gerente = id_gerente;
    }

}