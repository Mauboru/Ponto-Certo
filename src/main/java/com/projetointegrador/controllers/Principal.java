package com.projetointegrador.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.App;
import com.projetointegrador.model.entities.Rota;
import com.projetointegrador.model.repositories.RepositorioRota;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Principal implements Initializable {
    RepositorioRota repositorioOnibus;

    @FXML
    private ComboBox<Rota> cbRota;

    @FXML
    private ImageView imgRotas;

    public Principal(RepositorioRota repositorioOnibus) {
        this.repositorioOnibus = repositorioOnibus;
    }

    @FXML
    void exibir(MouseEvent event) {
        App.pushScreen("MEUPERFIL");
    }

    @FXML
    void atualizaRota(ActionEvent event) {
        if(cbRota.getSelectionModel().getSelectedItem().getNome().equals("Porto Seguro")){
            imgRotas.setImage(new Image(getClass().getResource("/com/projetointegrador/img/porto-seguro.PNG").toExternalForm()));
        }

        if(cbRota.getSelectionModel().getSelectedItem().getNome().equals("Jardim Iguaçu")){
            imgRotas.setImage(new Image(getClass().getResource("/com/projetointegrador/img/jardim-iguacu.PNG").toExternalForm()));
        }

        if(cbRota.getSelectionModel().getSelectedItem().getNome().equals("Vila Garcia")){
            imgRotas.setImage(new Image(getClass().getResource("/com/projetointegrador/img/vila-garcia.PNG").toExternalForm()));
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado resultado = repositorioOnibus.listar();

        if (resultado.foiSucesso()) {
            List<Rota> list = (List) resultado.comoSucesso().getObj();
            cbRota.getItems().addAll(list);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }
    }
}