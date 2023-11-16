package com.projetointegrador.model.repositories;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.daos.*;

public class RepositorioOnibus {
    private OnibusDAO onibusDAO;

    public RepositorioOnibus(OnibusDAO onibusDAO) {
        this.onibusDAO = onibusDAO;
    }

    public Resultado getOnibus(){
        int id = Integer.parseInt(onibusDAO.getInfo("ABC1234", "id"));
        return onibusDAO.getOnibus(id);
    }
}