/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.postac;

import com.mycompany.mario.Game;
import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import com.mycompany.mario.plytka.plytka;
import java.awt.Graphics;

/**
 *
 * @author kryst
 */

public class Kula extends postac {
    int PredkoscKuli = 0;
    public Kula(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik,int obrocenie) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
            switch (obrocenie) {
            case 0:
                PredkoscKuli = -9;
                break;
            case 1:
                PredkoscKuli = 9;
                break;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Game.spriteGwiazda.getBufferedImage(), x, y, szerokosc, wysokosc, null);
    }

    @Override
    public void tik() {
        x+= PredkoscKuli;
        for (int i = 0; i < przewodnik.Postac.size(); i++) {
            postac m = przewodnik.Postac.get(i);
            if(m.getID()== id.goomba || m.getID() == id.roslina || m.getID() == id.zolwik){
            if(DodajGranice().intersects(m.DodajGranice())){
                m.nie_zyjesz_mob();
                //nie_zyjesz();
            }}
        }
        for (int i = 0; i < przewodnik.Plytka.size(); i++) {
            plytka m = przewodnik.Plytka.get(i);
            if (m.solid) {
                if (DodajGraniceLewo().intersects(m.DodajGranice()) || DodajGranicePrawo().intersects(m.DodajGranice())) {
                    nie_zyjesz_mob();
                }
            }
        }
    }

}
