package DAO;

import Model.Aluno;
import Model.AtividadeComplementar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtividadeComplementarDAO {
    private AlunoDAO alunoDAO = new AlunoDAO();

    public void salvar(AtividadeComplementar atividadeComplementar) {
        String sql = "INSERT INTO atividade_complementar (codigo, carga_horaria, descricao, ano, semestre, aluno) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, atividadeComplementar.getCodigo());
            stmt.setDouble(2, atividadeComplementar.getCargaHoraria());
            stmt.setString(3, atividadeComplementar.getDescricao());
            stmt.setInt(4, atividadeComplementar.getAnoAC());
            stmt.setInt(5, atividadeComplementar.getSemestreAC());
            stmt.setString(6, atividadeComplementar.getAluno().getNumeroMatricula());
            //stmt.setInt(7, atividadeComplementar.getProfessor().getId());
            //stmt.setInt(8, atividadeComplementar.getCategoriaAC().getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limpar(){
        String sql = "DELETE FROM atividade_complementar";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AtividadeComplementar> getAtividades(String numeroMatricula){
        String sql = "SELECT * FROM atividade_complementar WHERE aluno = ?";
        List<AtividadeComplementar> listaAtividades = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, numeroMatricula);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                AtividadeComplementar atv = new AtividadeComplementar();
                atv.setCodigo(rs.getString("codigo"));
                atv.setCargaHoraria(rs.getDouble("carga_horaria"));
                atv.setSemestreAC(rs.getInt("semestre"));
                atv.setAnoAC(rs.getInt("ano"));
                atv.setDescricao(rs.getString("descricao"));
                atv.setAluno(alunoDAO.getAluno(rs.getString("aluno")));

                listaAtividades.add(atv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAtividades;
    }
}
