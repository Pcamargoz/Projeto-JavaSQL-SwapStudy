package program;


import db.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PuxarContratos  {
    private int fornecedor_id;
    private String fornecedor;
    private String servico;
    private double precoM;
    private int id;

    public String getServicoSwap() {
        return servicoSwap;
    }

    public void setServicoSwap(String servicoSwap) {
        this.servicoSwap = servicoSwap;
    }

    private String servicoSwap;

    @Override
    public String toString() {
        return "PuxarContratos{" +
                "fornecedor_id=" + fornecedor_id +
                ", fornecedor='" + fornecedor + '\'' +
                ", servico='" + servico + '\'' +
                ", precoM=" + precoM +
                ", id=" + id +
                ", servicoSwap='" + servicoSwap + '\'' +
                '}';
    }




    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PuxarContratos that = (PuxarContratos) o;
        return Objects.equals(fornecedor, that.fornecedor);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fornecedor);
    }



    public int getFornecedor_id() {
        return fornecedor_id;
    }

    public void setFornecedor_id(int fornecedor_id) {
        this.fornecedor_id = fornecedor_id;
    }



    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public double getPrecoM() {
        return precoM;
    }

    public void setPrecoM(double precoM) {
        this.precoM = precoM;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<PuxarContratos> getList() {
        return list;
    }

    public PuxarContratos() {}

    public PuxarContratos(String fornecedor, String servico, double precoM, int id,int fornecedor_id,String servicoSwap) {
        this.fornecedor = fornecedor;
        this.servico = servico;
        this.precoM = precoM;
        this.id = id;
        this.fornecedor_id = fornecedor_id;
        this.servicoSwap = servicoSwap;
    }



    public static List<PuxarContratos> list = new ArrayList<>();


    // *** MÉTODO CORRETO: recebe a conexão aberta da application ***
    public static void carregarContratos(Connection conn) {

        String sql = "SELECT id, nome_fornecedor, servico, preco_moedas,fornecedor_id, servico_swap FROM contratos";

        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            list.clear(); // limpa antes de recarregar

            while (rs.next()) {
                int id = rs.getInt("id");
                String fornecedor = rs.getString("nome_fornecedor");
                String servico = rs.getString("servico");
                double preco = rs.getDouble("preco_moedas");
                int fornecedor_id = rs.getInt("fornecedor_id");
                String servicoSwap = rs.getString("servico_swap");
                list.add(new PuxarContratos(fornecedor, servico, preco, id,fornecedor_id,servicoSwap));
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao carregar contratos: " + e.getMessage());
        }
    }
}
