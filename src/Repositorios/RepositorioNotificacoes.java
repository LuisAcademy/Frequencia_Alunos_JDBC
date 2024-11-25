package Repositorios;

import Entidades.Notificacao;
import java.util.ArrayList;
import java.util.List;

public class RepositorioNotificacoes {
    private List<Notificacao> notificacoes = new ArrayList<>();

    public void adicionarNotificacao(Notificacao notificacao) {
        notificacoes.add(notificacao);
        System.out.println("Notificação adicionada: " + notificacao.getMensagem());
    }

    public List<Notificacao> listarNotificacoes() {
        return notificacoes;
    }
}