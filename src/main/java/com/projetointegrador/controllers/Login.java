package com.projetointegrador.controllers;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.App;
import com.projetointegrador.model.repositories.RepositorioPassageiro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class Login {
    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfSenha;

    private RepositorioPassageiro repositorioPassageiro;

    public Login(RepositorioPassageiro repositorioPassageiro) {
        this.repositorioPassageiro = repositorioPassageiro;
    }

    // Funções Override

    // Funções FXML
    @FXML
    void cadastrar(ActionEvent event) {
        App.pushScreen("CADASTRAR");
    }

    @FXML
    void login(ActionEvent event) {
        String usuario = tfEmail.getText();
        String senha = tfSenha.getText();
        Alert alerta;
        Resultado resultado = repositorioPassageiro.login(usuario, senha);

        if (resultado.foiErro()) {
            alerta = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alerta = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tfEmail.clear();
            tfSenha.clear();
            App.pushScreen("PRINCIPAL");
        }
        alerta.showAndWait();
    }

    // Funções
}