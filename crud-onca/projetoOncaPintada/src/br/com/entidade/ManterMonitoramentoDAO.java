/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.RelatorioHistorico;
import br.com.controle.RelatorioObitos;
import br.com.controle.Sexo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author gabi
 */
public class ManterMonitoramentoDAO extends DAO {
    
    public ArrayList<RelatorioHistorico> historicoMonitoramento() throws Exception {
        RelatorioHistorico r;
        ArrayList<RelatorioHistorico> lista = new ArrayList<>();
        try {
            abrirBanco();
            String query = """
                           SELECT 
                               m.nome,
                               m.sexo,
                               m.idade,
                               h.tipo AS habitat,
                               m.quantidade_femeas_brasil,
                               m.quantidade_machos_brasil,
                               o.data_ultima_observacao,
                               IF(o.in_viva = 1, 'TRUE', 'FALSE') as in_viva
                           FROM monitoramento m
                           JOIN onca o ON m.id_onca = o.id_onca
                           LEFT JOIN habitat h ON m.id_habitat = h.id_habitat
                           ORDER BY o.data_ultima_observacao DESC""";
            
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                r = new RelatorioHistorico();
                r.setNome(rs.getString("nome"));
                
                String valorSexo = rs.getString("sexo");
                r.setSexo(Sexo.setarValor(valorSexo));
                
                r.setIdade(rs.getInt("idade"));
                r.setHabitat(rs.getString("habitat"));
                r.setQuantidadeFemeasBrasil(rs.getInt("quantidade_femeas_brasil"));
                r.setQuantidadeMachosBrasil(rs.getInt("quantidade_machos_brasil"));
                r.setDataUltimaObservacao(rs.getTimestamp("data_ultima_observacao"));
                r.setInViva(rs.getString("in_viva"));
                lista.add(r);
            }
            
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            
            return lista;
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            fecharBanco();
        }
    }
    
    public ArrayList<RelatorioObitos> historicoObitos() throws Exception {
        RelatorioObitos ro;
        ArrayList<RelatorioObitos> lista = new ArrayList<>();
        try {
            abrirBanco();
            String query = """
                           SELECT 
                               o.nome,
                               o.sexo,
                               o.idade,
                               o.peso,
                               o.comportamento,
                               o.descricao_fisica,
                               o.data_ultima_observacao,
                               h.tipo AS habitat,
                               h.estado
                           FROM onca o
                           LEFT JOIN habitat h ON o.id_habitat = h.id_habitat
                           WHERE o.in_viva = 0
                           ORDER BY o.data_ultima_observacao DESC""";
            
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ro = new RelatorioObitos();
                ro.setNome(rs.getString("nome"));
                
                String valorSexo = rs.getString("sexo");
                ro.setSexo(Sexo.setarValor(valorSexo));
                
                ro.setIdade(rs.getInt("idade"));
                ro.setPeso(rs.getDouble("peso"));
                ro.setComportamento(rs.getString("comportamento"));
                ro.setDescricaoFisica(rs.getString("descricao_fisica"));
                ro.setDataUltimaObservacao(rs.getTimestamp("data_ultima_observacao"));
                ro.setHabitat(rs.getString("habitat"));
                ro.setEstado(rs.getString("estado"));
                lista.add(ro);
            }
            
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            
            return lista;
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            fecharBanco();
            
        }
    }
    
}
