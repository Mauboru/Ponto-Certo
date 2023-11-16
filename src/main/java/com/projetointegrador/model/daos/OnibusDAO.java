package com.projetointegrador.model.daos;

import com.github.hugoperlin.results.Resultado;

public interface OnibusDAO {
    Resultado getOnibus(int id);

    String getInfo(String email, String tipo);
}