package com.projetointegrador.model.repositories;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.daos.LinhaDAO;
import com.projetointegrador.model.entities.*;

public class RepositorioLinha {
    private LinhaDAO linhaDAO;

    public RepositorioLinha(LinhaDAO linhaDAO) {
        this.linhaDAO = linhaDAO;
    }

    public Resultado listar() {
        return linhaDAO.listar();
    }

    public String gerarRota(Linha linha){
        if (linha.getNome().equals("Porto Seguro")) {
            return "/com/projetointegrador/img/porto-seguro.PNG";
        }
        if (linha.getNome().equals("Jardim Iguaçu")) {
            return "/com/projetointegrador/img/jardim-iguacu.PNG";
        }
        if (linha.getNome().equals("Vila Garcia")) {
            return "/com/projetointegrador/img/vila-garcia.PNG";
        }
        return "Linha não encontrada!";
    }
}