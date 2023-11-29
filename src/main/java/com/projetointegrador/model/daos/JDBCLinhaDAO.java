package com.projetointegrador.model.daos;

import java.sql.*;
import java.util.ArrayList;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Linha;

public class JDBCLinhaDAO implements LinhaDAO {
    private static final String SELECTSQL = "SELECT * FROM Linha";

    private FabricaConexoes fabrica;

    public JDBCLinhaDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);
            ResultSet rs = pstm.executeQuery();
            ArrayList<Linha> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                Linha Linhas = new Linha(id, nome, null);
                lista.add(Linhas);
            }
            return Resultado.sucesso("Lista carregada!", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}