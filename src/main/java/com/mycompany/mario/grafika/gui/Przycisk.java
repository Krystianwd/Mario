/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.grafika.gui;

import com.mycompany.mario.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author kryst
 */
public class Przycisk {
     public int x,y;
     public int szerokosc, wyskokosc;
     public String label;

    public Przycisk(int x, int y, int szerokosc, int wyskokosc, String label) {
        this.x = x;
        this.y = y;
        this.szerokosc = szerokosc;
        this.wyskokosc = wyskokosc;
        this.label = label;
    }
    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(new Font("Century gothic",Font.ITALIC,30));
        
        FontMetrics fm = g.getFontMetrics();
        int StringX = (getSzerokosc() - fm.stringWidth(getLabel()))/2;
        int StringY = (fm.getAscent() + (getWyskokosc() - (fm.getAscent()+fm.getDescent()))/2);
        g.drawString(getLabel(), getX()+StringX, getY()+StringY);
        g.drawRect(getX(), getY(), getSzerokosc(), getWyskokosc());
    }
    public void triggerEvent()
    {
        if(getLabel().toLowerCase().contains("rozpocznij")){Game.PodczasGry = true;System.out.println("WCISNIETY");}
        else if(getLabel().toLowerCase().contains("wyjdź"))System.exit(0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSzerokosc() {
        return szerokosc;
    }

    public void setSzerokosc(int szerokosc) {
        this.szerokosc = szerokosc;
    }

    public int getWyskokosc() {
        return wyskokosc;
    }

    public void setWyskokosc(int wyskokosc) {
        this.wyskokosc = wyskokosc;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
     
}
