package com.projetointegrador.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.github.hugoperlin.results.Resultado;
import javafx.util.Duration;
import com.projetointegrador.App;
import com.projetointegrador.model.entities.*;
import com.projetointegrador.model.repositories.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Principal implements Initializable {
    RepositorioLinha repositorioLinha;
    RepositorioPonto repositorioPonto;
    RepositorioViagem repositorioViagem;
    RepositorioPassageiro repositorioPassageiro;

    private boolean isViagem = false;
    private Timeline timer;
    private int segundos = 30;

    @FXML
    private Label lbTimer;

    @FXML
    private Button buttonSair;

    @FXML
    private ComboBox<Linha> cbLinha;

    @FXML
    private ComboBox<Ponto> cbPontoInicio;

    @FXML
    private ComboBox<Ponto> cbPontoFinal;

    @FXML
    private ImageView imgLinhas;

    public Principal(RepositorioLinha repositorioLinha, RepositorioPonto repositorioPonto,
            RepositorioViagem repositorioViagem, RepositorioPassageiro repositorioPassageiro) {
        this.repositorioLinha = repositorioLinha;
        this.repositorioPonto = repositorioPonto;
        this.repositorioViagem = repositorioViagem;
        this.repositorioPassageiro = repositorioPassageiro;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado resultado = repositorioLinha.listar();
        if (resultado.foiSucesso()) {
            List<Linha> list = (List) resultado.comoSucesso().getObj();
            cbLinha.getItems().addAll(list);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }
    }

    @FXML
    void exibir(MouseEvent event) {
        App.pushScreen("PERFIL");
    }

    @FXML
    void gerarRota(ActionEvent event) {
        Linha selecionado = cbLinha.getSelectionModel().getSelectedItem();
        String imagem = repositorioLinha.gerarRota(selecionado);
        imgLinhas.setImage(new Image(getClass().getResource(imagem).toExternalForm()));

        cbPontoFinal.getItems().clear();
        cbPontoInicio.getItems().clear();

        Resultado pontos = repositorioLinha.buscarPontosPorLinha(selecionado.getId());
        if (pontos.foiSucesso()) {
            List<Ponto> list = (List) pontos.comoSucesso().getObj();
            cbPontoInicio.getItems().addAll(list);
            cbPontoFinal.getItems().addAll(list);
        } else {
            Alert alert = new Alert(AlertType.ERROR, pontos.getMsg());
            alert.showAndWait();
        }
    }

    @FXML
    void iniciaViagem(ActionEvent event) {
        if(isViagem == true){
            encerrarViagem(event);
            return;
        }

        Linha linha = cbLinha.getSelectionModel().getSelectedItem();
        Resultado resultado = repositorioViagem.iniciarViagem(linha, cbPontoInicio.getValue(), cbPontoFinal.getValue());
        Alert alerta;

        if (resultado.foiErro()) {
            alerta = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alerta = new Alert(AlertType.INFORMATION, resultado.getMsg());
            buttonSair.setText("Encerrar Viagem");
            isViagem = true;

            iniciarTemporizador();
        }
        alerta.showAndWait();
    }

    void iniciarTemporizador() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> atualizarTempo()));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    void atualizarTempo() {
        segundos--;

        if (segundos < 0) {
            encerrarViagem(new ActionEvent());
            return;
        }

        int minutos = segundos / 60;
        int segundosRestantes = segundos % 60;
        lbTimer.setText(String.format("Tempo Estimado: %02d:%02d", minutos, segundosRestantes));
    }

    @FXML
    void encerrarViagem(ActionEvent event) {
        lbTimer.setText("");
        buttonSair.setText("Iniciar Viagem");
        isViagem = false;

        int novoTempo = (int) Math.random() * (30 - 10 + 1) + 10;
        segundos = novoTempo;

        if(timer != null){
            timer.stop();
        }
    }
}