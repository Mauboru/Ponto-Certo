package com.projetointegrador;

import io.github.hugoperlin.navigatorfx.*;

public class App extends BaseAppNavigator {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getAppTitle() {
        return "Ponto-Certo";
    }

    @Override
    public String getHome() {
        return "LOGIN";
    }

    @Override
    public void registrarTelas() {
        registraTela("LOGIN",
                 new ScreenRegistryFXML(App.class,
                        "cadastrar.fxml",
                        o -> new Cadastrar()));
    }
}