package com.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;    
import com.projetointegrador.App;
import com.projetointegrador.model.entities.Passageiro;
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
    private Passageiro passageiro;

    public Perfil(RepositorioPassageiro repositorioPassageiro, Passageiro passageiro) {
        this.repositorioPassageiro = repositorioPassageiro;
        this.passageiro = passageiro;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (passageiro != null) {
            tfEmail.setText(passageiro.getEmail());
            tfNome.setText(passageiro.getNome());
            tfSenha.setText(passageiro.getSenha());
        }
    }

    @FXML
    void editar(ActionEvent event) {
        App.pushScreen("CADASTRAR", o-> new Cadastrar(repositorioPassageiro, passageiro));
    }

    @FXML
    void sair(ActionEvent event) {
        App.pushScreen("LOGIN");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }
}