package Repositorios;

import Entidades.Frequencia;

import java.util.ArrayList;
import java.util.List;

public class RepositorioFrequencia {
    private List<Frequencia> frequencias = new ArrayList<>();

    public void adicionarFrequencia(Frequencia frequencia) {
        frequencias.add(frequencia);
        System.out.println("Frequência registrada para: " + frequencia.getAluno().getNome());
    }

    public List<Frequencia> listarFrequencias() {
        return frequencias;
    }

    
}