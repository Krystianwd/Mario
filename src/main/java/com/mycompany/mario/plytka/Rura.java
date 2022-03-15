/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.plytka;

import com.mycompany.mario.Game;
import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import com.mycompany.mario.postac.Roslina;
import java.awt.Graphics;

/**
 *
 * @author kryst
 */
public class Rura extends plytka {

    public Rura(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik, int obrocenie, boolean roslina) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
        this.obrocenie = obrocenie;
        if (roslina) {
            przewodnik.DodajPostac(new Roslina(x, y, 64, 128, true, ID.roslina, przewodnik));
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Game.spriteRura.getBufferedImage(), x, y, szerokosc, wysokosc, null);

    }

    @Override
    public void tik() {
    }

}
