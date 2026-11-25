public class Medicamento {
    private int id;
    private String nome;
    private String horario;
    private boolean tomado;

    // Construtor usado quando o cuidador cadastra um medicamento.
    public Medicamento(String nome, String horario) {
        this(0, nome, horario, false);
    }

    // Construtor usado quando o medicamento e carregado do H2.
    public Medicamento(int id, String nome, String horario, boolean tomado) {
        this.id = id;
        this.nome = nome;
        this.horario = horario;
        this.tomado = tomado;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getHorario() { return horario; }
    public boolean isTomado() { return tomado; }

    public void confirmarTomada() {
        tomado = true;
    }

    public void mostrarMedicamento() {
        System.out.println("Remedio: " + nome);
        System.out.println("Horario: " + horario);
        if (tomado) {
            System.out.println("Situacao: tomado (confirmado)");
        } else {
            System.out.println("Situacao: ainda nao confirmado");
        }
    }
}
