package RepositorioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConectionDataBase.Conection;
import Entidades.Professor;

public class ProfessorDAO {

    public void cadastrarProfessor(Professor professor) {
        String sql = "INSERT INTO PROFESSOR (nome, disciplina, salario) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conection.getConexao();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, professor.getNome());
                ps.setString(2, professor.getDisciplina());
                ps.setInt(3, professor.getSalario());

                ps.executeUpdate();
            } else {
                System.out.println("Falha ao obter conexão.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(Professor professor) {
        String sql = "UPDATE PROFESSOR SET nome = ?, disciplina = ?, salario = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conection.getConexao();
            ps = conn.prepareStatement(sql);
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getDisciplina());
            ps.setInt(3, professor.getSalario());
            ps.setInt(4, professor.getId());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteByID(int id) {
        String sql = "DELETE FROM PROFESSOR WHERE id = ?";

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conection.getConexao();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Professor> getProfessores() {
        String sql = "SELECT * FROM PROFESSOR";

        List<Professor> professores = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            conn = Conection.getConexao();
            ps = conn.prepareStatement(sql);

            rset = ps.executeQuery();

            while (rset.next()) {
                Professor professor = new Professor(
                    rset.getInt("id"),
                    rset.getString("nome"),
                    rset.getString("disciplina"),
                    rset.getInt("salario")
                );

                professores.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return professores;
    }

    public Professor buscarProfessorPorNome(String nome) {
        String sql = "SELECT * FROM PROFESSOR WHERE nome = ?";

        Professor professor = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            conn = Conection.getConexao();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            rset = ps.executeQuery();

            if (rset.next()) {
                professor = new Professor(
                    rset.getInt("id"),
                    rset.getString("nome"),
                    rset.getString("disciplina"),
                    rset.getInt("salario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return professor;
    }
}
