package com.projetointegrador.model.daos;

import java.sql.*;
import java.util.ArrayList;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Onibus;

public class JDBCOnibusDAO implements OnibusDAO {
    private static final String SELECTSQL = "SELECT * FROM PIOnibus";

    private FabricaConexoes fabrica;

    public JDBCOnibusDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado exibir() {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);
            ResultSet rs = pstm.executeQuery();
            ArrayList<Onibus> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("id");
                String placa = rs.getString("placa");
                String cor = rs.getString("cor");
                int qtdTotalPassageiro = rs.getInt("qtdTotalPassageiro");

                Onibus onibus = new Onibus(id, placa, cor, qtdTotalPassageiro);
                lista.add(onibus);
            }
            return Resultado.sucesso("Lista carregada!", lista);    
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }
}