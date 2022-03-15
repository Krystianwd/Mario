/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario;

import static com.mycompany.mario.Game.tytul;
import javax.swing.JFrame;

/**
 *
 * @author kryst
 */
public class Main {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args)
    {
        Game game = new Game();
        JFrame frame = new JFrame(tytul);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.startuj();
    }
    
}
