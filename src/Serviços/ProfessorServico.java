package Serviços;

import RepositorioDAO.ProfessorDAO; // Importando o ProfessorDAO
import Entidades.Professor;
import java.util.List;

public class ProfessorServico {
    private ProfessorDAO professorDAO; // Alterando para utilizar ProfessorDAO
    private ServicoNotificacoes servicoNotificacoes; // Serviço de notificações

    // Modificando o construtor para incluir o ProfessorDAO e ServicoNotificacoes
    public ProfessorServico(ProfessorDAO professorDAO, ServicoNotificacoes servicoNotificacoes) {
        this.professorDAO = professorDAO;
        this.servicoNotificacoes = servicoNotificacoes;
    }

    public void adicionar(String nome, String disciplina, int salario) {
        // O ID será gerado pelo banco de dados, portanto, não é necessário gerar manualmente
        Professor novoProfessor = new Professor(nome, disciplina, salario);
        professorDAO.cadastrarProfessor(novoProfessor); // Usando o DAO para cadastrar o professor
        
        // Enviando notificação após adicionar professor
        servicoNotificacoes.adicionar("Novo professor adicionado: " + nome);
    }

    public List<Professor> listar() {
        return professorDAO.getProfessores(); // Obtendo lista de professores a partir do DAO
    }
    public void atualizar(int id, String nome, String disciplina, int salario) {
        // Criando o novo objeto Professor com os dados recebidos
        Professor novoProfessor = new Professor(nome, disciplina, salario);
        
        // Garantir que o ID seja atribuído corretamente ao novoProfessor
        novoProfessor.setId(id);
        
        // Usando o DAO para atualizar o professor no banco de dados
        professorDAO.update(novoProfessor); 
        
        // Enviando notificação após atualizar professor
        servicoNotificacoes.adicionar("Professor atualizado: " + novoProfessor.getNome());
    }


    public boolean remover(int id) {
        // Deleta o professor diretamente pelo ID, não precisa buscar antes
        professorDAO.deleteByID(id); // Deleta o professor usando o DAO
        
        // Enviando notificação após remover professor
        servicoNotificacoes.adicionar("Professor removido com ID: " + id);
        return true; // A operação de remoção foi realizada
    }

    private int gerarId() {
        // O ID é gerado automaticamente pelo banco de dados, então esse método não é necessário
        return -1; // Retorno provisório, pois o banco de dados se encarrega do ID
    }

    public void atualizar(int idAtualizar, Professor professorAtualizado) {
        // Criar o objeto Professor com o id correto
        professorAtualizado.setId(idAtualizar);
        
        // Chama o método update no DAO
        ProfessorDAO professorDAO = new ProfessorDAO();
        professorDAO.update(professorAtualizado);
    }

}
