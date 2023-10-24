package com.projetointegrador.model.repositories;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.daos.OnibusDAO;

public class RepositorioOnibus {
    private OnibusDAO onibusDAO;

    public RepositorioOnibus(OnibusDAO onibusDAO) {
        this.onibusDAO = onibusDAO;
    }

    public Resultado listar() {
        return onibusDAO.exibir();
    }
}