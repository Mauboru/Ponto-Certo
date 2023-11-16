package com.projetointegrador.model.repositories;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.daos.AvaliacaoDAO;

public class RepositorioAvaliacao {
    private AvaliacaoDAO avaliacaoDAO;

    public RepositorioAvaliacao(AvaliacaoDAO avaliacaoDAO) {
        this.avaliacaoDAO = avaliacaoDAO;
    }

    public Resultado getAvaliacao(){
        int id = Integer.parseInt(avaliacaoDAO.getInfo("10", "id"));
        return avaliacaoDAO.getAvaliacao(id);
    }
}