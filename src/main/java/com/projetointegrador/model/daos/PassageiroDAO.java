package com.projetointegrador.model.daos;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Passageiro;

public interface PassageiroDAO {
    Resultado cadastrar(Passageiro passageiro);

    Resultado exibir();

    Resultado atualizar(int id, Passageiro novo);

    Resultado deletar(int id);

    Resultado verificaEmail(String email);

    Resultado getSenha(String email);

    Resultado login(String usuario, String senha);

    Resultado getPassageiroLogado(int id);

    String getInfo(String email, String tipo);
}