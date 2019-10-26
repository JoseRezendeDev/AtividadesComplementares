package DAO;

import Model.Aluno;
import Model.Professor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public List<Professor> getProfessores(){
        String sql = "SELECT * FROM professor";
        List<Professor> lista = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                lista.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Professor getProfessor(int codigo) {
        String sql = "SELECT * FROM professor WHERE id = ?";
        Professor professor = new Professor();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                return professor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
