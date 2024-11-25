package Entidades;

import java.time.LocalDate;

public class Frequencia {
    private Aluno aluno;
    private LocalDate data;
    private boolean presente; 

    public Frequencia(Aluno aluno, LocalDate data, boolean presente) {
        this.aluno = aluno;
        this.data = data;
        this.presente = presente;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isPresente() {
        return presente;
    }

    @Override
    public String toString() {
        return "Frequencia{" +
                "aluno=" + aluno +
                ", data=" + data +
                ", presente=" + presente +
                '}';
    }
}