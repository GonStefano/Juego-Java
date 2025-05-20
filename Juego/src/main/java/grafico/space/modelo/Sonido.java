package grafico.space.modelo;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class Sonido {

    private final double vol =0.2;

    public void  playSound(String sound){
        try {
            File soundFile = new File(sound);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            AudioFormat format = audioStream.getFormat();

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
