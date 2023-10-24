package com.projetointegrador.controllers;

import com.projetointegrador.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Principal {

    @FXML
    private ComboBox<?> cbOnibus;

    @FXML
    private ImageView imgRotas;

    @FXML
    void exibir(MouseEvent event) {
        App.pushScreen("MEUPERFIL");
    }
}