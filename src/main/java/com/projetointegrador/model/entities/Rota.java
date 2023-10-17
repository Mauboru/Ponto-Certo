package com.projetointegrador.model.entities;

public class Rota {
    private int id;
    private String nome;
    private Ponto pontoInicial;
    private Ponto pontoFinal;

    public Rota(int id, String nome, Ponto pontoInicial, Ponto pontoFinal) {
        this.id = id;
        this.nome = nome;
        this.pontoInicial = pontoInicial;
        this.pontoFinal = pontoFinal;
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

    public Ponto getPontoInicial() {
        return pontoInicial;
    }

    public void setPontoInicial(Ponto pontoInicial) {
        this.pontoInicial = pontoInicial;
    }

    public Ponto getPontoFinal() {
        return pontoFinal;
    }

    public void setPontoFinal(Ponto pontoFinal) {
        this.pontoFinal = pontoFinal;
    }   
    
}