package Entidades;

public class Aluno {
    private String nome;
    private int id;

    // Construtor padrão
    public Aluno() {
        this.nome = "";
        this.id = 0;
    }

    // Construtor com parâmetros
    public Aluno(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }
}
