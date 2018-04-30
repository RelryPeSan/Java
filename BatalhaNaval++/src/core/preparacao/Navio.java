/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.preparacao;

import core.Frame;

/**
 *
 * @author CMU
 */
public class Navio {

    // armazena os index do array
    private int[] position;
    private int id;
    private int direction;

    public Navio() {
    }
    
    public void initObject(int id, int tamanho, int direction){
        position = new int[tamanho];
        this.id = id;
        this.direction = direction;
        init();
    }

    public Navio(int id, int tamanho, int direction) {
        position = new int[tamanho];
        this.id = id;
        this.direction = direction;
        init();
    }

    public int getId() {
        return id;
    }

    public int getDirection() {
        return direction;
    }

    // metodo para setar todas as celulas que o navio estará
    private void init() {
        for (int x = 0; x < position.length; x++) {
            position[x] = id;

            if (direction == 0) {
                id++;
            } else {
                id += Frame.MAX_COLUNA;
            }
        }
    }

    // retorna um array com as posições do navio
    public int[] getPositions() {
        return position;
    }

    // Limpa a posição do navio
    // -1 significa que aquela parte já foi bombardeada
    public int cleanPosition(int pos) {
        int index = 0;
        for (index = 0; index < position.length; index++) {
            if (position[index] == pos) {
                position[index] = -1;
                break;
            }
        }
        return index;
    }

    public boolean isDestroyed() {
        for (int x : position) {
            if (x != -1) {
                return false;
            }
        }
        return true;
    }

}
