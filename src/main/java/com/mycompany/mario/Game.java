/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario;

import com.mycompany.mario.grafika.ArkuszSprite;
import com.mycompany.mario.grafika.gui.EkranPowitania;
import com.mycompany.mario.grafika.sprite;
import com.mycompany.mario.postac.BossWieza;
import com.mycompany.mario.postac.postac;
import com.mycompany.mario.wejscie.WejscieKlawiatury;
import com.mycompany.mario.wejscie.WejscieMyszki;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kryst
 */
public class Game extends Canvas implements Runnable {

    public static final int szerokosc = 270;
    public static final int wysokosc = szerokosc / 14 * 10;
    public static final int skala = 4;
    public static int poziom = 0;
    public static final String tytul = "Super Mario bros 2 baby";
    public static ArkuszSprite arkusz;
    public static WejscieMyszki mysz;
    public static sprite spriteTrawa, spriteDirt, spriteGrzybson, spriteMoneta, spriteGrzybsonZycia;
    public static sprite spriteGracz[] = new sprite[8], spriteGoomba[] = new sprite[10];
    public static sprite spriteWzmocnienie, spriteUzyteWzmocnienie, spriteRura, spriteRoslina;
    public static sprite spriteGwiazda;
    public static sprite spriteBossWirowanie[] = new sprite[3], spriteBossBieganie[] = new sprite[6];
    public static sprite spriteFlaga[] = new sprite[3];
    public static sprite spriteEfekty[] = new sprite[6];
    public static sprite spriteGraczOgien[] = new sprite[8];
    public static sprite spriteGraczDuzy[] = new sprite[8];
    public static sprite spriteKula, spriteKwiatek, spriteRoslinaRuszaSie;
    private static BufferedImage ObrazTla;
    public static int zycia = 2;
    public static boolean KoniecGry = false;
    public static boolean pokazEkranSmierci = true;
    public static boolean PodczasGry = false;
    public static boolean BossZabity = false;
    public static int czasEkranuSmierci = 0;
    private Thread watek;
    private boolean wlacz = false;
    public static int Monety = 0;
    public static Przewodnik przewodnik;
    public static Kamera kamera;
    //private BufferedImage poziom1, poziom2;
    private static BufferedImage[] poziomy = new BufferedImage[2];
    public static Dzwieki DzwiekSkok;
    public static Dzwieki DzwiekGoombaKrok;
    public static Dzwieki DzwiekNastPoziom;
    public static Dzwieki DzwiekStrataZycia;
    public static Dzwieki DzwiekMuzyka;
    public static EkranPowitania ekranP;
    public static int RuraGraczX, RuraGraczY;

    public static int getSzerokoscRamki() {
        return szerokosc * skala;
    }

    public static int getWysokoscRamki() {
        return wysokosc * skala;
    }

    public static void ZmienPoziom() {
        poziom++;
        przewodnik.wyczyscPoziom();
        przewodnik.UtworzPoziom(poziomy[poziom]);
    }

    public static Rectangle getWidocznyObszar() {
        for (int i = 0; i < przewodnik.Postac.size(); i++) {
            postac p = przewodnik.Postac.get(i);
            if (p.id == ID.gracz) {
                if (!p.spadanie_rura) {
                    RuraGraczX = p.x;
                    RuraGraczY = p.y;
                    return new Rectangle(RuraGraczX - getSzerokoscRamki() / 2 - 5, RuraGraczY - getWysokoscRamki() / 2 - 5, getSzerokoscRamki() + 10, getWysokoscRamki() + 10);

                } else {
                    return new Rectangle(RuraGraczX - getSzerokoscRamki() / 2 - 5, RuraGraczY - getWysokoscRamki() / 2 - 5, getSzerokoscRamki() + 10, getWysokoscRamki() + 10);
                }
            }
        }
        return null;
    }

    public Game() {
        Dimension rozmiar = new Dimension(szerokosc * skala, wysokosc * skala);
        setPreferredSize(rozmiar);
        setMaximumSize(rozmiar);
        setMinimumSize(rozmiar);
    }

    private void inicializuj() {
        mysz = new WejscieMyszki();
        addKeyListener(new WejscieKlawiatury());
        addMouseListener(mysz);
        addMouseMotionListener(mysz);
        ekranP = new EkranPowitania();
        przewodnik = new Przewodnik();
        arkusz = new ArkuszSprite("Mario.png");
        kamera = new Kamera();
        addKeyListener(new WejscieKlawiatury());
        for (int i = 0; i < Game.spriteGracz.length; i++) {
            spriteGracz[i] = new sprite(arkusz, i + 1, 1);
        }
        for (int i = 0; i < Game.spriteGraczOgien.length; i++) {
            spriteGraczOgien[i] = new sprite(arkusz, i + 1, 4);
        }
        for (int i = 0; i < Game.spriteGraczDuzy.length; i++) {
            spriteGraczDuzy[i] = new sprite(arkusz, i + 9, 1);
        }
        for (int i = 0; i < Game.spriteFlaga.length; i++) {
            spriteFlaga[i] = new sprite(arkusz, i + 10, 3);
        }
        for (int i = 0; i < Game.spriteGoomba.length; i++) {
            spriteGoomba[i] = new sprite(arkusz, i + 1, 2);
        }
        for (int i = 0; i < Game.spriteBossWirowanie.length; i++) {
            spriteBossWirowanie[i] = new sprite(arkusz, i + 1, 3);
        }
        for (int i = 0; i < Game.spriteBossBieganie.length; i++) {
            spriteBossBieganie[i] = new sprite(arkusz, i + 4, 3);
        }
//        for (int i = 0; i < Game.spriteEfekty.length; i++) {
//            spriteEfekty[i] = new sprite(arkusz, i + 1, 4);
//        }
        spriteGrzybsonZycia = new sprite(arkusz, 14, 3);
        spriteRura = new sprite(arkusz, 14, 2);
        spriteGrzybson = new sprite(arkusz, 16, 2);
        spriteTrawa = new sprite(arkusz, 10, 4);
        spriteWzmocnienie = new sprite(arkusz, 11, 2);
        spriteUzyteWzmocnienie = new sprite(arkusz, 12, 2);
        spriteMoneta = new sprite(arkusz, 13, 2);
        spriteDirt = new sprite(arkusz, 12, 3);
        spriteRoslina = new sprite(arkusz, 15, 3);
        spriteRoslinaRuszaSie = new sprite(arkusz, 16, 3);
        spriteGwiazda = new sprite(arkusz, 15, 2);
        spriteKwiatek = new sprite(arkusz, 13, 3);
        try {
            poziomy[0] = ImageIO.read(new File("poziom.png"));
            poziomy[1] = ImageIO.read(new File("poziom1.png"));
            ObrazTla = ImageIO.read(new File("obraztla.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        // przewodnik.UtworzPoziom(obraz);  
        DzwiekStrataZycia = new Dzwieki("DzwiekSmierc.wav");
        DzwiekStrataZycia.graj();
    }

    public synchronized void startuj() {
        if (wlacz) {
            return;
        }
        wlacz = true;
        watek = new Thread(this, "start");
        watek.start();
    }

    public synchronized void zatrzymaj() {
        if (!wlacz) {
            return;
        }
        wlacz = false;
        try {
            watek.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void renderuj() throws IOException {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        if (!pokazEkranSmierci) {
            g.drawImage(ObrazTla, 0, 0, getSzerokoscRamki(), getWysokoscRamki(), null);
        }
        if (pokazEkranSmierci) {
            g.setColor(Color.black);
            g.fillRect(0, 0, getSzerokoscRamki(), getWysokoscRamki());
            if (!KoniecGry) {
                g.drawImage(spriteGracz[3].getBufferedImage(), 500, 300, 100, 100, null);
                g.setColor(Color.white);
                g.setFont(new Font("Courier", Font.BOLD, 20));
                g.drawString("Życia: x" + zycia, 610, 400);
            } else if (BossZabity && KoniecGry) {
                g.setColor(Color.red);
                g.setFont(new Font("Courier", Font.BOLD, 50));
                g.drawString("Gratulacje!", 350, 200);
                g.drawString("Udało ci się zebrać "+Monety+" Monet", 200, 300);
                if(Monety<20)
                     g.drawString("Troche słabo", 350, 400);
                else g.drawString("Kozak", 350, 400);
                poziom = 0;
                zycia = 3;
            } else if (KoniecGry) {
                g.setColor(Color.red);
                g.setFont(new Font("Courier", Font.BOLD, 50));
                g.drawString("Umarłeś se", 400, 400);
                poziom = 0;
                zycia = 3;
            }
        }
        if (PodczasGry) {
            g.translate(kamera.getX(), kamera.getY() + 50);
        }
        if (!pokazEkranSmierci && PodczasGry) {
            przewodnik.renderuj(g);
        } else if (!PodczasGry) {
            ekranP.render(g);
        }
        g.dispose();
        bs.show();
    }

    public void tik() {
        if (PodczasGry) {
            przewodnik.tik();
        }
        for (postac p : przewodnik.Postac) {
            if (p.getID() == ID.gracz) {
                if (!p.spadanie_rura) {
                    kamera.tik(p);
                }
            }
        }
        if (pokazEkranSmierci) {
            czasEkranuSmierci++;
        }
        if (czasEkranuSmierci >= 180) {
            if (!KoniecGry) {
                pokazEkranSmierci = false;
                czasEkranuSmierci = 0;
                dodajPoziom();
            } else if (KoniecGry) {
                pokazEkranSmierci = false;
                czasEkranuSmierci = 0;
                PodczasGry = false;
                KoniecGry = false;
                dodajPoziom();
            }
        }
    }

    @Override
    public void run() {
        inicializuj();
        requestFocus();
        long OstatniRaz = System.nanoTime();
        long Stoper = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        int ramki = 0;
        int tiki = 0;
        while (wlacz) {
            long teraz = System.nanoTime();
            delta += (teraz - OstatniRaz) / ns;
            OstatniRaz = teraz;
            while (delta >= 1) {
                tik();
                tiki++;
                delta--;
            }
            try {
                renderuj();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            ramki++;
            if (System.currentTimeMillis() - Stoper > 1000) {
                Stoper += 1000;
                System.out.println(ramki + " FPS " + tiki + " TPS ");
                ramki = 0;
                tiki = 0;
            }
        }
        zatrzymaj();
    }

    void dodajPoziom() {
        przewodnik.wyczyscPoziom();
        przewodnik.UtworzPoziom(poziomy[poziom]);
    }

}
