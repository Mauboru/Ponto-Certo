package com.projetointegrador.model.repositories;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.daos.ViagemDAO;
import com.projetointegrador.model.entities.Ponto;

public class RepositorioViagem {
    private ViagemDAO viagemDAO;

    public RepositorioViagem(ViagemDAO viagemDAO) {
        this.viagemDAO = viagemDAO;
    }

    public Resultado iniciarViagem(Ponto pontoInicial, Ponto pontoFinal){
        if (pontoInicial == null || pontoFinal == null){
            return Resultado.erro("Selecion dois pontos!");
        }else{
            return viagemDAO.iniciar();
        }
    }
}