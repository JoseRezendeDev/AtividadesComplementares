package DAO;

import Model.Aluno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {
    public void salvar(Aluno aluno) throws SQLException {
        String sql = "CREATE DATABASE atividades";
        //String sql = "INSERT INTO aluno (nome) VALUES (?)";
        PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
        //stmt.setString(1, aluno.getNome());
        stmt.execute();
    }
}
