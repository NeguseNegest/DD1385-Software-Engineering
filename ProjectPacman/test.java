package ProjectPacman;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import ProjectPacman.PacmanController;

public class test {
    void pll() {
        playSound("ProjectPacman/assets/NewBackMusic.wav");
        while (true) {
            if (!clip.isRunning()) {
                playSound("ProjectPacman/assets/NewBackMusic.wav");
            }
            // Lägg eventuellt till en kort fördröjning här för att undvika att loopen körs för intensivt
            try {
                Thread.sleep(10); // Vänta 10 millisekunder
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }

    private Clip clip;

    private void playSound(String soundFileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test test = new test();
        test.pll();
    }
}

