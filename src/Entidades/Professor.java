package Entidades;

public class Professor {
    private String nome;
    private String disciplina;
    private int salario;
    private int id; 

    // Construtor com id, para uso quando o id for conhecido
    public Professor(int id, String nome, String disciplina, int salario) {
        this.id = id;
        this.nome = nome;
        this.disciplina = disciplina;
        this.salario = salario;
    }

    // Construtor sem id, para ser usado antes do banco gerar o id
    public Professor(String nome, String disciplina, int salario) {
        this.nome = nome;
        this.disciplina = disciplina;
        this.salario = salario;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public int getSalario() {
        return salario;
    }

    // Setter para id, caso precise definir o id depois de salvar no banco
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", disciplina='" + disciplina + '\'' +
                ", salario=" + salario +
                '}';
    }
}
