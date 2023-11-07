package com.projetointegrador.model.repositories;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.daos.RotaDAO;

public class RepositorioRota {
    private RotaDAO rotaDAO;

    public RepositorioRota(RotaDAO rotaDAO) {
        this.rotaDAO = rotaDAO;
    }

    public Resultado listar() {
        return rotaDAO.listar();
    }
}