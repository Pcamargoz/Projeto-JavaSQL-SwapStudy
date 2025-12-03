package program;

import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Indentificador {

    // Agora recebe a conexão por parâmetro
    public Usuario buscarPorNome(Connection conn, String nome) {

        String sql = "SELECT * FROM cliente WHERE nome = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, nome);

            try (ResultSet rs = st.executeQuery()) {

                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getInt("quantidade_moedas"),
                            rs.getInt("departamento_id")
                    );
                } else {
                    return null;
                }

            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar cliente: " + e.getMessage());
        }
    }
}
