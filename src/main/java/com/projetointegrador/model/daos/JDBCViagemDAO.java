package com.projetointegrador.model.daos;

import com.github.hugoperlin.results.Resultado;

public class JDBCViagemDAO implements ViagemDAO {
    private FabricaConexoes fabrica;

    public JDBCViagemDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado iniciar() {
        return null;
    }
}