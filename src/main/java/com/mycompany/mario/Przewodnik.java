/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario;

import static com.mycompany.mario.Game.Monety;
import com.mycompany.mario.plytka.BlokWzmocnienia;
import com.mycompany.mario.plytka.Flaga;
import com.mycompany.mario.plytka.Moneta;
import com.mycompany.mario.plytka.Rura;
import com.mycompany.mario.plytka.Sciana;
import com.mycompany.mario.plytka.ScianaDirt;
import com.mycompany.mario.plytka.plytka;
import com.mycompany.mario.postac.BossWieza;
import com.mycompany.mario.postac.Goomba;
import com.mycompany.mario.postac.Gracz;
import com.mycompany.mario.postac.Gwiazda;
import com.mycompany.mario.postac.postac;
//import com.mycompany.mario.postac.Grzybson;
import com.mycompany.mario.postac.wzmacniacz.Grzybson;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author kryst
 */
public class Przewodnik {

    public LinkedList<postac> Postac = new LinkedList<postac>();
    public LinkedList<plytka> Plytka = new LinkedList<plytka>();
    public Grzybson grzybson;

    public void renderuj(Graphics g) {
        for (int i = 0; i < Postac.size(); i++) {
            postac m = Postac.get(i);
            if (Game.getWidocznyObszar() != null && m.DodajGranice().intersects(Game.getWidocznyObszar())) {
                m.render(g);
            }
        }
        for (int i = 0; i < Plytka.size(); i++) {
            plytka m = Plytka.get(i);
            if (Game.getWidocznyObszar() != null && m.DodajGranice().intersects(Game.getWidocznyObszar())) {
                m.render(g);
            }
        }
        g.drawImage(Game.spriteMoneta.getBufferedImage(), Game.getWidocznyObszar().x+30, Game.getWidocznyObszar().y-20, 75, 75, null);
        g.setColor(Color.white);
        g.setFont(new Font("Courier", Font.BOLD, 20));
        g.drawString("x" + Monety, Game.getWidocznyObszar().x+100, Game.getWidocznyObszar().y+40);
        g.drawImage(Game.spriteGracz[0].getBufferedImage(), Game.getWidocznyObszar().x+1000, Game.getWidocznyObszar().y-20, 75, 75, null);
        g.setColor(Color.white);
        g.setFont(new Font("Courier", Font.BOLD, 20));
        g.drawString("x" + Game.zycia, Game.getWidocznyObszar().x+1000, Game.getWidocznyObszar().y+40);
    }

    public void tik() {
        for (int i = 0; i < Postac.size(); i++) {
            postac m = Postac.get(i);
            m.tik();
        }
        for (int i = 0; i < Plytka.size(); i++) {
            plytka m = Plytka.get(i);
            if (Game.getWidocznyObszar() != null && m.DodajGranice().intersects(Game.getWidocznyObszar())) {
                m.tik();
            }
        }
    }

    public void DodajPostac(postac m) {
        Postac.add(m);
    }

    public void UsunPostac(postac m) {
        Postac.remove(m);
    }

    public void Dodajplytke(plytka m) {
        Plytka.add(m);
    }

    public void Usunplytke(plytka m) {
        Plytka.remove(m);
    }

    public void wyczyscPoziom() {
        Postac.clear();
        Plytka.clear();
    }

    public void UtworzPoziom(BufferedImage poziom) {
        int szerokosc = poziom.getWidth();
        int wysokosc = poziom.getHeight();

        for (int y = 0; y < wysokosc; y++) {
            for (int x = 0; x < szerokosc; x++) {
                int piksel = poziom.getRGB(x, y);

                int czerwony = (piksel >> 16) & 0xFF;
                int zielony = (piksel >> 8) & 0xFF;
                int niebieski = (piksel) & 0xFF;

                if (czerwony == 0 && zielony == 0 && niebieski == 0) {
                    Dodajplytke(new Sciana(x * 64, y * 64, 64, 64, true, ID.sciana, this));
                }
                if (czerwony == 0 && zielony == 0 && niebieski == 255) {
                    DodajPostac(new Gracz(x * 64, y * 64, 64, 64, false, ID.gracz, this));
                }
                if (czerwony == 255 && zielony == 0 && niebieski == 0) {
                    Dodajplytke(new ScianaDirt(x * 64, y * 64, 64, 64, true, ID.scianadirt, this));
                }
                if (czerwony == 0 && zielony == 255 && niebieski == 0) {
                    DodajPostac(new Gwiazda(x * 64, y * 64, 64, 64, false, ID.gwiazda, this));
                }
                if (czerwony == 255 && zielony == 255 && niebieski == 0) {
                    DodajPostac(new Goomba(x * 64, y * 64, 64, 64, false, ID.goomba, this));
                }
                if (czerwony == 255 && zielony == 170 && niebieski == 0) {
                    Dodajplytke(new BlokWzmocnienia(x * 64, y * 64, 64, 64, true, ID.BlokWzmocnienia, this, Game.spriteKwiatek, 2));
                }
                if (czerwony == 255 && zielony == 170 && niebieski == 170) {
                    Dodajplytke(new BlokWzmocnienia(x * 64, y * 64, 64, 64, true, ID.BlokWzmocnienia, this, Game.spriteGrzybson, 0));
                }
                if (czerwony == 255 && zielony == 170 && niebieski == 240) {
                    Dodajplytke(new BlokWzmocnienia(x * 64, y * 64, 64, 64, true, ID.BlokWzmocnienia, this, Game.spriteGrzybsonZycia, 1));
                }
                if (czerwony == 255 && zielony == 160 && niebieski == 120) {
                    Dodajplytke(new BlokWzmocnienia(x * 64, y * 64, 64, 64, true, ID.BlokWzmocnienia, this, Game.spriteGwiazda, 2));
                }
                if (czerwony == 0 && (zielony > 123 && zielony < 129) && niebieski == 0) {
                    Dodajplytke(new Rura(x * 64, y * 64, 64, 64 * 10, true, ID.rura, this, 128 - zielony, false));
                }
                if (czerwony == 128 && zielony == 126 && niebieski == 0) {
                    Dodajplytke(new Rura(x * 64, y * 64+64, 64, 128, true, ID.rura, this, 128 - zielony, true));
                }
                if (czerwony == 255 && zielony == 255 && niebieski == 128) {
                    Dodajplytke(new Moneta(x * 64, y * 64, 64, 64, true, ID.Moneta, this));
                }
                if (czerwony == 255 && zielony == 0 && niebieski == 255) {
                    DodajPostac(new BossWieza(x * 64, y * 64, 64, 64, true, ID.bosswieza, this, 3));
                }
                if (czerwony == 128 && zielony == 0 && niebieski == 255) {
                    Dodajplytke(new Flaga(x * 64, y * 64, 64, 64 * 5, true, ID.flaga, this));
                }
            }
        }
    }
}
