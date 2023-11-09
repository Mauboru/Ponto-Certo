package com.projetointegrador.model.daos;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Passageiro;

public interface PassageiroDAO {
    Resultado cadastrar(Passageiro passageiro);

    Resultado exibir();

    Resultado atualizar(int id, Passageiro novo);

    Resultado deletar(int id);

    Resultado login(String usuario, String senha);

    String getInfo(String email, String tipo);
}