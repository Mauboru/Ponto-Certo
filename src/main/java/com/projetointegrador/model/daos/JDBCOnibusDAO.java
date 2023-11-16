package com.projetointegrador.model.daos;

import java.sql.*;
import java.util.ArrayList;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.*;

public class JDBCOnibusDAO implements OnibusDAO {
    private FabricaConexoes fabrica;

    public JDBCOnibusDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado getOnibus(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Onibus WHERE id=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            ArrayList<Onibus> lista = new ArrayList<>();

            if (rs.next()) {
                String placa = rs.getString("placa");
                String cor = rs.getString("cor");
                int qtdTotalPassageiro = rs.getInt("qtdTotalPassageiro");
                Onibus onibus = new Onibus(id, placa, cor, qtdTotalPassageiro);
                lista.add(onibus);
            }
            return Resultado.sucesso("Lista carregada!", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }   

    @Override
    public String getInfo(String email, String tipo) {
        try (Connection con = fabrica.getConnection()) {
            String GETINFO = "SELECT " + tipo + " FROM Onibus WHERE placa=?";
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