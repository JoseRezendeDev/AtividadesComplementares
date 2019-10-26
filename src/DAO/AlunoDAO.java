package DAO;

import Model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, email, telefone, numero_matricula, ano_ingresso, semestre_ingresso) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.setString(4, aluno.getNumeroMatricula());
            stmt.setInt(5, aluno.getAnoIngresso());
            stmt.setInt(6, aluno.getSemestreIngresso());
            //stmt.setDouble(7, aluno.getHorasCumpridas());
            //stmt.setString(8, aluno.getStatus().toString());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> getAlunos(){
        String sql = "SELECT * FROM aluno";
        List<Aluno> alunos = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Aluno aluno = new Aluno();
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setAnoIngresso(rs.getInt("ano_ingresso"));
                aluno.setSemestreIngresso(rs.getInt("semestre_ingresso"));
                aluno.setNumeroMatricula(rs.getString("numero_matricula"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public Aluno getAluno(String numeroMatricula){
        String sql = "SELECT * FROM aluno WHERE numero_matricula = ?";
        Aluno aluno = new Aluno();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, numeroMatricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setAnoIngresso(rs.getInt("ano_ingresso"));
                aluno.setSemestreIngresso(rs.getInt("semestre_ingresso"));
                aluno.setNumeroMatricula(rs.getString("numero_matricula"));
                return aluno;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void limpar(){
        String sql = "DELETE FROM aluno";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
