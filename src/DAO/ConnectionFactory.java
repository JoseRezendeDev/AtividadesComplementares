package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory implements AutoCloseable {
    private static Connection connection;
    private static PreparedStatement statement;

    private static Connection createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/atividades.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static PreparedStatement createPreparedStatement(String sql) {
        try {
            statement = createConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
            if (statement != null)
                statement.close();
        }
    }
}
