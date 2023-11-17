package com.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.App;
import com.projetointegrador.model.repositories.RepositorioPassageiro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class Cadastrar implements Initializable {
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfSenha;

    @FXML
    private Button button;

    @FXML
    private Button buttonSair;

    private RepositorioPassageiro repositorioPassageiro;

    public Cadastrar(RepositorioPassageiro repositorioPassageiro) {
        this.repositorioPassageiro = repositorioPassageiro;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String login = repositorioPassageiro.getLogin();
        if (login != null) {
            tfNome.setText(repositorioPassageiro.getInfo(login, "nome"));
            tfEmail.setText(repositorioPassageiro.getInfo(login, "email"));
            tfSenha.setText(repositorioPassageiro.getInfo(login, "senha"));
            button.setText("Atualizar");
            buttonSair.setText("Excluir");
            buttonSair.setOnAction(event -> excluir(event));
        }
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String senha = tfSenha.getText();
        String login = repositorioPassageiro.getLogin();
        Alert alerta;
        Resultado resultado;

        if (login == null) {
            resultado = repositorioPassageiro.cadastrar(nome, email, senha);
        } else {
            int id = Integer.parseInt(repositorioPassageiro.getInfo(login, "id"));
            resultado = repositorioPassageiro.atualizar(id, nome, email, senha);
            repositorioPassageiro.saveLogin(email);
            App.pushScreen("PERFIL", o -> new Perfil(repositorioPassageiro));
        }

        if (resultado.foiErro()) {
            alerta = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alerta = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tfNome.clear();
            tfEmail.clear();
            tfSenha.clear();
        }
        alerta.show();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void excluir(ActionEvent event) {
        int id = Integer.parseInt(repositorioPassageiro.getInfo(repositorioPassageiro.getLogin(), "id"));
        Resultado resultado = repositorioPassageiro.deletar(id);
        Alert alerta;

        if (resultado.foiErro()) {
            alerta = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alerta = new Alert(AlertType.INFORMATION, resultado.getMsg());
            repositorioPassageiro.saveLogin(null);
            App.pushScreen("LOGIN");
        }
        alerta.show();
    }
}