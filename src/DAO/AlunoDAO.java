package DAO;

import Model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlunoDAO {
    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, numero_matricula, ano_ingresso, semestre_ingresso, horas_cumpridas, graduando) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getNumeroMatricula());
            stmt.setInt(3, aluno.getAnoIngresso());
            stmt.setInt(4, aluno.getSemestreIngresso());
            stmt.setDouble(5, aluno.getHorasCumpridas());
            stmt.setInt(6, aluno.isGraduando() ? 1 : 0);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Aluno> getAlunosAsMap(){
        String sql = "SELECT * FROM aluno";
        Map<String, Aluno> alunos = new HashMap<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Aluno aluno = new Aluno();
                aluno.setNome(rs.getString("nome"));
                aluno.setAnoIngresso(rs.getInt("ano_ingresso"));
                aluno.setSemestreIngresso(rs.getInt("semestre_ingresso"));
                aluno.setNumeroMatricula(rs.getString("numero_matricula"));
                aluno.setHorasCumpridas(rs.getDouble("horas_cumpridas"));
                aluno.setGraduando(rs.getInt("graduando") == 1);
                alunos.put(aluno.getNumeroMatricula(), aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public List<Aluno> getAlunosAsList(){
        String sql = "SELECT * FROM aluno";
        List<Aluno> alunos = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Aluno aluno = new Aluno();
                aluno.setNome(rs.getString("nome"));
                aluno.setAnoIngresso(rs.getInt("ano_ingresso"));
                aluno.setSemestreIngresso(rs.getInt("semestre_ingresso"));
                aluno.setNumeroMatricula(rs.getString("numero_matricula"));
                aluno.setHorasCumpridas(rs.getDouble("horas_cumpridas"));
                aluno.setGraduando(rs.getInt("graduando") == 1);
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
                aluno.setAnoIngresso(rs.getInt("ano_ingresso"));
                aluno.setSemestreIngresso(rs.getInt("semestre_ingresso"));
                aluno.setNumeroMatricula(rs.getString("numero_matricula"));
                aluno.setHorasCumpridas(rs.getDouble("horas_cumpridas"));
                aluno.setGraduando(rs.getInt("graduando") == 1);
                return aluno;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remover(String prontuario){
        String sql = "UPDATE aluno SET graduando = 0 WHERE numero_matricula = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, prontuario);
            stmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void reativar(String prontuario){
        String sql = "UPDATE aluno SET graduando = 1 WHERE numero_matricula = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, prontuario);
            stmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private double getHorasCumpridas(String numeroMatricula) {
        String sql = "SELECT SUM(carga_horaria) FROM atividade_complementar WHERE aluno = ?";
        double horasCumpridas = 0;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, numeroMatricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                horasCumpridas = rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horasCumpridas;
    }

    public void setHorasCumpridas(String numeroMatricula){
        String sql = "UPDATE aluno SET horas_cumpridas = ? WHERE numero_matricula = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, numeroMatricula);
            stmt.setDouble(2, getHorasCumpridas(numeroMatricula));
            stmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
