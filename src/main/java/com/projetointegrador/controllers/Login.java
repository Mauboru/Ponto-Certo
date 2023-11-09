package com.projetointegrador.controllers;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.App;
import com.projetointegrador.model.repositories.*;
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
            alerta.showAndWait();
        } else {
            repositorioPassageiro.saveLogin(usuario);
            App.pushScreen("PRINCIPAL");
        }
    }
}