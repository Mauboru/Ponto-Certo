package com.projetointegrador.model.daos;

import java.sql.*;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Viagem;

public class JDBCViagemDAO implements ViagemDAO {
    private static final String INSERTSQL = "INSERT INTO Viagem(idPassageiro, idAvaliacao, idOnibus, idRota, pontoInicial, pontoFinal) VALUES (?, ?, ?, ?, ?, ?)";

    private FabricaConexoes fabrica;

    public JDBCViagemDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado cadastrarViagem(Viagem viagem) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);

            System.out.println(viagem.getPassageiro().getId());
            System.out.println(viagem.getAvaliacao().getId());
            System.out.println(viagem.getOnibus().getId());

            pstm.setInt(1, viagem.getPassageiro().getId());
            pstm.setInt(2, viagem.getAvaliacao().getId());
            pstm.setInt(3, viagem.getOnibus().getId());
            pstm.setInt(4, viagem.getRota().getId());
            pstm.setInt(5, viagem.getPontoInicial().getId());
            pstm.setInt(6, viagem.getPontoFinal().getId());
            
            int ret = pstm.executeUpdate();

            pstm.close();
            con.close();

            if (ret == 1) {
                return Resultado.sucesso("Viagem Cadastrada!", viagem);
            } else {
                return Resultado.erro("Erro não identificado!");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}