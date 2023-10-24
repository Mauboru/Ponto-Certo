package com.projetointegrador.model.entities;

public class Onibus {
    private int id;
    private String placa;
    private String cor;
    private int qtdTotalPassageiros;
    
    public Onibus(int id, String placa, String cor, int qtdTotalPassageiros) {
        this.id = id;
        this.placa = placa;
        this.cor = cor;
        this.qtdTotalPassageiros = qtdTotalPassageiros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getQtdTotalPassageiros() {
        return qtdTotalPassageiros;
    }

    public void setQtdTotalPassageiros(int qtdTotalPassageiros) {
        this.qtdTotalPassageiros = qtdTotalPassageiros;
    }
}