package com.projetointegrador.model.daos;

import java.sql.*;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Passageiro;

public class JDBCPassageiroDAO implements PassageiroDAO {
    private static final String INSERTSQL = "INSERT INTO PIPassageiro(nome, email, senha) VALUES (?, ?, ?)";
    // private static final String SELECTSQL = "SELECT * FROM passageiro";

    private FabricaConexoes fabrica;

    public JDBCPassageiroDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado cadastrar(Passageiro passageiro) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(INSERTSQL);

            pstm.setString(1, passageiro.getNome());
            pstm.setString(3, passageiro.getEmail());
            pstm.setString(5, passageiro.getSenha());
            
            int ret = pstm.executeUpdate();

            pstm.close();
            con.close();

            if (ret == 1) {
                return Resultado.sucesso("Passageiro Cadastrado!", passageiro);
            } else {
                return Resultado.erro("Erro não identificado!");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado exibir() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exibir'");
    }

    @Override
    public Resultado editar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    @Override
    public Resultado desativar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desativar'");
    }
}