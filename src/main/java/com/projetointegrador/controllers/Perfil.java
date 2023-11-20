package com.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;    
import com.projetointegrador.App;
import com.projetointegrador.model.daos.*;
import com.projetointegrador.model.entities.Passageiro;
import com.projetointegrador.model.repositories.*;
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

    private PassageiroDAO passageiroDAO = new JDBCPassageiroDAO(FabricaConexoes.getInstance());
    private RepositorioPassageiro repositorioPassageiro = new RepositorioPassageiro(passageiroDAO);

    private LinhaDAO LinhaDAO = new JDBCLinhaDAO(FabricaConexoes.getInstance());
    private RepositorioLinha repositorioLinha = new RepositorioLinha(LinhaDAO);

    private PontoDAO pontoDAO = new JDBCPontoDAO(FabricaConexoes.getInstance());
    private RepositorioPonto repositorioPonto = new RepositorioPonto(pontoDAO);

    private ViagemDAO viagemDAO = new JDBCViagemDAO(FabricaConexoes.getInstance());
    private RepositorioViagem repositorioViagem = new RepositorioViagem(viagemDAO);

    private OnibusDAO onibusDAO = new JDBCOnibusDAO(FabricaConexoes.getInstance());
    private RepositorioOnibus repositorioOnibus = new RepositorioOnibus(onibusDAO);

    private AvaliacaoDAO avaliacaoDAO = new JDBCAvaliacaoDAO(FabricaConexoes.getInstance());
    private RepositorioAvaliacao repositorioAvaliacao = new RepositorioAvaliacao(avaliacaoDAO);

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
        App.pushScreen("LOGIN", o -> new Login(repositorioPassageiro, null));
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPAL", o -> new Principal(repositorioLinha, repositorioPonto, repositorioViagem, repositorioPassageiro, repositorioOnibus, repositorioAvaliacao, passageiro));
    }
}