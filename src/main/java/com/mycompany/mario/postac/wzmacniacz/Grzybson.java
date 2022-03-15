/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.postac.wzmacniacz;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.mario.Game;
import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import com.mycompany.mario.plytka.plytka;
import com.mycompany.mario.postac.postac;
import java.awt.Graphics;
import java.util.Random;

import com.mycompany.mario.Game;
import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import com.mycompany.mario.plytka.plytka;
import com.mycompany.mario.postac.postac;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author kryst
 */
public class Grzybson extends postac {

    private Random random = new Random();
    int predkoscXGrzyba = 0, predkoscYGrzyba = 0;

    public Grzybson(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik, int typ) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
        this.typ = typ;
        int kierunek = random.nextInt(2);
        switch (kierunek) {
            case 0:
                predkoscXGrzyba = -5;
                break;
            case 1:
                predkoscXGrzyba = 5;
                break;
        }
        Grawitacja = 10.0;
        skok = true;
    }

    @Override
    public void render(Graphics g) {
        switch (getTyp()) {
            case 0:
                g.drawImage(Game.spriteGrzybson.getBufferedImage(), x, y, szerokosc, wysokosc, null);
                break;
            case 1:
                g.drawImage(Game.spriteGrzybsonZycia.getBufferedImage(), x, y, szerokosc, wysokosc, null);
                break;

        }
    }

    @Override
    public void tik() {
        x += predkoscXGrzyba;
        y += predkoscYGrzyba;
        for (int i = 0; i < przewodnik.Plytka.size(); i++) {
            plytka t = przewodnik.Plytka.get(i);
            if (t.solid) {
                if (DodajGraniceLewo().intersects(t.DodajGranice())) {
                    predkoscXGrzyba = 4;
                }
                if (DodajGranicePrawo().intersects(t.DodajGranice())) {
                    predkoscXGrzyba = -4;
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
            predkoscYGrzyba = ((int) -Grawitacja);
            spadanie = false;
            if (Grawitacja <= 0.6) {
                skok = false;
                spadanie = true;
            }
        }
        if (spadanie) {
            Grawitacja += 0.3;
            predkoscYGrzyba = ((int) Grawitacja);
        }
    }
}
