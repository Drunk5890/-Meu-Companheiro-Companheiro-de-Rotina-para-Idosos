import java.sql.SQLException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class MeuCompanheiro {
    static Scanner ler = new Scanner(System.in);
    static MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    static AtividadeDAO atividadeDAO = new AtividadeDAO();

    public static void main(String[] args) {
        try {
            BancoDados.iniciar();
            System.out.println("Banco H2 conectado! Cadastros ficam salvos na pasta dados.");
        } catch (SQLException erro) {
            System.out.println("ERRO: nao foi possivel abrir o banco H2.");
            System.out.println("Verifique se o arquivo h2-2.5.252.jar esta na pasta lib.");
            System.out.println("Detalhe: " + erro.getMessage());
            return;
        }

        try {
            System.out.print("Nome do idoso: ");
            Idoso idoso = new Idoso(ler.nextLine());
            System.out.print("Nome do cuidador/familiar: ");
            Cuidador cuidador = new Cuidador(ler.nextLine());

            int opcao = -1;
            do {
                System.out.println("\n===== MEU COMPANHEIRO =====");
                System.out.println("1 - Entrar como idoso (" + idoso.getNome() + ")");
                System.out.println("2 - Entrar como cuidador/familiar (" + cuidador.getNome() + ")");
                System.out.println("0 - Sair");
                opcao = lerNumero();
                switch (opcao) {
                    case 1: menuIdoso(); break;
                    case 2: menuCuidador(); break;
                    case 0: System.out.println("Encerrando o programa..."); break;
                    default: System.out.println("Opcao invalida.");
                }
            } while (opcao != 0);
        } catch (NoSuchElementException erro) {
            System.out.println("\nEntrada encerrada. Dados ja salvos permanecem no H2.");
        } finally {
            ler.close();
        }
    }

    public static void menuIdoso() {
        int opcao = -1;
        do {
            System.out.println("\n===== MENU DO IDOSO =====");
            System.out.println("1 - Ver remedios");
            System.out.println("2 - Confirmar remedio tomado");
            System.out.println("3 - Ver atividades");
            System.out.println("4 - Confirmar atividade realizada");
            System.out.println("0 - Voltar ao menu principal");
            opcao = lerNumero();
            try {
                switch (opcao) {
                    case 1: listarRemedios(); break;
                    case 2: confirmarRemedio(); break;
                    case 3: listarAtividades(); break;
                    case 4: confirmarAtividade(); break;
                    case 0: break;
                    default: System.out.println("Opcao invalida.");
                }
            } catch (SQLException erro) {
                System.out.println("Erro ao consultar o banco: " + erro.getMessage());
            }
        } while (opcao != 0);
    }

    public static void menuCuidador() {
        int opcao = -1;
        do {
            System.out.println("\n===== MENU DO CUIDADOR/FAMILIAR =====");
            System.out.println("1 - Cadastrar remedio");
            System.out.println("2 - Ver remedios e confirmacoes");
            System.out.println("3 - Excluir remedio");
            System.out.println("4 - Cadastrar atividade");
            System.out.println("5 - Ver atividades e confirmacoes");
            System.out.println("6 - Excluir atividade");
            System.out.println("0 - Voltar ao menu principal");
            opcao = lerNumero();
            try {
                switch (opcao) {
                    case 1: cadastrarRemedio(); break;
                    case 2: listarRemedios(); break;
                    case 3: excluirRemedio(); break;
                    case 4: cadastrarAtividade(); break;
                    case 5: listarAtividades(); break;
                    case 6: excluirAtividade(); break;
                    case 0: break;
                    default: System.out.println("Opcao invalida.");
                }
            } catch (SQLException erro) {
                System.out.println("Erro ao acessar o banco: " + erro.getMessage());
            }
        } while (opcao != 0);
    }

    public static int lerNumero() {
        System.out.print("Escolha um numero: ");
        String entrada = ler.nextLine().trim();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException erro) {
            return -1;
        }
    }

    public static void cadastrarRemedio() throws SQLException {
        System.out.print("Nome do remedio: ");
        String nome = ler.nextLine().trim();
        System.out.print("Horario do remedio (HH:mm): ");
        String horario = ler.nextLine().trim();
        if (!dadosValidos(nome, horario)) return;

        medicamentoDAO.cadastrar(new Medicamento(nome, horario));
        System.out.println("Remedio cadastrado e salvo no H2!");
    }

    public static void listarRemedios() throws SQLException {
        Medicamento[] remedios = medicamentoDAO.listar();
        if (remedios.length == 0) {
            System.out.println("Nenhum remedio cadastrado.");
            return;
        }
        for (int i = 0; i < remedios.length; i++) {
            System.out.println("\nRemedio " + (i + 1));
            remedios[i].mostrarMedicamento();
        }
    }

    public static void confirmarRemedio() throws SQLException {
        Medicamento[] remedios = medicamentoDAO.listar();
        if (remedios.length == 0) {
            System.out.println("Nenhum remedio cadastrado.");
            return;
        }
        listarRemedios();
        System.out.println("Numero do remedio que foi tomado:");
        int numero = lerNumero();
        if (numero < 1 || numero > remedios.length) {
            System.out.println("Numero invalido.");
            return;
        }
        Medicamento selecionado = remedios[numero - 1];
        selecionado.confirmarTomada();
        if (medicamentoDAO.confirmar(selecionado)) {
            System.out.println("Confirmacao salva no H2.");
        } else {
            System.out.println("O remedio nao foi encontrado no banco.");
        }
    }

    public static void excluirRemedio() throws SQLException {
        Medicamento[] remedios = medicamentoDAO.listar();
        if (remedios.length == 0) {
            System.out.println("Nenhum remedio cadastrado.");
            return;
        }
        listarRemedios();
        System.out.println("Numero do remedio para excluir:");
        int numero = lerNumero();
        if (numero < 1 || numero > remedios.length) {
            System.out.println("Numero invalido.");
            return;
        }
        System.out.print("Confirma exclusao? (S/N): ");
        if (!ler.nextLine().equalsIgnoreCase("S")) {
            System.out.println("Exclusao cancelada.");
            return;
        }
        if (medicamentoDAO.excluir(remedios[numero - 1].getId())) {
            System.out.println("Remedio excluido do banco.");
        } else {
            System.out.println("O remedio nao foi encontrado no banco.");
        }
    }

    public static void cadastrarAtividade() throws SQLException {
        System.out.print("Nome da atividade: ");
        String nome = ler.nextLine().trim();
        System.out.print("Horario da atividade (HH:mm): ");
        String horario = ler.nextLine().trim();
        if (!dadosValidos(nome, horario)) return;

        atividadeDAO.cadastrar(new Atividade(nome, horario));
        System.out.println("Atividade cadastrada e salva no H2!");
    }

    public static void listarAtividades() throws SQLException {
        Atividade[] atividades = atividadeDAO.listar();
        if (atividades.length == 0) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }
        for (int i = 0; i < atividades.length; i++) {
            System.out.println("\nAtividade " + (i + 1));
            atividades[i].mostrarAtividade();
        }
    }

    public static void confirmarAtividade() throws SQLException {
        Atividade[] atividades = atividadeDAO.listar();
        if (atividades.length == 0) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }
        listarAtividades();
        System.out.println("Numero da atividade realizada:");
        int numero = lerNumero();
        if (numero < 1 || numero > atividades.length) {
            System.out.println("Numero invalido.");
            return;
        }
        Atividade selecionada = atividades[numero - 1];
        selecionada.confirmarAtividade();
        if (atividadeDAO.confirmar(selecionada)) {
            System.out.println("Atividade confirmada e salva no H2!");
        } else {
            System.out.println("A atividade nao foi encontrada no banco.");
        }
    }

    public static void excluirAtividade() throws SQLException {
        Atividade[] atividades = atividadeDAO.listar();
        if (atividades.length == 0) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }
        listarAtividades();
        System.out.println("Numero da atividade para excluir:");
        int numero = lerNumero();
        if (numero < 1 || numero > atividades.length) {
            System.out.println("Numero invalido.");
            return;
        }
        System.out.print("Confirma exclusao? (S/N): ");
        if (!ler.nextLine().equalsIgnoreCase("S")) {
            System.out.println("Exclusao cancelada.");
            return;
        }
        if (atividadeDAO.excluir(atividades[numero - 1].getId())) {
            System.out.println("Atividade excluida do banco.");
        } else {
            System.out.println("A atividade nao foi encontrada no banco.");
        }
    }

    private static boolean dadosValidos(String nome, String horario) {
        if (nome.isEmpty() || horario.isEmpty()) {
            System.out.println("Nome e horario nao podem ficar vazios.");
            return false;
        }
        if (nome.length() > 120 || horario.length() > 30) {
            System.out.println("Nome muito longo ou horario muito longo.");
            return false;
        }
        if (!horarioValido(horario)) {
            System.out.println("Horario invalido. Use HH:mm, de 00:00 a 23:59.");
            return false;
        }
        return true;
    }

    // Valida o formato de 24 horas sem instalar outra biblioteca.
    static boolean horarioValido(String horario) {
        return horario.matches("([01][0-9]|2[0-3]):[0-5][0-9]");
    }
}
