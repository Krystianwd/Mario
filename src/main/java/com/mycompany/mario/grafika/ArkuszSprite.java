/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.grafika;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kryst
 */
public class ArkuszSprite {
    private BufferedImage arkusz;
    
    public ArkuszSprite(String sciezka)
    {
        try {
                    arkusz = ImageIO.read(new File(sciezka));
        } catch (IOException ex) {
            Logger.getLogger(ArkuszSprite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public BufferedImage getSprite(int x,int y)
    {
        return arkusz.getSubimage(x*32-32, y*32-32, 32, 32);
    }
}
