package com.projetointegrador.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.App;
import com.projetointegrador.model.entities.Onibus;
import com.projetointegrador.model.repositories.RepositorioOnibus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Principal implements Initializable{
    RepositorioOnibus repositorioOnibus;

    @FXML
    private ComboBox<Onibus> cbOnibus;

    @FXML
    private ImageView imgRotas;

    public Principal(RepositorioOnibus repositorioOnibus){
        this.repositorioOnibus = repositorioOnibus;
    }

    @FXML
    void exibir(MouseEvent event) {
        App.pushScreen("MEUPERFIL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado resultado = repositorioOnibus.listar();

        if (resultado.foiSucesso()) {
            List<Onibus> list = (List) resultado.comoSucesso().getObj();
            cbOnibus.getItems().addAll(list);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }
    }
}