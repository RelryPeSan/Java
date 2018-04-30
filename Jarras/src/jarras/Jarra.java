/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarras;

/**
 *
 * @author Administrador
 */
public class Jarra {

    private int capacidade;
    private int volumeAtual;
    private String nome;

    public Jarra(String nome, int max) {
        
        this.nome = nome;
        capacidade = max;
        volumeAtual = 0;

    }

    public String encherJarra() {
        volumeAtual = capacidade;
        return nome + " foi cheia, agora com: " + volumeAtual + "/" + capacidade + " Litros\n";
    }

    public String esvaziarJarra() {

        volumeAtual = 0;
        return nome + " foi esvaziada, agora com: " + volumeAtual + "/" + capacidade + "Litros\n";
    }

    public String transferirVolume(Jarra j) {

        int vol = j.getCapacidade() - j.getVolumeAtual();
        vol = vol > volumeAtual?volumeAtual:vol;
        j.volumeAtual += vol;
        volumeAtual -= vol;
        return nome+ " transferiu: " + vol + " Litros para a "+j.nome+" de capacidade " + j.capacidade +" Litros\n";
        
    }
    
    public void status(){
        System.out.println(nome + ": " + volumeAtual + "/" + capacidade +"\n");
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getVolumeAtual() {
        return volumeAtual;
    }

    public void setVolumeAtual(int volumeAtual) {
        this.volumeAtual = volumeAtual;
    }

}
