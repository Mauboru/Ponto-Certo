package com.projetointegrador.model.daos;

import java.sql.*;
import java.util.ArrayList;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Ponto;

public class JDBCPontoDAO implements PontoDAO {
    private static final String SELECTSQL = "SELECT * FROM PIPonto";

    private FabricaConexoes fabrica;

    public JDBCPontoDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado listar() {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);
            ResultSet rs = pstm.executeQuery();
            ArrayList<Ponto> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                Ponto pontos = new Ponto(id, nome);
                lista.add(pontos);
            }
            return Resultado.sucesso("Lista carregada!", lista);    
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }
}