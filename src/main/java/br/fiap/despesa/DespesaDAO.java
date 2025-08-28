package br.fiap.despesa;

import br.fiap.conexao.Conexao;
import br.fiap.modelo.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {

    /// VARIAVEIS --------------------------
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;

    /// MÉTODOS -----------------------
    public void inserir (Despesa despesa){
        sql = "insert INTO java_despesa VALUES (seqd.netval, ?, ?, ?, ?)";

        try(Connection connection = Conexao.conectar()){
            ps = connection.prepareStatement(sql);
            //ps.setLong(1,despesa.getId());
            ps.setString(1, despesa.getDescricao());
            ps.setDouble(2,despesa.getValor());
            ps.setDate(3, Date.valueOf(despesa.getData()));
            ps.setLong(4,despesa.getCategoria().getId());
            ps.execute();
        }
        catch(SQLException e){
            System.out.println("ERRO ao inserir despesa\n" + e);
        }
    }

    public List<Despesa> listar(){
        List<Despesa> lista = new ArrayList<>();
        sql = "select * FROM java_despesa";
        try(Connection connection = Conexao.conectar()){
            ps = connection.prepareStatement(sql);
            rs =  ps.executeQuery();

          while (rs.next()){
              Despesa despesa = new Despesa();
              despesa.setId(rs.getLong("id_despesa"));
              despesa.setDescricao(rs.getString("descricao"));
              despesa.setValor(rs.getDouble("valor"));
              despesa.setCategoria(new Categoria(rs.getLong("id_categoria"),""));
              despesa.setData(rs.getDate("data").toLocalDate());
              lista.add(despesa);
          }
        }
        catch(SQLException e){
            System.out.println("ERRO ao listar despesa\n" + e);
        }
        return lista;
    }

    public List<Despesa> relatorio(){
        List<Despesa> lista = new ArrayList<>();
        sql = "SELECT d.descricao, d.valor, d.data, c.categoria\n" +
                "FROM java_despesa d\n" +
                "INNER JOIN java_categoria c\n" +
                "ON d.id_categoria = c.id_categoria";
        try(Connection connection = Conexao.conectar()){
            ps = connection.prepareStatement(sql);
            rs =  ps.executeQuery();

            while (rs.next()){
                Despesa despesa = new Despesa();
                despesa.setDescricao(rs.getString("descricao"));
                despesa.setValor(rs.getDouble("valor"));
                despesa.setCategoria(new Categoria(0L, rs.getString("categoria")));
                despesa.setData(rs.getDate("data").toLocalDate());
                lista.add(despesa);
            }
        }
        catch(SQLException e){
            System.out.println("ERRO ao gerar relatório das despesas\n" + e);
        }
        return lista;
    }
}///////////////////////////
