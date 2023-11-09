package com.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.App;
import com.projetointegrador.model.repositories.RepositorioPassageiro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Perfil implements Initializable{
    @FXML
    private Label tfEmail;

    @FXML
    private Label tfNome;

    @FXML
    private Label tfSenha;

    private RepositorioPassageiro repositorioPassageiro;

    public Perfil(RepositorioPassageiro repositorioPassageiro) {
        this.repositorioPassageiro = repositorioPassageiro;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String email = repositorioPassageiro.getLogin();
        String nome = repositorioPassageiro.getInfo(email, "nome");
        String senha = repositorioPassageiro.getInfo(email, "senha");

        tfEmail.setText(email);
        tfNome.setText(nome);
        tfSenha.setText(senha);
    }

    @FXML
    void editar(ActionEvent event) {
        App.pushScreen("EDITAR", o-> new Editar());
    }

    @FXML
    void sair(ActionEvent event) {
        App.pushScreen("LOGIN");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
}