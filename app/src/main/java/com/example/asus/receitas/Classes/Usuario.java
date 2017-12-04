package com.example.asus.receitas.Classes;

public class Usuario {

    private int id;
    private String email;
    private String senha;
    private String apelido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

}
