package com.projetointegrador.model.repositories;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.daos.PontoDAO;

public class RepositorioPonto {
    private PontoDAO pontoDAO;

    public RepositorioPonto(PontoDAO pontoDAO) {
        this.pontoDAO = pontoDAO;
    }

    public Resultado listar() {
        return pontoDAO.listar();
    }

    public Resultado buscarPontosPorLinha(int id){
        return pontoDAO.buscarPontosPorLinha(id);
    }
}