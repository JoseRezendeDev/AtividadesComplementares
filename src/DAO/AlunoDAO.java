package DAO;

import Model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, email, telefone, numero_matricula, ano_ingresso, semestre_ingresso) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
        try {
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
        PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
        List<Aluno> alunos = new ArrayList<>();
        try {
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

    public void limpar(){
        String sql = "DELETE FROM aluno";
        PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
        try {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
