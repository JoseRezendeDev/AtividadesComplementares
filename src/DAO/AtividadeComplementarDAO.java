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
    private ProfessorDAO professorDAO = new ProfessorDAO();
    private CategoriaACDAO categoriaACDAO = new CategoriaACDAO();
    private ItemCategoriaACDAO itemDAO = new ItemCategoriaACDAO();

    public void salvar(AtividadeComplementar atividadeComplementar) {
        String sql = "INSERT INTO atividade_complementar (codigo, carga_horaria, descricao, ano, semestre, aluno, professor, item_categoria_ac) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, atividadeComplementar.getCodigo());
            stmt.setDouble(2, atividadeComplementar.getCargaHoraria());
            stmt.setString(3, atividadeComplementar.getDescricao());
            stmt.setInt(4, atividadeComplementar.getAnoAC());
            stmt.setInt(5, atividadeComplementar.getSemestreAC());
            stmt.setString(6, atividadeComplementar.getAluno().getNumeroMatricula());
            stmt.setInt(7, atividadeComplementar.getProfessor().getId());
            stmt.setInt(8, atividadeComplementar.getItemCategoriaAC().getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AtividadeComplementar> getAtividades(String numeroMatricula) {
        String sql = "SELECT * FROM atividade_complementar WHERE aluno = ?";
        List<AtividadeComplementar> listaAtividades = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, numeroMatricula);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AtividadeComplementar atv = new AtividadeComplementar();
                atv.setCodigo(rs.getString("codigo"));
                atv.setCargaHoraria(rs.getDouble("carga_horaria"));
                atv.setSemestreAC(rs.getInt("semestre"));
                atv.setAnoAC(rs.getInt("ano"));
                atv.setDescricao(rs.getString("descricao"));
                atv.setAluno(alunoDAO.getAluno(rs.getString("aluno")));
                atv.setItemCategoriaAC(itemDAO.getItemCategoria(rs.getInt("item_categoria_ac")));
                atv.setProfessor(professorDAO.getProfessor(rs.getInt("professor")));
                listaAtividades.add(atv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAtividades;
    }

    public AtividadeComplementar getAtividade(String codigo) {
        String sql = "SELECT * FROM atividade_complementar WHERE codigo = ?";
        AtividadeComplementar atv = new AtividadeComplementar();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                atv.setCodigo(rs.getString("codigo"));
                atv.setCargaHoraria(rs.getDouble("carga_horaria"));
                atv.setDescricao(rs.getString("descricao"));
                atv.setAnoAC(rs.getInt("ano"));
                atv.setSemestreAC(rs.getInt("semestre"));
                atv.setAluno(alunoDAO.getAluno(rs.getString("aluno")));
                atv.setProfessor(professorDAO.getProfessor(rs.getInt("professor")));
                atv.setItemCategoriaAC(itemDAO.getItemCategoria(rs.getInt("item_categoria_ac")));
                return atv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double getSomaHorasPorItem(String aluno, Integer idItemCategoria) {
        String sql = "SELECT sum(carga_horaria) as horas FROM atividade_complementar WHERE aluno = ? and item_categoria_ac = ?";
        Double horas;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, aluno);
            stmt.setInt(2, idItemCategoria);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                horas = rs.getDouble("horas");
                return horas;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluirAtividade(String codigo){
        String sql = "DELETE FROM atividade_complementar WHERE codigo = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarAtividade(AtividadeComplementar atividadeComplementar){
        String sql = "update atividade_complementar set carga_horaria = ? , descricao = ?, ano = ?, semestre = ?, professor = ?, item_categoria_ac = ? where codigo = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setDouble(1, atividadeComplementar.getCargaHoraria());
            stmt.setString(2, atividadeComplementar.getDescricao());
            stmt.setInt(3, atividadeComplementar.getAnoAC());
            stmt.setInt(4, atividadeComplementar.getSemestreAC());
            stmt.setInt(5, atividadeComplementar.getProfessor().getId());
            stmt.setInt(6, atividadeComplementar.getItemCategoriaAC().getId());
            stmt.setString(7, atividadeComplementar.getCodigo());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
