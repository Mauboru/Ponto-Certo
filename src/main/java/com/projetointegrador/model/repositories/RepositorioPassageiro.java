package com.projetointegrador.model.repositories;

import java.util.*;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.daos.PassageiroDAO;
import com.projetointegrador.model.entities.Passageiro;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RepositorioPassageiro {
    private PassageiroDAO passageiroDAO;
    private ArrayList<Passageiro> passageiro;
    private String email, nome, senha;

    public RepositorioPassageiro(PassageiroDAO passageiroDAO) {
        passageiro = new ArrayList<>();
        this.passageiroDAO = passageiroDAO;
    }

    public Resultado cadastrar(String nome, String email, String senha) {
        if (nome.isEmpty() || nome.isBlank())
            return Resultado.erro("Nome inválido!");
        if (email.isEmpty() || email.isBlank())
            return Resultado.erro("Email vazio, coloque seu email!");
        if (senha.isEmpty() || senha.isBlank())
            return Resultado.erro("Senha inválido!");

        // Verificação por Trigger do banco de dados
        Resultado resultado = passageiroDAO.verificaEmail(email);
        if (resultado.foiErro()) {
            return Resultado.erro(resultado.getMsg());
        } else {
            Passageiro passageiro = new Passageiro(nome, email, senha);
            return passageiroDAO.cadastrar(passageiro);
        }
    }

    public Resultado atualizar(int id, String nome, String email, String senha) {
        Passageiro passageiro = new Passageiro(nome, email, senha);
        return passageiroDAO.atualizar(id, passageiro);
    }

    public Resultado deletar(int id) {
        return passageiroDAO.deletar(id);
    }

    public Resultado login(String usuario, String senha) {
        if (usuario.isEmpty() || usuario.isBlank())
            return Resultado.erro("Insira um usuário!");
        if (senha.isEmpty() || senha.isBlank())
            return Resultado.erro("Insira uma senha!");

        return passageiroDAO.login(usuario, senha);
    }

    public Resultado enviarEmail(String email) {
        Resultado resultado = passageiroDAO.verificaEmail(email);
        
        if (resultado.getMsg().equals("E-mail já cadastrado!")) {
            Resultado resultadoSenha = passageiroDAO.getSenha(email);
            
            if (resultadoSenha.foiSucesso()) {
                String senha = (String) resultadoSenha.getMsg();
                boolean envioSucesso = enviarSenhaPorEmail(email, senha);

                if (envioSucesso) {
                    return Resultado.sucesso("Senha enviada com sucesso para o e-mail: ", email);
                } else {
                    return Resultado.erro("Falha ao enviar e-mail com a senha.");
                }
            } else {
                return Resultado.erro(resultadoSenha.getMsg());
            }
        }
        return resultado;
    }

    private boolean enviarSenhaPorEmail(String email, String senha) {
        return false;
    }

    public void saveLogin(String email) {
        this.email = email;
    }

    public String getLogin() {
        return email;
    }

    public Resultado getPassageiro() {
        int id = Integer.parseInt(getInfo(getLogin(), "id"));
        return passageiroDAO.getPassageiroLogado(id);
    }

    public String getInfo(String email, String tipo) {
        String resultado = passageiroDAO.getInfo(email, tipo);
        if (resultado.equals("Dados não encontrados!")) {
            Alert alerta = new Alert(AlertType.ERROR, resultado);
            alerta.show();
        }
        return resultado;
    }
}