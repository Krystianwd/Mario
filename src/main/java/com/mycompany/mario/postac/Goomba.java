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
import java.util.Random;

/**
 *
 * @author kryst
 */
public class Goomba extends postac {

    int predkoscXGoomba = 0, predkoscYGoomba = 0;
    int obrocenieTwarzyGoomba = 0;
    int opoznienie_ramki = 0, ramka = 0;
    private Random random = new Random();

    public Goomba(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
        int kierunek = random.nextInt(2);
        switch (kierunek) {
            case 0:
                //setPredokoscX(-5);
                predkoscXGoomba = -5;
                obrocenieTwarzyGoomba = 0;
                break;
            case 1:
                predkoscXGoomba = 5;
                //setPredokoscX(5);
                obrocenieTwarzyGoomba = 1;
                break;
        }
    }

    @Override
    public void render(Graphics g) {
        if (obrocenieTwarzyGoomba == 0) {
            g.drawImage(Game.spriteGoomba[ramka + 5].getBufferedImage(), x, y, szerokosc, wysokosc, null);

        } else if (obrocenieTwarzyGoomba == 1) {
            g.drawImage(Game.spriteGoomba[ramka].getBufferedImage(), x, y, szerokosc, wysokosc, null);
        }

    }

    @Override
    public void tik() {
        x += predkoscXGoomba;
        y += predkoscYGoomba;
        for (int i = 0; i < przewodnik.Plytka.size(); i++) {
            plytka t = przewodnik.Plytka.get(i);
            if (t.solid) {
                if (DodajGraniceDol().intersects(t.DodajGranice())) {
                    setPredkoscY(0);
                    
//                    if (spadanie) {
//                        spadanie = false;
//                    } else {
//                        if (!spadanie) {
//                            spadanie = true;
//                            Grawitacja = 0.0;
//                        }
//
//                    }
                }
                if (DodajGraniceLewo().intersects(t.DodajGranice())) {
                    //setPredokoscX(5);
                    predkoscXGoomba = 5;
                    obrocenieTwarzyGoomba = 1;
                }
                if (DodajGranicePrawo().intersects(t.DodajGranice())) {
                    predkoscXGoomba = -5;
                    //setPredokoscX(-5);
                    obrocenieTwarzyGoomba = 0;
                }
            }
        }
        opoznienie_ramki++;
        if (opoznienie_ramki >= 3) {
            ramka++;
            if (ramka >= 5) {
                ramka = 0;
            }
            opoznienie_ramki = 0;
        }
    }
}
