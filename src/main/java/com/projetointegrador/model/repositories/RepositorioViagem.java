package com.projetointegrador.model.repositories;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.daos.ViagemDAO;
import com.projetointegrador.model.entities.*;

public class RepositorioViagem {
    private ViagemDAO viagemDAO;

    public RepositorioViagem(ViagemDAO viagemDAO) {
        this.viagemDAO = viagemDAO;
    }

    public Resultado iniciarViagem(Linha linha, Ponto pontoInicial, Ponto pontoFinal){
        if (linha == null){
            return Resultado.erro("Selecione uma linha!");
        }
        if (pontoInicial == null || pontoFinal == null){
            return Resultado.erro("Selecione dois pontos!");
        }
        if(pontoInicial == pontoFinal){
            return Resultado.erro("Não selecione dois pontos iguais!");
        }
        else{
            return viagemDAO.cadastrarViagem();
        }
    }
}