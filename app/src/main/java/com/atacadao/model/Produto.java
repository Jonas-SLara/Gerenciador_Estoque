package com.atacadao.model;

public class Produto {
    private int id_produto;
    private String nome;
    private Double preco;
    private int id_setor;
    private int quantidade;
    
    public int getIdProduto() {
        return id_produto;
    }
    public void setIdProduto(int id_produto) {
        this.id_produto = id_produto;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public int getIdSetor() {
        return id_setor;
    }
    public void setIdSetor(int id_setor) {
        this.id_setor = id_setor;
    }
    public int getQuantidade(){
        return this.quantidade;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
}
