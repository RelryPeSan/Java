/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarras;

import java.awt.CardLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class PanelPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form PanelPrincipal
     */
    public PanelPrincipal() {
        jarra1 = new Jarra("Jarra 3L", 3);
        jarra2 = new Jarra("Jarra 4L", 4);
        initComponents();
        setLocationRelativeTo(null);
    }

    public void updateBar() {
        pBarJarra1.setValue(jarra1.getVolumeAtual());
        pBarJarra2.setValue(jarra2.getVolumeAtual());
        verificaFim();
    }

    public void verificaFim() {
        if (pBarJarra2.getValue() == 2) {
            JOptionPane.showMessageDialog(null, "FIM DE JOGO!!!\nVocê venceu, parabens.\n\n"
                    + "Desenvolvedores:\n"
                    + "Relry Pereira\n"
                    + "Thayson Rodrigues\n"
                    + "Luccas Fabio\n");
            
            CardLayout cl = (CardLayout) cl_main.getLayout();
            cl.show(cl_main, "pnl_main");
            
            txtInfo.setText(null);
            jarra1.setVolumeAtual(0);
            jarra2.setVolumeAtual(0);
            updateBar();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        cl_main = new javax.swing.JPanel();
        pnl_main = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        pnl_jogo = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtInfo = new javax.swing.JTextPane();
        pBarJarra1 = new javax.swing.JProgressBar();
        pBarJarra2 = new javax.swing.JProgressBar();
        btnEncherJarra1 = new javax.swing.JButton();
        btnEsvaziarJarra1 = new javax.swing.JButton();
        btnTransferirJarra1 = new javax.swing.JButton();
        btnEncherJarra2 = new javax.swing.JButton();
        btnEsvaziarJarra2 = new javax.swing.JButton();
        btnTransferirJarra2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo das Jarras");
        setResizable(false);

        cl_main.setLayout(new java.awt.CardLayout());

        pnl_main.setBackground(new java.awt.Color(153, 153, 255));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("JOGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setText("Jogo das Jarras:\n\nTente deixar a jarra de 4 litros apenas com 2 litros dentro dela.\nVocê só pode fazer as seguintes ações:\n\n1. Encher o jarro selecionado\n2. Esvaziar o jarro selecionado\n3. Transferir todo o conteudo cabivel de um jarro para o outro.\n\nBom jogo!");
        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout pnl_mainLayout = new javax.swing.GroupLayout(pnl_main);
        pnl_main.setLayout(pnl_mainLayout);
        pnl_mainLayout.setHorizontalGroup(
            pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnl_mainLayout.setVerticalGroup(
            pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_mainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        cl_main.add(pnl_main, "pnl_main");

        jScrollPane3.setViewportView(txtInfo);

        pBarJarra1.setMaximum(3);
        pBarJarra1.setOrientation(1);

        pBarJarra2.setMaximum(4);
        pBarJarra2.setOrientation(1);

        btnEncherJarra1.setText("Encher");
        btnEncherJarra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncherJarra1ActionPerformed(evt);
            }
        });

        btnEsvaziarJarra1.setText("Esvaziar");
        btnEsvaziarJarra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsvaziarJarra1ActionPerformed(evt);
            }
        });

        btnTransferirJarra1.setText("Transferir");
        btnTransferirJarra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferirJarra1ActionPerformed(evt);
            }
        });

        btnEncherJarra2.setText("Encher");
        btnEncherJarra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncherJarra2ActionPerformed(evt);
            }
        });

        btnEsvaziarJarra2.setText("Esvaziar");
        btnEsvaziarJarra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsvaziarJarra2ActionPerformed(evt);
            }
        });

        btnTransferirJarra2.setText("Transferir");
        btnTransferirJarra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferirJarra2ActionPerformed(evt);
            }
        });

        jLabel1.setText("1L");

        jLabel2.setText("2L");

        jLabel3.setText("3L");

        jLabel4.setText("1L");

        jLabel5.setText("2L");

        jLabel6.setText("3L");

        jLabel7.setText("4L");

        javax.swing.GroupLayout pnl_jogoLayout = new javax.swing.GroupLayout(pnl_jogo);
        pnl_jogo.setLayout(pnl_jogoLayout);
        pnl_jogoLayout.setHorizontalGroup(
            pnl_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_jogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(pnl_jogoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnl_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnTransferirJarra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEncherJarra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pBarJarra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEsvaziarJarra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addGroup(pnl_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnTransferirJarra2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEncherJarra2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEsvaziarJarra2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pBarJarra2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(35, 35, 35))
        );
        pnl_jogoLayout.setVerticalGroup(
            pnl_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_jogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(pnl_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnl_jogoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel5)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4))
                    .addComponent(pBarJarra2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnl_jogoLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addGap(9, 9, 9)
                            .addComponent(jLabel1))
                        .addComponent(pBarJarra1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_jogoLayout.createSequentialGroup()
                        .addComponent(btnEncherJarra1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEsvaziarJarra1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTransferirJarra1))
                    .addGroup(pnl_jogoLayout.createSequentialGroup()
                        .addComponent(btnEncherJarra2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEsvaziarJarra2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTransferirJarra2)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        cl_main.add(pnl_jogo, "pnl_jogo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cl_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cl_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout cl = (CardLayout) cl_main.getLayout();
        cl.show(cl_main, "pnl_jogo");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEncherJarra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncherJarra1ActionPerformed
        txtInfo.setText(txtInfo.getText() + jarra1.encherJarra());
        updateBar();
    }//GEN-LAST:event_btnEncherJarra1ActionPerformed

    private void btnEsvaziarJarra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsvaziarJarra1ActionPerformed
        txtInfo.setText(txtInfo.getText() + jarra1.esvaziarJarra());
        updateBar();
    }//GEN-LAST:event_btnEsvaziarJarra1ActionPerformed

    private void btnTransferirJarra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirJarra1ActionPerformed
        txtInfo.setText(txtInfo.getText() + jarra1.transferirVolume(jarra2));
        updateBar();
    }//GEN-LAST:event_btnTransferirJarra1ActionPerformed

    private void btnEncherJarra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncherJarra2ActionPerformed
        txtInfo.setText(txtInfo.getText() + jarra2.encherJarra());
        updateBar();
    }//GEN-LAST:event_btnEncherJarra2ActionPerformed

    private void btnEsvaziarJarra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsvaziarJarra2ActionPerformed
        txtInfo.setText(txtInfo.getText() + jarra2.esvaziarJarra());
        updateBar();
    }//GEN-LAST:event_btnEsvaziarJarra2ActionPerformed

    private void btnTransferirJarra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirJarra2ActionPerformed
        txtInfo.setText(txtInfo.getText() + jarra2.transferirVolume(jarra1));
        updateBar();
    }//GEN-LAST:event_btnTransferirJarra2ActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelPrincipal().setVisible(true);
            }
        });
    }
    

    Jarra jarra1;
    Jarra jarra2;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEncherJarra1;
    private javax.swing.JButton btnEncherJarra2;
    private javax.swing.JButton btnEsvaziarJarra1;
    private javax.swing.JButton btnEsvaziarJarra2;
    private javax.swing.JButton btnTransferirJarra1;
    private javax.swing.JButton btnTransferirJarra2;
    private javax.swing.JPanel cl_main;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JProgressBar pBarJarra1;
    private javax.swing.JProgressBar pBarJarra2;
    private javax.swing.JPanel pnl_jogo;
    private javax.swing.JPanel pnl_main;
    private javax.swing.JTextPane txtInfo;
    // End of variables declaration//GEN-END:variables
}
