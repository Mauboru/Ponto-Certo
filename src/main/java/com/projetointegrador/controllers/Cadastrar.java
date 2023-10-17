package com.projetointegrador.controllers;

import com.projetointegrador.model.repositories.RepositorioPassageiro;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Cadastrar {
    @FXML
    private TextField input;

    private RepositorioPassageiro repositorioPassageiro;

    public Cadastrar(RepositorioPassageiro repositorioPassageiro){
        this.repositorioPassageiro = repositorioPassageiro;
    }
}