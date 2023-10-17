package com.projetointegrador.model.entities;

public class PassageiroViagem {
    private Viagem viagem;
    private Passageiro passageiro;
    private Avaliacao avaliacao;

    public PassageiroViagem(Viagem viagem, Passageiro passageiro, Avaliacao avaliacao) {
        this.viagem = viagem;
        this.passageiro = passageiro;
        this.avaliacao = avaliacao;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
}