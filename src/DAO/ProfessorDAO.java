package DAO;

import Model.Professor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfessorDAO {
    public void salvar(Professor professor) throws SQLException {
        String sql = "INSERT INTO professor VALUES (?, ?)";
        PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
        stmt.setInt(1, professor.getId());
        stmt.setString(2, professor.getNome());
        stmt.execute();
    }
}
