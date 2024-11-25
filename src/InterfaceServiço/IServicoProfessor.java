package InterfaceServiço;

import Entidades.Professor;
import java.util.List;

public interface IServicoProfessor {
    void adicionar(Professor professor);
    List<Professor> listar();
    void atualizar(String nome, Professor professorAtualizado);
    void remover(String nome);
}