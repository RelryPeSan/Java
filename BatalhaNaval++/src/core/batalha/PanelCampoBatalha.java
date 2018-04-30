/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.batalha;

import java.awt.Graphics;

/**
 *
 * @author CMU
 */
public class PanelCampoBatalha extends javax.swing.JPanel{

    javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/img/mar.jpg"));
    java.awt.Image imageMar;

    public PanelCampoBatalha() {
        super();
        
        imageMar = icon.getImage().getScaledInstance(500, 500, 0);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        grphcs.drawImage(imageMar, 0, 0, this);
    }
    
}
