package com.projetointegrador.model.daos;

import com.github.hugoperlin.results.Resultado;

public interface AvaliacaoDAO {
    Resultado getAvaliacao(int id);

    String getInfo(String email, String tipo);
}