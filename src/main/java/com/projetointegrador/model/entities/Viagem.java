package com.projetointegrador.model.entities;

public class Viagem {
    private int id;
    private Onibus onibus;
    private Rota rota;

    public Viagem(int id, Onibus onibus, Rota rota) {
        this.id = id;
        this.onibus = onibus;
        this.rota = rota;
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

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public void iniciarViagem(){
        //Logica
    }

    public void encerrarViagem(){
        //Logica
    }
}