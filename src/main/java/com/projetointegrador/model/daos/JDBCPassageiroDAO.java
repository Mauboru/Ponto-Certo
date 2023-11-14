package com.projetointegrador.model.daos;

import java.sql.*;
import java.util.ArrayList;
import com.github.hugoperlin.results.Resultado;
import com.projetointegrador.model.entities.Passageiro;

public class JDBCPassageiroDAO implements PassageiroDAO {
    private static final String INSERTSQL = "INSERT INTO Passageiro(nome, email, senha) VALUES (?, ?, ?)";
    private static final String SELECTSQL = "SELECT * FROM Passageiro";
    private static final String UPDATESQL = "UPDATE Passageiro SET nome=?, email=?, senha=? WHERE id=?";
    private static final String DELETESQL = "DELETE FROM Passageiro WHERE id=?";
    private static final String LOGINSQL = "SELECT nome FROM Passageiro WHERE email=? AND senha=?";

    private FabricaConexoes fabrica;

    public JDBCPassageiroDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado cadastrar(Passageiro passageiro) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(INSERTSQL);

            pstm.setString(1, passageiro.getNome());
            pstm.setString(2, passageiro.getEmail());
            pstm.setString(3, passageiro.getSenha());
            
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
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);
            ResultSet rs = pstm.executeQuery();
            ArrayList<Passageiro> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");

                Passageiro passageiro = new Passageiro(id, nome, email, senha);
                lista.add(passageiro);
            }
            return Resultado.sucesso("Lista carregada!", lista);    
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Passageiro novo) {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(UPDATESQL);

            pstm.setString(1, novo.getNome());
            pstm.setString(2, novo.getEmail());
            pstm.setString(3, novo.getSenha());
            pstm.setInt(4, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                novo.setId(id);
                return Resultado.sucesso("Passageiro editado!", novo);
            }
            return Resultado.erro("Erro desconhecido!");
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(DELETESQL);
            
            pstm.setInt(1, id);
            int ret = pstm.executeUpdate();

            if(ret == 1){
                return Resultado.sucesso("Passageiro deletado!", id);
            }
            return Resultado.erro("Erro desconhecido!");
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado login(String usuario, String senha){
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(LOGINSQL);

            pstm.setString(1, usuario);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                return Resultado.sucesso("Login feito com Sucesso!", senha);
            }
            return Resultado.erro("Usuario não encontrado!");
            
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public String getInfo(String email, String tipo) {
        try (Connection con = fabrica.getConnection()) {
            String GETINFO = "SELECT " + tipo + " FROM Passageiro WHERE email=?";
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