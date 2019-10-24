package DAO;

import Model.Aluno;
import Model.AtividadeComplementar;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AtividadeComplementarDAO {
    public void salvar(AtividadeComplementar atividadeComplementar) throws SQLException {
        String sql = "INSERT INTO atividade_complementar VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
        stmt.setString(1, atividadeComplementar.getCodigo());
        stmt.setDouble(2, atividadeComplementar.getCargaHoraria());
        stmt.setString(3, atividadeComplementar.getDescricao());
        stmt.setInt(4, atividadeComplementar.getAnoAC());
        stmt.setInt(5, atividadeComplementar.getSemestreAC());
        //stmt.setString(6, atividadeComplementar.getAluno().getNumeroMatricula());
        stmt.setInt(7, atividadeComplementar.getProfessor().getId());
        stmt.setInt(8, atividadeComplementar.getCategoriaAC().getId());
        stmt.execute();
    }
}
