package com.projetointegrador.model.daos;

import java.sql.*;
import java.util.ArrayList;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Rota;

public class JDBCRotaDAO implements RotaDAO {
    private static final String SELECTSQL = "SELECT * FROM PIRota";

    private FabricaConexoes fabrica;

    public JDBCRotaDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado listar() {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);
            ResultSet rs = pstm.executeQuery();
            ArrayList<Rota> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                Rota rotas = new Rota(id, nome);
                lista.add(rotas);
            }
            return Resultado.sucesso("Lista carregada!", lista);    
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }
}