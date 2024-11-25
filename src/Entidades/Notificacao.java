package Entidades;

public class Notificacao {
    private int id; // Identificador para o banco de dados
    private String mensagem;
    private boolean lida;

    // Construtores
    public Notificacao(int id, String mensagem, boolean lida) {
        this.id = id;
        this.mensagem = mensagem;
        this.lida = lida;
    }

    public Notificacao(String mensagem) {
        this.mensagem = mensagem;
        this.lida = false;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    // Método para marcar como lida
    public void marcarComoLida() {
        this.lida = true;
    }

    @Override
    public String toString() {
        return "Notificação{" +
                "id=" + id +
                ", mensagem='" + mensagem + '\'' +
                ", lida=" + (lida ? "Sim" : "Não") +
                '}';
    }
}
