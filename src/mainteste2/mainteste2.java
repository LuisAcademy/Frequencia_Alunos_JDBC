package mainteste2;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import Entidades.Aluno;
import Entidades.Frequencia;
import Entidades.Notificacao;
import Entidades.Professor;
import Repositorios.RepositorioFrequencia;
import Repositorios.RepositorioNotificacoes; // Importando o repositório de notificações
import Repositorios.RepositorioProfessor;
import Serviços.ServicoFrequencia;
import Serviços.ServicoNotificacoes; // Importando o serviço de notificações

public class mainteste2 {

    public static void main(String[] args) {
        RepositorioProfessor repositorio = new RepositorioProfessor();
        Serviços.ProfessorServico servico = new Serviços.ProfessorServico(repositorio);
        RepositorioFrequencia repositorioFrequencia = new RepositorioFrequencia();
        ServicoFrequencia servicoFrequencia = new ServicoFrequencia(repositorioFrequencia);

       
        RepositorioNotificacoes repositorioNotificacoes = new RepositorioNotificacoes();
        ServicoNotificacoes servicoNotificacoes = new ServicoNotificacoes(repositorioNotificacoes);

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
                scanner.next(); // 
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // 

            switch (opcao) {
                case 1:
                    
                    gerenciarProfessores(servico, scanner, servicoNotificacoes);
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

    private static void gerenciarProfessores(Serviços.ProfessorServico servico, Scanner scanner, ServicoNotificacoes servicoNotificacoes) {
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
                    adicionarProfessor(servico, scanner, servicoNotificacoes);
                    break;

                case 2:
                    listarProfessores(servico);
                    break;

                case 3:
                    atualizarProfessor(servico, scanner, servicoNotificacoes);
                    break;

                case 4:
                    removerProfessor(servico, scanner, servicoNotificacoes);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }
    private static void adicionarProfessor(Serviços.ProfessorServico servico, Scanner scanner, ServicoNotificacoes servicoNotificacoes) {
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
    	   
    	   servico.adicionar(nome, disciplina, salario); 
    	   servicoNotificacoes.adicionar("Novo professor adicionado: " + nome);
    	}
  
    private static void listarProfessores(Serviços.ProfessorServico servico) {
        System.out.println("\nLista de Professores:");
        List<Professor> listaProfessores = servico.listar();
        
        for (Professor p : listaProfessores) {
            System.out.println(p);
        }
    }

    private static void atualizarProfessor(Serviços.ProfessorServico servico, Scanner scanner, ServicoNotificacoes servicoNotificacoes) {
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
    	   
    	  
    	   Professor professorAtualizado = new Professor(idAtualizar, novoNome, novaDisciplina, novoSalario);
    	   
    	   servico.atualizar(idAtualizar, professorAtualizado); 
    	   servicoNotificacoes.adicionar("Professor atualizado: " + novoNome);
    	}

    private static void removerProfessor(Serviços.ProfessorServico servico, Scanner scanner, ServicoNotificacoes servicoNotificacoes) {
        System.out.print("Digite o ID do professor a ser removido: ");
        
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um ID válido.");
            scanner.next(); 
        }
        
        int idRemover = scanner.nextInt();

        if (servico.remover(idRemover)) { 
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

           Aluno aluno = new Aluno(nomeAluno, /* ID do aluno */ 0); 
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
           System.out.println("\nNenhuma notificação disponível.");
       } else {
           for (Notificacao n : listaNotificacoes) {
               System.out.println(n);
           }
       }
   }
}