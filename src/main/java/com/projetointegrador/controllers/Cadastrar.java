package com.projetointegrador.controllers;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.App;
import com.projetointegrador.model.repositories.RepositorioPassageiro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class Cadastrar {
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfSenha;

    private RepositorioPassageiro repositorioPassageiro;

    public Cadastrar(RepositorioPassageiro repositorioPassageiro){
        this.repositorioPassageiro = repositorioPassageiro;
    }

    //Funções Override

    //Funções FXML
    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String senha = tfSenha.getText();
        Alert alerta;
        Resultado resultado = repositorioPassageiro.cadastrar(nome, email, senha);

        if(resultado.foiErro()){
            alerta = new Alert(AlertType.ERROR, resultado.getMsg());
        }else{
            alerta = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tfNome.clear();
            tfEmail.clear();
            tfSenha.clear();
        }
        alerta.showAndWait();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    //Funções
}