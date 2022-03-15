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
public class Gwiazda extends postac {

    private Random random = new Random();

    public Gwiazda(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
        int kierunek = random.nextInt(2);
        switch (kierunek) {
            case 0:
                PredokoscX = 4;
                break;
            case 1:
                PredokoscX = -4;
                break;
        }
        Grawitacja = 10.0;
        skok = true;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Game.spriteGwiazda.getBufferedImage(), x, y, szerokosc, wysokosc, null);
    }

    @Override
    public void tik() {
        y += PredkoscY;
        x += PredokoscX;

        for (int i = 0; i < przewodnik.Plytka.size(); i++) {
            plytka t = przewodnik.Plytka.get(i);
            if (t.solid) {
                if (DodajGraniceLewo().intersects(t.DodajGranice())) {
                    setPredokoscX(4);
                }
                if (DodajGranicePrawo().intersects(t.DodajGranice())) {
                    setPredokoscX(-4);
                }

                if (DodajGraniceDol().intersects(t.DodajGranice())) {
                    skok = true;
                    spadanie = false;
                    Grawitacja = 8.0;
                }
            }
        }
        if (skok) {
            Grawitacja -= 0.3;
            PredkoscY = ((int) -Grawitacja);
            spadanie = false;
            if (Grawitacja <= 0.6) {
                skok = false;
                spadanie = true;
            }
        }
        if (spadanie) {
            Grawitacja += 0.3;
            PredkoscY = ((int) Grawitacja);
        }
    }

}
