package com.projetointegrador;

import com.projetointegrador.controllers.*;
import com.projetointegrador.model.daos.*;
import com.projetointegrador.model.entities.Passageiro;
import com.projetointegrador.model.repositories.*;
import io.github.hugoperlin.navigatorfx.*;

public class App extends BaseAppNavigator {
    private PassageiroDAO passageiroDAO = new JDBCPassageiroDAO(FabricaConexoes.getInstance());
    private RepositorioPassageiro repositorioPassageiro = new RepositorioPassageiro(passageiroDAO);

    private LinhaDAO LinhaDAO = new JDBCLinhaDAO(FabricaConexoes.getInstance());
    private RepositorioLinha repositorioLinha = new RepositorioLinha(LinhaDAO);

    private PontoDAO pontoDAO = new JDBCPontoDAO(FabricaConexoes.getInstance());
    private RepositorioPonto repositorioPonto = new RepositorioPonto(pontoDAO);

    private ViagemDAO viagemDAO = new JDBCViagemDAO(FabricaConexoes.getInstance());
    private RepositorioViagem repositorioViagem = new RepositorioViagem(viagemDAO);

    private OnibusDAO onibusDAO = new JDBCOnibusDAO(FabricaConexoes.getInstance());
    private RepositorioOnibus repositorioOnibus = new RepositorioOnibus(onibusDAO);

    private AvaliacaoDAO avaliacaoDAO = new JDBCAvaliacaoDAO(FabricaConexoes.getInstance());
    private RepositorioAvaliacao repositorioAvaliacao = new RepositorioAvaliacao(avaliacaoDAO);

    private Passageiro passageiro;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getAppTitle() {
        return "Ponto-Certo";
    }

    @Override
    public String getHome() {
        // Verificar antes se o usuario ja n esta logado
        return "LOGIN";
    }

    @Override
    public void registrarTelas() {
        registraTela("LOGIN",
                new ScreenRegistryFXML(App.class,
                        "login.fxml",
                        o -> new Login(repositorioPassageiro, passageiro)));
        registraTela("CADASTRAR",
                new ScreenRegistryFXML(App.class,
                        "cadastrar.fxml",
                        o -> new Cadastrar(repositorioPassageiro, passageiro)));
        registraTela("PRINCIPAL",
                new ScreenRegistryFXML(App.class,
                        "principal.fxml",
                        o -> new Principal(repositorioLinha, repositorioPonto, repositorioViagem, repositorioPassageiro,
                        repositorioOnibus, repositorioAvaliacao, passageiro)));
        registraTela("PERFIL",
                new ScreenRegistryFXML(App.class,
                        "perfil.fxml",
                        o -> new Perfil(repositorioPassageiro, passageiro)));
    }
}