package com.projetointegrador;

import com.projetointegrador.controllers.*;
import com.projetointegrador.model.daos.*;
import com.projetointegrador.model.repositories.*;
import io.github.hugoperlin.navigatorfx.*;

public class App extends BaseAppNavigator {
    private PassageiroDAO passageiroDAO = new JDBCPassageiroDAO(FabricaConexoes.getInstance());
    private RepositorioPassageiro repositorioPassageiro = new RepositorioPassageiro(passageiroDAO);

    private OnibusDAO onibusDAO = new JDBCOnibusDAO(FabricaConexoes.getInstance());
    private RepositorioOnibus repositorioOnibus = new RepositorioOnibus(onibusDAO);
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getAppTitle() {
        return "Ponto-Certo";
    }

    @Override
    public String getHome() {
        //Verificar antes se o usuario ja n esta logado
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
        registraTela("PRINCIPAL",
                 new ScreenRegistryFXML(App.class,
                        "principal.fxml",
                        o -> new Principal(repositorioOnibus)));
    }
}