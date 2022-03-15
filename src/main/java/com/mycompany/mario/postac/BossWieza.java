/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.postac;

import com.mycompany.mario.Game;
import static com.mycompany.mario.Game.poziom;
import static com.mycompany.mario.Game.zycia;
import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import com.mycompany.mario.Status.StatusBossa;
import com.mycompany.mario.Status.StatusGracza;
import com.mycompany.mario.plytka.plytka;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import static java.lang.Math.abs;
import java.util.Random;

/**
 *
 * @author kryst
 */
public class BossWieza extends postac {

    int predkoscXBossa = 0, PredkoscYBossa = 0;
    int zapisanapredkoscX = 0, obrocenie_twarzy_boss = 0;
    int opoznienie_ramki = 0, ramka = 0;
    private Random random;

    public BossWieza(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik, int hp) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
        this.hp = hp;
        statusbossa = StatusBossa.Bezczynny;
        random = new Random();
    }

    public int CzasSkoku = 0;
    public boolean DodajCzasSkoku = false;

    @Override
    public void render(Graphics g) {
        if (statusbossa == StatusBossa.leczenie || statusbossa == StatusBossa.Bezczynny) {
            g.drawImage(Game.spriteBossWirowanie[1].getBufferedImage(), x, y, szerokosc, wysokosc, null);
        } else if (statusbossa == StatusBossa.wirowanie) {
            g.drawImage(Game.spriteBossWirowanie[ramka].getBufferedImage(), x, y, szerokosc, wysokosc, null);
        } else if (statusbossa == StatusBossa.bieganie || statusbossa == StatusBossa.skakanie) {
            if (obrocenie_twarzy_boss == 1) {
                g.drawImage(Game.spriteBossBieganie[ramka].getBufferedImage(), x, y, szerokosc, wysokosc, null);
            } else {
                g.drawImage(Game.spriteBossBieganie[ramka + 3].getBufferedImage(), x, y, szerokosc, wysokosc, null);
            }

        }
    }

    @Override
    public void tik() {
        System.out.println("predkoscX " + abs(predkoscXBossa));
        x += predkoscXBossa;
        y += PredkoscYBossa;
        if (hp <= 0) {
            nie_zyjesz_mob();
            Game.pokazEkranSmierci = true;
            Game.KoniecGry = true;
            Game.BossZabity = true;
        }
        CzasFazy++;
        if (CzasFazy >= 180 && statusbossa == statusbossa.Bezczynny || (CzasFazy >= 600 && statusbossa != StatusBossa.wirowanie)) {
            wyberzStatus();
        }
        if (statusbossa == StatusBossa.leczenie && CzasFazy >= 180) {
            statusbossa = StatusBossa.wirowanie;
            CzasFazy = 0;
        }
        if (CzasFazy >= 360 && statusbossa == StatusBossa.wirowanie) {
            CzasFazy = 0;
            statusbossa = statusbossa.Bezczynny;
        }
        if (statusbossa == StatusBossa.Bezczynny || statusbossa == StatusBossa.leczenie) {
            predkoscXBossa = 0;
            PredkoscYBossa = 0;
            skok = false;
            spadanie = true;
        }
        if (statusbossa == StatusBossa.skakanie || statusbossa == StatusBossa.bieganie) {
            MoznaAtakowac = true;
        } else {
            MoznaAtakowac = false;
        }
        if (statusbossa == StatusBossa.bieganie || statusbossa == StatusBossa.wirowanie) {
            DodajCzasSkoku = false;
        }
        if (statusbossa == StatusBossa.skakanie) {
            DodajCzasSkoku = true;
            CzasSkoku = 0;
        }
        if (DodajCzasSkoku) {
            CzasSkoku++;
            if (CzasSkoku >= 30) {
                DodajCzasSkoku = false;
                CzasSkoku = 0;
            }
            if (!skok && !spadanie) {
                skok = true;
                Grawitacja = 8.0;
            }
        }

        for (int i = 0; i < przewodnik.Plytka.size(); i++) {
            plytka t = przewodnik.Plytka.get(i);
            if (t.solid) {
                if (DodajGraniceDol().intersects(t.DodajGranice())) {
                    PredkoscYBossa = 0;
                    if (spadanie) {
                        spadanie = false;
                        DodajCzasSkoku = true;
                    }
                }
                if (DodajGraniceLewo().intersects(t.DodajGranice())) {
                    if (abs(zapisanapredkoscX) == 15) {
                        predkoscXBossa = 0;
                        zapisanapredkoscX = 0;
                    }
                    zapisanapredkoscX = predkoscXBossa;
                    predkoscXBossa = 0;
                    if (statusbossa == StatusBossa.bieganie || statusbossa == StatusBossa.skakanie) {
                        predkoscXBossa = abs(zapisanapredkoscX) + 5;
                        obrocenie_twarzy_boss = 1;
                    }
                    x = t.x + t.szerokosc;
                }
                if (DodajGranicePrawo().intersects(t.DodajGranice())) {
                    if (abs(zapisanapredkoscX) == 15) {
                        predkoscXBossa = 0;
                        zapisanapredkoscX = 0;
                    }
                    zapisanapredkoscX = predkoscXBossa;
                    predkoscXBossa = 0;
                    if (statusbossa == StatusBossa.bieganie || statusbossa == StatusBossa.skakanie) {
                        predkoscXBossa = -abs(zapisanapredkoscX) - 5;
                        obrocenie_twarzy_boss = 0;
                    }
                    //x = t.x - szerokosc;
                }
                if (DodajGraniceGora().intersects(t.DodajGranice())) {
                    PredkoscYBossa = 0;
                    if (skok) {
                        skok = false;
                        Grawitacja = 0.0;
                        spadanie = true;
                    }
                }
            }

        }
        for (int i = 0; i < przewodnik.Postac.size(); i++) {
            postac t = przewodnik.Postac.get(i);
            if (t.getID() == id.gracz) {
                if (statusbossa == StatusBossa.skakanie) {
                    if (skok && !spadanie || spadanie && !skok) {
                        if (x >= t.x - 4 && x <= t.x + 4) {
                            predkoscXBossa = 0;
                        } else if (t.x <= x) {
                            predkoscXBossa = -4;
                            obrocenie_twarzy_boss = 0;
                        } else if (t.x >= x) {
                            predkoscXBossa = 3;
                            obrocenie_twarzy_boss = 1;
                        } else {
                            predkoscXBossa = 0;
                        }
                    }
                } else if (statusbossa == StatusBossa.wirowanie) {

                    if (x >= t.x - 4 && x <= t.x + 4) {
                        predkoscXBossa = 0;
                    } else if (t.x <= x) {
                        predkoscXBossa = -3;
                    } else if (t.x >= x) {
                        predkoscXBossa = 3;
                    } else {
                        predkoscXBossa = 0;
                    }
                }
            }
        }
        if (skok) {
            Grawitacja -= 0.15;
            PredkoscYBossa = ((int) -Grawitacja);
            spadanie = false;
            if (Grawitacja <= 0.6) {
                skok = false;
                spadanie = true;
            }
        }
        if (spadanie) {
            Grawitacja += 0.15;
            PredkoscYBossa = ((int) Grawitacja);
        }
        opoznienie_ramki++;
        if (opoznienie_ramki >= 3) {
            ramka++;
            if (ramka >= 3) {
                ramka = 0;
            }
            opoznienie_ramki = 0;
        }
    }

    public void wyberzStatus() {
        int nastepnaFaza = random.nextInt(2);
        if (nastepnaFaza == 0) {
            statusbossa = StatusBossa.bieganie;
            int kierunek = random.nextInt(2);
            if (kierunek == 0) {
                predkoscXBossa = -5;
                obrocenie_twarzy_boss = 0;
            } else {
                predkoscXBossa = 5;
                obrocenie_twarzy_boss = 1;
            }
        } else if (nastepnaFaza == 1) {
            statusbossa = StatusBossa.skakanie;

            skok = true;
            Grawitacja = 8.0;
        }
        CzasFazy = 0;
    }
}
