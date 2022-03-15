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
public class Efekty extends plytka {

    private int ramka = 1;
    private int opoznienie_ramki = 0;

    private boolean opadanie = false;

    public Efekty(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
    }

    @Override
    public void render(Graphics g) {
            g.drawImage(Game.spriteGwiazda.getBufferedImage(), y, x, wysokosc, szerokosc, null);
    }

    @Override
    public void tik() {
        opoznienie_ramki++;

        if (opoznienie_ramki >= 3) {
            ramka++;
            opoznienie_ramki = 0;
        }
        if (ramka > Game.spriteEfekty.length - 1) {
            opadanie = true;
        }
        if (ramka >= 6) {
            nie_zyjesz_plytka();
        }
    }

}
