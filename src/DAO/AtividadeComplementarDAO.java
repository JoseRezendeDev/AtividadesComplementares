package DAO;

import Model.Aluno;
import Model.AtividadeComplementar;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AtividadeComplementarDAO {
    public void salvar(AtividadeComplementar atividadeComplementar) throws SQLException {
        String sql = "INSERT INTO atividade_complementar (codigo, descricao) VALUES (?, ?)";
        PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
        stmt.setString(1, atividadeComplementar.getCodigo());
        stmt.setString(2, atividadeComplementar.getDescricao());
        stmt.execute();
    }
}
