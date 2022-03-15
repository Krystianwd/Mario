/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.postac;
import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import com.mycompany.mario.Status.StatusZolwik;
import com.mycompany.mario.plytka.plytka;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author kryst
 */
public class Zolwik extends postac {

    public int predkoscXZolwik = 0, predkoscYZolwik = 0;
    public int obrocenieTwarzyZolwik = 0;
    int opoznienie_ramki = 0, ramka = 0;
    private Random random = new Random();

    public Zolwik(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
        int kierunek = random.nextInt(2);
        switch (kierunek) {
            case 0:
                predkoscXZolwik = -5;
                obrocenieTwarzyZolwik = 0;
                break;
            case 1:
                predkoscXZolwik = 5;
                obrocenieTwarzyZolwik = 1;
                break;
        }
        statuszolwik = StatusZolwik.CHODZENIE;
    }

    @Override
    public void render(Graphics g) {
        if (statuszolwik == StatusZolwik.CHODZENIE) {
            g.setColor(Color.red);
        }
        if (statuszolwik == StatusZolwik.KRECENIE) {
            g.setColor(Color.green);
        }
        if (statuszolwik == StatusZolwik.SKORUPA) {
            g.setColor(Color.MAGENTA);
        }
        g.fillRect(x, y, szerokosc, wysokosc);
    }

    @Override
    public void tik() {
        x += predkoscXZolwik;
        y += predkoscYZolwik;
        for (int i = 0; i < przewodnik.Plytka.size(); i++) {
            plytka t = przewodnik.Plytka.get(i);
            if (t.solid) {
                if (DodajGraniceDol().intersects(t.DodajGranice())) {
                    setPredkoscY(0);
                }
                if (DodajGraniceLewo().intersects(t.DodajGranice())) {
                    predkoscXZolwik = 5;
                    obrocenieTwarzyZolwik = 1;
                }
                if (DodajGranicePrawo().intersects(t.DodajGranice())) {
                    predkoscXZolwik = -5;
                    obrocenieTwarzyZolwik = 0;
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
