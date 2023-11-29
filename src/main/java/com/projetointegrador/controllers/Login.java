package com.projetointegrador.controllers;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.App;
import com.projetointegrador.model.daos.*;
import com.projetointegrador.model.entities.Passageiro;
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

    @FXML
    private TextField tfEmailRecuperar;

    @FXML
    private Button buttonSair;

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

    public Login(RepositorioPassageiro repositorioPassageiro, Passageiro passageiro) {
        this.repositorioPassageiro = repositorioPassageiro;
        this.passageiro = passageiro;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        App.pushScreen("CADASTRAR", o -> new Cadastrar(repositorioPassageiro, passageiro));
    }

    @FXML
    void login(ActionEvent event) {
        String email = tfEmail.getText();
        String senha = tfSenha.getText();
        Alert alerta;
        Resultado resultado = repositorioPassageiro.login(email, senha);
        
        if (resultado.foiErro()) {
            System.out.println(resultado.getMsg());
            alerta = new Alert(AlertType.ERROR, resultado.getMsg());
            alerta.show();
        } else {
            String nome = repositorioPassageiro.getInfo(email, "nome");
            int id = Integer.parseInt(repositorioPassageiro.getInfo(email, "id"));
            Passageiro logado = new Passageiro(id, nome, email, senha);
            App.pushScreen("PRINCIPAL", o -> new Principal(repositorioLinha, repositorioPonto, repositorioViagem, repositorioPassageiro, repositorioOnibus, repositorioAvaliacao, logado));
        }
    }

    @FXML
    void mostrarLabel(){
        buttonSair.setVisible(true);
        tfEmailRecuperar.setVisible(true);
    }

    @FXML
    void enviarEmail(){
        String email = tfEmailRecuperar.getText();
        Resultado resultado = repositorioPassageiro.enviarEmail(email);
        Alert alerta;

        if (resultado.foiErro()){
            alerta = new Alert(AlertType.ERROR, resultado.getMsg());
            alerta.show();
        }
        else{
            alerta = new Alert(AlertType.ERROR, resultado.getMsg());
            alerta.show();
            System.out.println(passageiro);
            buttonSair.setVisible(false);
            tfEmailRecuperar.setVisible(false);
        }
    }
}