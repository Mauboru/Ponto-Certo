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
    private RepositorioLinha repositorioLinha;
    private RepositorioPonto repositorioPonto;
    private RepositorioViagem repositorioViagem;
    private RepositorioPassageiro repositorioPassageiro;
    private RepositorioOnibus repositorioOnibus;
    private RepositorioAvaliacao repositorioAvaliacao;
    private Passageiro passageiro;
    private boolean isViagem = false;
    private boolean viagemTermino = false;
    private Timeline timer;
    private int segundos = 5;

    @FXML
    private Label lbTimer;

    @FXML
    private ComboBox<Avaliacao> cbAvaliacao;

    @FXML
    private ComboBox<Onibus> cbOnibus;

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
            RepositorioViagem repositorioViagem, RepositorioPassageiro repositorioPassageiro,
            RepositorioOnibus repositorioOnibus, RepositorioAvaliacao repositorioAvaliacao, Passageiro passageiro) {
        this.repositorioLinha = repositorioLinha;
        this.repositorioPonto = repositorioPonto;
        this.repositorioViagem = repositorioViagem;
        this.repositorioPassageiro = repositorioPassageiro;
        this.repositorioOnibus = repositorioOnibus;
        this.repositorioAvaliacao = repositorioAvaliacao;
        this.passageiro = passageiro;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado resultado = repositorioLinha.listar();
        if (resultado.foiSucesso()) {
            List<Linha> list = (List) resultado.comoSucesso().getObj();
            cbLinha.getItems().addAll(list);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.show();
        }

        Resultado avaliacao = repositorioAvaliacao.getAvaliacao();
        if (avaliacao.foiSucesso()) {
            List<Avaliacao> list = (List) avaliacao.comoSucesso().getObj();
            cbAvaliacao.getItems().addAll(list);
            cbAvaliacao.getSelectionModel().select(0);
        } else {
            Alert alert = new Alert(AlertType.ERROR, avaliacao.getMsg());
            alert.show();
        }

        Resultado onibus = repositorioOnibus.getOnibus();
        if (onibus.foiSucesso()) {
            List<Onibus> list = (List) onibus.comoSucesso().getObj();
            cbOnibus.getItems().addAll(list);
            cbOnibus.getSelectionModel().select(0);
        } else {
            Alert alert = new Alert(AlertType.ERROR, onibus.getMsg());
            alert.show();
        }
    }

    @FXML
    void exibir(MouseEvent event) {
        App.pushScreen("PERFIL", o -> new Perfil(repositorioPassageiro, passageiro));
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
            alert.show();
        }
    }

    @FXML
    void iniciaViagem(ActionEvent event) {
        if (isViagem == true) {
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
        alerta.show();
    }

    @FXML
    void encerrarViagem(ActionEvent event) {
        Avaliacao idAvaliacao = cbAvaliacao.getSelectionModel().getSelectedItem();
        Onibus idOnibus = cbOnibus.getSelectionModel().getSelectedItem();
        Linha idLinha = cbLinha.getSelectionModel().getSelectedItem();
        Ponto idPontoInicial = cbPontoInicio.getSelectionModel().getSelectedItem();
        Ponto idPontoFinal = cbPontoFinal.getSelectionModel().getSelectedItem();

        lbTimer.setText("");
        buttonSair.setText("Iniciar Viagem");
        isViagem = false;
        viagemTermino = false;

        if (timer != null) {
            timer.stop();
        }
        
        Resultado viagem = repositorioViagem.terminarViagem(passageiro, idAvaliacao, idOnibus, idLinha,
                idPontoInicial, idPontoFinal);
        if (viagem.foiSucesso()) {
            Alert alert = new Alert(AlertType.INFORMATION, viagem.getMsg());
            alert.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR, viagem.getMsg());
            alert.show();
        }
    }

    @FXML
    void iniciouViagem(ActionEvent event) {
        Alert alerta = new Alert(AlertType.INFORMATION, "Seu ônibus chegou!");
        alerta.show();
        if (timer != null) {
            timer.stop();
        }
        segundos = 12;
        viagemTermino = true;
        iniciarTemporizador();
    }

    void iniciarTemporizador() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> atualizarTempo()));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    void atualizarTempo() {
        segundos--;

        if (segundos < 0 && viagemTermino == false) {
            iniciouViagem(new ActionEvent());
            return;
        }
        if (segundos < 0 && viagemTermino == true) {
            encerrarViagem(new ActionEvent());
            return;
        }

        int minutos = segundos / 60;
        int segundosRestantes = segundos % 60;
        lbTimer.setText(String.format("Tempo Estimado: %02d:%02d", minutos, segundosRestantes));
    }
}