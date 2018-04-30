/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.preparacao;

import core.Frame;
import core.batalha.PanelCampoBatalha;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author CMU
 */
public class PanelPreparacao extends javax.swing.JPanel {

    private ArrayList<Navio> navioPlayer;

    /**
     * Creates new form PanelPreparacao
     */
    public PanelPreparacao() {
        navioPlayer = new ArrayList<>();
        initComponents();

        for (int x : Frame.porteNavios) {
            navioPlayer.add(new Navio());
        }

        criaNavios();
        criaCelulas();
    }

    public void criaCelulas() {
        int totalCelulas = (Frame.MAX_COLUNA * Frame.MAX_COLUNA);

        for (int x = 0; x < totalCelulas; x++) {
            pnlEditCampo.add(new PanelCelulaPreparacao(x) {
                @Override
                public void enteredCelula() {
                    super.enteredCelula();

                    for (java.awt.Component c : pnlEditNavios.getComponents()) {
                        // verificação de qual navio esta selecionado pela borda azul do Panel do Navio
                        if (((PanelNavioPreparacao) c).isCurrentSelect()) {
                            int porte = ((PanelNavioPreparacao) c).getPorteNavio();

                            // se o navio tiver na horizontal excuta essas funções
                            if (((PanelNavioPreparacao) c).getDirection() == 0) {
                                for (int x = 0; x < porte; x++) {
                                    //verifica se não vai ultrapassar a ultima coluna - Dependendo do tamanho do Navio
                                    if ((getId() + x) % Frame.MAX_COLUNA >= getId() % Frame.MAX_COLUNA) {
                                        if (!getCelulaPreparacao(getId() + x).isCelulaSelecionada()) {
                                            getCelulaPreparacao(getId() + x).setBorderEntered();
                                        } else {
                                            getCelulaPreparacao(getId() + x).setBorderSobreposta();
                                        }
                                    }
                                }
                            }
                            // se o navio tiver na vertical excuta essas funções
                            else {
                                for (int x = 0; x < porte; x++) {
                                    //verifica se não vai ultrapassar a ultima linha - Dependendo do tamanho do Navio
                                    if ((getId() + (x * Frame.MAX_COLUNA)) < Frame.MAX_COLUNA * Frame.MAX_COLUNA) {
                                        if (!getCelulaPreparacao(getId() + (x * Frame.MAX_COLUNA)).isCelulaSelecionada()) {
                                            getCelulaPreparacao(getId() + (x * Frame.MAX_COLUNA)).setBorderEntered();
                                        } else {
                                            getCelulaPreparacao(getId() + (x * Frame.MAX_COLUNA)).setBorderSobreposta();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void exitCelula() {
                    // CORRIGE BUG DO QUADRADO VERMELHO
                    if (isCelulaSelecionada()) {
                        setBorderSelected();
                    } // FIM DA CORREÇÂO DE BUG DO QUADRADO VERMELHO
                    super.exitCelula();

                    for (java.awt.Component c : pnlEditNavios.getComponents()) {
                        // verificação de qual navio esta selecionado pela borda azul do Panel do Navio
                        if (((PanelNavioPreparacao) c).isCurrentSelect()) {
                            int porte = ((PanelNavioPreparacao) c).getPorteNavio();

                            // se o navio tiver na horizontal excuta essas funções
                            if (((PanelNavioPreparacao) c).getDirection() == 0) {
                                for (int x = 0; x < porte; x++) {
                                    if ((getId() + x) % Frame.MAX_COLUNA > getId() % Frame.MAX_COLUNA) {
                                        if (!getCelulaPreparacao(getId() + x).isCelulaSelecionada()) {
                                            getCelulaPreparacao(getId() + x).exitCelula();
                                        } else {
                                            getCelulaPreparacao(getId() + x).setBorderSelected();
                                        }
                                    }
                                }
                            }
                            // se o navio tiver na vertical excuta essas funções
                            else {
                                for (int x = 0; x < porte; x++) {
                                    if ((getId() + (x * Frame.MAX_COLUNA)) < Frame.MAX_COLUNA * Frame.MAX_COLUNA) {
                                        if (!getCelulaPreparacao(getId() + (x * Frame.MAX_COLUNA)).isCelulaSelecionada()) {
                                            getCelulaPreparacao(getId() + (x * Frame.MAX_COLUNA)).setBorder(bordaPadrao);
                                        } else {
                                            getCelulaPreparacao(getId() + (x * Frame.MAX_COLUNA)).setBorderSelected();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void clickCelula() {
                    super.clickCelula();

                    int indexNavio = 0;
                    for (java.awt.Component c : pnlEditNavios.getComponents()) {

                        // verificação de qual navio esta selecionado pela borda AZUL do Panel do Navio
                        if (((PanelNavioPreparacao) c).isCurrentSelect()) {
                            System.out.println("tamanho: " + ((PanelNavioPreparacao) c).getPorteNavio());

                            //armazena o tamanho do navio selecionado
                            int porte = ((PanelNavioPreparacao) c).getPorteNavio();

                            // se o navio tiver na horizontal excuta essas funções
                            if (((PanelNavioPreparacao) c).getDirection() == 0) {
                                // verifica se a seleção não irá sair da coluna
                                if ((getId() + porte - 1) % Frame.MAX_COLUNA < getId() % Frame.MAX_COLUNA) {
                                    JOptionPane.showMessageDialog(null, "Por favor selecione um campo valido para este navio.");
                                    return;
                                }

                                // verifica se o usuario não esta tentando colocar o navio em cima de outro
                                for (int x = 0; x < porte; x++) {
                                    System.out.println("id: " + (getId() + x) + ", select: " + getCelulaPreparacao(getId() + x).isCelulaSelecionada());
                                    if (getCelulaPreparacao(getId() + x).isCelulaSelecionada()) {
                                        JOptionPane.showMessageDialog(null, "Por favor selecione um campo ainda não selecionado para este navio.");
                                        return;
                                    }
                                }
                                
                                // faz tratamento das outras celulas - dependendo do tamanho do Navio
                                for (int x = 0; x < porte; x++) {
                                    if ((getId() + x) % Frame.MAX_COLUNA >= getId() % Frame.MAX_COLUNA) {
                                        getCelulaPreparacao(getId() + x).setBorderSelected();
                                    }
                                }
                            }// fim tratamento navio horizontal
                            // se o navio tiver na vertical excuta essas funções
                            else {
                                // verifica se a seleção não irá sair da coluna
//                                System.out.println("verifi: " + ((getId() + (porte - 1) * Frame.MAX_COLUNA)) + " > " + (Frame.MAX_COLUNA * Frame.MAX_COLUNA));
                                if ((getId() + (porte - 1) * Frame.MAX_COLUNA) >= Frame.MAX_COLUNA * Frame.MAX_COLUNA) {
                                    JOptionPane.showMessageDialog(null, "Por favor selecione um campo valido para este navio.");
                                    return;
                                }
                                
                                // verifica se o usuario não esta tentando colocar o navio em cima de outro
                                for (int x = 0; x < porte; x++) {
                                    System.out.println("id: " + (getId() + (x * Frame.MAX_COLUNA)) + ", select: " + getCelulaPreparacao(getId() + (x * Frame.MAX_COLUNA)).isCelulaSelecionada());
                                    if (getCelulaPreparacao(getId() + (x * Frame.MAX_COLUNA)).isCelulaSelecionada()) {
                                        JOptionPane.showMessageDialog(null, "Por favor selecione um campo ainda não selecionado para este navio.");
                                        return;
                                    }
                                }
                                
                                // faz tratamento das outras celulas - dependendo do tamanho do Navio
                                for (int x = 0; x < porte; x++) {
                                    if ((getId() + (x * Frame.MAX_COLUNA)) < Frame.MAX_COLUNA * Frame.MAX_COLUNA) {
                                        getCelulaPreparacao(getId() + (x * Frame.MAX_COLUNA)).setBorderSelected();
                                    }
                                }
                            } // fim do tratamento do navio vertical 

                            //verifica se o navio em questão já tem posição gravada
                            //se sim, faz as celulas voltarem a serem de bordaPadrao
                            if (getNavioPlayer().get(indexNavio).getPositions() != null) {
                                System.out.println("Navio já tem posição, será sobrescrevida");
                                for (int x : getNavioPlayer().get(indexNavio).getPositions()) {
                                    ((PanelCelulaPreparacao) pnlEditCampo.getComponent(x)).setBorder(bordaPadrao);
                                    ((PanelCelulaPreparacao) pnlEditCampo.getComponent(x)).setCelulaSelecionada(false);
                                }
                            }
                            ((PanelNavioPreparacao) c).setEmCampo(true);
                            ((PanelNavioPreparacao) c).setBorder(PanelNavioPreparacao.bordaVerde);
                            
                            //adiciona as posições dentro do array, para ser passado para o panel da batalha
                            getNavioPlayer().get(indexNavio).initObject(getId(), porte, ((PanelNavioPreparacao) c).getDirection());

                            for (Navio n : getNavioPlayer()) {
                                System.out.println("n: " + Arrays.toString(n.getPositions()));
                            }
                        }
                        indexNavio++; // variavel auxiliar para armazenar valor no array das posições dos navios
                    }
                }
            });
        }
        pnlEditCampo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 51, 4), 1));
    }

    public PanelCelulaPreparacao getCelulaPreparacao(int id) {
        for (java.awt.Component c : pnlEditCampo.getComponents()) {
            if (((PanelCelulaPreparacao) c).getId() == id) {
                return ((PanelCelulaPreparacao) c);
            }
        }
        return null;
    }

    public void criaNavios() {
        for (int size : Frame.porteNavios) {
            pnlEditNavios.add(new PanelNavioPreparacao(size));
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

        pnlEditCampo = new PanelCampoBatalha();
        pnlEditNavios = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        pnlEditCampo.setBackground(new java.awt.Color(0, 255, 255));
        pnlEditCampo.setLayout(new java.awt.GridLayout(Frame.MAX_COLUNA, Frame.MAX_COLUNA));

        pnlEditNavios.setBackground(new java.awt.Color(255, 255, 153));
        pnlEditNavios.setLayout(new java.awt.GridLayout(10, 1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Prepare sua frota para a batalha.");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("CONFIRMAR");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEditCampo, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEditNavios, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlEditNavios, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlEditCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        System.out.println(" --- Preparação ---");
        for (Navio n : navioPlayer) {
            System.out.println("pos: " + Arrays.toString(n.getPositions()));
        }
        System.out.println(" ------------------");
        for (Navio n : navioPlayer) {
            if (n.getPositions() == null) {
                JOptionPane.showMessageDialog(null, "Coloque todos seus navios dentro do campo para continuar.");
                return;
            }
        }
        btnConfirma();
    }//GEN-LAST:event_jButton1MouseClicked

    public void btnConfirma() {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlEditCampo;
    private javax.swing.JPanel pnlEditNavios;
    // End of variables declaration//GEN-END:variables

    public ArrayList<Navio> getNavioPlayer() {
        return navioPlayer;
    }

    public ArrayList<core.batalha.Navio> getNavioPlayerToBatalha() {
        ArrayList<core.batalha.Navio> arrayNavios = new ArrayList<>();
        for (Navio n : navioPlayer) {
            // o getId estava retornando valor dpois do ultimo valor da posição, por isso foi usado "- n.getPositions().length"
            arrayNavios.add(new core.batalha.Navio(n.getId() - n.getPositions().length * (n.getDirection() == 0 ? 1 : Frame.MAX_COLUNA), n.getPositions().length, n.getDirection()));
        }
        return arrayNavios;
    }
}
