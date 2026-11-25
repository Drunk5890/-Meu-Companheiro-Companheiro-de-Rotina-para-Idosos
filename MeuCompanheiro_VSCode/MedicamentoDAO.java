import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// DAO = classe responsavel pelos comandos do banco de dados.
public class MedicamentoDAO {

    public void cadastrar(Medicamento remedio) throws SQLException {
        String sql = "INSERT INTO medicamentos (nome, horario, tomado) VALUES (?, ?, ?)";
        try (Connection conexao = BancoDados.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, remedio.getNome());
            comando.setString(2, remedio.getHorario());
            comando.setBoolean(3, remedio.isTomado());
            comando.executeUpdate();
        }
    }

    public Medicamento[] listar() throws SQLException {
        try (Connection conexao = BancoDados.conectar()) {
            int quantidade;
            try (Statement comando = conexao.createStatement();
                 ResultSet resultado = comando.executeQuery("SELECT COUNT(*) FROM medicamentos")) {
                resultado.next();
                quantidade = resultado.getInt(1);
            }
            Medicamento[] remedios = new Medicamento[quantidade];
            String sql = "SELECT id, nome, horario, tomado FROM medicamentos ORDER BY id";
            try (PreparedStatement comando = conexao.prepareStatement(sql);
                 ResultSet resultado = comando.executeQuery()) {
                int i = 0;
                while (resultado.next()) {
                    remedios[i] = new Medicamento(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("horario"),
                        resultado.getBoolean("tomado")
                    );
                    i++;
                }
            }
            return remedios;
        }
    }

    public boolean confirmar(Medicamento remedio) throws SQLException {
        String sql = "UPDATE medicamentos SET tomado = ? WHERE id = ?";
        try (Connection conexao = BancoDados.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setBoolean(1, remedio.isTomado());
            comando.setInt(2, remedio.getId());
            return comando.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM medicamentos WHERE id = ?";
        try (Connection conexao = BancoDados.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);
            return comando.executeUpdate() > 0;
        }
    }
}
