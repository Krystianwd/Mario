/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.grafika.gui;

import com.mycompany.mario.Game;
import static com.mycompany.mario.Game.getSzerokoscRamki;
import static com.mycompany.mario.Game.getWysokoscRamki;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author kryst
 */
public class EkranPowitania {

    public Przycisk[] przyciski;
    private static BufferedImage EkranP;

    public EkranPowitania() {
        przyciski = new Przycisk[2];
        przyciski[0] = new Przycisk(250, 250, 200, 100, "Rozpocznij");
        przyciski[1] = new Przycisk(650, 250, 200, 100, "Wyjdź");
    }

    public void render(Graphics g) throws IOException {
        EkranP = ImageIO.read(new File("EkranP.jpg"));
        g.drawImage(EkranP, 0, 0, Game.getSzerokoscRamki(), Game.getWysokoscRamki(), null);
        g.setColor(Color.white);
        g.setFont(new Font("Courier", Font.BOLD, 50));
        g.drawString("Super Mario Bros 2 babyyy", 250, 100);
        for (int i = 0; i < przyciski.length; i++) {
            przyciski[i].render(g);
        }
    }

}
