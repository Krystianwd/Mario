/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.wejscie;

import com.mycompany.mario.Game;
import com.mycompany.mario.ID;
import com.mycompany.mario.Status.StatusGracza;
import com.mycompany.mario.plytka.plytka;
import com.mycompany.mario.postac.Kula;
import com.mycompany.mario.postac.postac;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author kryst
 */
public class WejscieKlawiatury implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int klucz = e.getKeyCode();
        for (int i = 0; i < Game.przewodnik.Postac.size(); i++) {
            postac m = Game.przewodnik.Postac.get(i);
            if (m.getID() == ID.gracz) {
                if (m.spadanie_rura) {
                    return;
                }
                switch (klucz) {
                    case KeyEvent.VK_W:
                        for (int j = 0; j < Game.przewodnik.Plytka.size(); j++) {
                            plytka t = Game.przewodnik.Plytka.get(j);
                            if (t.getId() == ID.rura) {
                                if (m.DodajGraniceGora().intersects(t.DodajGranice())) {
                                    if (!m.spadanie_rura) {
                                        m.spadanie_rura = true;
                                    }
                                }
                            }
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if (m.skok || m.spadanie) {
                            break;
                        } else {
                            m.spadanie = false;
                            m.skok = true;
                            if(m.status == StatusGracza.Duzy)
                                m.Grawitacja = 11.0;
                            m.Grawitacja = 10.0;
                            break;
                        }
                    case KeyEvent.VK_A:
                        if (m.status == StatusGracza.Duzy) {
                            m.setPredokoscX(-9);
                        } else {
                            m.setPredokoscX(-6);
                        }
                        m.obrocenie_twarzy = 0;
                        break;
                    case KeyEvent.VK_D:
                        if (m.status == StatusGracza.Duzy) {
                            m.setPredokoscX(9);
                        } else {
                            m.setPredokoscX(6);
                        }
                        m.obrocenie_twarzy = 1;
                        break;
                    case KeyEvent.VK_S:
                        for (int j = 0; j < Game.przewodnik.Plytka.size(); j++) {
                            plytka t = Game.przewodnik.Plytka.get(j);
                            if (t.getId() == ID.rura) {
                                if (m.DodajGraniceDol().intersects(t.DodajGranice())) {
                                    if (!m.spadanie_rura) {
                                        m.spadanie_rura = true;
                                    }
                                }
                            }
                        }
                        break;
                    case KeyEvent.VK_CONTROL:
                        if (m.status == StatusGracza.ogien) {
                            switch (m.obrocenie_twarzy) {
                                case 1:
                                    Game.przewodnik.DodajPostac(new Kula(m.x + 64, m.y + 12, 13, 13, true, ID.kula, Game.przewodnik, m.obrocenie_twarzy));
                                    break;
                                case 0:
                                    Game.przewodnik.DodajPostac(new Kula(m.x, m.y + 12, 13, 13, true, ID.kula, Game.przewodnik, m.obrocenie_twarzy));
                                    break;
                            }
                        }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int klucz = e.getKeyCode();
        for (postac m : Game.przewodnik.Postac) {
            switch (klucz) {
                case KeyEvent.VK_SPACE:
                    m.setPredkoscY(0);
                    break;
                case KeyEvent.VK_A:
                    m.setPredokoscX(0);
                    break;
                case KeyEvent.VK_D:
                    m.setPredokoscX(0);
                    break;
                case KeyEvent.VK_CONTROL:
            }
        }
    }
}
