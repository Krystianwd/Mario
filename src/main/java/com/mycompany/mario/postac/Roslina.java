/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.postac;

import com.mycompany.mario.Game;
import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author kryst
 */
public class Roslina extends postac {

    private int czasCzekania = 0;
    private boolean wRurze, RuszaSie;
    private int PrzebytePixele = 0;

    public Roslina(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
        wRurze = false;
        RuszaSie = false;
    }

    @Override
    public void render(Graphics g) {
        if(!RuszaSie)
        g.drawImage(Game.spriteRoslina.getBufferedImage(), x, y, szerokosc, wysokosc, null);
        else
            g.drawImage(Game.spriteRoslinaRuszaSie.getBufferedImage(), x, y, szerokosc, wysokosc, null);

    }

    @Override
    public void tik() {
        y += PredkoscY;
        if (!RuszaSie) {
            czasCzekania++;
        }
        if (czasCzekania > 180) {
            if (!wRurze) {
                wRurze = true;
            } else {
                wRurze = false;
            }
            RuszaSie = true;
            czasCzekania = 0;
        }
        if (RuszaSie) {
            if (wRurze) {
                setPredkoscY(-2);
            } else {
                setPredkoscY(2);
            }
            PrzebytePixele += PredkoscY;
            if (PrzebytePixele >= wysokosc || PrzebytePixele <= -wysokosc) {
                PrzebytePixele = 0;
                RuszaSie = false;
                PredkoscY = 0;
            }
        }
    }

}
