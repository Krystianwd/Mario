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
public class Flaga extends plytka {

    public Flaga(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Game.spriteFlaga[0].getBufferedImage(), x, y, szerokosc, 64, null);
        g.drawImage(Game.spriteFlaga[1].getBufferedImage(), x, y + 64, szerokosc, 64, null);
        g.drawImage(Game.spriteFlaga[1].getBufferedImage(), x, y + 128, szerokosc, 64, null);
        g.drawImage(Game.spriteFlaga[1].getBufferedImage(), x, y + 192, szerokosc, 64, null);
        g.drawImage(Game.spriteFlaga[2].getBufferedImage(), x, y + wysokosc - 64, szerokosc, 64, null);
    }

    @Override
    public void tik() {
    }

}
