package com.projetointegrador;

import com.projetointegrador.controllers.Cadastrar;
import com.projetointegrador.controllers.Login;
import com.projetointegrador.model.daos.*;
import com.projetointegrador.model.repositories.RepositorioPassageiro;
import io.github.hugoperlin.navigatorfx.*;

public class App extends BaseAppNavigator {
    private PassageiroDAO passageiroDAO = new JDBCPassageiroDAO(FabricaConexoes.getInstance());
    private RepositorioPassageiro repositorioPassageiro = new RepositorioPassageiro(passageiroDAO);
    
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
                        "login.fxml", 
                        o -> new Login(repositorioPassageiro)));
        registraTela("CADASTRAR",
                 new ScreenRegistryFXML(App.class,
                        "cadastrar.fxml",
                        o -> new Cadastrar(repositorioPassageiro)));
    }
}