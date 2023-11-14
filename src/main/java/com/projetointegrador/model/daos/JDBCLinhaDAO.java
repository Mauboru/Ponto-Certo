package com.projetointegrador.model.daos;

import java.sql.*;
import java.util.ArrayList;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Linha;
import com.projetointegrador.model.entities.Ponto;

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

                Linha Linhas = new Linha(id, nome);
                lista.add(Linhas);
            }
            return Resultado.sucesso("Lista carregada!", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarPontosPorLinha(int idLinha) {
        try (Connection con = fabrica.getConnection()) {
            String sql = "SELECT Ponto.* FROM Ponto INNER JOIN LinhaPonto ON Ponto.id = LinhaPonto.idPonto INNER JOIN Linha ON LinhaPonto.idLinha = Linha.id WHERE Linha.id = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idLinha);
            ResultSet rs = pstm.executeQuery();
            ArrayList<Ponto> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Ponto ponto = new Ponto(id, nome);
                lista.add(ponto);
            }
            return Resultado.sucesso("Deu certo!", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}