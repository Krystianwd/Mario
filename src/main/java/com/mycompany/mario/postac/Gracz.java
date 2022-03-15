/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.postac;

import com.mycompany.mario.Game;
import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import com.mycompany.mario.Status.StatusBossa;
import com.mycompany.mario.Status.StatusGracza;
import com.mycompany.mario.Status.StatusZolwik;
import com.mycompany.mario.plytka.Efekty;
import com.mycompany.mario.plytka.Slad;
import com.mycompany.mario.plytka.plytka;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author kryst
 */
public class Gracz extends postac {

    Random random = new Random();
    private int PrzebytePixele = 0;
    private StatusGracza status;
    private boolean animacja = false;
    private int ramka = 0, opoznienie_ramki = 0;
    private boolean niesmiertelny = false;
    private int czasNiesmiertelnosci = 0;
    private int opoznienie_efektow = 0;

    public Gracz(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
        status = StatusGracza.maly;
    }

    @Override
    public void render(Graphics g) {
        if (status == StatusGracza.ogien) {
            if (obrocenie_twarzy == 0) {
                g.drawImage(Game.spriteGraczOgien[ramka + 4].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                if (skok) {
                    g.drawImage(Game.spriteGraczOgien[5].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }
                if (!skok && spadanie) {
                    g.drawImage(Game.spriteGraczOgien[6].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }

            } else if (obrocenie_twarzy == 1) {
                g.drawImage(Game.spriteGraczOgien[ramka].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                if (skok) {
                    g.drawImage(Game.spriteGraczOgien[1].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }
                if (!skok && spadanie) {
                    g.drawImage(Game.spriteGraczOgien[2].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }
            }
        } else if (status == StatusGracza.maly) {
            if (obrocenie_twarzy == 0) {
                g.drawImage(Game.spriteGracz[ramka + 4].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                if (skok) {
                    g.drawImage(Game.spriteGracz[5].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }
                if (!skok && spadanie) {
                    g.drawImage(Game.spriteGracz[6].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }

            } else if (obrocenie_twarzy == 1) {
                g.drawImage(Game.spriteGracz[ramka].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                if (skok) {
                    g.drawImage(Game.spriteGracz[1].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }
                if (!skok && spadanie) {
                    g.drawImage(Game.spriteGracz[2].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }
            }
        } else if (status == StatusGracza.Duzy) {
            if (obrocenie_twarzy == 0) {
                g.drawImage(Game.spriteGraczDuzy[ramka + 4].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                if (skok) {
                    g.drawImage(Game.spriteGraczDuzy[5].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }
                if (!skok && spadanie) {
                    g.drawImage(Game.spriteGraczDuzy[6].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }

            } else if (obrocenie_twarzy == 1) {
                g.drawImage(Game.spriteGraczDuzy[ramka].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                if (skok) {
                    g.drawImage(Game.spriteGraczDuzy[1].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }
                if (!skok && spadanie) {
                    g.drawImage(Game.spriteGraczDuzy[2].getBufferedImage(), x, y, szerokosc, wysokosc, null);
                }
            }
        }

    }

    @Override
    public void tik() {
        System.out.println("skok " + skok + "spadanie " + spadanie + "Grawitacja: " + Grawitacja);
        spadanie = true;
        x += PredokoscX;
        y += PredkoscY;
        if (PredokoscX == 0) {
            animacja = false;
        } else {
            animacja = true;
        }
        if (niesmiertelny) {
            if (obrocenie_twarzy == 0) {
                przewodnik.Dodajplytke(new Slad(x, y, szerokosc, wysokosc, false, id.slad, przewodnik, Game.spriteGracz[ramka + 4].getBufferedImage()));
            } else {
                przewodnik.Dodajplytke(new Slad(x, y, szerokosc, wysokosc, false, id.slad, przewodnik, Game.spriteGracz[ramka].getBufferedImage()));

            }
            opoznienie_efektow++;
            czasNiesmiertelnosci++;
            if (opoznienie_efektow >= 3) {
                przewodnik.Dodajplytke(new Efekty(y + random.nextInt(wysokosc), x + random.nextInt(szerokosc), 15, 15, false, id.efekty, przewodnik));
                opoznienie_efektow = 0;
            }
            if (czasNiesmiertelnosci >= 610) {
                niesmiertelny = false;
                czasNiesmiertelnosci = 0;

            }
            if (PredokoscX == 6) {
                PredokoscX = 8;
            } else if (PredokoscX == -6) {
                PredokoscX = -8;
            }
        } else {
            if (PredokoscX == 8) {
                PredokoscX = 6;
            } else if (PredokoscX == -8) {
                PredokoscX = -6;
            }
        }
        for (int i = 0; i < przewodnik.Plytka.size(); i++) {
            plytka t = przewodnik.Plytka.get(i);
            if (t.solid && !spadanie_rura) {
                if (DodajGranice().intersects(t.DodajGranice()) && t.getId() == id.flaga) {
                    setStatus(StatusGracza.ogien);
                    Game.ZmienPoziom();
                }
                if (DodajGraniceLewo().intersects(t.DodajGranice()) && t.getId() != ID.Moneta) {
                    PredokoscX = 0;
                    x = t.x + t.szerokosc;
                }
                if (DodajGranicePrawo().intersects(t.DodajGranice()) && t.getId() != ID.Moneta) {
                    PredokoscX = 0;
                    if (status == StatusGracza.maly) {
                        x = t.x - t.szerokosc;
                    } else {
                        x = (t.x - szerokosc);
                    }
                }
                if (DodajGraniceGora().intersects(t.DodajGranice()) && t.getId() != ID.Moneta) {
                    PredkoscY = 0;
                    if (skok && !spadanie_rura) {
                        skok = false;
                        Grawitacja = 0.0;
                        spadanie = true;

                    }
                    if (t.getId() == id.BlokWzmocnienia) {
                        t.aktywowany = true;
                    }
                }
                if (DodajGraniceDol().intersects(t.DodajGranice()) && t.getId() != ID.Moneta) {
                    PredkoscY = 0;
                    spadanie = false;
                    Grawitacja = 10.0;
                }
                if (DodajGranice().intersects(t.DodajGranice()) && t.getId() == ID.Moneta) {
                    Game.Monety++;
                    t.nie_zyjesz_plytka();
                }
            }

        }
        for (int i = 0; i < przewodnik.Postac.size(); i++) {
            postac p = przewodnik.Postac.get(i);
            if (p.getID() == id.grzybson) {
                switch (p.getTyp()) {
                    case 0:
                        if (DodajGranice().intersects(p.DodajGranice()) && status == StatusGracza.maly) {
                            status = StatusGracza.Duzy;
                            setStatus(StatusGracza.Duzy);
                            p.nie_zyjesz_mob();
                        }
                        break;
                    case 1:
                        if (DodajGranice().intersects(p.DodajGranice())) {
                            Game.zycia++;
                            p.nie_zyjesz_mob();
                        }
                }

            } else if (p.getID() == id.goomba || p.getID() == id.bosswieza) {
                if (niesmiertelny && DodajGranice().intersects(p.DodajGranice())) {
                    p.nie_zyjesz_mob();
                } else {
                    if (DodajGraniceDol().intersects(p.DodajGranice())) {
                        if (p.getID() != ID.bosswieza) {
                            p.nie_zyjesz_mob();
                        } else if (p.MoznaAtakowac == true && p.getID() == ID.bosswieza) {
                            System.out.println("Trafiony");
                            p.CzasFazy = 0;
                            p.hp--;
                            p.statusbossa = StatusBossa.leczenie;
                            p.MoznaAtakowac = false;
                        }
                        skok = true;
                    } else if (DodajGranice().intersects(p.DodajGranice())) {
                        obrazenia();
                        if (status == StatusGracza.Duzy || status == StatusGracza.ogien) {
                            p.nie_zyjesz_mob();
                        }
                    }
                }
            } else if (p.getID() == id.roslina) {
                if (DodajGranice().intersects(p.DodajGranice())) {
                    obrazenia();
                }
            }
            if (DodajGranice().intersects(p.DodajGranice()) && p.getID() == ID.zolwik) {
                if (p.statuszolwik == StatusZolwik.CHODZENIE) {
                    if (DodajGraniceDol().intersects(p.DodajGranice())) {
                        p.statuszolwik = StatusZolwik.SKORUPA;
                        skok = true;
                        spadanie = false;
                    } else if (DodajGranice().intersects(p.DodajGranice())) {
                        obrazenia();
                    }
                } else if (p.statuszolwik == StatusZolwik.SKORUPA) {
                    if (DodajGraniceDol().intersects(p.DodajGranice())) {
                        p.statuszolwik = StatusZolwik.KRECENIE;

                        Random random = new Random();
                        int kierunek = random.nextInt(2);
                        if (statuszolwik == StatusZolwik.KRECENIE) {
                            skok = true;
                            spadanie = false;
                        }
                    }
                } else if (p.statuszolwik == StatusZolwik.KRECENIE) {
                    if (DodajGraniceDol().intersects(p.DodajGranice())) {
                        p.statuszolwik = StatusZolwik.SKORUPA;
                        skok = true;
                        spadanie = false;
                    }
                }
            }
            if (DodajGranice().intersects(p.DodajGranice()) && p.getID() == ID.gwiazda) {
                niesmiertelny = true;
                p.nie_zyjesz_mob();
            }
            if (DodajGranice().intersects(p.DodajGranice()) && p.getID() == ID.kwiatek) {
                if (niesmiertelny != true) {
                    status = StatusGracza.ogien;
                    setStatus(StatusGracza.ogien);
                    p.nie_zyjesz_mob();
                }
            }
        }
        if (skok && !spadanie_rura) {
            Grawitacja -= 0.3;
            PredkoscY = ((int) -Grawitacja);
            spadanie = false;
            if (Grawitacja <= 1.5) {
                skok = false;
                spadanie = true;
            }
        }
        if (spadanie && !spadanie_rura) {
            if (Grawitacja <= 10.0) {
                Grawitacja += 0.3;
            }
            PredkoscY = ((int) Grawitacja);
        }
        if (animacja && !spadanie_rura) {
            opoznienie_ramki++;
            if (opoznienie_ramki >= 3) {
                ramka++;
                if (ramka >= 4) {
                    ramka = 0;
                }
                opoznienie_ramki = 0;
            }
        }
        if (spadanie_rura) {
            spadanie = false;
            skok = false;
            for (int i = 0; i < przewodnik.Plytka.size(); i++) {
                plytka t = przewodnik.Plytka.get(i);
                if (t.getId() == ID.rura) {
                    if (DodajGranice().intersects(t.DodajGranice())) {
                        switch (t.obrocenie) {
                            case 0:
                                PredkoscY = -5;
                                PredokoscX = 0;
                                PrzebytePixele += -PredkoscY;
                                break;
                            case 2:
                                PredkoscY = 5;
                                PredokoscX = 0;
                                PrzebytePixele += PredkoscY;
                                break;
                        }
                        if (PrzebytePixele >= t.wysokosc) {
                            //x = 15;
                            spadanie_rura = false;
                            //spadanie = true;
                            PrzebytePixele = 0;
                            if (t.obrocenie == 2) {
                                y = t.y + t.wysokosc;
                            } else {
                                y = t.y - wysokosc;
                                x = t.x;
                            }
                        }

                    }

                }

            }
        }
    }

    public void obrazenia() {
        if (status == StatusGracza.maly) {
            nie_zyjesz();
            return;
        }
        if (status == StatusGracza.Duzy) {
            status = StatusGracza.maly;
            return;
        }
        if (status == StatusGracza.ogien) {
            status = StatusGracza.Duzy;
            return;
        }
    }

}
