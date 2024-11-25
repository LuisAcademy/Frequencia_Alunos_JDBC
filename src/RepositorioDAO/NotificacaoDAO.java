package RepositorioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConectionDataBase.Conection;
import Entidades.Notificacao;

public class NotificacaoDAO {

    // Método para cadastrar uma notificação
    public void cadastrarNotificacao(Notificacao notificacao) {
        String sql = "INSERT INTO NOTIFICACAO (mensagem, lida) VALUES (?, ?)";

        try (Connection conn = Conection.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, notificacao.getMensagem());
            ps.setBoolean(2, notificacao.isLida());

            ps.executeUpdate();

            // Recuperar o ID gerado pelo banco de dados
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    notificacao.setId(rs.getInt(1)); // Atualiza o ID no objeto
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar notificação: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para atualizar uma notificação
    public void atualizarNotificacao(Notificacao notificacao) {
        String sql = "UPDATE NOTIFICACAO SET mensagem = ?, lida = ? WHERE id = ?";

        try (Connection conn = Conection.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, notificacao.getMensagem());
            ps.setBoolean(2, notificacao.isLida());
            ps.setInt(3, notificacao.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar notificação: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para deletar uma notificação pelo ID
    public void deletarNotificacao(int id) {
        String sql = "DELETE FROM NOTIFICACAO WHERE id = ?";

        try (Connection conn = Conection.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar notificação: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para buscar uma notificação pelo ID
    public Notificacao buscarNotificacaoPorId(int id) {
        String sql = "SELECT * FROM NOTIFICACAO WHERE id = ?";
        Notificacao notificacao = null;

        try (Connection conn = Conection.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    notificacao = new Notificacao(
                        rs.getInt("id"),
                        rs.getString("mensagem"),
                        rs.getBoolean("lida")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar notificação por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return notificacao;
    }

    // Método para listar todas as notificações
    public List<Notificacao> listarNotificacoes() {
        String sql = "SELECT * FROM NOTIFICACAO";
        List<Notificacao> notificacoes = new ArrayList<>();

        try (Connection conn = Conection.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Notificacao notificacao = new Notificacao(
                    rs.getInt("id"),
                    rs.getString("mensagem"),
                    rs.getBoolean("lida")
                );
                notificacoes.add(notificacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar notificações: " + e.getMessage());
            e.printStackTrace();
        }

        return notificacoes;
    }
}
