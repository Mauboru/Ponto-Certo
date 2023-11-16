package com.projetointegrador.model.daos;

import java.sql.*;
import java.util.ArrayList;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.*;

public class JDBCAvaliacaoDAO implements AvaliacaoDAO {
    private FabricaConexoes fabrica;

    public JDBCAvaliacaoDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado getAvaliacao(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Avaliacao WHERE id=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            ArrayList<Avaliacao> lista = new ArrayList<>();

            if (rs.next()) {
                int nota = rs.getInt("nota");
                String comentario = rs.getString("comentario");
                Avaliacao avaliacao = new Avaliacao(id, nota, comentario);
                lista.add(avaliacao);
            }
            return Resultado.sucesso("Lista carregada!", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }   

    @Override
    public String getInfo(String email, String tipo) {
        try (Connection con = fabrica.getConnection()) {
            String GETINFO = "SELECT " + tipo + " FROM Avaliacao WHERE nota=?";
            PreparedStatement pstm = con.prepareStatement(GETINFO);

            pstm.setString(1, email);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getString(tipo);
            }
            return "Dados não encontrados!";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}