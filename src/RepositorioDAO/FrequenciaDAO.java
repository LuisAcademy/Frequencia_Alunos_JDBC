package RepositorioDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConectionDataBase.Conection;
import Entidades.Aluno;
import Entidades.Frequencia;

public class FrequenciaDAO {

    public void cadastrarFrequencia(Frequencia frequencia) {
        String sql = "INSERT INTO FREQUENCIA (aluno_id, data, presente) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conection.getConexao();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, frequencia.getAluno().getId()); // Supondo que Aluno tenha um getId()
                ps.setDate(2, Date.valueOf(frequencia.getData()));
                ps.setBoolean(3, frequencia.isPresente());

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

    public void update(Frequencia frequencia) {
        String sql = "UPDATE FREQUENCIA SET aluno_id = ?, data = ?, presente = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conection.getConexao();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, frequencia.getAluno().getId());
            ps.setDate(2, Date.valueOf(frequencia.getData()));
            ps.setBoolean(3, frequencia.isPresente());
            ps.setInt(4, frequencia.getAluno().getId()); // Supondo que o ID seja o mesmo do aluno

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
        String sql = "DELETE FROM FREQUENCIA WHERE id = ?";

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

    public List<Frequencia> getFrequencias() {
        String sql = "SELECT * FROM FREQUENCIA";

        List<Frequencia> frequencias = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            conn = Conection.getConexao();
            ps = conn.prepareStatement(sql);

            rset = ps.executeQuery();

            while (rset.next()) {
                Aluno aluno = new Aluno(); // Supondo que Aluno tenha um construtor ou setters
                aluno.setId(rset.getInt("aluno_id"));

                Frequencia frequencia = new Frequencia(
                    aluno,
                    rset.getDate("data").toLocalDate(),
                    rset.getBoolean("presente")
                );

                frequencias.add(frequencia);
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
        return frequencias;
    }

    public Frequencia buscarFrequenciaPorID(int id) {
        String sql = "SELECT * FROM FREQUENCIA WHERE id = ?";

        Frequencia frequencia = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            conn = Conection.getConexao();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rset = ps.executeQuery();

            if (rset.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rset.getInt("aluno_id"));

                frequencia = new Frequencia(
                    aluno,
                    rset.getDate("data").toLocalDate(),
                    rset.getBoolean("presente")
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

        return frequencia;
    }
}
