package com.projetointegrador.model.daos;

import com.github.hugoperlin.results.Resultado;

public interface LinhaDAO {
    Resultado listar();

    Resultado buscarPontosPorLinha(int id);
}