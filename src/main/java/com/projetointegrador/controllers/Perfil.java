package com.projetointegrador.controllers;

import com.projetointegrador.App;
import com.projetointegrador.model.repositories.RepositorioPassageiro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Perfil {
    @FXML
    private Label tfEmail;

    @FXML
    private Label tfNome;

    private RepositorioPassageiro repositorioPassageiro;

    public Perfil(RepositorioPassageiro repositorioPassageiro) {
        this.repositorioPassageiro = repositorioPassageiro;
    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void sair(ActionEvent event) {
        repositorioPassageiro.logout();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
}