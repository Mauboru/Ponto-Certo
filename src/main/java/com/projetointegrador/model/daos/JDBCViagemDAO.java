package com.projetointegrador.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.github.hugoperlin.results.Resultado;

public class JDBCViagemDAO implements ViagemDAO {
    private static final String INSERTSQL = "INSERT INTO Viagem(nome, email, senha) VALUES (?, ?, ?)";

    private FabricaConexoes fabrica;

    public JDBCViagemDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado cadastrarViagem(Viagem viagem) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(INSERTSQL);

            pstm.setString(1, viagem.getNome());
            pstm.setString(2, viagem.getEmail());
            pstm.setString(3, viagem.getSenha());
            
            int ret = pstm.executeUpdate();

            pstm.close();
            con.close();

            if (ret == 1) {
                return Resultado.sucesso("Passageiro Cadastrado!", viagem);
            } else {
                return Resultado.erro("Erro não identificado!");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}