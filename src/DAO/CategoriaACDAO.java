package DAO;

import Model.CategoriaAC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoriaACDAO {
    public void salvar (CategoriaAC categoriaAC) throws SQLException {
        String sql = "INSERT INTO categoria_ac VALUES (?, ?, ?)";
        PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
        stmt.setInt(1, categoriaAC.getId());
        stmt.setString(2, categoriaAC.getNome());
        stmt.setDouble(3, categoriaAC.getMaximoHoras());
        stmt.execute();
    }
}
