package ModelDaoImp;

import Modelos.UsuarioDao;
import db.DB;
import db.DbException;
import program.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJDBC implements UsuarioDao {
    private Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Usuario obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO cliente "
                            + "(nome, email, quantidade_moedas, departamento_id) "
                            + "VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDouble(3, obj.getMoedas());
            st.setInt(4, obj.getDepartamentoId());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DbException("Erro inesperado, nenhuma linha afetada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Usuario obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE cliente "
                            + "SET nome = ?, email = ?, quantidade_moedas = ?, departamento_id = ?  "
                            + "WHERE Id = ? ");

            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDouble(3, obj.getMoedas());
            st.setInt(4, obj.getDepartamentoId());
            st.setInt(5, obj.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM cliente WHERE Id = ?");
            st.setInt(1, id);

            int rowsAffeceted = st.executeUpdate();
            if (rowsAffeceted == 0) {
                throw new DbException("Este Usuario Não existe");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Usuario findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM cliente WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateUsuario(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Usuario> findALL() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM cliente ORDER BY nome");
            rs = st.executeQuery();

            List<Usuario> list = new ArrayList<>();

            while (rs.next()) {
                Usuario us = instantiateUsuario(rs);
                list.add(us);
            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
        Usuario us = new Usuario();
        us.setId(rs.getInt("Id"));
        us.setNome(rs.getString("nome")); // <<< corrigido Name -> nome
        us.setDepartamentoId(rs.getInt("departamento_id"));
        us.setEmail(rs.getString("email"));
        us.setMoedas(rs.getInt("quantidade_moedas"));
        return us;
    }
}

