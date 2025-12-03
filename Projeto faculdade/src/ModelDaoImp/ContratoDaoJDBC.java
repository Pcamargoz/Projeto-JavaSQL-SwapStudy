package ModelDaoImp;

import Modelos.ContratoDao;
import db.DB;
import db.DbException;
import program.PuxarContratos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratoDaoJDBC implements ContratoDao {
    private Connection conn;

    public ContratoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(PuxarContratos obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO department "
                            + "(fornecedor_id,nome_fornecedor,servico,preco_moedas) "
                            +"VALUES "
                            +"(?,?,?,?) ",
                    // para gerar a chave aleatoria
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1,obj.getFornecedor_id());
            st.setString(2,obj.getFornecedor());
            st.setString(3,obj.getServico());
            st.setDouble(4,obj.getPrecoM());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0 ){
                // ele esta pegando a chave que o proprio banco da , quando e inserido alguem
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    // e aqui ele pega o item gerado na primeira coluna que no caso sera o id
                    // ele esta pegando da coluna 1 que no caso seria o id do departamento
                    int id = rs.getInt(1);
                    // e adiciona a entidade id do department aqui
                    obj.setId(id);
                }
                //DB.closeResultSet(rs);
            }
            else{
                throw new DbException("Erro inesperado , nenhumna linha afetada");
            }
        }catch (SQLException e){
            // passando a mensagem da exceção original
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(PuxarContratos obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE contratos "
                            +"SET forncedor_id = ?, nome_fornecedor = ?, servico = ?, preco_moedas = ? "
                            + "WHERE Id = ? ");

            st.setInt(1,obj.getFornecedor_id());
            st.setString(2,obj.getFornecedor());
            st.setString(3,obj.getServico());
            st.setDouble(4,obj.getPrecoM());

            st.setInt(6,obj.getId());

            st.executeUpdate();

        }
        catch (SQLException e){
            // passando a mensagem da exceção original
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            // Deletando com o comando do sql apenas
            st = conn.prepareStatement("DELETE FROM contratos WHERE Id = ?");

            st.setInt(1,id);

            int rowsAffeceted = st.executeUpdate();
            if(rowsAffeceted == 0){
                throw new DbException("Este Usuario Não existe");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public PuxarContratos findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM contratos WHERE id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                PuxarContratos c1 = instantiateContrato(rs);
                return c1;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            //DB.closeResultSet(rs);
        }
    }

    private PuxarContratos instantiateContrato(ResultSet rs) throws SQLException {
        PuxarContratos obj = new PuxarContratos();
        obj.setId(rs.getInt("id"));
        obj.setFornecedor_id(rs.getInt("fornecedor_id"));
        obj.setFornecedor(rs.getString("nome_fornecedor"));
        obj.setServico(rs.getString("servico"));
        obj.setPrecoM(rs.getDouble("preco_moedas"));
        return obj;
    }

    @Override
    public List<PuxarContratos> findALL() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM contratos ORDER BY nome_fornecedor");
            rs = st.executeQuery();

            List<PuxarContratos> list = new ArrayList<>();

            while (rs.next()) {
                // instanciando o que ele ta lendo e armazenando na lista
                list.add(instantiateContrato(rs));
            }
            // retornando a lista para que ela possa ser impressa
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            //DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
    }

