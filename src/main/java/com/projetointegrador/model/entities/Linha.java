package com.projetointegrador.model.entities;

import java.util.ArrayList;

public class Linha {
    private int id;
    private String nome;
    private ArrayList<Ponto> pontos;

    public Linha(int id, String nome, ArrayList<Ponto> pontos) {
        this.id = id;
        this.nome = nome;
        this.pontos = pontos;
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

    public ArrayList<Ponto> getPontos() {
        return pontos;
    }

    public void setPontos(ArrayList<Ponto> pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return nome;
    }
}