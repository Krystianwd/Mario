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
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author kryst
 */
public abstract class postac {

    public int x,y,szerokosc,wysokosc;
    public ID id;
    public boolean solid,skok = false,spadanie = false,spadanie_rura = false;
    public Przewodnik przewodnik;
    int PredokoscX,PredkoscY;
    public int obrocenie_twarzy = 0;//0 -> lewo, 1 -> prawo
    public double Grawitacja = 0.0;
    public boolean MoznaAtakowac = false;
    public int hp;
    public StatusGracza status;
    public StatusBossa statusbossa;
    public StatusZolwik statuszolwik;
    public int CzasFazy;
    public int typ;
    

    public postac(int x, int y, int szerokosc, int wysokosc, boolean solid,ID id,Przewodnik przewodnik) {
        this.x = x;
        this.y = y;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.solid = solid;
        this.id = id;
        this.przewodnik = przewodnik;
    }
    public abstract void render(Graphics g);
    public abstract void tik();

    public ID getID()
    {
      return id;   
    }
    public void setStatus(StatusGracza status)
    {
        this.status = status;
    }
    public StatusGracza getStatus()
    {
        return this.status;
    }
    public void nie_zyjesz_mob()
    {
        przewodnik.UsunPostac(this);

    }
    public void nie_zyjesz()
    {
        przewodnik.UsunPostac(this);
        Game.zycia--;
        Game.Monety = 0;
        Game.pokazEkranSmierci = true;
        if(Game.zycia <=0)
        {
            Game.KoniecGry = true;
        }
        
    }
    public int getTyp()
    {
        return typ;
    }
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
    public Rectangle DodajGranice()
    {
        return new Rectangle(getX(),getY(),szerokosc,wysokosc);
    }
    public Rectangle DodajGraniceGora()
    {
        return new Rectangle(getX()+10,getY(),szerokosc-20,5);
    }
    public Rectangle DodajGraniceDol()
    {
        return new Rectangle(getX()+10,getY()+wysokosc-5,szerokosc-20,5);
    }
    public Rectangle DodajGraniceLewo()
    {
        return new Rectangle(getX(),getY()+10,5,wysokosc-20);
    }
    public Rectangle DodajGranicePrawo()
    {
        return new Rectangle(getX()+szerokosc,getY()+10,5,wysokosc-20);
    }
}
