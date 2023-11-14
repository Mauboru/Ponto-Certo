package com.projetointegrador.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.App;
import com.projetointegrador.model.entities.*;
import com.projetointegrador.model.repositories.*;
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

    @FXML
    private ComboBox<Linha> cbLinha;

    @FXML
    private ComboBox<Ponto> cbPontoInicio;

    @FXML
    private ComboBox<Ponto> cbPontoFinal;

    @FXML
    private ImageView imgLinhas;

    public Principal(RepositorioLinha repositorioLinha, RepositorioPonto repositorioPonto) {
        this.repositorioLinha = repositorioLinha;
        this.repositorioPonto = repositorioPonto;
    }

    @FXML
    void exibir(MouseEvent event) {
        App.pushScreen("PERFIL");
    }

    @FXML
    void gerarRota(ActionEvent event) {
        Linha selecionado = cbLinha.getSelectionModel().getSelectedItem();
        String imagem =  repositorioLinha.gerarRota(selecionado);
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
        repositorioViagem.iniciarViagem(cbPontoInicio.getValue(), cbPontoFinal.getValue());
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
}