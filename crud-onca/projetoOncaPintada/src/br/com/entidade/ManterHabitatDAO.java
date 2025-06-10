/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.Habitat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author gabi
 */
public class ManterHabitatDAO extends DAO {

    public void inserir(Habitat h) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO habitat(tipo, estado, clima, descricao_habitat) "
                    + "VALUES (?, ?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, h.getTipo());
            pst.setString(2, h.getEstado());
            pst.setString(3, h.getClima());
            pst.setString(4, h.getDescricaoHabitat());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void deletar(Habitat h) {
        try {
            abrirBanco();
            String query = "DELETE FROM habitat WHERE id_habitat = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, h.getIdHabitat());
            pst.execute();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public ArrayList<Habitat> pesquisarTudo() throws Exception {
        ArrayList<Habitat> habitat = new ArrayList<>();
        try {
            abrirBanco();
            String query = "select * FROM habitat";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Habitat h;
            while (rs.next()) {
                h = new Habitat();
                h.setIdHabitat(rs.getInt("id_habitat"));
                h.setTipo(rs.getString("tipo"));
                h.setEstado(rs.getString("estado"));
                h.setClima(rs.getString("clima"));
                h.setDescricaoHabitat(rs.getString("descricao_habitat"));
                habitat.add(h);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return habitat;
    }

    public void pesquisarHabitat(Habitat h) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM habitat Where id_habitat=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, h.getIdHabitat());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                h.setIdHabitat(rs.getInt("id_habitat"));
                h.setTipo(rs.getString("tipo"));
                h.setEstado(rs.getString("estado"));
                h.setClima(rs.getString("clima"));
                h.setDescricaoHabitat(rs.getString("descricao_habitat"));

            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void editarHabitat(Habitat h) throws Exception {
        abrirBanco();
        String query = "UPDATE habitat set tipo = ?, estado = ?, clima = ?, descricao_habitat = ? where id_habitat = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, h.getTipo());
        pst.setString(2, h.getEstado());
        pst.setString(3, h.getClima());
        pst.setString(4, h.getDescricaoHabitat());
        pst.setInt(5, h.getIdHabitat());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Habitat alterado com sucesso!");
        fecharBanco();
    }

}
