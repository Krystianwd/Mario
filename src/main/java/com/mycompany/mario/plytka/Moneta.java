/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.plytka;

import com.mycompany.mario.Game;
import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import java.awt.Graphics;

/**
 *
 * @author kryst
 */
public class Moneta extends plytka{

    public Moneta(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
    }

    @Override
    public void render(Graphics g) {
            g.drawImage(Game.spriteMoneta.getBufferedImage(), x, y,szerokosc,wysokosc, null);
    }

    @Override
    public void tik() {
    }
    
}
