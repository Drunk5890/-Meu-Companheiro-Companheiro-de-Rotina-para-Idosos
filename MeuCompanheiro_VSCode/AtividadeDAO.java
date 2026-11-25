import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AtividadeDAO {

    public void cadastrar(Atividade atividade) throws SQLException {
        String sql = "INSERT INTO atividades (nome, horario, concluida) VALUES (?, ?, ?)";
        try (Connection conexao = BancoDados.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, atividade.getNome());
            comando.setString(2, atividade.getHorario());
            comando.setBoolean(3, atividade.isConcluida());
            comando.executeUpdate();
        }
    }

    public Atividade[] listar() throws SQLException {
        try (Connection conexao = BancoDados.conectar()) {
            int quantidade;
            try (Statement comando = conexao.createStatement();
                 ResultSet resultado = comando.executeQuery("SELECT COUNT(*) FROM atividades")) {
                resultado.next();
                quantidade = resultado.getInt(1);
            }
            Atividade[] atividades = new Atividade[quantidade];
            String sql = "SELECT id, nome, horario, concluida FROM atividades ORDER BY id";
            try (PreparedStatement comando = conexao.prepareStatement(sql);
                 ResultSet resultado = comando.executeQuery()) {
                int i = 0;
                while (resultado.next()) {
                    atividades[i] = new Atividade(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("horario"),
                        resultado.getBoolean("concluida")
                    );
                    i++;
                }
            }
            return atividades;
        }
    }

    public boolean confirmar(Atividade atividade) throws SQLException {
        String sql = "UPDATE atividades SET concluida = ? WHERE id = ?";
        try (Connection conexao = BancoDados.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setBoolean(1, atividade.isConcluida());
            comando.setInt(2, atividade.getId());
            return comando.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM atividades WHERE id = ?";
        try (Connection conexao = BancoDados.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);
            return comando.executeUpdate() > 0;
        }
    }
}
