package com.projetointegrador.model.daos;

import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Viagem;

public interface ViagemDAO {
    Resultado cadastrarViagem(Viagem viagem);
}