/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.batalha;

import core.Frame;
import static core.batalha.PanelJogo.naviosDoPlayer;
import java.util.ArrayList;

/**
 *
 * @author CMU
 */
public class InteligenciaCpu {

    final int nivel;
    ArrayList<LookNavio> lookNavios;
    javax.swing.JPanel panelCamposCelulas;

    public InteligenciaCpu(int nivel, javax.swing.JPanel panelCamposCelulas) {
        this.nivel = nivel;
        this.panelCamposCelulas = panelCamposCelulas;
        lookNavios = new ArrayList<>();
    }

    public int jogadaCpu() {
        if (nivel == 1) {
            return intRandom(PanelJogo.opcoesHitCpu.size());
        } else if (nivel == 2) {
            int numHit;
            // sorteia um numero
            if (lookNavios.isEmpty()) {
                numHit = intRandom(PanelJogo.opcoesHitCpu.size());

                LOOP_NAVIOSPLAYER:
                //verificação se a maquina acertou o tiro para armazenar o  valor da celula
                for (int x = 0; x < naviosDoPlayer.size(); x++) {
                    int indexNavio = 0;
                    for (int y : naviosDoPlayer.get(x).getPositions()) {
                        if (y == numHit) {
                            lookNavios.add(new LookNavio(numHit, naviosDoPlayer.get(x).getPositions().length, indexNavio));
                            
                            break LOOP_NAVIOSPLAYER;
                        }
                        indexNavio++;
                    }
                }
                
            }
            else {
                numHit = lookNavios.get(0).navioAcertoAtual + lookNavios.get(0).getDirecoes()[intRandom(lookNavios.get(0).getDirecoes().length)];
                
            }
            ((PanelCelulaAgua) panelCamposCelulas.getComponent(numHit)).mousePressed();
            
        }
        return -1;
    }

    public void huntInicio() {

    }

    public void huntFinal() {

    }

    public int intRandom(int max) {
        return (int) (java.lang.Math.random() * max);
    }

}

class LookNavio {

    // armazena o id da celula que foi acertada
    int navioAcertoAtual = -1;

    // identifica em qual direção a CPU deve procurar as outras partes do navio
    // -2 -> Oeste
    // -1 -> Sul
    //  0 -> Não definido
    //  1 -> Norte
    //  2 -> Leste
    int navioDirecao = 0;

    // Oeste, Sul, Norte, Leste
    boolean[] navioDirecoesDiponiveis = {false, false, false, false};
    private final int[] direcoes = {-1, +Frame.MAX_COLUNA, -Frame.MAX_COLUNA, +1};

    // armazena o tamanho do navio que esta caçando atualmente
    int navioTamanho = -1;

    int[] navioPosicoes;

    public LookNavio(int firstHit, int size, int index) {
        this.navioAcertoAtual = firstHit;
        this.navioTamanho = size;
        this.navioPosicoes[index] = firstHit;
        verificaDirecoes();
    }

    public void verificaDirecoes() {
        if (navioAcertoAtual == -1) {
            return;
        }

        // Oeste
        if ((navioAcertoAtual - 1) % Frame.MAX_COLUNA < navioAcertoAtual % Frame.MAX_COLUNA) {
            navioDirecoesDiponiveis[0] = true;
        }

        // Sul
        if ((navioAcertoAtual + Frame.MAX_COLUNA < (Frame.MAX_COLUNA * Frame.MAX_COLUNA))) {
            navioDirecoesDiponiveis[1] = true;
        }

        // Norte
        if ((navioAcertoAtual - Frame.MAX_COLUNA) >= 0) {
            navioDirecoesDiponiveis[2] = true;
        }

        // Leste
        if ((navioAcertoAtual + 1) % Frame.MAX_COLUNA > navioAcertoAtual % Frame.MAX_COLUNA) {
            navioDirecoesDiponiveis[3] = true;
        }
    }

    public int[] getDirecoes() {
        int count = 0;
        for(boolean b : navioDirecoesDiponiveis){
            if(b){
                count++;
            }
        }
        
        int[] tmp = new int[count];
        count = 0;
        for(int x = 0; x < navioDirecoesDiponiveis.length; x++){
            if(navioDirecoesDiponiveis[x]){
                tmp[count] = direcoes[x];
            }
        }
        
        //retorn só as direções que podem ser chamadas
        return tmp;
    }
}
