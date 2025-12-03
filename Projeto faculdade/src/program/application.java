package program;

import db.DB;
import db.DbException;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class application {

    static void main() {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        Statement st = null;
        Indentificador i = new Indentificador();


        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);

            // IDENTIFICAÇÃO DO USUÁRIO
            System.out.println("Se identifique pelo nome de usuário: ");
            String NameUser = sc.nextLine();

            Usuario cliente = i.buscarPorNome(conn,NameUser);

            if (cliente == null) {
                System.out.println("Usuário não encontrado!");
                return;
            }

            System.out.println("\nUsuário identificado:");
            System.out.println(cliente);

            // CARREGANDO CONTRATOS
            PuxarContratos.carregarContratos(conn);
            List<PuxarContratos> contratos = PuxarContratos.list;

            System.out.println("Você deseja ver os contratos disponíveis? (S/N)");
            char resposta = sc.next().charAt(0);

            if (resposta != 'S') {
                System.out.println("Muito obrigado. Volte sempre!");
                return;
            }

            // LISTA OS CONTRATOS
            for (PuxarContratos c : contratos) {
                System.out.println("---------------------");
                System.out.println("ID: " + c.getId());
                System.out.println("Fornecedor: " + c.getFornecedor());
                System.out.println("Serviço: " + c.getServico());
                System.out.println("Preço: " + c.getPrecoM());
            }
            System.out.println("---------------------");

            // ESCOLHENDO O CONTRATO
            System.out.println("Escolha o contrato pelo ID (número): ");
            int idEscolhido = sc.nextInt();
            // Armazenar um contrato escolhido
            PuxarContratos contratoEscolhido = null;

            for (PuxarContratos c : contratos) {
                if (c.getId() == idEscolhido) {
                    contratoEscolhido = c;
                    break;
                }
            }

            if (contratoEscolhido == null) {
                System.out.println("Contrato não encontrado!");
                return;
            }

            System.out.println("\nVocê escolheu o contrato:");
            System.out.println("ID: " + contratoEscolhido.getId());
            System.out.println("Fornecedor: " + contratoEscolhido.getFornecedor());
            System.out.println("Serviço: " + contratoEscolhido.getServico());
            System.out.println("Preço: " + contratoEscolhido.getPrecoM());
            System.out.println("---------------------");

            System.out.println("Deseja realizar o pagamento? (S/N)");
            char resposta3 = sc.next().charAt(0);
            double cal = 0.0;
            if (resposta3 == 'S') {
                //cal = contratoEscolhido.getPrecoM() - cliente.getMoedas();
                String sql = "UPDATE cliente SET quantidade_moedas = quantidade_moedas - ? WHERE id = ?";
                // indentifcando o fornecedor do contrato para fazer a adição do valor do contrato
                String sql2 = "UPDATE cliente SET quantidade_moedas = quantidade_moedas + ? WHERE id = ?";
                Usuario cliente2 = i.buscarPorNome(conn,contratoEscolhido.getFornecedor());

                PreparedStatement ss = conn.prepareStatement(sql);
                PreparedStatement sss = conn.prepareStatement(sql2);

                ss.setDouble(1,contratoEscolhido.getPrecoM());
                ss.setInt(2,cliente.getId());
                sss.setDouble(1,contratoEscolhido.getPrecoM());
                sss.setInt(2,cliente2.getId());

                ss.executeUpdate();
                sss.executeUpdate();
                System.out.println("Pagamento realizado com sucesso!");

            } else {

                System.out.println("Ok, volte sempre!");
            }

            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);

        }
    }
}
