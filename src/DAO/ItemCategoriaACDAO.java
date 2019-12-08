package DAO;

import Model.CategoriaAC;
import Model.ItemCategoriaAC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCategoriaACDAO {
    private CategoriaACDAO categoriaACDAO;

    public ItemCategoriaACDAO(){
        this.categoriaACDAO = new CategoriaACDAO();
    }

    public void salvar(ItemCategoriaAC itemCategoriaAC) {
        String sql = "INSERT INTO item_categoria_ac (nome, maximo_horas, categoria_ac, numero_tabela, id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, itemCategoriaAC.getNome());
            stmt.setDouble(2, itemCategoriaAC.getMaximoHoras());
            stmt.setInt(3, itemCategoriaAC.getCategoriaAC().getId());
            stmt.setInt(4, itemCategoriaAC.getNumeroTabela());
            stmt.setInt(5, itemCategoriaAC.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemCategoriaAC> getTodosItensCategoria(){
        String sql = "SELECT * FROM item_categoria_ac";
        List<ItemCategoriaAC> lista = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItemCategoriaAC item = new ItemCategoriaAC();
                item.setId(rs.getInt("id"));
                item.setNome(rs.getString("nome"));
                item.setMaximoHoras(rs.getDouble("maximo_horas"));
                item.setNumeroTabela(rs.getInt("numero_tabela"));
                item.setCategoriaAC(categoriaACDAO.getCategoria(rs.getInt("categoria_ac")));
                lista.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<ItemCategoriaAC> getItensCategoria(int numeroTabela){
        String sql = "SELECT * FROM item_categoria_ac WHERE numero_tabela = ?";
        List<ItemCategoriaAC> lista = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, numeroTabela);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItemCategoriaAC item = new ItemCategoriaAC();
                item.setId(rs.getInt("id"));
                item.setNome(rs.getString("nome"));
                item.setMaximoHoras(rs.getDouble("maximo_horas"));
                item.setNumeroTabela(rs.getInt("numero_tabela"));
                item.setCategoriaAC(categoriaACDAO.getCategoria(rs.getInt("categoria_ac")));
                lista.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ItemCategoriaAC getItemCategoria(int id){
        String sql = "SELECT * FROM item_categoria_ac WHERE id = ?";
        ItemCategoriaAC item = new ItemCategoriaAC();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                item.setId(rs.getInt("id"));
                item.setNome(rs.getString("nome"));
                item.setMaximoHoras(rs.getDouble("maximo_horas"));
                item.setNumeroTabela(rs.getInt("numero_tabela"));
                item.setCategoriaAC(categoriaACDAO.getCategoria(rs.getInt("categoria_ac")));
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void limpar(){
        String sql = "DELETE FROM item_categoria_ac";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
