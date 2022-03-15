/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mario;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author kryst
 */
public class Dzwieki {

    private Clip clip;

    public Dzwieki(String sciezka) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(sciezka));
            AudioFormat FormatBazowy = ais.getFormat();
            AudioFormat FormatDekodowania = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    FormatBazowy.getSampleRate(), 16, FormatBazowy.getChannels(),
                    FormatBazowy.getChannels() * 2, FormatBazowy.getSampleRate(), false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(FormatDekodowania, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        } catch (Exception e) {
        }
    }

    public void graj() {
        if (clip == null) {
            return;
        }
        zamknij();
        clip.setFramePosition(0);
        clip.start();
    }

    public void zamknij() {
        zatrzymaj();
        clip.close();
    }

    public void zatrzymaj() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }
}
