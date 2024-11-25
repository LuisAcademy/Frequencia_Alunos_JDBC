package Serviços;

import Entidades.Frequencia;
import RepositorioDAO.FrequenciaDAO;
import java.util.List;

public class ServicoFrequencia {
    private final FrequenciaDAO frequenciaDAO;

    // Construtor que recebe o DAO
    public ServicoFrequencia(FrequenciaDAO frequenciaDAO) {
        this.frequenciaDAO = frequenciaDAO;
    }

    // Método para adicionar uma nova frequência
    public void adicionar(Frequencia frequencia) {
        if (frequencia != null) {
            frequenciaDAO.cadastrarFrequencia(frequencia);
        } else {
            System.out.println("Não é possível adicionar uma frequência nula.");
        }
    }

    // Método para listar todas as frequências
    public List<Frequencia> listar() {
        return frequenciaDAO.getFrequencias();
    }

    // Método para atualizar uma frequência
    public void atualizar(Frequencia frequencia) {
        if (frequencia != null) {
            frequenciaDAO.update(frequencia);
        } else {
            System.out.println("Não é possível atualizar uma frequência nula.");
        }
    }

    // Método para deletar uma frequência por ID
    public void deletar(int id) {
        if (id > 0) {
            frequenciaDAO.deleteByID(id);
        } else {
            System.out.println("ID inválido.");
        }
    }

    // Método para buscar uma frequência por ID
    public Frequencia buscarPorId(int id) {
        if (id > 0) {
            return frequenciaDAO.buscarFrequenciaPorID(id);
        } else {
            System.out.println("ID inválido.");
            return null;
        }
    }
}
