package br.csi.model;

public class Usuario {
     private int id;
     private String senha;
     private String email;
     private boolean ativo;

     public int getId() {
         return id;
     }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAtivo() {
         return ativo;
    }

    public void setId(int id) {this.id = id;}
    public void setEmail(String email) {this.email = email;}
    public void setSenha(String senha) {this.senha = senha;}
    public void setAtivo(boolean ativo) {this.ativo = ativo;}

    public Usuario(String email, String senha, boolean ativo) {
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
     }
}
