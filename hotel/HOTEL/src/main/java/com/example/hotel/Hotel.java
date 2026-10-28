package com.example.hotel;

public class Hotel {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private int quarto;
    private int diasHospedagem;

    public Hotel(String nome, String cpf, String telefone, String email, int quarto, int diasHospedagem) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.quarto = quarto;
        this.diasHospedagem = diasHospedagem;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getQuarto() { return quarto; }
    public void setQuarto(int quarto) { this.quarto = quarto; }

    public int getDiasHospedagem() { return diasHospedagem; }
    public void setDiasHospedagem(int diasHospedagem) { this.diasHospedagem = diasHospedagem; }
}