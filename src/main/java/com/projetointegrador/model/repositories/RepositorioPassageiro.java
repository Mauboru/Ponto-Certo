package com.projetointegrador.model.repositories;

import java.util.*;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.daos.PassageiroDAO;
import com.projetointegrador.model.entities.Passageiro;

public class RepositorioPassageiro {
    private PassageiroDAO passageiroDAO;
    private ArrayList<Passageiro> passageiro;

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
}