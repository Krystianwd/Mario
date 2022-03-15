/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.plytka;

import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 *
 * @author kryst
 */
public class Slad extends plytka {

    private float alpha = (float) 1.0;
    private BufferedImage obraz;
    public Slad(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik,BufferedImage obraz) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
        this.obraz = obraz;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g.drawImage(obraz, x, y, szerokosc,wysokosc,null);
    }

    @Override
    public void tik() {
        alpha-=0.05;
        
        if(alpha<0.06)
        {
            nie_zyjesz_plytka();
        }
    }

}
