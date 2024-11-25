package InterfaceRepositorio;

import Entidades.Professor;
import java.util.List;

public interface IprofessorRepositorio {
    void adicionarProfessor(Professor professor);
    List<Professor> listarProfessores();
    void atualizarProfessor(String nome, Professor professorAtualizado);
    void removerProfessor(String nome);
}