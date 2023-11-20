package com.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.App;
import com.projetointegrador.model.entities.Passageiro;
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
    private Passageiro passageiro;

    public Cadastrar(RepositorioPassageiro repositorioPassageiro, Passageiro passageiro) {
        this.repositorioPassageiro = repositorioPassageiro;
        this.passageiro = passageiro;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (passageiro != null){
            tfNome.setText(passageiro.getNome());
            tfEmail.setText(passageiro.getEmail());
            tfSenha.setText(passageiro.getSenha());
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
        Alert alerta;
        Resultado resultado;

        if (passageiro == null) {
            resultado = repositorioPassageiro.cadastrar(nome, email, senha);
        } else {
            int id = passageiro.getId();
            resultado = repositorioPassageiro.atualizar(id, nome, email, senha);
            Passageiro logado = new Passageiro(id, nome, email, senha);
            App.pushScreen("PERFIL", o -> new Perfil(repositorioPassageiro, logado));
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
        int id = Integer.parseInt(repositorioPassageiro.getInfo(passageiro.getEmail(), "id"));
        Resultado resultado = repositorioPassageiro.deletar(id);
        Alert alerta;

        if (resultado.foiErro()) {
            alerta = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alerta = new Alert(AlertType.INFORMATION, resultado.getMsg());
            App.pushScreen("LOGIN");
        }
        alerta.show();
    }
}