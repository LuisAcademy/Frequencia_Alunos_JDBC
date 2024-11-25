package Serviços;

import Entidades.Notificacao;
import RepositorioDAO.NotificacaoDAO;

import java.util.List;

public class ServicoNotificacoes {
    private final NotificacaoDAO notificacaoDAO;

    public ServicoNotificacoes(NotificacaoDAO notificacaoDAO) {
        this.notificacaoDAO = notificacaoDAO;
    }

    // Método para adicionar uma nova notificação
    public void adicionar(String mensagem) {
        Notificacao notificacao = new Notificacao(mensagem);
        notificacaoDAO.cadastrarNotificacao(notificacao); // Chama o DAO para persistir a notificação
    }

    // Método para listar todas as notificações
    public List<Notificacao> listar() {
        return notificacaoDAO.listarNotificacoes(); // Chama o DAO para buscar as notificações
    }

    // Método para atualizar uma notificação
    public void atualizar(Notificacao notificacao) {
        notificacaoDAO.atualizarNotificacao(notificacao); // Chama o DAO para atualizar
    }

    // Método para deletar uma notificação pelo ID
    public void remover(int id) {
        notificacaoDAO.deletarNotificacao(id); // Chama o DAO para deletar
    }
}
