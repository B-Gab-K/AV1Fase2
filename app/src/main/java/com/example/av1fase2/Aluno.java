package com.example.av1fase2;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String campus;
    private String genero;

    public Aluno() {
    }

    public Aluno(String nome, String email, String telefone, String campus, String genero) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.campus = campus;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

