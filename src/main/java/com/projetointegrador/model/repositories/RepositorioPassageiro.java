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
            return Resultado.erro("Email inválido!");
        if (senha.isEmpty() || senha.isBlank())
            return Resultado.erro("Senha inválido!");

        Passageiro passageiro = new Passageiro(nome, email, senha);
        return passageiroDAO.cadastrar(passageiro);
    }

    public Resultado atualizar(int id, String nome, String email, String senha){
        Passageiro passageiro = new Passageiro(nome, email, senha);
        return passageiroDAO.atualizar(id, passageiro);
    }

    public Resultado deletar(int id){
        return passageiroDAO.deletar(id);
    }

    public Resultado login(String usuario, String senha){
        if (usuario.isEmpty() || usuario.isBlank())
            return Resultado.erro("Insira um usuário!");
        if (senha.isEmpty() || senha.isBlank())
            return Resultado.erro("Insira uma senha!");

        return passageiroDAO.login(usuario, senha);
    }

    public void saveLogin(String email){
        this.email = email;
    }

    public String getLogin(){
        return email;
    }

    public String getInfo(String email, String tipo){
        String resultado = passageiroDAO.getInfo(email, tipo);
        if (resultado.equals("Dados não encontrados!")){
            Alert alerta = new Alert(AlertType.ERROR, resultado);
            alerta.showAndWait();
        }
        return resultado;
    }
}