/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario.grafika;

import java.awt.image.BufferedImage;

/**
 *
 * @author kryst
 */
public class sprite {
    public ArkuszSprite arkusz;
    public BufferedImage obraz;
    public sprite(ArkuszSprite arkusz, int x, int y)
    {
        obraz = arkusz.getSprite(x, y);
    }
    public BufferedImage getBufferedImage()
    {
        return obraz;
    }
}
