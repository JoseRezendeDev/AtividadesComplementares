package DAO;

import Model.Aluno;
import Model.CategoriaAC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaACDAO {

    public void salvar(CategoriaAC categoriaAC){
        String sql = "INSERT INTO categoria_ac (nome) VALUES (?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, categoriaAC.getNome());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CategoriaAC> getCategorias(){
        String sql = "SELECT * FROM categoria_ac";
        List<CategoriaAC> lista = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CategoriaAC categoriaAC = new CategoriaAC();
                categoriaAC.setId(rs.getInt("id"));
                categoriaAC.setNome(rs.getString("nome"));
                lista.add(categoriaAC);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public CategoriaAC getCategoria(int id){
        String sql = "SELECT * FROM categoria_ac WHERE id = ?";
        CategoriaAC categoriaAC = new CategoriaAC();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                categoriaAC.setId(rs.getInt("id"));
                categoriaAC.setNome(rs.getString("nome"));
                return categoriaAC;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void limpar(){
        String sql = "DELETE FROM categoria_ac";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
