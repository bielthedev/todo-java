import java.io.*;
import java.util.*;

public class TodoApp {
    private static final String FILE_NAME = "tarefas.txt";
    private static List<String> tarefas = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        carregarTarefas();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- To-do List Java ---");
            System.out.println("1. Listar tarefas");
            System.out.println("2. Adicionar tarefa");
            System.out.println("3. Marcar como concluída");
            System.out.println("4. Remover tarefa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: listarTarefas(); break;
                case 2: adicionarTarefa(scanner); break;
                case 3: marcarComoConcluida(scanner); break;
                case 4: removerTarefa(scanner); break;
                case 0: salvarTarefas(); System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void carregarTarefas() throws IOException {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha;
            while ((linha = br.readLine()) != null) {
                tarefas.add(linha);
            }
            br.close();
        }
    }

    private static void salvarTarefas() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
        for (String tarefa : tarefas) {
            bw.write(tarefa);
            bw.newLine();
        }
        bw.close();
    }

    private static void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            System.out.println("\nTarefas:");
            for (int i = 0; i < tarefas.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tarefas.get(i));
            }
        }
    }

    private static void adicionarTarefa(Scanner scanner) {
        System.out.print("Digite a nova tarefa: ");
        String tarefa = "[ ] " + scanner.nextLine();
        tarefas.add(tarefa);
        System.out.println("Tarefa adicionada.");
    }

    private static void marcarComoConcluida(Scanner scanner) {
        listarTarefas();
        System.out.print("Número da tarefa para marcar como concluída: ");
        int i = scanner.nextInt() - 1;
        if (i >= 0 && i < tarefas.size()) {
            String tarefa = tarefas.get(i);
            if (!tarefa.startsWith("[x]")) {
                tarefas.set(i, tarefa.replace("[ ]", "[x]"));
                System.out.println("Tarefa marcada como concluída.");
            } else {
                System.out.println("Essa tarefa já está concluída.");
            }
        } else {
            System.out.println("Tarefa inválida.");
        }
    }

    private static void removerTarefa(Scanner scanner) {
        listarTarefas();
        System.out.print("Número da tarefa para remover: ");
        int i = scanner.nextInt() - 1;
        if (i >= 0 && i < tarefas.size()) {
            tarefas.remove(i);
            System.out.println("Tarefa removida.");
        } else {
            System.out.println("Tarefa inválida.");
        }
    }
}
