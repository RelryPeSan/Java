/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.batalha;

import core.Frame;
import static core.Frame.MAX_COLUNA;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author CMU
 */
public class PanelJogo extends javax.swing.JPanel {

    // array para armazenar os Navios com suas posiçoes em campo
    ArrayList<Navio> naviosDoInimigo;
    static ArrayList<Navio> naviosDoPlayer;
    static ArrayList<Integer> opcoesHitCpu;

    // -1 - CPU Win
    // 0 - Jogo em andamento
    // 1 - Player Win
    int endGame = 0;

    /**
     * Creates new form PanelJogo
     */
    public PanelJogo() {
        init();
    }

    public PanelJogo(ArrayList<Navio> nPlayer) {
        naviosDoPlayer = nPlayer;
        init();
        System.out.println(" --- Jogo ---");
        for (Navio n : naviosDoPlayer) {
            System.out.println("pos: " + Arrays.toString(n.getPositions()));
        }
        System.out.println(" ------------");
    }

    private void init() {
        // instanciando e inicializando objetos
        naviosDoInimigo = new ArrayList<>();
        opcoesHitCpu = new ArrayList<>();
        for (int x = 0; x < (MAX_COLUNA * MAX_COLUNA); x++) {
            opcoesHitCpu.add(x);
        }

        initComponents();
        criaCelulas();

        // cria os naviosDoInimigo em campo - tamanho é passado por parametro
        // range: 1 - 5
        for (int x : Frame.porteNavios) {
            insertNavio(x);
        }

        // insere os navios no campo do Player
        putNavio();
    }

    public void criaCelulas() {
        // cria todas as celulas do campo conforme tamanho de MAX_COLUNA
        int totalCelulas = (MAX_COLUNA * MAX_COLUNA);

        // celulas do campo inimigo
        for (int x = 0; x < totalCelulas; x++) {
            pnlCampoInimigo.add(new PanelCelulaAgua(x) {
                // sobrescreve a função de quando o mouse é pressionado na classe da celula
                // assim chama uma função da classe Frame
                @Override
                public void mousePressed() {
                    // se o jogo tiver terminado corta as funções
                    if (endGame != 0) {
                        return;
                    }

                    //verifica se o player não esta clicando em uma celula já clicada
                    if (getShot()) {
                        return;
                    }

                    super.mousePressed();

                    jogadaCpu();
                    checkFim(getId());
                }

                @Override
                public void mouseEntered() {
                    // se o jogo tiver terminado corta as funções
                    if (endGame != 0) {
                        return;
                    }
                    super.mouseEntered(); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited() {
                    // se o jogo tiver terminado corta as funções
                    if (endGame != 0) {
                        return;
                    }
                    super.mouseExited(); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }

        // celulas do jogador
        for (int x = 0; x < totalCelulas; x++) {
            PanelCelulaAgua pca = new PanelCelulaAgua(x);

            // remove todos os listeners do mouse para as celulas do player
            for (java.awt.event.MouseListener ml : pca.getMouseListeners()) {
                System.out.println("ml: " + ml);
                pca.removeMouseListener(ml);
            }
            pnlCampoPlayer.add(pca);
        }
//        // zera todos os listeners do mouse para as celulas do player
//                @Override
//                public void mouseEntered() {
//                }
//
//                @Override
//                public void mouseExited() {
//                }
//
//                @Override
//                public void mousePressed() {
//                }
    }

    public void jogadaCpu() {
        if (opcoesHitCpu.size() <= 0) {
            return;
        }

        int cpuHit = opcoesHitCpu.remove(intRandom(opcoesHitCpu.size()));
        System.out.println("cpuHit: " + cpuHit);
        LOOP_NAVIOSPLAYER:
        for (int x = 0; x < naviosDoPlayer.size(); x++) {
            for (int y : naviosDoPlayer.get(x).getPositions()) {
                if (y == cpuHit) {
                    int index = naviosDoPlayer.get(x).cleanPosition(y);
                    ((PanelNavioStatus) pnlNaviosPlayer.getComponent(x)).setExplosion(index);
                    System.out.println("CPU acertou um tiro");
                    break LOOP_NAVIOSPLAYER;
                }
            }
        }

        // CPU pressiona a celula
        ((PanelCelulaAgua) pnlCampoPlayer.getComponent(cpuHit)).mousePressed();

        for (Navio n : naviosDoPlayer) {
            System.out.println("n: " + Arrays.toString(n.getPositions()));
        }
//        for(int x = 0; x < naviosDoPlayer.size(); x++){
//            for(int y : naviosDoPlayer.get(x).getPositions()){
//                System.out.println("posPlayer: " + y);
//            }
//        }
    }

    public boolean checkFim(int id) {

        // nomenclatura para quebrar este laço 'for' dentro de outro laço
        LOOP_NAVIOS:
        for (int n = 0; n < naviosDoInimigo.size(); n++) { // conta quantos naviosDoInimigo existem em campo

            // forech para pegar as possições do navio 'n'
            for (int i : naviosDoInimigo.get(n).getPositions()) {

                // comapra se o id da celula clicada é igual a posição 'i' do navio 'n'
                if (id == i) {
                    int index = naviosDoInimigo.get(n).cleanPosition(id);
                    ((PanelNavioStatus) pnlNaviosInimigo.getComponent(n)).setExplosion(index);

                    // verifica se o navio foi destruido para retirar do array
//                    if (naviosDoInimigo.get(n).isDestroyed()) {
//                        naviosDoInimigo.remove(n);
//                    }// se retirar atrapalha/bug na verificação
                    // quebra o looping do primeiro for
                    break LOOP_NAVIOS;
                }
            }
        }

        // verifica se todos os naviosDoInimigo foram destruido
        LOOP_VERIFICA_PLAYER_WIN:
        {
            for (Navio n : naviosDoInimigo) {
                for (int x : n.getPositions()) {
                    if (x != -1) {
                        break LOOP_VERIFICA_PLAYER_WIN;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "PARABÉNS!!!\nVocê venceu o jogo. Obrigado por jogar\n\nDesenvolvedores:\nLuccas Fabio\nRelry Pereira\nThayson Rodrigues");
            endGame = 1; // Player Win
            return true;
        }

        LOOP_VERIFICACPU_WIN:
        {
            for (Navio n : naviosDoPlayer) {
                for (int x : n.getPositions()) {
                    if (x != -1) {
                        break LOOP_VERIFICACPU_WIN;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "DERROTADO!!!\nVocê deu o seu melhor, quem sabe na próxima. Obrigado por jogar\n\nDesenvolvedores:\nLuccas Fabio\nRelry Pereira\nThayson Rodrigues");
            endGame = -1; // CPU Win
            return true;
        }

        return false;
    }

    public void putNavio() {
        for (Navio n : naviosDoPlayer) {
            System.out.println("n: " + Arrays.toString(n.getPositions()));
            for (int x : n.getPositions()) {
                ((PanelCelulaAgua) pnlCampoPlayer.getComponent(x)).setCheckNavio(true);
            }
        }
    }

    public void insertNavio(int tam) {

        // 0 - horizontal, 1 - vertical
        int direction = 0;

        int posInicioNavio = 0;
        Navio newNav = null;
        boolean runRandom = true;

        // gera numero aleatorio enquanto não gerar num campo valido
        // OBSERVAÇÃO: pode gerar looping infinito dependendo da quantidade de naviosDoInimigo
        // melhorar assim que possivel
        int tentativa = 1;
        System.out.println("Geração de Navio de porte: " + tam);
        while (runRandom) {
            System.out.println("Tentativa de geração: " + tentativa++);
            runRandom = false;

            // sorteia a direção do navio, 0 para horizontal e 1 para vertical
            direction = (int) (java.lang.Math.random() * MAX_COLUNA) % 2;
            do {
                posInicioNavio = intRandom(Frame.MAX_COLUNA * Frame.MAX_COLUNA);
            } while ((posInicioNavio % MAX_COLUNA > MAX_COLUNA - tam && direction == 0) || (posInicioNavio / MAX_COLUNA > MAX_COLUNA - tam && direction == 1));

            newNav = new Navio(posInicioNavio, tam, direction);
            // verifica se não foi gerado em cima de outro navio
            LOOP_SOBREPOSICAO:
            for (Navio nav : naviosDoInimigo) {
                for (int x : nav.getPositions()) {
                    for (int poNewNav : newNav.getPositions()) {
                        if (poNewNav == x) {
                            System.out.println("Sobrepos outra embarcação. Gerar outro lugar...");
                            runRandom = true;
                            break LOOP_SOBREPOSICAO;
                        }
                    }
                }
            }
        }
        System.out.println("Navio gerado.\nidCelula: " + posInicioNavio + "\ndireção: " + direction);
        System.out.println("");

        // adiciona informações do navio dentro deste array
        naviosDoInimigo.add(newNav);

        // setando os checkNavio's das celulas
        for (int x : naviosDoInimigo.get(naviosDoInimigo.size() - 1).getPositions()) {
//            System.out.println("x: " + x);
            getCelula(x).setCheckNavio(true);
        }

        // adiciona um navio no panel de status
        pnlNaviosInimigo.add(new PanelNavioStatus(tam));
        pnlNaviosPlayer.add(new PanelNavioStatus(tam, 20));
    }

    public int intRandom(int max) {
        return (int) (java.lang.Math.random() * max);
    }

    public PanelCelulaAgua getCelula(int id) {
        return ((PanelCelulaAgua) pnlCampoInimigo.getComponent(id));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        pnlCampoInimigo = new PanelCampoBatalha();
        jLabel3 = new javax.swing.JLabel();
        pnlNaviosInimigo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnlCampoPlayer = new PanelCampoBatalha();
        pnlNaviosPlayer = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Campo Inimigo");

        pnlCampoInimigo.setBackground(new java.awt.Color(0, 255, 255));
        pnlCampoInimigo.setLayout(new java.awt.GridLayout(MAX_COLUNA, MAX_COLUNA));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Navios inimigos");

        pnlNaviosInimigo.setBackground(new java.awt.Color(255, 255, 153));
        pnlNaviosInimigo.setLayout(new java.awt.GridLayout(10, 1));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Meu Campo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        pnlCampoPlayer.setBackground(new java.awt.Color(153, 153, 255));
        pnlCampoPlayer.setMaximumSize(new java.awt.Dimension(200, 200));
        pnlCampoPlayer.setMinimumSize(new java.awt.Dimension(200, 200));
        pnlCampoPlayer.setPreferredSize(new java.awt.Dimension(200, 200));
        pnlCampoPlayer.setLayout(new java.awt.GridLayout(MAX_COLUNA, MAX_COLUNA));

        pnlNaviosPlayer.setBackground(new java.awt.Color(102, 255, 153));
        pnlNaviosPlayer.setMinimumSize(new java.awt.Dimension(200, 200));
        pnlNaviosPlayer.setLayout(new java.awt.GridLayout(5, 2));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNaviosPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCampoPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlCampoPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlNaviosPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCampoInimigo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlNaviosInimigo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlNaviosInimigo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCampoInimigo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlCampoInimigo;
    private javax.swing.JPanel pnlCampoPlayer;
    private javax.swing.JPanel pnlNaviosInimigo;
    private javax.swing.JPanel pnlNaviosPlayer;
    // End of variables declaration//GEN-END:variables
}
