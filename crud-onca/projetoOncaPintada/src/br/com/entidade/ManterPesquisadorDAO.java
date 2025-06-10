/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.Pesquisador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author gabi
 */
public class ManterPesquisadorDAO extends DAO {

    public void inserir(Pesquisador p) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO pesquisador(nome, email, cpf, endereco) "
                    + "VALUES (?, ?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, p.getNome());
            pst.setString(2, p.getEmail());
            pst.setString(3, p.getCpf());
            pst.setString(4, p.getEndereco());
            pst.execute();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        } finally {
            fecharBanco();
        }
    }

    public void deletar(Pesquisador p) {
        try {
            abrirBanco();
            String query = "DELETE FROM pesquisador WHERE id_pesquisador = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, p.getIdPesquisador());
            pst.execute();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public ArrayList<Pesquisador> pesquisarTudo() throws Exception {
        ArrayList<Pesquisador> pesquisador = new ArrayList<>();
        try {
            abrirBanco();
            String query = "select * FROM pesquisador";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Pesquisador p;
            while (rs.next()) {
                p = new Pesquisador();
                p.setIdPesquisador(rs.getInt("id_pesquisador"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setCpf(rs.getString("cpf"));
                p.setEndereco(rs.getString("endereco"));
                pesquisador.add(p);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return pesquisador;
    }

    public void pesquisarRegistro(Pesquisador p) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM pesquisador Where id_pesquisador=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, p.getIdPesquisador());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p.setIdPesquisador(rs.getInt("id_pesquisador"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setCpf(rs.getString("cpf"));
                p.setEndereco(rs.getString("endereco"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        } finally {
            fecharBanco();
        }
    }

    public void editarPesquisador(Pesquisador p) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE pesquisador set  nome = ?, email = ?, cpf = ?, endereco = ? where id_pesquisador = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, p.getNome());
            pst.setString(2, p.getEmail());
            pst.setString(3, p.getCpf());
            pst.setString(4, p.getEndereco());
            pst.setInt(5, p.getIdPesquisador());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pesquisador alterado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            fecharBanco();
        }
    }
}
