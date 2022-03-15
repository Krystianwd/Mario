/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.wejscie;

import com.mycompany.mario.Game;
import com.mycompany.mario.grafika.gui.Przycisk;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author kryst
 */
public class WejscieMyszki implements MouseMotionListener,MouseListener{

    public int x,y;
    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int i=0;i<Game.ekranP.przyciski.length;i++)
        {
            Przycisk przycisk = Game.ekranP.przyciski[i];
            
            if(x>=przycisk.getX()&&y>=przycisk.getY()&&x<=przycisk.getX()+przycisk.getSzerokosc()
                    &&y<=przycisk.getY()+przycisk.getWyskokosc()){przycisk.triggerEvent();}
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
