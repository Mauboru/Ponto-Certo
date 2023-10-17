package com.projetointegrador;

import com.projetointegrador.controllers.Cadastrar;
import com.projetointegrador.model.repositories.RepositorioPassageiro;

import io.github.hugoperlin.navigatorfx.*;

public class App extends BaseAppNavigator {
    private RepositorioPassageiro repositorioPassageiro;
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getAppTitle() {
        return "Ponto-Certo";
    }

    @Override
    public String getHome() {
        return "CADASTRAR";
    }

    @Override
    public void registrarTelas() {
        registraTela("CADASTRAR",
                 new ScreenRegistryFXML(App.class,
                        "cadastrar.fxml",
                        o -> new Cadastrar(repositorioPassageiro)));
    }
}