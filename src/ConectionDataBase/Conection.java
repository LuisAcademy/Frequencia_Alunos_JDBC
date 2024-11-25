package ConectionDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import java.sql.Connection;
//import java.sql.DriverManager; // Importação do DriverManager
//import java.sql.SQLException;

public class Conection {
    
    private static final String url = "jdbc:mysql://localhost:3306/frequencia_aluno"; // Corrigi "localrost" para "localhost"
    private static final String user = "root";
    private static final String password = "estudarmysql";
    
    private static Connection conn;
    
    public static Connection getConexao() {
        try {
            if (conn == null || conn.isClosed()) { // Verifica se a conexão está fechada
                conn = DriverManager.getConnection(url, user, password);
            }
            return conn; // Corrigi o erro de digitação "retunr" para "return"
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}