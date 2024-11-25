package mainteste2;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import Entidades.Aluno;
import Entidades.Frequencia;
import Entidades.Notificacao;
import Entidades.Professor;
import RepositorioDAO.FrequenciaDAO;
import RepositorioDAO.NotificacaoDAO;
import RepositorioDAO.ProfessorDAO;



import Serviços.ServicoFrequencia;
import Serviços.ServicoNotificacoes;
import Serviços.ProfessorServico;

public class mainDAO {

    public static void main(String[] args) {
       ProfessorDAO repositorioProfessor = new ProfessorDAO();
       NotificacaoDAO repositorioNotificacoes = new  NotificacaoDAO();
       FrequenciaDAO repositorioFrequencia = new FrequenciaDAO();
        
        // Instanciando os serviços com os respectivos repositórios
        ServicoNotificacoes servicoNotificacoes = new ServicoNotificacoes(repositorioNotificacoes);
        ProfessorServico servicoProfessor = new ProfessorServico(repositorioProfessor, servicoNotificacoes);
        ServicoFrequencia servicoFrequencia = new ServicoFrequencia(repositorioFrequencia);

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gerenciar Professores");
            System.out.println("2. Gerenciar Frequência");
            System.out.println("3. Ver Notificações");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next(); 
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    gerenciarProfessores(servicoProfessor, scanner, servicoNotificacoes);
                    break;

                case 2:
                    gerenciarFrequencia(servicoFrequencia, scanner);
                    break;

                case 3:
                    listarNotificacoes(servicoNotificacoes);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void gerenciarProfessores(ProfessorServico servicoProfessor, Scanner scanner, ServicoNotificacoes servicoNotificacoes) {
        int opcao;

        do {
            System.out.println("\n=== Gerenciar Professores ===");
            System.out.println("1. Adicionar Professor");
            System.out.println("2. Listar Professores");
            System.out.println("3. Atualizar Professor");
            System.out.println("4. Remover Professor");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next(); 
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarProfessor(servicoProfessor, scanner, servicoNotificacoes);
                    break;

                case 2:
                    listarProfessores(servicoProfessor);
                    break;

                case 3:
                    atualizarProfessor(servicoProfessor, scanner, servicoNotificacoes);
                    break;

                case 4:
                    removerProfessor(servicoProfessor, scanner, servicoNotificacoes);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void adicionarProfessor(ProfessorServico servicoProfessor, Scanner scanner, ServicoNotificacoes servicoNotificacoes) {
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a disciplina do professor: ");
        String disciplina = scanner.nextLine();
        System.out.print("Digite o salário do professor: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um salário válido.");
            scanner.next(); 
        }

        int salario = scanner.nextInt();

        // Adiciona o professor
        servicoProfessor.adicionar(nome, disciplina, salario);

        // Adiciona a notificação
        System.out.println("Adicionando a notificação...");
       
    }

    private static void listarProfessores(ProfessorServico servicoProfessor) {
        System.out.println("\nLista de Professores:");
        List<Professor> listaProfessores = servicoProfessor.listar();

        for (Professor p : listaProfessores) {
            System.out.println(p);
        }
    }

    private static void atualizarProfessor(ProfessorServico servicoProfessor, Scanner scanner, ServicoNotificacoes servicoNotificacoes) {
        System.out.print("Digite o ID do professor a ser atualizado: ");
        
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um ID válido.");
            scanner.next(); 
        }

        int idAtualizar = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o novo nome do professor: ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite a nova disciplina do professor: ");
        String novaDisciplina = scanner.nextLine();

        System.out.print("Digite o novo salário do professor: ");
        
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um salário válido.");
            scanner.next(); 
        }
        
        int novoSalario = scanner.nextInt();

     // Criação de um novo professor com os dados atualizados
        Professor professorAtualizado = new Professor(idAtualizar, novoNome, novaDisciplina, novoSalario);

        // Atualiza o professor no banco de dados
        servicoProfessor.atualizar(idAtualizar, professorAtualizado);

        // Envia a notificação de sucesso
        servicoNotificacoes.adicionar("Professor atualizado: " + novoNome);

        // Verifica se o professor foi realmente atualizado
        List<Professor> listaProfessores = ProfessorDAO.getProfessores();
        Professor professorVerificado = null;
        for (Professor p : listaProfessores) {
            if (p.getId() == idAtualizar) {
                professorVerificado = p;
                break;
            }
        }

        // Verificação de sucesso
        if (professorVerificado != null) {
            System.out.println("Professor atualizado com sucesso!");
        } else {
            System.out.println("Professor não encontrado para atualização.");
            }
        }


    private static void removerProfessor(ProfessorServico servicoProfessor, Scanner scanner, ServicoNotificacoes servicoNotificacoes) {
        System.out.print("Digite o ID do professor a ser removido: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um ID válido.");
            scanner.next(); 
        }

        int idRemover = scanner.nextInt();

        if (servicoProfessor.remover(idRemover)) { 
            servicoNotificacoes.adicionar("Professor removido com ID: " + idRemover);
            System.out.println("Professor removido com sucesso!");
        } else {
            System.out.println("Nenhum professor encontrado com esse ID.");
        }
    }

    private static void gerenciarFrequencia(ServicoFrequencia servicoFrequencia, Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n=== Gerenciar Frequência ===");
            System.out.println("1. Registrar Frequência");
            System.out.println("2. Listar Frequências");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next(); 
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarFrequencia(servicoFrequencia, scanner);
                    break;

                case 2:
                    listarFrequencias(servicoFrequencia);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void registrarFrequencia(ServicoFrequencia servicoFrequencia, Scanner scanner) {
        System.out.print("Digite o nome do aluno: ");
        String nomeAluno = scanner.nextLine();

        System.out.print("Digite a data (YYYY-MM-DD): ");
        String dataStr = scanner.nextLine();

        LocalDate data;
        try {
            data = LocalDate.parse(dataStr);

            System.out.print("O aluno esteve presente? (true/false): ");
            boolean presente = scanner.nextBoolean();

            Aluno aluno = new Aluno(); 
            Frequencia frequencia = new Frequencia(aluno, data, presente);

            servicoFrequencia.adicionar(frequencia);

        } catch (Exception e) {
            System.out.println("Data inválida! Por favor, use o formato YYYY-MM-DD.");
        }
    }

    private static void listarFrequencias(ServicoFrequencia servicoFrequencia) {
        System.out.println("\nLista de Frequências:");
        List<Frequencia> listaFrequencias = servicoFrequencia.listar();

        for (Frequencia f : listaFrequencias) {
            System.out.println(f);
        }
    }

    private static void listarNotificacoes(ServicoNotificacoes servicoNotificacoes) {
        List<Notificacao> listaNotificacoes = servicoNotificacoes.listar(); 
        
        if (listaNotificacoes.isEmpty()) {
            System.out.println("\nNenhuma notificação registrada.");
        } else {
            System.out.println("\nLista de Notificações:");
            for (Notificacao n : listaNotificacoes) {
                System.out.println(n);
            }
        }
    }
}
