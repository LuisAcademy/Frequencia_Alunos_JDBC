package Repositorios;

import Entidades.Professor;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProfessor {
    private List<Professor> professores = new ArrayList<>();
    
    public void adicionar(Professor professor) {
        if (professor != null) {
            professores.add(professor);
            System.out.println("Professor adicionado: " + professor.getNome());
        } else {
            System.out.println("Não é possível adicionar um professor nulo.");
        }
    }

    public List<Professor> listar() {
        return professores;
    }

    public void atualizar(int id, Professor professorAtualizado) {
        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getId() == id) {
                professores.set(i, professorAtualizado);
                System.out.println("Professor atualizado: " + professorAtualizado.getNome());
                return;
            }
        }
        System.out.println("Professor com ID '" + id + "' não encontrado.");
    }

    public boolean remover(int id) { 
        return professores.removeIf(professor -> professor.getId() == id);
    }
}