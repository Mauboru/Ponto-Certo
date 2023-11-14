package com.projetointegrador.model.entities;

public class Viagem {
    private int id;
    private Onibus onibus;
    private Linha rota;
    private Passageiro passageiro;
    private Ponto pontoInicial;
    private Ponto pontoFinal;
    private Avaliacao avaliacao;
    
    public Viagem(Onibus onibus, Linha rota, Passageiro passageiro, Ponto pontoInicial, Ponto pontoFinal,
            Avaliacao avaliacao) {
        this.onibus = onibus;
        this.rota = rota;
        this.passageiro = passageiro;
        this.pontoInicial = pontoInicial;
        this.pontoFinal = pontoFinal;
        this.avaliacao = avaliacao;
    
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public Linha getRota() {
        return rota;
    }

    public void setRota(Linha rota) {
        this.rota = rota;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
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

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }    
}