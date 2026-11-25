public class Atividade {
    private int id;
    private String nome;
    private String horario;
    private boolean concluida;

    public Atividade(String nome, String horario) {
        this(0, nome, horario, false);
    }

    public Atividade(int id, String nome, String horario, boolean concluida) {
        this.id = id;
        this.nome = nome;
        this.horario = horario;
        this.concluida = concluida;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getHorario() { return horario; }
    public boolean isConcluida() { return concluida; }

    public void confirmarAtividade() {
        concluida = true;
    }

    public void mostrarAtividade() {
        System.out.println("Atividade: " + nome);
        System.out.println("Horario: " + horario);
        if (concluida) {
            System.out.println("Situacao: concluida");
        } else {
            System.out.println("Situacao: pendente");
        }
    }
}
