/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.plytka;

import com.mycompany.mario.Game;
import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import com.mycompany.mario.grafika.sprite;
import com.mycompany.mario.postac.Gwiazda;
import com.mycompany.mario.postac.Kwiatek;
import com.mycompany.mario.postac.wzmacniacz.Grzybson;
import java.awt.Graphics;

/**
 *
 * @author kryst
 */
public class BlokWzmocnienia extends plytka{

    private sprite wzmocnienie;
    private boolean pojawienie;
    private int spriteY = getY();
    public int typ;
    public BlokWzmocnienia(int x, int y, int szerokosc, int wysokosc, boolean solid, ID id, Przewodnik przewodnik,sprite wzmocnienie,int typ) {
        super(x, y, szerokosc, wysokosc, solid, id, przewodnik);
        this.wzmocnienie = wzmocnienie;
        this.typ= typ;
    }

    @Override
    public void render(Graphics g) {
        if(!pojawienie)g.drawImage(wzmocnienie.getBufferedImage(), x, spriteY, szerokosc, wysokosc, null);
        if(!aktywowany)g.drawImage(Game.spriteWzmocnienie.getBufferedImage(), x, y, szerokosc, wysokosc, null);
        else g.drawImage(Game.spriteUzyteWzmocnienie.getBufferedImage(), x, y, szerokosc, wysokosc, null);

    }

    @Override
    public void tik() {
        if(aktywowany&&!pojawienie)
        {
            spriteY--;
            if(spriteY<=y-wysokosc)
            {
                if(wzmocnienie == Game.spriteGrzybson || wzmocnienie == Game.spriteGrzybsonZycia)przewodnik.DodajPostac(new Grzybson(x, spriteY, szerokosc, wysokosc, true, ID.grzybson, przewodnik,typ));
                else if(wzmocnienie == Game.spriteKwiatek)przewodnik.DodajPostac(new Kwiatek(x, spriteY, szerokosc, wysokosc, true, ID.kwiatek, przewodnik));
                else if (wzmocnienie == Game.spriteGwiazda)przewodnik.DodajPostac(new Gwiazda(x, spriteY, szerokosc, wysokosc, true, id.gwiazda, przewodnik));
                pojawienie = true;
            }
        }
    }
    
}
