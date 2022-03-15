/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.plytka;

import com.mycompany.mario.ID;
import com.mycompany.mario.Przewodnik;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author kryst
 */
public abstract class plytka {
    public int x,y,szerokosc,wysokosc;
    public Przewodnik przewodnik;
    public boolean solid;
    public ID id;
    int PredokoscX,PredkoscY;
    public boolean aktywowany = false;
    public int obrocenie;

    public plytka(int x, int y, int szerokosc, int wysokosc, boolean solid,ID id,Przewodnik przewodnik) {
        this.x = x;
        this.y = y;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.solid = solid;
        this.przewodnik = przewodnik;
        this.id = id;
    }
    public void nie_zyjesz_plytka()
    {
        przewodnik.Usunplytke(this);
    }
    public abstract void render(Graphics g);
    public abstract void tik();
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public int getPredokoscX() {
        return PredokoscX;
    }

    public void setPredokoscX(int PredokoscX) {
        this.PredokoscX = PredokoscX;
    }

    public int getPredkoscY() {
        return PredkoscY;
    }

    public void setPredkoscY(int PredkoscY) {
        this.PredkoscY = PredkoscY;
    }

    public ID getId() {
        return id;
    }
    
    public Rectangle DodajGranice()
    {
        return new Rectangle(getX(),getY(),szerokosc,wysokosc);
    }
    
}
