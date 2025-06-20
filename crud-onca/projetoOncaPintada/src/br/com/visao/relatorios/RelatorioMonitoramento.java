/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.visao.relatorios;

import br.com.controle.Monitoramento;
import br.com.controle.RelatorioHistorico;
import br.com.entidade.ManterMonitoramentoDAO;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabi
 */
public class RelatorioMonitoramento extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RelatorioMonitoramento.class.getName());

    /**
     * Creates new form RelatórioMonitoramento
     */
    public RelatorioMonitoramento() {
        initComponents();
        
        RelatorioHistorico monitoramento = new RelatorioHistorico();
        ArrayList<RelatorioHistorico> listaMonitoramentos = null;
        ManterMonitoramentoDAO monitoramentoDAO = new ManterMonitoramentoDAO();

        DefaultTableModel tabelaMonitoramento = (DefaultTableModel) jTrelatorioMonitoramento.getModel();
        tabelaMonitoramento.setRowCount(0);
        try {
            listaMonitoramentos = monitoramentoDAO.historicoMonitoramento();
        } catch (Exception ex) {
            logger.getLogger(RelatorioMonitoramento.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < listaMonitoramentos.size(); i++) {
            monitoramento = listaMonitoramentos.get(i);
            tabelaMonitoramento.addRow(new Object[]{
                monitoramento.getNome(),
                monitoramento.getSexo(),
                monitoramento.getIdade(),
                monitoramento.getHabitat(),
                monitoramento.getQuantidadeFemeasBrasil(),
                monitoramento.getQuantidadeMachosBrasil(),
                monitoramento.getDataUltimaObservacao(),
                monitoramento.getInViva()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTrelatorioMonitoramento = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Fira Sans", 0, 18)); // NOI18N
        jLabel1.setText("Relatório de monitoramento das onças pintadas no Brasil");

        jTrelatorioMonitoramento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Sexo", "Idade", "Habitat", "Qtd. fêmeas", "Qtd. machos", "Data u. obs.", "Viva"
            }
        ));
        jScrollPane1.setViewportView(jTrelatorioMonitoramento);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new RelatorioMonitoramento().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTrelatorioMonitoramento;
    // End of variables declaration//GEN-END:variables
}
