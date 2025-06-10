/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.Onca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author gabi
 */
public class ManterOncaDAO extends DAO {

    public void inserir(Onca o) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO onca(nome, sexo, idade, peso, comportamento, descricao_fisica, id_habitat) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, o.getNome());
            pst.setString(2, o.getSexo());
            pst.setInt(3, o.getIdade());
            pst.setDouble(4, o.getPeso());
            pst.setString(5, o.getComportamento());
            pst.setString(6, o.getDescricaoFisica());
            pst.setInt(7, o.getIdHabitat());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void deletar(Onca o) {
        try {
            abrirBanco();
            this.deletarFkMonitoramento(o);
            String query = "DELETE FROM onca WHERE id_onca = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, o.getIdOnca());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    private void deletarFkMonitoramento(Onca o) throws Exception {
        try {
            System.out.println("Apagando a FK da onça na tabela MONITORAMENTO...");
            String query = "DELETE FROM monitoramento WHERE id_onca = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, o.getIdOnca());
            pst.execute();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ArrayList<Onca> pesquisarTudo() throws Exception {
        ArrayList<Onca> onca = new ArrayList<>();
        try {
            abrirBanco();
            String query = "select * FROM onca";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Onca o;
            while (rs.next()) {
                o = new Onca();
                o.setIdOnca(rs.getInt("id_onca"));
                o.setNome(rs.getString("nome"));
                o.setSexo(rs.getString("sexo"));
                o.setIdade(rs.getInt("idade"));
                o.setPeso(rs.getDouble("peso"));
                o.setComportamento(rs.getString("comportamento"));
                o.setDescricaoFisica(rs.getString("descricao_fisica"));
                o.setDataUltimaObservacao(rs.getTimestamp("data_ultima_observacao"));
                o.setInViva(rs.getByte("in_viva"));
                o.setIdHabitat(rs.getInt("id_habitat"));
                onca.add(o);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return onca;
    }

    public void pesquisarRegistro(Onca o) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM onca Where id_onca=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, o.getIdOnca());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                o.setIdOnca(rs.getInt("id_onca"));
                o.setNome(rs.getString("nome"));
                o.setSexo(rs.getString("sexo"));
                o.setIdade(rs.getInt("idade"));
                o.setPeso(rs.getDouble("peso"));
                o.setComportamento(rs.getString("comportamento"));
                o.setDescricaoFisica(rs.getString("descricao_fisica"));
                o.setDataUltimaObservacao(rs.getTimestamp("data_ultima_observacao"));
                o.setInViva(rs.getByte("in_viva"));
                o.setIdHabitat(rs.getInt("id_habitat"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void editarOnca(Onca o) throws Exception {
        try {
            abrirBanco();
            String query = """
                            UPDATE onca SET 
                            nome = ?,
                            sexo = ?,
                            idade = ?,
                            peso = ?,
                            comportamento = ?,
                            descricao_fisica = ?,
                            data_ultima_observacao = ?,
                            in_viva = ?, 
                            id_habitat = ? 
                            WHERE id_onca = ?
                           """;
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, o.getNome());
            pst.setString(2, o.getSexo());
            pst.setInt(3, o.getIdade());
            pst.setDouble(4, o.getPeso());
            pst.setString(5, o.getComportamento());
            pst.setString(6, o.getDescricaoFisica());
            pst.setTimestamp(7, o.getDataUltimaObservacao());
            pst.setByte(8, o.getInViva());
            pst.setInt(9, o.getIdHabitat());
            pst.setInt(10, o.getIdOnca());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            fecharBanco();
        }
    }
}
