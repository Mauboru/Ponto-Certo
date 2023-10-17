package com.projetointegrador.model.daos;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Passageiro;

public interface PassageiroDAO {
    Resultado cadastrar(Passageiro passageiro);

    Resultado exibir();

    Resultado editar();

    Resultado desativar();
}